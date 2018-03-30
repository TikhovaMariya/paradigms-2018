package expression;

import expression.exceptions.IllegalLogArgumentException;

public class CheckedLog extends AbstractUnaryOperation {
    public CheckedLog(CommonExpression x) {
        super(x);
    }

    protected int perform(int x) throws IllegalLogArgumentException {
        if (x <= 0) {
            throw new IllegalLogArgumentException();
        }
        int result = 0;
        while(x > 0) {
            result++;
            x /= 10;
        }
        return result - 1;
    }
}