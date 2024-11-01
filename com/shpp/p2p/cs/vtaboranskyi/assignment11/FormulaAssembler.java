package com.shpp.p2p.cs.vtaboranskyi.assignment11;

import java.util.HashMap;

public class FormulaAssembler {

    protected static String assemble(String[] args) throws ValidationException {
        checkNoData(args);

        // To simplify next "=" searching.
        String strArgs = String.join("", args);
        strArgs = removeSpaces(strArgs);

        // First "=" symbol index in the input string.
        int equationIndex = getEquationIndex(strArgs);

        // Formula and variables are separated.
        String formula = equationIndex != 0 ? getFormula(strArgs, equationIndex) : strArgs;
        HashMap<String, Double> vars = equationIndex != 0 ? getVars(strArgs, equationIndex) : null;

        checkVarsDefined(formula, vars);
        formula = insertVars(formula, vars);
        formula = removeDoubleMinuses(formula);
        checkDivision(formula);
        return formula;
    }

    private static void checkNoData(String[] args) throws ValidationException {
        if (args == null || args.length == 0 || args.length == 1 && (args[0] == null || args[0].isEmpty()))
            throw new ValidationException("No data found!");
    }

    /**
     * Checks if all variables mentioned in the expression are defined next to it.
     *
     * @param formula The expression to be solved.
     * @param vars    Variables and their values the expression may contain.
     */
    private static void checkVarsDefined(String formula, HashMap<String, Double> vars) throws ValidationException {
        // This will contain letters symbols the formula may have.
        StringBuilder varsInFormula = new StringBuilder();
        for (int i = 0; i < formula.length(); i++)
            if (isCharVar(formula, i))
                varsInFormula.append(formula.charAt(i));


        // In case the formula contains variables positive and negative matches are checked.
        if (!varsInFormula.isEmpty())
            for (int i = 0; i < varsInFormula.length(); i++)
                if (vars.get(String.valueOf(varsInFormula.charAt(i))) == null &&
                        vars.get("-" + varsInFormula.charAt(i)) == null)
                    throw new ValidationException("Variables aren't defined!");
    }

    /**
     * Removes all the whitespaces from the input line.
     *
     * @param spacesPossible Original input represented as a string.
     * @return Input string with whitespaces removed.
     */
    private static String removeSpaces(String spacesPossible) {
        // This will contain same string but with no spaces.
        StringBuilder spacesRemoved = new StringBuilder();
        for (int i = 0; i < spacesPossible.length(); i++)
            if (spacesPossible.charAt(i) != ' ')
                spacesRemoved.append(spacesPossible.charAt(i));

        return spacesRemoved.toString();
    }

    /**
     * Retrieves all the variables and their values
     * from the input, if such exist.
     *
     * @param strArgs       Input string with whitespaces removed.
     * @param equationIndex First "=" symbol index in the input string.
     * @return Map with [variable=value] entries.
     */
    private static HashMap<String, Double> getVars(String strArgs, int equationIndex) {
        // This will contain entries.
        HashMap<String, Double> vars = new HashMap<>();

        // This will keep parts of variables' names and their values during the string parsing.
        StringBuilder currVarName = new StringBuilder();
        StringBuilder currValue = new StringBuilder();

        for (int i = equationIndex - 1; i < strArgs.length(); i++) {
            if (Character.isLetter(strArgs.charAt(i))) {
                // Case -> this variable's name is negative.
                if (strArgs.charAt(i - 1) == '-')
                    currVarName.append("-");
                currVarName.append(strArgs.charAt(i));
            } else if (Character.isDigit(strArgs.charAt(i)) || strArgs.charAt(i) == '.') {
                // Case -> this variable's value is negative.
                if (strArgs.charAt(i - 1) == '-')
                    currValue.append("-");
                currValue.append(strArgs.charAt(i));
                // Case -> single entry is parsed.
                if (i == strArgs.length() - 1 || !Character.isDigit(strArgs.charAt(i + 1)) && strArgs.charAt(i + 1) != '.') {
                    vars.put(currVarName.toString(), Double.parseDouble(currValue.toString()));
                    currVarName = new StringBuilder();
                    currValue = new StringBuilder();
                }
            }
        }
        return vars;
    }

    /**
     * Retrieves the expression to be solved from the input.
     *
     * @param strArgs       Input string with whitespaces removed.
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
     * Searches the first matching "=" symbol index used as separation point
     * between the expression and the variables' definitions.
     *
     * @param args Input string with whitespaces removed.
     * @return First "=" symbol index in the input line;
     * 0 if no variables are defined.
     */
    private static int getEquationIndex(String args) {
        for (int i = 0; i < args.length(); i++)
            if (args.charAt(i) == '=')
                return i;

        return 0;
    }

    /**
     * Checks if the formula contains division by 0.
     * If so, prints warning and finishes the program.
     *
     * @param formula The expression separated from possible variables definitions.
     */
    private static void checkDivision(String formula) throws ValidationException {
        if (formula.contains("/0"))
            throw new ValidationException("Division by zero!");
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
     * @param vars    The map with [variable=value] entries.
     * @return The expression with inserted possible variables values.
     */
    private static String insertVars(String formula, HashMap<String, Double> vars) {
        if (vars != null) {
            StringBuilder completed = new StringBuilder();

            for (int i = 0; i < formula.length(); i++) {
                if (isCharVar(formula, i)) {
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
        } else {
            return formula;
        }
    }

    protected static boolean isCharVar(String formula, int index) {
        boolean noLetterNumberBefore = index == 0 ||
                !Character.isDigit(formula.charAt(index - 1)) && !Character.isLetter(formula.charAt(index - 1));
        boolean noLetterNumberAfter = index == formula.length() - 1 ||
                !Character.isDigit(formula.charAt(index + 1)) && !Character.isLetter(formula.charAt(index + 1));
        return Character.isLetter(formula.charAt(index)) && noLetterNumberBefore && noLetterNumberAfter;
    }
}
