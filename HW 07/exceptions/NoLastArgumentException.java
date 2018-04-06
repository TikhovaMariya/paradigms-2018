package expression.exceptions;

public class NoLastArgumentException extends NoArgumentException {
    public NoLastArgumentException() {
        super("no argument exception");
    }

    public NoLastArgumentException(int index) {
        super("last argument expected at index: " + Integer.toString(index));
    }
}