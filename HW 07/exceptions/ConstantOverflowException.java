package expression.exceptions;

public class ConstantOverflowException extends ParsingException {
    public ConstantOverflowException() {
        super("constant overflow exception");
    }

    public ConstantOverflowException(int index) {
        super("constant overflow at index: " + Integer.toString(index));
    }
}