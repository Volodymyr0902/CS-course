package com.shpp.p2p.cs.vtaboranskyi.assignment11;

public class BracketsCalculator extends Calculator{

    protected static String calcAll(String formula) throws ValidationException {
        checkBrackets(formula);
        StringBuilder lessBrackets = new StringBuilder(formula);

        while (formula.contains("(")) {
            int openIndex = formula.indexOf("(");
            int closeIndex = getPairIndex(formula, openIndex);
            StringBuilder subFormula = new StringBuilder();

            for (int i = openIndex + 1; i < closeIndex; i++)
                subFormula.append(formula.charAt(i));

            double subResult = calculate(subFormula.toString());
            lessBrackets.replace(openIndex, closeIndex + 1, String.valueOf(subResult));
            formula = lessBrackets.toString();
        }
        return formula;
    }

    private static void checkBrackets(String formula) throws ValidationException {
        int bracketsCounter = 0;
        for (int i = 0; i < formula.length(); i++)
            if (formula.charAt(i) == '(' || formula.charAt(i) == ')')
                bracketsCounter++;
        if (bracketsCounter % 2 != 0)
            throw new ValidationException("Brackets must be opened and closed!");
    }

    private static int getPairIndex(String formula, int openIndex) {
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
