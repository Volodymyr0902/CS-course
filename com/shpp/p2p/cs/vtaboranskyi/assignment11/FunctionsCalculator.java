package com.shpp.p2p.cs.vtaboranskyi.assignment11;

import java.util.Arrays;

public class FunctionsCalculator {

    private static final String[] FUNCTIONS = {"sin", "cos", "atan", "tan", "log10", "log2", "sqrt"};

    protected static String calcAll(String formula) {
        StringBuilder lessFunctions = new StringBuilder(formula);

        while (formula.chars().anyMatch(Character::isLetter)) {
            StringBuilder arg = new StringBuilder();
            String currFunction = getFunction(formula);
            assert currFunction != null;
            int currArgInd = formula.indexOf(currFunction) + currFunction.length();

            do {
                arg.append(formula.charAt(currArgInd));
                currArgInd++;
            } while (currArgInd < formula.length() &&
                    (Character.isDigit(formula.charAt(currArgInd)) || formula.charAt(currArgInd) == '.'));

            double result = calcMathFunction(currFunction, arg.toString());
            lessFunctions.replace(formula.indexOf(currFunction), currArgInd, String.valueOf(result));
            formula = lessFunctions.toString();
        }
        return formula;
    }

    private static double calcMathFunction(String currFunction, String arg) {
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

    private static String getFunction(String formula) {
        for (String function : FUNCTIONS)
            if (formula.contains(function))
                return function;
        return null;
    }
}
