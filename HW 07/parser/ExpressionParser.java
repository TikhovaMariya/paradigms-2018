package expression.parser;

import expression.*;
import expression.exceptions.*;

public class ExpressionParser implements Parser {
    private String expression;
    private String variable;

    private int index = 0;
    private int value;
    private int brace_balance = 0;

    private Token curToken;
    private Token previousToken;

    private enum Token {
        CONST, VAR, NEG, ADD, SUB, DIV, MUL, LOG10, POW10,
        OPEN_BRACE, CLOSE_BRACE, AND, XOR, OR, END
    }


    private boolean isOperandExpectedAfter(Token token) {
        return token == Token.CONST || token == Token.VAR || token == Token.CLOSE_BRACE;
    }

    private boolean isArgumentExpectedAfter(Token token) {
        return (token == Token.END && index != expression.length()) || token == Token.NEG ||
                token == Token.OPEN_BRACE || token == Token.ADD || token == Token.SUB || token == Token.POW10 ||
                token == Token.LOG10 || token == Token.DIV || token == Token.MUL;
    }

    private void setOperandToken(Token token) throws NoArgumentException {
        if (isArgumentExpectedAfter(curToken)) {
            throw new NoArgumentException(index, expression);
        } else {
            curToken = token;
        }
    }

    private void skipWhitespaces() {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
    }

    private void parseValue() throws ConstantOverflowException {
        int start = index;
        while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
            index++;
        }
        if(curToken == Token.NEG) {
            value = Integer.parseInt("-" + expression.substring(start, index));
        } else {
            value = Integer.parseInt(expression.substring(start, index));
        }
        index--;
    }

    private void parseText() throws ParsingException {
        int start = index;
        while (index < expression.length() && Character.isLetter(expression.charAt(index))) {
            index++;
        }
        String s = expression.substring(start, index);
        switch (s) {
            case "log":
                if (expression.charAt(index) == '1' && expression.charAt(index + 1) == '0') {
                    index += 2;
                    curToken = Token.LOG10;
                } else {
                    throw new UnknownVariableException(index - 3, s);
                }
                break;
            case "pow":
                if (expression.charAt(index) == '1' && expression.charAt(index + 1) == '0') {
                    index += 2;
                    curToken = Token.POW10;
                } else {
                    throw new UnknownVariableException(index - 3, s);
                }
                break;
            case "x":
            case "y":
            case "z":
                variable = s;
                curToken = Token.VAR;
                break;
            default:
                throw new UnknownVariableException(index, s);
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
                        index++;
                        skipWhitespaces();
                        if (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                            curToken = Token.NEG;
                            parseValue();
                            curToken = Token.CONST;
                        } else {
                            index--;
                            curToken = Token.NEG;
                        }
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
                    if (isOperandExpectedAfter(curToken)) {
                        throw new NoOperandException(index, expression);
                    } else {
                        curToken = Token.OPEN_BRACE;
                    }
                    break;
                case ')':
                    brace_balance--;
                    if (brace_balance < 0) {
                        throw new NoOpeningParenthesisException(index, expression);
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
                    previousToken = curToken;
                    if (Character.isDigit(ch)) { //next token is a number
                        parseValue();
                        curToken = Token.CONST;
                    } else if (Character.isLetter(ch)) { //next token is a variable
                        parseText();
                    } else {
                        throw new UnknownSymbolException(index, expression);
                    }
                    if (isOperandExpectedAfter(previousToken)) {
                        throw new NoOperandException(index, expression);
                    }
            }
            index++;
        } else {
            if (brace_balance > 0) {
                throw new NoClosingParenthesisException(index, expression);
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
                CommonExpression logPow10 = logPow10();
                result = new CheckedNegate(logPow10);
                break;
            case OPEN_BRACE:
                result = or();
                previousToken = curToken;
                nextToken();
                break;
            case LOG10:
            case POW10:
                previousToken = curToken;
                result = new Const(0);
                break;
            default:
                result = new Const(0);
                if (isArgumentExpectedAfter(previousToken)) {
                    if (curToken == Token.CLOSE_BRACE) {
                        index--;
                    }
                    throw new NoArgumentException(index, expression);
                }
        }
        return result;
    }


    // logPow10() - логарифм и возведение в степень по основанию 10
    private CommonExpression logPow10() throws ParsingException {
        CommonExpression result = prim();
        while (true) {
            switch (curToken) {
                case POW10:
                    result = new CheckedPow(logPow10());
                    break;
                case LOG10:
                    result = new CheckedLog(logPow10());
                    break;
                default:
                    return result;
            }
        }
    }

    // term() - умножение и деление
    private CommonExpression term() throws ParsingException {
        CommonExpression result = logPow10();
        while (true) {
            switch (curToken) {
                case MUL:
                    result = new CheckedMultiply(result, logPow10());
                    break;
                case DIV:
                    result = new CheckedDivide(result, logPow10());
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