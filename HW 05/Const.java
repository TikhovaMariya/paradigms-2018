package expression;

public class Const implements Expressions {
    private final Number value;

    public Const(Number x) {
        value = x;
    }

    public int evaluate(int x) {
        return value.intValue();
    }

    public double evaluate(double x) {
        return value.doubleValue();
    }
}