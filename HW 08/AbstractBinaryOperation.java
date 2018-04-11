package expression;

import expression.exceptions.EvaluatingException;

public abstract class AbstractBinaryOperation implements CommonExpression {
    private final CommonExpression first, second;

    public AbstractBinaryOperation(CommonExpression x, CommonExpression y) {
        first = x;
        second = y;
    }

    protected abstract int perform(int x, int y) throws EvaluatingException;

    public int evaluate(int x) throws EvaluatingException {
        return perform(first.evaluate(x), second.evaluate(x));
    }

    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return perform(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}