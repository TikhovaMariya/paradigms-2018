package expression;

import expression.exceptions.OverflowException;
import expression.exceptions.DividionByZero;
import expression.exceptions.IllegalLogArgumentException;

public interface IntegerOperation implements Operation<Integer> {

    private final boolean checked;

    void checkAdd(final Integer x, final Integer y) throws OverflowException {
        if ((Integer.MAX_VALUE - y < x && y > 0) || // x + y > Integer.MAX_VALUE
                (Integer.MIN_VALUE - y > x && y < 0)) { // x + y < Integer.MIN_VALUE
            throw new OverflowException("overflow while adding");
        }
    }

    @Override
    Integer Add(final Integer x, final Integer y) throws OverflowException {
        if (checked) {
            checkAdd(x, y);
        }
        return x + y;
    }

    void checkSubtract(final Integer x, final Integer y) throws OverflowException {
        if ((Integer.MAX_VALUE + y < x && y < 0) || // x - y > Integer.MAX_VALUE
                (Integer.MIN_VALUE + y > x && y > 0)) { // x - y < Integer.MIN_VALUE
            throw new OverflowException();
        }
    }

    @Override
    Integer Subtract(final Integer x, final Integer y) throws OverflowException {
        if (checked) {
            checkSubtract(x, y);
        }
        return x - y;
    }

    void checkMultiply(final Integer x, final Integer y) throws OverflowException {
        if ((y > 0 && Integer.MAX_VALUE / y < x) || // x * y > MAX_VALUE && x > 0 && y > 0
                (y < 0 && Integer.MAX_VALUE / y > x) || // x * y > MAX_VALUE && x < 0 && y < 0
                (x > 0 && Integer.MIN_VALUE / x > y) || // x * y < MIN_VALUE && x > 0 && y < 0
                (y > 0 && Integer.MIN_VALUE / y > x)) { // x * y < MIN_VALUE && x < 0 && y > 0
            throw new OverflowException("overflow while multiplying");
        }
    }

    @Override
    Integer Multiply(final Integer x, final Integer y) throws OverflowException {
        if (checked) {
            checkMultiply(x, y);
        }
        return x * y;
    }

    void checkDivisionByZero(final Integer x, final Integer y) throws DivisionByZeroException {
        if (y == 0) { // (x / 0)
            throw new DivisionByZeroException(Integer.toString(x) + " / 0");
        }
    }

    void checkDivisionOverflow(final Integer x, final Integer y) throws OverflowException {
        if (x == Integer.MIN_VALUE && y == -1) { //2^31 is out of Integer range
            throw new OverflowException("overflow while dividing");
        }
    }

    @Override
    Integer Divide(final Integer x, final Integer y) throws OverflowException, DivisionByZeroException {
        if (checked) {
            checkDivisionOverflow(x, y);
        }
        checkDivisionByZero(x, y);
        return x / y;
    }

    void checkNegate(final Integer x) throws OverflowException {
        if (Integer.compareUnsigned(x, Integer.MIN_VALUE) == 0) { //2^32 is out of Integer range
            throw new OverflowException();
        }
    }

    @Override
    Integer Negate(final Integer x) throws OverflowException {
        if (checked) {
            checkNegate(x);
        }
        return -x;
    }

    void checkPow(final Integer x) throws OverflowException {
        if (x > 9 || x < 0) {
            throw new OverflowException();
        }
    }

    @Override
    Integer Pow(final Integer x) throws OverflowException {
        if (checked) {
            checkPow(x);
        }
        int result = 1;
        for (int i = 0; i < x; i++) {
            result *= 10;
        }
        return result;
    }

    void checkLog(final Integer x) throws IllegalLogArgumentException {
        if (x <= 0) {
            throw new IllegalLogArgumentException(x);
        }
    }

    @Override
    Integer Log(final Integer x) throws IllegalLogArgumentException {
        int result = 0;
        while (x > 0) {
            result++;
            x /= 10;
        }
        return result - 1;
    }
}