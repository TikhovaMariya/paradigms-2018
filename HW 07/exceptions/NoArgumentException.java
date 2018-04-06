package expression.exceptions;

public class NoArgumentException extends ParsingException {
    public NoArgumentException() {
        super("no argument exception");
    }

    public NoArgumentException(int index) {
        super("argument excpected at index: " + Integer.toString(index));
    }

    public NoArgumentException(int index, String expression) {
        super(expression.substring(0, index) + "[argument expected]" +
                expression.substring(index, expression.length()));
    }

    public NoArgumentException(String message) {
        super(message);
    }
}