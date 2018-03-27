package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {
    private String expression;
    private int index = 0;
    private int value;
    private String variable;
    private Token curToken;

    private enum Token {CONST, VARIABLE, NEG, ADD, SUB, DIV, MUL,
                        OPEN_BRACE, CLOSE_BRACE, AND, XOR, OR, END}


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

    private void parseVariable() {
        int start = index;
        while (index < expression.length() && Character.isLetter(expression.charAt(index))) {
            index++;
        }
        variable = expression.substring(start, index);
        index--;
    }

    private void nextToken() {
        skipWhitespaces();
        if (index < expression.length()) {
            char ch = expression.charAt(index);
            switch (ch) {
                case '-':
                    if (curToken == Token.CONST || curToken == Token.VARIABLE || curToken == Token.CLOSE_BRACE) {
                        curToken = Token.SUB;
                    } else {
                        curToken = Token.NEG;
                    }
                    break;
                case '+':
                    curToken = Token.ADD;
                    break;
                case '/':
                    curToken = Token.DIV;
                    break;
                case '*':
                    curToken = Token.MUL;
                    break;
                case '(':
                    curToken = Token.OPEN_BRACE;
                    break;
                case ')':
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
                        parseValue();
                        curToken = Token.CONST;
                    } else if (Character.isLetter(ch)) { //next token is a variable
                        parseVariable();
                        curToken = Token.VARIABLE;
                    }
            }
            index++;
        } else {
            curToken = Token.END;
        }
    }

    // prim() - обрабатывает первичные выражения
    private CommonExpression prim() {
        nextToken();
        CommonExpression result;
        switch (curToken) {
            case CONST:
                result = new Const(value);
                nextToken();
                break;
            case VARIABLE:
                result = new Variable(variable);
                nextToken();
                break;
            case NEG:
                result = new Subtract(new Const(0), prim());
                break;
            case OPEN_BRACE:
                result = or();
                nextToken();
                break;
            default:
                result = new Const(0);
        }
        return result;
    }

    // term() - умножение и деление
    private CommonExpression term() {
        CommonExpression result = prim();
        while (true) {
            switch (curToken) {
                case MUL:
                    result = new Multiply(result, prim());
                    break;
                case DIV:
                    result = new Divide(result, prim());
                    break;
                default:
                    return result;
            }
        }
    }

    // expr() - сложение и вычитание
    private CommonExpression expr() {
        CommonExpression result = term();
        while (true) {
            switch (curToken) {
                case ADD:
                    result = new Add(result, term());
                    break;
                case SUB:
                    result = new Subtract(result, term());
                    break;
                default:
                    return result;
            }
        }
    }

    // and() - побитное и
    private CommonExpression and() {
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
    private CommonExpression xor() {
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
    private CommonExpression or() {
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

    public CommonExpression parse(String s) {
        expression = s;
        index = 0;
        curToken = Token.END;
        return or();
    }

}