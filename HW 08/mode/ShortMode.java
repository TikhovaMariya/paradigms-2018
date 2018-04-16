package expression.mode;

import expression.exceptions.DivisionByZeroException;

public class ShortMode implements GenericMode<Short> {

    @Override
    public Short Add(final Short x, final Short y) {
        return (short) (x + y);
    }

    @Override
    public Short Subtract(final Short x, final Short y) {
        return (short) (x - y);
    }

    @Override
    public Short Multiply(final Short x, final Short y) {
        return (short) (x * y);
    }

    private void checkDivisionByZero(final Short x, final Short y) throws DivisionByZeroException {
        if (y == 0) { // (x / 0)
            throw new DivisionByZeroException(Short.toString(x) + " / 0");
        }
    }

    @Override
    public Short Divide(final Short x, final Short y) throws DivisionByZeroException {
        checkDivisionByZero(x, y);
        return (short) (x / y);
    }

    @Override
    public Short Negate(final Short x) {
        return (short) (-x);
    }

    @Override
    public Short getValue(int x) {
        return (short) (x);
    }

    @Override
    public Short parseValue(String s) {
        return Short.parseShort(s);
    }
}