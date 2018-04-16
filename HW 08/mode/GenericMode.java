package expression.mode;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public interface GenericMode<T> {
    T Add(final T x, final T y) throws OverflowException;

    T Subtract(final T x, final T y) throws OverflowException;

    T Multiply(final T x, final T y) throws OverflowException;

    T Divide(final T x, final T y) throws OverflowException, DivisionByZeroException;

    T Negate(final T x) throws OverflowException;

    T getValue(final int x);

    T parseValue(final String s);
}