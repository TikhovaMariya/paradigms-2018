package expression.exceptions;

public class IllegalLogArgumentException extends EvaluatingException {
    public IllegalLogArgumentException() {
        super("illegal log argument");
    }

    public IllegalLogArgumentException(int argument) {
        super("negative log argument: " + Integer.toString(argument));
    }
}