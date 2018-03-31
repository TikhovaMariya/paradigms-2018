package expression.exceptions;

public class DivisionByZeroException extends EvaluatingException {
    public DivisionByZeroException() {
        super("division by zero");
    }

    public DivisionByZeroException(String expression) {
        super(expression);
    }
}