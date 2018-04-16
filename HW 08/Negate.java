package expression;

import expression.exceptions.OverflowException;
import expression.mode.*;

public class Negate<T> extends AbstractUnaryOperation<T> {
    public Negate(CommonExpression<T> x, GenericMode<T> mode) {
        super(x, mode);
    }

    protected T perform(T x) throws OverflowException {
        return modeType.Negate(x);
    }
}