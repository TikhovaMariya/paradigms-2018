package expression.exceptions;

public class UnknownSymbolException extends ParsingException {
    public UnknownSymbolException() {
        super("unknown symbol exception");
    }

    public UnknownSymbolException(int index, String expression) {
        super(expression.substring(0, index) + "[unknown symbol ->]" +
                expression.substring(index, expression.length()));
        //super("unknown symbol at index: " + Integer.toString(index));
    }
}