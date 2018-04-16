package expression.parser;

import expression.*;
import expression.mode.*;
import expression.exceptions.*;

public class ExpressionParser<T> implements Parser {
    private String expression;
    private String variable;

    private int index = 0;
    private T value;
    private int brace_balance = 0;

    private Token curToken;
    private Token previousToken;

    GenericMode<T> modeType;

    public ExpressionParser(final GenericMode<T> mode) {
        modeType = mode;
    }

    private enum Token {
        CONST, VAR, NEG, ADD, SUB, DIV, MUL, OPEN_BRACE, CLOSE_BRACE, END
    }


    private boolean isOperandExpectedAfter(Token token) {
        return token == Token.CONST || token == Token.VAR || token == Token.CLOSE_BRACE;
    }

    private boolean isArgumentExpectedAfter(Token token) {
        return (token == Token.END && index != expression.length()) || token == Token.NEG || token == Token.OPEN_BRACE
                || token == Token.ADD || token == Token.SUB || token == Token.DIV || token == Token.MUL;
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
        boolean e = true, dot = true;
        while (index < expression.length() && (Character.isDigit(expression.charAt(index)) ||
                (Character.toLowerCase(expression.charAt(index)) == 'e' && e) ||
                ((expression.charAt(index) == '.' || expression.charAt(index) == ',') && dot))) {
            if(Character.toLowerCase(expression.charAt(index)) == 'e') {
                e = false;
            } else if(expression.charAt(index) == '.' || expression.charAt(index) == ',') {
                dot = false;
            }
            index++;
        }
        String s = expression.substring(start, index);
        if (curToken == Token.NEG) {
           s = "-" + s;
        }
        value = modeType.parseValue(s);
        index--;
    }

    private void parseText() throws ParsingException {
        int start = index;
        while (index < expression.length() && Character.isLetter(expression.charAt(index))) {
            index++;
        }
        String s = expression.substring(start, index);
        switch (s) {
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
                result = new Const(value, modeType);
                previousToken = curToken;
                nextToken();
                break;
            case VAR:
                result = new Variable(variable, modeType);
                previousToken = curToken;
                nextToken();
                break;
            case NEG:
                CommonExpression prim = prim();
                result = new Negate(prim, modeType);
                break;
            case OPEN_BRACE:
                result = expr();
                previousToken = curToken;
                nextToken();
                break;
            default:
                result = new Const(0, modeType);
                if (isArgumentExpectedAfter(previousToken)) {
                    if (curToken == Token.CLOSE_BRACE) {
                        index--;
                    }
                    throw new NoArgumentException(index, expression);
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
                    result = new Multiply(result, prim(), modeType);
                    break;
                case DIV:
                    result = new Divide(result, prim(), modeType);
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
                    result = new Add(result, term(), modeType);
                    break;
                case SUB:
                    result = new Subtract(result, term(), modeType);
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
        return expr();
    }

}