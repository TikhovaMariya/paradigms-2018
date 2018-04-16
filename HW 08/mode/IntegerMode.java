package expression.mode;

import expression.exceptions.OverflowException;
import expression.exceptions.DivisionByZeroException;

public class IntegerMode implements GenericMode<Integer> {
    private final boolean checked;

    public IntegerMode(final boolean ch) {
        checked = ch;
    }

    private void checkAdd(final Integer x, final Integer y) throws OverflowException {
        if ((Integer.MAX_VALUE - y < x && y > 0) || // x + y > Integer.MAX_VALUE
                (Integer.MIN_VALUE - y > x && y < 0)) { // x + y < Integer.MIN_VALUE
            throw new OverflowException("overflow while adding");
        }
    }

    @Override
    public Integer Add(final Integer x, final Integer y) throws OverflowException {
        if (checked) {
            checkAdd(x, y);
        }
        return x + y;
    }

    private void checkSubtract(final Integer x, final Integer y) throws OverflowException {
        if ((Integer.MAX_VALUE + y < x && y < 0) || // x - y > Integer.MAX_VALUE
                (Integer.MIN_VALUE + y > x && y > 0)) { // x - y < Integer.MIN_VALUE
            throw new OverflowException();
        }
    }

    @Override
    public Integer Subtract(final Integer x, final Integer y) throws OverflowException {
        if (checked) {
            checkSubtract(x, y);
        }
        return x - y;
    }

    private void checkMultiply(final Integer x, final Integer y) throws OverflowException {
        if ((y > 0 && Integer.MAX_VALUE / y < x) || // x * y > MAX_VALUE && x > 0 && y > 0
                (y < 0 && Integer.MAX_VALUE / y > x) || // x * y > MAX_VALUE && x < 0 && y < 0
                (x > 0 && Integer.MIN_VALUE / x > y) || // x * y < MIN_VALUE && x > 0 && y < 0
                (y > 0 && Integer.MIN_VALUE / y > x)) { // x * y < MIN_VALUE && x < 0 && y > 0
            throw new OverflowException("overflow while multiplying");
        }
    }

    @Override
    public Integer Multiply(final Integer x, final Integer y) throws OverflowException {
        if (checked) {
            checkMultiply(x, y);
        }
        return x * y;
    }

    private void checkDivisionByZero(final Integer x, final Integer y) throws DivisionByZeroException {
        if (y == 0) { // (x / 0)
            throw new DivisionByZeroException(Integer.toString(x) + " / 0");
        }
    }

    private void checkDivisionOverflow(final Integer x, final Integer y) throws OverflowException {
        if (x == Integer.MIN_VALUE && y == -1) { //2^31 is out of Integer range
            throw new OverflowException("overflow while dividing");
        }
    }

    @Override
    public Integer Divide(final Integer x, final Integer y) throws OverflowException, DivisionByZeroException {
        if (checked) {
            checkDivisionOverflow(x, y);
        }
        checkDivisionByZero(x, y);
        return x / y;
    }

    private void checkNegate(final Integer x) throws OverflowException {
        if (Integer.compareUnsigned(x, Integer.MIN_VALUE) == 0) { //2^32 is out of Integer range
            throw new OverflowException();
        }
    }

    @Override
    public Integer Negate(final Integer x) throws OverflowException {
        if (checked) {
            checkNegate(x);
        }
        return -x;
    }

    @Override
    public Integer getValue(int x) {
        return x;
    }

    @Override
    public Integer parseValue(String s) {
        return Integer.parseInt(s);
    }
}