package expression;

public class Or extends AbstractBinaryOperation {
    public Or(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    protected int perform(int x, int y) {
        return x | y;
    }

    protected double perform(double x, double y) {
        Double d_x = x, d_y = y;
        Integer i = d_x.intValue() | d_y.intValue();
        return i.doubleValue();
    }
}