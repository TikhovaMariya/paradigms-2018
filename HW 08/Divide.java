package expression;

import expression.exceptions.OverflowException;
import expression.exceptions.DivisionByZeroException;
import expression.mode.*;

public class Divide<T> extends AbstractBinaryOperation<T> {
    public Divide(CommonExpression<T> x, CommonExpression<T> y, GenericMode<T> mode) {
        super(x, y, mode);
    }

    protected T perform(T x, T y) throws OverflowException, DivisionByZeroException {
        return modeType.Divide(x, y);
    }
}