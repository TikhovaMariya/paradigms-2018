package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;
import expression.mode.*;

public abstract class AbstractUnaryOperation<T> implements CommonExpression<T> {
    private final CommonExpression<T> element;
    protected final GenericMode<T> modeType;

    public AbstractUnaryOperation(CommonExpression<T> x, GenericMode<T> mode) {
        element = x;
        modeType = mode;
    }

    protected abstract T perform(T x) throws EvaluatingException, ParsingException;

    public T evaluate(T x) throws EvaluatingException, ParsingException {
        return perform(element.evaluate(x));
    }

    public T evaluate(T x, T y, T z) throws EvaluatingException, ParsingException {
        return perform(element.evaluate(x, y, z));
    }
}