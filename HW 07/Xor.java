package expression;

public class Xor extends AbstractBinaryOperation {
    public Xor(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    protected int perform(int x, int y) {
        return x ^ y;
    }
}