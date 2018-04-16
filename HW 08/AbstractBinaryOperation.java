package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;
import expression.mode.*;

public abstract class AbstractBinaryOperation<T> implements CommonExpression<T> {
    private final CommonExpression<T> first, second;
    protected final GenericMode<T> modeType;

    public AbstractBinaryOperation(CommonExpression<T> x, CommonExpression<T> y, GenericMode<T> mode) {
        first = x;
        second = y;
        modeType = mode;
    }

    protected abstract T perform(T x, T y) throws EvaluatingException, ParsingException;

    public T evaluate(T x) throws EvaluatingException, ParsingException {
        return perform(first.evaluate(x), second.evaluate(x));
    }

    public T evaluate(T x, T y, T z) throws EvaluatingException, ParsingException {
        return perform(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}