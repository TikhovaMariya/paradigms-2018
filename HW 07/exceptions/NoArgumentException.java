package expression.exceptions;

public class NoArgumentException extends ParsingException {
    public NoArgumentException() {
        super("no argument exception");
    }

    public NoArgumentException(int index) {
        super("argument expected before index: " + Integer.toString(index));
    }

    public NoArgumentException(String message) {
        super(message);
    }
}