package expression;

import expression.exceptions.EvaluatingException;

public class Variable implements CommonExpression {
    private String name;

    public Variable(String x) {
        name = x;
    }

    public Variable(char x) {
        name = String.valueOf(x);
    }

    public int evaluate(int x) throws EvaluatingException {
        if (name != "x") {
            System.out.println("duh " + name);
        }
        return x;
    }

    public int evaluate(int x, int y, int z) throws EvaluatingException {
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