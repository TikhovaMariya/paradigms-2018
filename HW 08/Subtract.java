package expression;

import expression.exceptions.OverflowException;
import expression.mode.*;

public class Subtract<T> extends AbstractBinaryOperation<T> {
    public Subtract(CommonExpression<T> x, CommonExpression<T> y, GenericMode mode) {
        super(x, y, mode);
    }

    protected T perform(T x, T y) throws OverflowException {
        return modeType.Subtract(x, y);
    }
}