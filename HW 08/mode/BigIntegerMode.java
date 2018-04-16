package expression.mode;

import java.math.BigInteger;

import expression.exceptions.DivisionByZeroException;

public class BigIntegerMode implements GenericMode<BigInteger> {

    @Override
    public BigInteger Add(final BigInteger x, final BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger Subtract(final BigInteger x, final BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger Multiply(final BigInteger x, final BigInteger y) {
        return x.multiply(y);
    }

    private void checkDivisionByZero(final BigInteger x, final BigInteger y) throws DivisionByZeroException {
        if (y.compareTo(BigInteger.valueOf(0)) == 0) { // (x / 0)
            throw new DivisionByZeroException(x.toString() + " / 0");
        }
    }

    @Override
    public BigInteger Divide(final BigInteger x, final BigInteger y) throws DivisionByZeroException {
        checkDivisionByZero(x, y);
        return x.divide(y);
    }

    @Override
    public BigInteger Negate(final BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger getValue(int x) {
        return BigInteger.valueOf((long)(x));
    }

    @Override
    public BigInteger parseValue(String s) {
        return new BigInteger(s);
    }
}