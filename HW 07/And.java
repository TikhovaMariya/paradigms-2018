package expression;

public class And extends AbstractBinaryOperation {
    public And(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    protected int perform(int x, int y) {
        return x & y;
    }
}