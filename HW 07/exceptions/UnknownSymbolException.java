package expression.exceptions;

public class UnknownSymbolException extends ParsingException {
    public UnknownSymbolException() {
        super("unknown symbol exception");
    }

    public UnknownSymbolException(int index) {
        super("unknown symbol at index: " + Integer.toString(index));
    }
}