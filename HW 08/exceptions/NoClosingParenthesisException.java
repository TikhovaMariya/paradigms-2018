package expression.exceptions;

public class NoClosingParenthesisException extends ParsingException {
    public NoClosingParenthesisException() {
        super("no closing parenthesis exception");
    }

    public NoClosingParenthesisException(int index, String expression) {
        super(expression.substring(0, index) + "[closing parenthesis expected]" +
                expression.substring(index, expression.length()));
    }
}