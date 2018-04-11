package expression.exceptions;

public class OverflowException extends EvaluatingException {
    public OverflowException() {
        super("overflow");
    }

    public OverflowException(String message) {
        super(message);
    }
}