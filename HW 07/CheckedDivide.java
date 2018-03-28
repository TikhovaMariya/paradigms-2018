package expression;

import expression.exceptions.OverflowException;
import expression.exceptions.DivisionByZeroException;;

public class CheckedDivide extends AbstractBinaryOperation {
    public CheckedDivide(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    protected int perform(int x, int y) throws OverflowException, DivisionByZeroException {
        if (y == 0) { // (x / 0)
            throw new DivisionByZeroException();
        }
        if (x == Integer.MIN_VALUE && y == -1) { //2^32 is out of Integer range
            throw new OverflowException();
        }
        return x / y;
    }
}