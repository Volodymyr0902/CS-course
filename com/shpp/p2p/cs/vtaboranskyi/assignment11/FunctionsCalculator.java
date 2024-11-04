package com.shpp.p2p.cs.vtaboranskyi.assignment11;

import java.util.Arrays;

/**
 * This class as an extension of simple calculator is the set of methods
 * for solving functions a math expression contains.
 */
public class FunctionsCalculator {

    /**
     * The enlisted supported functions.
      */
    private static final String[] FUNCTIONS = {"sin", "cos", "atan", "tan", "log10", "log2", "sqrt"};

    /**
     * Calculates all the functions and replaces them with results until there's none of them.
     * Functions presence is controlled by checking letters presence, so the formula must be
     * preformatted so that all the variables are replaced with their values.
     *
     * @param formula The expression preformatted by FormulaAssembler and
     *                in-brackets expressions replaced with the results.
     * @return The formula with all the functions evaluated.
     */
    protected static String calcAll(String formula) {
        // This will let replace functions with their results.
        StringBuilder lessFunctions = new StringBuilder(formula);

        while (formula.chars().anyMatch(Character::isLetter)) {
            // The argument value for the current function.
            StringBuilder arg = new StringBuilder();

            // Current function type, must not be null.
            String currFunction = getFunction(formula);
            assert currFunction != null;

            // The index of the first value of the function argument in the formula.
            int currArgInd = formula.indexOf(currFunction) + currFunction.length();

            do {
                arg.append(formula.charAt(currArgInd));
                currArgInd++;
            } while (currArgInd < formula.length() &&
                    (Character.isDigit(formula.charAt(currArgInd)) || formula.charAt(currArgInd) == '.'));

            // The result of the current function.
            double result = calcMathFunction(currFunction, arg.toString());
            lessFunctions.replace(formula.indexOf(currFunction), currArgInd, String.valueOf(result));
            formula = lessFunctions.toString();
        }
        return formula;
    }

    /**
     * Calculates single function using built-in library.
     *
     * @param currFunction The current function type.
     * @param arg The argument of the current function.
     * @return The result of the current function or 0 if given function type is not supported.
     */
    private static double calcMathFunction(String currFunction, String arg) {
        // The argument in digit format.
        double digitArg = Double.parseDouble(arg);

        return switch (Arrays.asList(FUNCTIONS).indexOf(currFunction)) {
            case 0 -> // sin
                    Math.sin(digitArg);
            case 1 -> // cos
                    Math.cos(digitArg);
            case 2 -> // tan
                    Math.atan(digitArg);
            case 3 -> // atan
                    Math.tan(digitArg);
            case 4 -> // log10
                    Math.log10(digitArg);
            case 5 -> // log2
                    Math.log(digitArg) / Math.log(2);
            case 6 -> // sqrt
                    Math.sqrt(digitArg);
            default -> 0;
        };

    }

    /**
     * Searches the first matching function the formula contains.
     *
     * @param formula The expression containing letters, so possibly a function.
     * @return The first matching function type in the formula.
     */
    private static String getFunction(String formula) {
        for (String function : FUNCTIONS)
            if (formula.contains(function))
                return function;
        return null;
    }
}
