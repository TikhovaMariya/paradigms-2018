package expression;

public class Multiply extends AbstractBinaryOperation {
    public Multiply(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    protected int perform(int x, int y) {
        return x * y;
    }

    protected double perform(double x, double y) {
        return x * y;
    }
}