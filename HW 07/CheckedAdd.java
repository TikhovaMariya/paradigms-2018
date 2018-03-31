package expression;

import expression.exceptions.OverflowException;

public class CheckedAdd extends AbstractBinaryOperation {
    public CheckedAdd(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    protected int perform(int x, int y) throws OverflowException {
        if ((Integer.MAX_VALUE - y < x && y > 0) || // x + y > Integer.MAX_VALUE
                (Integer.MIN_VALUE - y > x && y < 0)) { // x + y < Integer.MIN_VALUE
            throw new OverflowException("overflow while adding");
        }
        return x + y;
    }
}