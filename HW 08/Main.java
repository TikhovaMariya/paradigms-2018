package expression;

import expression.*;
import expression.parser.*;
import expression.exceptions.*;

public class Main {
    public static void main(String[] args) throws ParsingException, EvaluatingException {
        Integer a = 100, b = 200;
        Const x = new Const(a);
        Const y = new Const(b);
        Const z = new Const(a);
        System.out.println((new Add(x, y, false)).evaluate(0, 0, 0));
    }
}