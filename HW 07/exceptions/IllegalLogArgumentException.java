package expression.exceptions;

public class IllegalLogArgumentException extends EvaluatingException {
    public IllegalLogArgumentException() {
        super("illegal log argument");
    }
}