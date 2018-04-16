package expression;

import expression.exceptions.OverflowException;
import java.math.BigInteger;
import expression.mode.*;

public class Add<T> extends AbstractBinaryOperation<T> {
    public Add(CommonExpression<T> x, CommonExpression<T> y, GenericMode<T> mode) {
        super(x, y, mode);
    }

    protected T perform(final T x, final T y) throws OverflowException {
        return modeType.Add(x, y);
    }
}