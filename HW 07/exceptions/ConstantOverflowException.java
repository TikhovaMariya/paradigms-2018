package expression.exceptions;

public class ConstantOverflowException extends ParsingException {
    public ConstantOverflowException() {
        super("constant overflow exception");
    }

    public ConstantOverflowException(int index, String constant) {
        super("constant <" + constant + "> overflow at index: " + Integer.toString(index));
    }
}