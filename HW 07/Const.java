package expression;

public class Const implements CommonExpression {
    private final Number value;

    public Const(Number x) {
        value = x;
    }

    public int evaluate(int x) {
        return value.intValue();
    }

    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

}