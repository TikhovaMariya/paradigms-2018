package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;

public interface TripleExpression<T> {
    T evaluate(T x, T y, T z) throws EvaluatingException, ParsingException;
}
