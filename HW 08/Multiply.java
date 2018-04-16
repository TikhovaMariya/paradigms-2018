package expression;

import expression.exceptions.OverflowException;
import expression.mode.*;

public class Multiply<T> extends AbstractBinaryOperation<T> {
    public Multiply(CommonExpression<T> x, CommonExpression<T> y, GenericMode<T> mode) {
        super(x, y, mode);
    }

    protected T perform(T x, T y) throws OverflowException {
        return modeType.Multiply(x, y);
    }
}