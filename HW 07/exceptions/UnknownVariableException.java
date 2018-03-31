package expression.exceptions;

public class UnknownVariableException extends ParsingException {
    public UnknownVariableException() {
        super("unknown variable exception");
    }

    public UnknownVariableException(int index) {
        super("unknown variable at index: " + Integer.toString(index));
    }
}