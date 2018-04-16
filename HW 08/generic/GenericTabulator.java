package expression.generic;

import java.math.BigInteger;

import expression.*;
import expression.mode.*;
import expression.parser.*;
import expression.exceptions.*;

import java.lang.Package;

public class GenericTabulator implements Tabulator {
    public Object[][][] tabulate(String mode, String expr, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        GenericMode modeType;
        switch (mode) {
            case "i":
                modeType = new IntegerMode(true);
                break;
            case "u":
                modeType = new IntegerMode(false);
                break;
            case "d":
                modeType = new DoubleMode();
                break;
            case "bi":
                modeType = new BigIntegerMode();
                break;
            case "l":
                modeType = new LongMode();
                break;
            case "s":
                modeType = new ShortMode();
                break;
            default:
                throw new Exception("Illegal mode " + mode);
        }

        return getTable(modeType, expr, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] getTable(GenericMode<T> modeType, String expr, int x1, int x2, int y1, int y2,
                                      int z1, int z2) throws ParsingException {
        Object[][][] table = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        ExpressionParser<T> parser = new ExpressionParser(modeType);
        CommonExpression<T> expression = parser.parse(expr);
        T argumenti, argumentj, argumentk;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        argumenti = modeType.getValue(i);
                        argumentj = modeType.getValue(j);
                        argumentk = modeType.getValue(k);
                        table[i - x1][j - y1][k - z1] = expression.evaluate(argumenti, argumentj, argumentk);
                    } catch (EvaluatingException e) {
                        table[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return table;
    }
}
