package expression.exceptions;

public class NoOpeningParenthesisException extends ParsingException {
    public NoOpeningParenthesisException() {
        super("no opening parenthesis exception");
    }

    public NoOpeningParenthesisException(int index, String expression) {
        super(expression.substring(0, index) + "[opening parenthesis expected before ->]" +
                expression.substring(index, expression.length()));
    }
}