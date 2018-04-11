package expression;

import expression.exceptions.EvaluatingException;

public interface Expression<T> {
    <T> evaluate(<T> x) throws EvaluatingException;
}