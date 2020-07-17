package anc101.parser;

import anc101.SpreadsheetData;
import anc101.SpreadsheetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Wellington Della Mura
 */
public class SpreadsheetEvaluator {

    private SpreadsheetData<Double> data;
    private SpreadsheetData<String> rawData;

    public SpreadsheetEvaluator() {
    }

    public SpreadsheetData eval(SpreadsheetData<String> rawData) throws SpreadsheetException {
        this.data = new SpreadsheetData<Double>(rawData.getLines(), rawData.getColumns());
        this.rawData = rawData;
        for (int l = 0; l < data.getLines(); l++) {
            for (int c = 0; c < data.getColumns(); c++) {
                data.setData(l, c, evalExpression(rawData.getData(l, c)));
            }
        }
        return data;
    }

    private double evalExpression(String expression) throws SpreadsheetException {
        return evalExpression(expression, new HashSet<String>());
    }

    private double evalExpression(String expression, HashSet<String> previousRefs) throws SpreadsheetException {
        Stack<String> stack = new Stack<String>();
        stack.addAll(Arrays.asList(expression.trim().split("[ \t]+")));
        return evalrpn(stack, previousRefs);
    }

    private double evalrpn(Stack<String> stack, HashSet<String> previousRefs) throws SpreadsheetException {
        String value = stack.pop();

        //number
        if (Character.isDigit(value.charAt(0))) {
            return Double.parseDouble(value);
        }

        //reference
        if (Character.isAlphabetic(value.charAt(0))) {
            Double v = data.getData(value);
            if (v == null) {
                if (previousRefs.contains(value)) {
                    throw new SpreadsheetException("A cyclic dependency was detected. The references are "+previousRefs.toString().replaceAll("[\\[\\]]","").replace(",", " ->")+" -> "+value);
                }
                previousRefs.add(value);
                return evalExpression(rawData.getData(value), previousRefs);
            }
            return v;
        }

        //expression
        Double y = evalrpn(stack, previousRefs);
        Double x = evalrpn(stack, previousRefs);
        switch (value.charAt(0)) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
            default:
                throw new SpreadsheetException("Invalid operation: " + value + ". The supported operations are +, -, *, /.");
        }
    }

}
