package expression.parser;

import expression.*;
import expression.exceptions.*;

public class ExpressionParser implements Parser {
    private String expression;
    private int index = 0;
    private int value;
    private int brace_balance = 0;

    private String variable;
    private Token curToken;
    private Token previousToken;

    private enum Token {
        CONST, VAR, NEG, ADD, SUB, DIV, MUL,
        OPEN_BRACE, CLOSE_BRACE, AND, XOR, OR, END
    }


    private boolean isOperandExpectedAfter(Token token) {
        return token == Token.CONST || token == Token.VAR || token == Token.CLOSE_BRACE;
    }

    private boolean isArgumentExpectedAfter(Token token) {
        return (token == Token.END && index != expression.length()) || token == Token.NEG ||
                token == Token.OPEN_BRACE || token == Token.ADD || token == Token.SUB ||
                token == Token.DIV || token == Token.MUL;
    }

    private void setOperandToken(Token token) throws NoArgumentException {
        if (isOperandExpectedAfter(curToken)) {
            curToken = token;
        } else {
            throw new NoArgumentException();
        }
    }

    private void skipWhitespaces() {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
    }

    private void parseValue() {
        int start = index;
        while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
            index++;
        }
        value = Integer.parseUnsignedInt(expression.substring(start, index));
        index--;
    }

    private void parseVariable() throws ParsingException {
        int start = index;
        while (index < expression.length() && Character.isLetter(expression.charAt(index))) {
            index++;
        }
        variable = expression.substring(start, index);
        switch (variable) {
            case "x":
            case "y":
            case "z":
                break;
            default:
                throw new UnknownVariableException();
        }
        index--;
    }

    private void nextToken() throws ParsingException {
        skipWhitespaces();
        if (index < expression.length()) {
            char ch = expression.charAt(index);
            switch (ch) {
                case '-':
                    if (isOperandExpectedAfter(curToken)) {
                        curToken = Token.SUB;
                    } else {
                        curToken = Token.NEG;
                    }
                    break;
                case '+':
                    setOperandToken(Token.ADD);
                    break;
                case '/':
                    setOperandToken(Token.DIV);
                    break;
                case '*':
                    setOperandToken(Token.MUL);
                    break;
                case '(':
                    brace_balance++;
                    if (isArgumentExpectedAfter(curToken)) {
                        curToken = Token.OPEN_BRACE;
                    } else {
                        throw new NoOperandException();
                    }
                    break;
                case ')':
                    brace_balance--;
                    if (brace_balance < 0) {
                        throw new NoOpeningParenthesisException();
                    }
                    curToken = Token.CLOSE_BRACE;
                    break;
                case '&':
                    curToken = Token.AND;
                    break;
                case '^':
                    curToken = Token.XOR;
                    break;
                case '|':
                    curToken = Token.OR;
                    break;
                default:
                    if (Character.isDigit(ch)) { //next token is a number
                        if (isOperandExpectedAfter(curToken)) {
                            throw new IllegalOperandException();
                        }
                        parseValue();
                        curToken = Token.CONST;
                    } else if (Character.isLetter(ch)) { //next token is a variable
                        if (isOperandExpectedAfter(curToken)) {
                            throw new IllegalOperandException();
                        }
                        parseVariable();
                        curToken = Token.VAR;
                    } else {
                        throw new UnknownSymbolException();
                    }
            }
            index++;
        } else {
            if (brace_balance > 0) {
                throw new NoClosingParenthesisException();
            }
            curToken = Token.END;
        }
    }

    // prim() - обрабатывает первичные выражения
    private CommonExpression prim() throws ParsingException {
        previousToken = curToken;
        nextToken();
        CommonExpression result;
        switch (curToken) {
            case CONST:
                if (value < 0 &&
                        !(value == Integer.MIN_VALUE && (previousToken == Token.NEG || previousToken == Token.SUB))) {
                    throw new ConstantOverflowException();
                }
                result = new Const(value);
                previousToken = curToken;
                nextToken();
                break;
            case VAR:
                result = new Variable(variable);
                previousToken = curToken;
                nextToken();
                break;
            case NEG:
                CommonExpression prim = prim();
                if (previousToken == Token.CONST && value < 0) {
                    if (value == Integer.MIN_VALUE) {
                        result = prim;
                    } else {
                        throw new ConstantOverflowException();
                    }
                } else {
                    result = new CheckedNegate(prim);
                }
                break;
            case OPEN_BRACE:
                result = or();
                previousToken = curToken;
                nextToken();
                break;
            default:
                result = new Const(0);
                if (isArgumentExpectedAfter(previousToken)) {
                    throw new NoArgumentException();
                }
        }
        return result;
    }

    // term() - умножение и деление
    private CommonExpression term() throws ParsingException {
        CommonExpression result = prim();
        while (true) {
            switch (curToken) {
                case MUL:
                    result = new CheckedMultiply(result, prim());
                    break;
                case DIV:
                    result = new CheckedDivide(result, prim());
                    break;
                default:
                    return result;
            }
        }
    }

    // expr() - сложение и вычитание
    private CommonExpression expr() throws ParsingException {
        CommonExpression result = term();
        while (true) {
            switch (curToken) {
                case ADD:
                    result = new CheckedAdd(result, term());
                    break;
                case SUB:
                    result = new CheckedSubtract(result, term());
                    break;
                default:
                    return result;
            }
        }
    }

    // and() - побитное и
    private CommonExpression and() throws ParsingException {
        CommonExpression result = expr();
        while (true) {
            switch (curToken) {
                case AND:
                    result = new And(result, expr());
                    break;
                default:
                    return result;
            }
        }
    }

    // xor() - побитное иключающее или
    private CommonExpression xor() throws ParsingException {
        CommonExpression result = and();
        while (true) {
            switch (curToken) {
                case XOR:
                    result = new Xor(result, and());
                    break;
                default:
                    return result;
            }
        }
    }

    // or() - побитное или
    private CommonExpression or() throws ParsingException {
        CommonExpression result = xor();
        while (true) {
            switch (curToken) {
                case OR:
                    result = new Or(result, xor());
                    break;
                default:
                    return result;
            }
        }
    }

    public CommonExpression parse(String s) throws ParsingException {
        expression = s;
        index = 0;
        brace_balance = 0;
        curToken = Token.END;
        return or();
    }

}