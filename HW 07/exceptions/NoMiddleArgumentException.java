package expression.exceptions;

public class NoMiddleArgumentException extends NoArgumentException {
    public NoMiddleArgumentException() {
        super("no argument exception");
    }

    public NoMiddleArgumentException(int index) {
        super("middle argument expected at index: " + Integer.toString(index));
    }
}