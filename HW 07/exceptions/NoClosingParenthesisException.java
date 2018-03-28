package expression.exceptions;

public class NoClosingParenthesisException extends ParsingException {
    public NoClosingParenthesisException() {
        super("no closing parenthesis exception");
    }
}