package com.shpp.p2p.cs.vtaboranskyi.assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * File: Assignment10Part1.java.
 * Simple calculator program which receives a math expression and (optionally)
 * its variables' values, solves it and prints the result if input data is correct:
 * contains only digits and supported operators ("+", "-", "/", "*", "^").
 * Digits, variables and their names may be negative.
 * If program is executed via terminal "*" must be screened with "\".
 * Specifications are in materials of level #10, Task "Simple calculator".
 */
public class Assignment10Part1 {
    public static void main(String[] args) {
        // Checks if no arguments were input.
        if (args.length == 0) {
            System.out.println("No data found!");
            return;
        }

        // To simplify next "=" searching.
        String strArgs = String.join("", args);
        strArgs = removeSpaces(strArgs);

        // First "=" symbol index in the input string.
        int equationIndex = getEquationIndex(strArgs);

        // Formula and variables are separated.
        String formula = equationIndex != 0 ? getFormula(strArgs, equationIndex) : strArgs;
        HashMap<String, Double> vars = equationIndex != 0 ? getVars(strArgs, equationIndex) : null;
        checkVarsDefined(formula, vars);

        // The solution to the input expression.
        double result = calculate(formula, vars);
        System.out.println("Result for " + Arrays.toString(args) + " is: " + result);
    }

