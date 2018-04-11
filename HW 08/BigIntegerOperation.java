package expression;

import java.math.BigInteger;

import expression.exceptions.OverflowException;
import expression.exceptions.DividionByZero;
import expression.exceptions.IllegalLogArgumentException;

public interface BigIntegerOperation implements Operation<BigInteger> {

    @Override
    BigInteger Add(final BigInteger x, final BigInteger y) {
        return x.add(y);
    }

    @Override
    BigInteger Subtract(final BigInteger x, final BigInteger y) {
        return x.subtract(y);
    }

    @Override
    BigInteger Multiply(final BigInteger x, final BigInteger y) {
        return x.multiply(y);
    }

    void checkDivisionByZero(final BigInteger x, final BigInteger y) throws DivisionByZeroException {
        if (y.compareTo(0) == 0) { // (x / 0)
            throw new DivisionByZeroException(BigInteger.toString(x) + " / 0");
        }
    }

    @Override
    BigInteger Divide(final BigInteger x, final BigInteger y) throws DivisionByZeroException {
        checkDivisionByZero(x, y);
        return x.divide(y);
    }

    @Override
    BigInteger Negate(final BigInteger x) {
        return x.negate();
    }

    void checkPow(final BigInteger x) throws OverflowException {
        if(x.compareTo(Integer.MAX_VALUE) == 1 || x.compareTo(Integer.MIN_VALUE) == -1) {
            throw new OverflowException();
        }
    }

    @Override
    BigInteger Pow(final BigInteger x) throws OverflowException{
        checkPow(x);
        int a = x.intValue;
        long result = 1;
        for(int i = 0; i < a; i++) {
            result *= 10;
        }
        return BigInteger.valueOf(result);
    }

    void checkLog(final BigInteger x) throws OverflowException {
        if(x.compareTo(0) != 1) {
            throw new OverflowException();
        }
    }

    @Override
    BigInteger Log(final BigInteger x) throws IllegalLogArgumentException{
        checkLog(x);
        BigInteger result = 0;
        while (x.compareTo(0) == 1) {
            result.add(1);
            x.divide(10);
        }
        return result.subtract(1);
    }
}