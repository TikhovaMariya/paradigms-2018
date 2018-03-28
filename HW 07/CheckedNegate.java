package expression;

import expression.exceptions.OverflowException;

public class CheckedNegate extends AbstractUnaryOperation {
    public CheckedNegate(CommonExpression x) {
        super(x);
    }

    protected int perform(int x) throws OverflowException {
        if (Integer.compareUnsigned(x, Integer.MIN_VALUE) == 0) { //2^32 is out of Integer range
            throw new OverflowException();
        }
        return -x;
    }
}