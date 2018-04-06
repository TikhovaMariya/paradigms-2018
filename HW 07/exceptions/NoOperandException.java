package expression.exceptions;

public class NoOperandException extends ParsingException {
    public NoOperandException() {
        super("no operand exception");
    }

    public NoOperandException(int index) {
        super("operand excpected at index: " + Integer.toString(index));
    }

    public NoOperandException(int index, String expression) {
        super(expression.substring(0, index) + "[operand expected]" +
                expression.substring(index, expression.length()));
    }
}