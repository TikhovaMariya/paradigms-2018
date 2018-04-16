package expression;

import expression.exceptions.EvaluatingException;
import expression.exceptions.ParsingException;

public interface Expression<T> {
    T evaluate(T x) throws EvaluatingException, ParsingException;
}