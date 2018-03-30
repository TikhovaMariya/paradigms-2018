package expression;

import expression.exceptions.OverflowException;

public class CheckedPow extends AbstractUnaryOperation {
    public CheckedPow(CommonExpression x) {
        super(x);
    }

    protected int perform(int x) throws OverflowException {
        if (x > 9 || x < 0) {
            throw new OverflowException();
        }
        int result = 1;
        for(int i = 0; i < x; i++) {
            result *= 10;
        }
        return result;
    }
}