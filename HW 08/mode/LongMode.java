package expression.mode;

import expression.exceptions.DivisionByZeroException;

public class LongMode implements GenericMode<Long> {

    @Override
    public Long Add(final Long x, final Long y) {
        return (long)(x + y);
    }

    @Override
    public Long Subtract(final Long x, final Long y) {
        return (long)(x - y);
    }

    @Override
    public Long Multiply(final Long x, final Long y) {
        return (long)(x * y);
    }

    private void checkDivisionByZero(final Long x, final Long y) throws DivisionByZeroException {
        if (y == 0) { // (x / 0)
            throw new DivisionByZeroException(Long.toString(x) + " / 0");
        }
    }

    @Override
    public Long Divide(final Long x, final Long y) throws DivisionByZeroException {
        checkDivisionByZero(x, y);
        return (long)(x / y);
    }

    @Override
    public Long Negate(final Long x) {
        return (long)(-x);
    }

    @Override
    public Long getValue(int x) {
        return (long)(x);
    }

    @Override
    public Long parseValue(String s) {
        return Long.parseLong(s);
    }
}