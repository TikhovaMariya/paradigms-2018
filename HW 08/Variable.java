package expression;

import expression.exceptions.UnknownVariableException;
import expression.mode.*;

public class Variable<T> implements CommonExpression<T> {
    private String name;
    private final GenericMode<T> modeType;

    public Variable(String x, GenericMode<T> mode) {
        name = x;
        modeType = mode;
    }

    public Variable(char x, GenericMode<T> mode) {
        name = String.valueOf(x);
        modeType = mode;
    }

    public T evaluate(T x) throws UnknownVariableException {
        if (name != "x") {
            throw new UnknownVariableException(name);
        }
        return x;
    }

    public T evaluate(T x, T y, T z) throws UnknownVariableException {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new UnknownVariableException(name);
        }
    }

}