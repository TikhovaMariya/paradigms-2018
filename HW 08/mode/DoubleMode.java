package expression.mode;

import java.lang.Math;

public class DoubleMode implements GenericMode<Double> {

    @Override
    public Double Add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double Subtract(final Double x, final Double y) {
        return x - y;
    }

    @Override
    public Double Multiply(final Double x, final Double y) {
        return x * y;
    }

    @Override
    public Double Divide(final Double x, final Double y) {
        return x / y;
    }

    @Override
    public Double Negate(final Double x) {
        return -x;
    }

    @Override
    public Double getValue(int x) {
        return Double.valueOf(x);
    }

    @Override
    public Double parseValue(String s) {
        return Double.parseDouble(s);
    }
}