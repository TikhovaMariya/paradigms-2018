package expression;

import expression.exceptions.EvaluatingException;

public abstract class AbstractUnaryOperation implements CommonExpression {
    private final CommonExpression element;

    public AbstractUnaryOperation(CommonExpression x) {
        element = x;
    }

    protected abstract int perform(int x) throws EvaluatingException;

    public int evaluate(int x) throws EvaluatingException {
        return perform(element.evaluate(x));
    }

    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return perform(element.evaluate(x, y, z));
    }
}