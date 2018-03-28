package expression.exceptions;

public class NoArgumentException extends ParsingException {
    public NoArgumentException() {
        super("no argument exception");
    }
}