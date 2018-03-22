package expression;

public class Multiply extends AbstractBinaryOperation {
    public Multiply(Expressions x, Expressions y) {
        super(x, y);
    }

    protected int perform(int x, int y) {
        return x * y;
    }

    protected double perform(double x, double y) {
        return x * y;
    }
}