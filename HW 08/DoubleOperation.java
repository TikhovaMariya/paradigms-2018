package expression;

import java.lang.Math;

import expression.exceptions.DividionByZero;
import expression.exceptions.IllegalLogArgumentException;

public interface DoubleOperation implements Operation<Double> {

    @Override
    Double Add(Double x, Double y) {
        return x + y;
    }

    @Override
    Double Subtract(final Double x, final Double y) {
        return x - y;
    }

    @Override
    Double Multiply(final Double x, final Double y) {
        return x * y;
    }

    void checkDivisionByZero(Integer x, Integer y) throws DivisionByZeroException {
        if (y == 0) { // (x / 0)
            throw new DivisionByZeroException(Integer.toString(x) + " / 0");
        }
    }

    @Override
    Double Divide(final Double x, final Double y) throws DivisionByZeroException {
        checkDivisionByZero(x, y);
        return x / y;
    }

    @Override
    Double Negate(final Double x) {
        return -x;
    }

    @Override
    Double Pow(final Double x) {
        return pow(10, x);
    }

    void checkLog(Double x) throws IllegalLogArgumentException {
        if (x <= 0) {
            throw new IllegalLogArgumentException(x);
        }
    }

    @Override
    Double Log(final Double x) throws IllegalLogArgumentException {
        return log10(x);
    }
}