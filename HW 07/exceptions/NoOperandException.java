package expression.exceptions;

public class NoOperandException extends ParsingException {
    public NoOperandException() {
        super("no operand exception");
    }

    public NoOperandException(int index) {
        super("operand excpected at index: " + Integer.toString(index));
    }
}