package expression;

public class Variable implements Expressions {
    private final String name;

    public Variable(String x) {
        name = x;
    }

    public int evaluate(int x) {
        return x;
    }

    public double evaluate(double x) {
        return x;
    }
}