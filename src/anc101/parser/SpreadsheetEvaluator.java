package anc101.parser;

import anc101.SpreadsheetData;
import anc101.SpreadsheetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 * Evaluates a spreadsheet by interpreting formulas in RPN format
 *
 * @author Wellington Della Mura
 */
public class SpreadsheetEvaluator {

    /**
     * The spreadsheet that has already been evaluated
     */
    private SpreadsheetData<Double> data;

    /**
     * The spreadsheet being evaluated
     */
    private SpreadsheetData<String> rawData;

    /**
     * Performs the evaluation of the spreadsheet interpreting the formulas in RPN format and their references
     * @param rawData The spreadsheet being evaluated
     * @return The evaluated spreadsheet
     * @throws SpreadsheetException If there is a syntax or reference error
     */
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

    /**
     * Evaluates an expression in RPN format
     *
     * @param expression The expression
     * @return The result value
     * @throws SpreadsheetException If there is a syntax or reference error
     */
    private double evalExpression(String expression) throws SpreadsheetException {
        return evalExpression(expression, new HashSet<String>());
    }

    /**
     * Evaluates an expression in RPN format
     *
     * @param expression The expression
     * @param previousRefs Stores previous calls from a reference. Used to
     * detect the cyclic reference
     * @return The result value
     * @throws SpreadsheetException If there is a syntax or reference error
     */
    private double evalExpression(String expression, HashSet<String> previousRefs) throws SpreadsheetException {
        Stack<String> stack = new Stack<String>();
        stack.addAll(Arrays.asList(expression.trim().split("[ \t]+")));
        return evalrpn(stack, previousRefs);
    }

    /**
     * Evaluates a stack with values and references that were previously in RPN
     *
     * @param stack The stack to be evaluated
     * @param previousRefsStores previous calls from a reference. Used to detect
     * the cyclic reference
     * @return The result value
     * @throws SpreadsheetException If there is a syntax or reference error
     */
    private double evalrpn(Stack<String> stack, HashSet<String> previousRefs) throws SpreadsheetException {
        if (stack.empty()) {
            throw new SpreadsheetException("Syntax error.");
        }

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
                    throw new SpreadsheetException("A cyclic dependency was detected. The references are " + previousRefs.toString().replaceAll("[\\[\\]]", "").replace(",", " ->") + " -> " + value);
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
