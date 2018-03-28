package expression;

public class Or extends AbstractBinaryOperation {
    public Or(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    protected int perform(int x, int y) {
        return x | y;
    }
}