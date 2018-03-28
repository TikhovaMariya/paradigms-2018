package expression.exceptions;

public class NoOpeningParenthesisException extends ParsingException {
    public NoOpeningParenthesisException() {
        super("no opening parenthesis exception");
    }
}