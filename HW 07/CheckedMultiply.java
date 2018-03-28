package expression;

import expression.exceptions.OverflowException;

public class CheckedMultiply extends AbstractBinaryOperation {
    public CheckedMultiply(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    protected int perform(int x, int y) throws OverflowException {
        if ((y > 0 && Integer.MAX_VALUE / y < x) || // x * y > MAX_VALUE && x > 0 && y > 0
                (y < 0 && Integer.MAX_VALUE / y > x) || // x * y > MAX_VALUE && x < 0 && y < 0
                (x > 0 && Integer.MIN_VALUE / x > y) || // x * y < MIN_VALUE && x > 0 && y < 0
                (y > 0 && Integer.MIN_VALUE / y > x)) { // x * y < MIN_VALUE && x < 0 && y > 0
            throw new OverflowException();
        }
        return x * y;
    }
}