    /**
     * Checks if all variables mentioned in the expression are defined next to it.
     *
     * @param formula The expression to be solved.
     * @param vars Variables and their values the expression may contain.
     */
    private static void checkVarsDefined(String formula, HashMap<String, Double> vars) {
        // This will contain letters symbols the formula may have.
        StringBuilder varsInFormula = new StringBuilder();
        for (int i = 0; i < formula.length(); i++) {
            if (Character.isLetter(formula.charAt(i)))
                varsInFormula.append(formula.charAt(i));
        }

        // In case the formula contains variables positive and negative matches are checked.
        if (!varsInFormula.isEmpty()) {
            for (int i = 0; i < varsInFormula.length(); i++) {
                if (vars.get(String.valueOf(varsInFormula.charAt(i))) == null &&
                        vars.get("-" + varsInFormula.charAt(i)) == null) {
                    System.err.println("Variables aren't defined!");
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Removes all the whitespaces from the input line.
     *
     * @param spacesPossible Original input represented as a string.
     * @return Input string with whitespaces removed.
     */
    private static String removeSpaces(String spacesPossible) {
        // This will contain same string but with no spaces.
        String spacesRemoved = "";
        for (int i = 0; i < spacesPossible.length(); i++) {
            if (spacesPossible.charAt(i) != ' ') {
                spacesRemoved += spacesPossible.charAt(i);
            }
        }
        return spacesRemoved;
    }

    /**
     * Retrieves all the variables and their values
     * from the input, if such exist.
     *
     * @param strArgs Input string with whitespaces removed.
     * @param equationIndex First "=" symbol index in the input string.
     * @return Map with [variable=value] entries.
     */
    private static HashMap<String, Double> getVars(String strArgs, int equationIndex) {
        // This will contain entries.
        HashMap<String, Double> vars = new HashMap<>();

        // This will keep parts of variables' names and their values during the string parsing.
        String currVarName = "";
        String currValue = "";

        for (int i = equationIndex - 1; i < strArgs.length(); i++) {
            if (Character.isLetter(strArgs.charAt(i))) {
                // Case -> this variable's name is negative.
                if (strArgs.charAt(i - 1) == '-')
                    currVarName += "-";
                currVarName += String.valueOf(strArgs.charAt(i));
            } else if (Character.isDigit(strArgs.charAt(i)) || strArgs.charAt(i) == '.') {
                // Case -> this variable's value is negative.
                if (strArgs.charAt(i - 1) == '-')
                    currValue += "-";
                currValue += String.valueOf(strArgs.charAt(i));
                // Case -> single entry is parsed.
                if (i == strArgs.length() - 1 || !Character.isDigit(strArgs.charAt(i + 1)) && strArgs.charAt(i + 1) != '.') {
                    vars.put(currVarName, Double.parseDouble(currValue));
                    currVarName = "";
                    currValue = "";
                }
            }
        }
        return vars;
    }

    /**
     * Retrieves the expression to be solved from the input.
     *
     * @param strArgs Input string with whitespaces removed.
     * @param equationIndex First "=" symbol index in the input string.
     * @return The expression separated from possible variables definitions.
     */
    private static String getFormula(String strArgs, int equationIndex) {
        // This will only contain the expression.
        StringBuilder formula = new StringBuilder();
        for (int i = 0; i < equationIndex - 1; i++)
            formula.append(strArgs.charAt(i));

        // To get rid of the variable name negative sign which may go after last expression's symbol.
        if (formula.charAt(formula.length() - 1) == '-')
            formula.deleteCharAt(formula.length() - 1);
        return formula.toString();
    }

    /**
     * Preparation:
     * inserts variables' value in the expression if such exist,
     * removes double minuses, checks division by 0,
     * separates operands and operators from the formula,
     * Checks if input operators are supported.
     * Then executes math operations in a strict order:
     * raising to power, multiplication and division,
     * adding and subtraction, until only one operand remains.
     *
     * @param formula The expression separated from possible variables definitions.
     * @param vars The map with [variable=value] entries.
     * @return The solution of the formula.
     */
    private static double calculate(String formula, HashMap<String, Double> vars) {
        if (vars != null)
            formula = insertVars(formula, vars);

        formula = removeDoubleMinuses(formula);
        checkDivision(formula);

        // Thi will keep single operand's part during the formula's parsing.
        String operand = "";

        // Lists of all the operands and operators from the formula, respectively.
        ArrayList<Double> operands = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        for (int i = 0; i < formula.length(); i++) {
            if (isOperandPart(formula, i)) {
                operand += formula.charAt(i);
                if (isLastOperandPart(formula, i)) {
                    operands.add(Double.parseDouble(operand));
                    operand = "";
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
        return i > 0 && !(formula.charAt(i) == '-' && !Character.isDigit(formula.charAt(i - 1))) &&
                !(formula.charAt(i) == '+' && !Character.isDigit(formula.charAt(i - 1)));
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
     * Checks if the formula contains division by 0.
     * If so, prints warning and finishes the program.
     *
     * @param formula The expression separated from possible variables definitions.
     */
    private static void checkDivision(String formula) {
        if (formula.contains("/0")) {
            System.err.println("Division by zero!");
            System.exit(1);
        }
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
    private static void validateOperators(ArrayList<Character> operators) {
        if (!operators.stream().allMatch(character -> {
            ArrayList<Character> availableOperators = new ArrayList<>(Arrays.asList('+', '-', '/', '*', '^'));
            return availableOperators.contains(character);
        })) {
            System.err.println("Unexpected operators!");
            System.exit(1);
        }
    }

    /**
     * Converts sequence of "--" to "+" symbol.
     *
     * @param formula The expression with inserted possible variables values.
     * @return The expression with "--" sequences replaced with "+".
     */
    private static String removeDoubleMinuses(String formula) {
        StringBuilder lessMinuses = new StringBuilder(formula);

        for (int i = 0; i < lessMinuses.length() - 1; i++) {
            if (lessMinuses.charAt(i) == '-' && lessMinuses.charAt(i + 1) == '-') {
                lessMinuses.setCharAt(i, '+');
                lessMinuses.deleteCharAt(i + 1);
                i--;
            }
        }

        return lessMinuses.toString();
    }

    /**
     * Replaces variables names with their numeric values.
     *
     * @param formula The expression separated from possible variables definitions.
     * @param vars The map with [variable=value] entries.
     * @return The expression with inserted possible variables values.
     */
    private static String insertVars(String formula, HashMap<String, Double> vars) {
        StringBuilder completed = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {
            if (Character.isLetter(formula.charAt(i))) {
                // The value corresponding to the variable's name in the map.
                Double value = vars.get(String.valueOf(formula.charAt(i)));

                // If negative variable name is queried, value is inverted.
                if (value == null) {
                    value = -vars.get("-" + formula.charAt(i));
                }
                completed.append(value);
            } else {
                completed.append(formula.charAt(i));
            }
        }

        return completed.toString();
    }

    /**
     * Searches the first matching "=" symbol index used as separation point
     * between the expression and the variables' definitions.
     *
     * @param args Input string with whitespaces removed.
     * @return First "=" symbol index in the input line;
     *         0 if no variables are defined.
     */
    private static int getEquationIndex(String args) {
        for (int i = 0; i < args.length(); i++) {
            if (args.charAt(i) == '=')
                return i;
        }
        return 0;
    }
}