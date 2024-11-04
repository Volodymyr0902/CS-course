package com.shpp.p2p.cs.vtaboranskyi.assignment11;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the set of methods for calculating math expression received as a string preformatted by
 * FormulaAssembler (with no variables, spaced, double minuses or input mistakes).
 */
public class Calculator {

    /**
     * Step by step, checks if given formula contains specific operation and executes it if so.
     * Starts from calculating expressions in brackets, then counts functions, until the formula
     * only consists of numbers and simple math operators.
     * Creates two respective lists of operands and operators, checks operators are supported and executes
     * math operations considering either math priorities or operators sequence in the given formula.
     *
     * @param formula The expression preformatted by FormulaAssembler.
     * @return The solution of the formula.
     */
    protected static double calculate(String formula) throws ValidationException {
        if (formula.contains("(") || formula.contains(")"))
            formula = BracketsCalculator.calcAll(formula);

        formula = FunctionsCalculator.calcAll(formula);

        // This will keep single operand's part during the formula's parsing.
        StringBuilder operand = new StringBuilder();

        // Lists of all the operands and operators from the formula, respectively.
        ArrayList<Double> operands = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        for (int i = 0; i < formula.length(); i++) {
            if (isOperandPart(formula, i)) {
                operand.append(formula.charAt(i));
                if (isLastOperandPart(formula, i)) {
                    operands.add(Double.parseDouble(operand.toString()));
                    operand = new StringBuilder();
                }
            } else if (isOperator(formula, i)) {
                operators.add(formula.charAt(i));
            }
        }

        validateOperators(operators);
        raiseToPower(operands, operators);
        multiplyAndDivide(operators, operands);
        addAndSubtract(operators, operands);

        return operands.getFirst();
    }

    /**
     * Checks if parsed symbol is a supported math operator.
     *
     * @param formula The expression separated from possible variables definitions.
     * @param i The current symbol's index in the formula.
     * @return True if the symbol is a supported operator and not an operand's sign.
     */
    private static boolean isOperator(String formula, int i) {
        return i > 0 &&
                !((formula.charAt(i) == '-' || formula.charAt(i) == '+') && !Character.isDigit(formula.charAt(i - 1)));
    }

    /**
     * Checks if parsed symbol is last part of an operand (used for adding it to the relevant list).
     *
     * @param formula The expression separated from possible variables definitions.
     * @param i The current symbol's index in the formula.
     * @return True if the symbol is the last in the formula or next symbol is not a number
     *          or a point of floating-point number.
     */
    private static boolean isLastOperandPart(String formula, int i) {
        return i == formula.length() - 1 || !Character.isDigit(formula.charAt(i + 1)) && formula.charAt(i + 1) != '.';
    }

    /**
     * Checks if parsed symbol is a part of an operand.
     *
     * @param formula The expression separated from possible variables definitions.
     * @param i The current symbol's index in the formula.
     * @return True if it's a number, point or negative operand's sign.
     */
    private static boolean isOperandPart(String formula, int i) {
        return Character.isDigit(formula.charAt(i)) || formula.charAt(i) == '.' ||
                formula.charAt(i) == '-' && (i == 0 || !Character.isDigit(formula.charAt(i - 1)));
    }

    /**
     * Executes adding and subtraction operations in the order they
     * appear in the formula, until the operators list doesn't contain such.
     *
     * @param operators Sequential list of all the operators from the formula.
     * @param operands Sequential list of all the operands from the formula.
     */
    private static void addAndSubtract(ArrayList<Character> operators, ArrayList<Double> operands) {
        while (!operators.isEmpty()) {
            if (operators.getFirst() == '+') {
                // The result of adding first two operands.
                double sum = operands.getFirst() + operands.get(1);
                operands.set(1, sum);
            } else {
                // The result of subtracting the second operand from the first one.
                double remainder = operands.getFirst() - operands.get(1);
                operands.set(1, remainder);
            }

            // Remove the duplicating operand and the used operator.
            operands.removeFirst();
            operators.removeFirst();
        }
    }

    /**
     * Executes multiplication and division operations in the order they
     * appear in the formula, until the operators list doesn't contain such.
     *
     * @param operators Sequential list of all the operators from the formula.
     * @param operands Sequential list of all the operands from the formula.
     */
    private static void multiplyAndDivide(ArrayList<Character> operators, ArrayList<Double> operands) {
        while (operators.contains('*') || operators.contains('/')) {
            // Until one of two operators is met.
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '*') {
                    // Index of found matching operator in the list.
                    int operatorIndex = operators.indexOf('*');

                    // Corresponding index operand multiplied by the next one.
                    double product = operands.get(operatorIndex) * operands.get(operatorIndex + 1);
                    operands.set(operatorIndex, product);

                    // Remove the duplicating operand and the used operator.
                    operands.remove(operatorIndex + 1);
                    operators.remove(operatorIndex);
                    break;
                } else if (operators.get(i) == '/') {
                    // Index of found matching operator in the list.
                    int operatorIndex = operators.indexOf('/');

                    // Corresponding index operand divided by the next one.
                    double remainder = operands.get(operatorIndex) / operands.get(operatorIndex + 1);
                    operands.set(operatorIndex, remainder);

                    // Remove the duplicating operand and the used operator.
                    operands.remove(operatorIndex + 1);
                    operators.remove(operatorIndex);
                    break;
                }
            }
        }
    }

    /**
     * Executes raising to power as many times as operators list includes relevant symbol.
     * for the next operands from another list: operand with operator's index is raised to
     * power of the operand right next to it.
     *
     * @param operands Sequential list of all the operands from the formula.
     * @param operators Sequential list of all the operators from the formula.
     */
    private static void raiseToPower(ArrayList<Double> operands, ArrayList<Character> operators) {
        while (operators.contains('^')) {
            // First matching symbol index.
            int operatorIndex = operators.indexOf('^');

            // The result of raising the corresponding operand to power of the next one.
            double raised = Math.pow(operands.get(operatorIndex), operands.get(operatorIndex + 1));
            operands.set(operatorIndex, raised);

            // Remove the duplicating operand and the used operator.
            operands.remove(operatorIndex + 1);
            operators.remove(operatorIndex);
        }
    }

    /**
     * Check if every symbol from the operators list is supported as a math operator.
     *
     * @param operators Sequential list of all the operators from the formula.
     */
    private static void validateOperators(ArrayList<Character> operators) throws ValidationException {
        if (!operators.stream().allMatch(character -> {
            ArrayList<Character> availableOperators = new ArrayList<>(Arrays.asList('+', '-', '/', '*', '^'));
            return availableOperators.contains(character);
        })) {
            throw new ValidationException("Unexpected operators!");
        }
    }
}


