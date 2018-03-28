package expression.exceptions;

public class UnknownVariableException extends ParsingException {
    public UnknownVariableException() {
        super("unknown variable exception");
    }
}