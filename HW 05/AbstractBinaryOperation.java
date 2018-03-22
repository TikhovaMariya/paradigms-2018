package expression;

public abstract class AbstractBinaryOperation implements Expressions {
    private final Expressions first, second;

    public AbstractBinaryOperation(Expressions x, Expressions y) {
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


}