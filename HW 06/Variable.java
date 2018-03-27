package expression;

public class Variable implements CommonExpression {
    private String name;

    public Variable(String x) {
        name = x;
    }

    public Variable(char x) {
        name = String.valueOf(x);
    }

    public int evaluate(int x) {
        return x;
    }

    public double evaluate(double x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }
    }

}