package com.shpp.p2p.cs.vtaboranskyi.assignment11;

/**
 * This class as an extension of simple calculator is the set of methods
 * for solving inner expressions given in brackets and
 * replacing it with the result, as if: (a + b) * c -> a + b = d -> d * c.
 */
public class BracketsCalculator extends Calculator{

    /**
     * Firstly checks all the brackets are opened and closed correctly.
     * Then calculates expressions inside brackets until there's none of them.
     * If an expression in brackets has another expression inside it will be solved
     * first of all, and such result will be kept in stack of the recursive descending.
     *
     * @param formula The formula with at least one pair of brackets.
     * @return The formula with expressions in brackets solved and replaced by respective results.
     * @throws ValidationException This may be thrown during brackets validation.
     */
    protected static String calcAll(String formula) throws ValidationException {
        checkBrackets(formula);

        // This is used for replacing expressions in brackets wit their results.
        StringBuilder lessBrackets = new StringBuilder(formula);

        while (formula.contains("(")) {
            // The opening and closing brackets indexes, respectively.
            int openIndex = formula.indexOf("(");
            int closeIndex = getPairIndex(formula, openIndex);

            // This will sequentially collect inner expression symbols.
            StringBuilder subFormula = new StringBuilder();

            for (int i = openIndex + 1; i < closeIndex; i++)
                subFormula.append(formula.charAt(i));

            // The result of an inner expression.
            double subResult = calculate(subFormula.toString());
            lessBrackets.replace(openIndex, closeIndex + 1, String.valueOf(subResult));
            formula = lessBrackets.toString();
        }
        return formula;
    }

    /**
     * Checks if all the brackets were opened and closed that means their number must be even.
     *
     * @param formula The formula with at least one pair of brackets.
     * @throws ValidationException This is thrown if total brackets number is odd.
     */
    private static void checkBrackets(String formula) throws ValidationException {
        int bracketsCounter = 0;
        for (int i = 0; i < formula.length(); i++)
            if (formula.charAt(i) == '(' || formula.charAt(i) == ')')
                bracketsCounter++;
        if (bracketsCounter % 2 != 0)
            throw new ValidationException("Brackets must be opened and closed!");
    }

    /**
     * Finds the index of respective closing bracket.
     *
     * @param formula An expression with brackets.
     * @param openIndex The index of the first opening brackets in the @formula.
     * @return The index of the relevant closing bracket.
     */
    private static int getPairIndex(String formula, int openIndex) {
        // The counter for opening brackets met during the formula iteration.
        // If it's nonzero and a closing bracket is met it's decreased by 1.
        int openedMet = 0;

        for (int i = openIndex + 1; i <= formula.lastIndexOf(")"); i++) {
            if (formula.charAt(i) == '(') {
                openedMet++;
            } else if (formula.charAt(i) == ')' && openedMet == 0) {
                return i;
            } else if (formula.charAt(i) == ')' && openedMet > 0) {
                openedMet--;
            }
        }

        return -1;
    }
}
