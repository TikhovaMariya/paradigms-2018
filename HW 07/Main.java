package expression;

import expression.parser.*;
import expression.exceptions.*;

public class Main {
    public static void main(String[] args) throws ParsingException, EvaluatingException {
        ExpressionParser p = new ExpressionParser();
        String expression = "log10 (594442021 Z/ y / z * 1958532834)\n";
        int x = 100;
        int y = 10;
        int z = 100;
        System.out.println(p.parse(expression).evaluate(x, y, z));
    }
}