package expression.exceptions;

public class ConstantOverflowException extends ParsingException {
    public ConstantOverflowException() {
        super("constant overflow exception");
    }
}