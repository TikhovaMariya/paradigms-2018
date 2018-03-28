package expression.exceptions;

public class NoOperandException extends ParsingException {
    public NoOperandException() {
        super("no operand exception");
    }
}