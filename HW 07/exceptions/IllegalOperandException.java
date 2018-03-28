package expression.exceptions;

public class IllegalOperandException extends ParsingException {
    public IllegalOperandException() {
        super("illegal operand exception");
    }
}