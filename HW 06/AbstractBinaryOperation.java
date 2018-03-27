package expression;

public abstract class AbstractBinaryOperation implements CommonExpression {
    private final CommonExpression first, second;

    public AbstractBinaryOperation(CommonExpression x, CommonExpression y) {
        first = x;
        second = y;
    }

    protected abstract double perform(double x, double y);

    protected abstract int perform(int x, int y);

    public double evaluate(double x) {
        return perform(first.evaluate(x), second.evaluate(x));
    }

    public int evaluate(int x) {
        return perform(first.evaluate(x), second.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return perform(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}