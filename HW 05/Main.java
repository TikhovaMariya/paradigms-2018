package expression;

public class Main {
    public static void main(String[] args) {
        int result = new Add(
                new Multiply(
                        new Variable("x"),
                        new Substract(
                                new Variable("x"),
                                new Const(2)
                        )
                ),
                new Const(1)
        ).evaluate(Integer.valueOf(args[0]));
        System.out.println(result);
    }
}