package com.shpp.p2p.cs.vtaboranskyi.assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// decompose
// comments

public class Assignment10Part1 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No data found!");
            return;
        }

        String strArgs = String.join("", args);
        strArgs = removeSpaces(strArgs);
        int equationIndex = getEquationIndex(strArgs);

        String formula = equationIndex != 0 ? getFormula(strArgs, equationIndex) : strArgs;
        HashMap<String, Double> vars = equationIndex != 0 ? getVars(strArgs, equationIndex) : null;

        checkVarsDefined(formula, vars);
        double result = calculate(formula, vars);
        System.out.println("Result for " + Arrays.toString(args) + " is: " + result);
    }

    private static void checkVarsDefined(String formula, HashMap<String, Double> vars) {
        StringBuilder varsInFormula = new StringBuilder();
        for (int i = 0; i < formula.length(); i++) {
            if (Character.isLetter(formula.charAt(i)))
                varsInFormula.append(formula.charAt(i));
        }

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

    private static String removeSpaces(String spacesPossible) {
        String spacesRemoved = "";
        for (int i = 0; i < spacesPossible.length(); i++) {
            if (spacesPossible.charAt(i) != ' ') {
                spacesRemoved += spacesPossible.charAt(i);
            }
        }
        return spacesRemoved;
    }

    private static HashMap<String, Double> getVars(String strArgs, int equationIndex) {
        HashMap<String, Double> vars = new HashMap<>();
        String currVarName = "";
        String currValue = "";

        for (int i = equationIndex - 1; i < strArgs.length(); i++) {
            if (Character.isLetter(strArgs.charAt(i))) {
                if (strArgs.charAt(i - 1) == '-')
                    currVarName += "-";
                currVarName += String.valueOf(strArgs.charAt(i));
            } else if (Character.isDigit(strArgs.charAt(i)) || strArgs.charAt(i) == '.') {
                if (strArgs.charAt(i - 1) == '-')
                    currValue += "-";
                currValue += String.valueOf(strArgs.charAt(i));
                if (i == strArgs.length() - 1 || !Character.isDigit(strArgs.charAt(i + 1)) && strArgs.charAt(i + 1) != '.') {
                    vars.put(currVarName, Double.parseDouble(currValue));
                    currVarName = "";
                    currValue = "";
                }
            }
        }
        return vars;
    }

    private static String getFormula(String strArgs, int equationIndex) {
        StringBuilder formula = new StringBuilder();
        for (int i = 0; i < equationIndex - 1; i++) {
            formula.append(strArgs.charAt(i));
        }
        if (formula.charAt(formula.length() - 1) == '-')
            formula.deleteCharAt(formula.length() - 1);
        return formula.toString();
    }

    private static double calculate(String formula, HashMap<String, Double> vars) {
        if (vars != null)
            formula = insertVars(formula, vars);

        formula = removeDoubleMinuses(formula);
        checkDivision(formula);

        String operand = "";
        ArrayList<Double> operands = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        for (int i = 0; i < formula.length(); i++) {
            if (Character.isDigit(formula.charAt(i)) || formula.charAt(i) == '.' ||
                    formula.charAt(i) == '-' && (i == 0 || !Character.isDigit(formula.charAt(i - 1)))) {
                operand += formula.charAt(i);
                if (i == formula.length() - 1 || !Character.isDigit(formula.charAt(i + 1)) && formula.charAt(i + 1) != '.') {
                    operands.add(Double.parseDouble(operand));
                    operand = "";
                }
            } else if (i > 0 && !(formula.charAt(i) == '-' && !Character.isDigit(formula.charAt(i - 1))) &&
                    !(formula.charAt(i) == '+' && !Character.isDigit(formula.charAt(i - 1)))) {
                operators.add(formula.charAt(i));
            }
        }

        validateOperators(operators);
        raiseToPower(operands, operators);
        multiplyAndDivide(operators, operands);
        addAndSubtract(operators, operands);

        return operands.getFirst();
    }

    private static void checkDivision(String formula) {
        if (formula.contains("/0")) {
            System.err.println("Division by zero!");
            System.exit(1);
        }
    }

    private static void addAndSubtract(ArrayList<Character> operators, ArrayList<Double> operands) {
        while (!operators.isEmpty()) {
            if (operators.getFirst() == '+') {
                double sum = operands.getFirst() + operands.get(1);
                operands.set(1, sum);
            } else {
                double remainder = operands.getFirst() - operands.get(1);
                operands.set(1, remainder);
            }
            operands.removeFirst();
            operators.removeFirst();
        }
    }

    private static void multiplyAndDivide(ArrayList<Character> operators, ArrayList<Double> operands) {
        while (operators.contains('*') || operators.contains('/')) {
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '*') {
                    int operatorIndex = operators.indexOf('*');
                    double product = operands.get(operatorIndex) * operands.get(operatorIndex + 1);
                    operands.set(operatorIndex, product);
                    operands.remove(operatorIndex + 1);
                    operators.remove(operatorIndex);
                    break;
                } else if (operators.get(i) == '/') {
                    int operatorIndex = operators.indexOf('/');
                    double remainder = operands.get(operatorIndex) / operands.get(operatorIndex + 1);
                    operands.set(operatorIndex, remainder);
                    operands.remove(operatorIndex + 1);
                    operators.remove(operatorIndex);
                    break;
                }
            }
        }
    }

    private static void raiseToPower(ArrayList<Double> operands, ArrayList<Character> operators) {
        while (operators.contains('^')) {
            int operatorIndex = operators.indexOf('^');
            double raised = Math.pow(operands.get(operatorIndex), operands.get(operatorIndex + 1));
            operands.set(operatorIndex, raised);
            operands.remove(operatorIndex + 1);
            operators.remove(operatorIndex);
        }
    }

    private static void validateOperators(ArrayList<Character> operators) {
        if (!operators.stream().allMatch(character -> {
            ArrayList<Character> availableOperators = new ArrayList<>(Arrays.asList('+', '-', '/', '*', '^'));
            return availableOperators.contains(character);
        })) {
            System.err.println("Unexpected operators!");
            System.exit(1);
        }
    }

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

    private static String insertVars(String formula, HashMap<String, Double> vars) {
        StringBuilder completed = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {
            if (Character.isLetter(formula.charAt(i))) {
                Double value = vars.get(String.valueOf(formula.charAt(i)));
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

    private static int getEquationIndex(String args) {
        for (int i = 0; i < args.length(); i++) {
            if (args.charAt(i) == '=')
                return i;
        }
        return 0;
    }
}






//        // distinguish operands and operators
//        for (int i = 0; i < formula.length(); i++) {
//            if (Character.isDigit(formula.charAt(i)) || formula.charAt(i) == '.' ||
//                    formula.charAt(i) == '-' && (i == 0 || !Character.isDigit(formula.charAt(i - 1)))) {
//                operand += formula.charAt(i);
//                if (i == formula.length() - 1 || !Character.isDigit(formula.charAt(i + 1)) && formula.charAt(i + 1) != '.') {
//                    operands.add(Double.parseDouble(operand));
//                    operand = "";
//                }
//            } else if (i > 0 && !(formula.charAt(i) == '-' && !Character.isDigit(formula.charAt(i - 1))) &&
//                    !(formula.charAt(i) == '+' && !Character.isDigit(formula.charAt(i - 1)))) {
//                operators.add(formula.charAt(i));
//            }
//        }