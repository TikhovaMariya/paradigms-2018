package expression;

public class Add extends AbstractBinaryOperation {
    public Add(Expressions x, Expressions y) {
        super(x, y);
    }

    protected int perform(int x, int y) {
        return x + y;
    }

    protected double perform(double x, double y) {
        return x + y;
    }
}