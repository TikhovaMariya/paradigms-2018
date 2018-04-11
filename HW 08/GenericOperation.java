package expression;

public interface Operation<T> {
    T Add(final T x, final T y) throws OverflowException;

    T Subtract(final T x, final T y) throws OverflowException;

    T Multiply(final T x, final T y) throws OverflowException;

    T Divide(final T x, final T y) throws OverflowException, DivisionByZeroException;

    T Negate(final T x) throws OverflowException;

    T Pow(final T x) throws OverflowException;

    T Log(final T x) throws IllegalLogArgumentException;
}