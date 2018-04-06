package expression.exceptions;

public class NoFirstArgumentException extends  NoArgumentException {
    public NoFirstArgumentException() {
        super("no first argument exception");
    }

    public NoFirstArgumentException(int index) {
        super("first argument expected at index: " + Integer.toString(index));
    }
}