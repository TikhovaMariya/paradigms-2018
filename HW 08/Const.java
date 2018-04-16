package expression;

import expression.mode.*;
import expression.exceptions.EvaluatingException;

public class Const<T> implements CommonExpression<T> {
    private final T value;
    private final GenericMode<T> modeType;

    public Const(T x, GenericMode<T> mode) {
        value = x;
        modeType = mode;
    }

    public T evaluate(T x) throws EvaluatingException {
        return value;
    }

    public T evaluate(T x, T y, T z) {
        return value;
    }

}