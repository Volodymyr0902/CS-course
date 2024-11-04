package com.shpp.p2p.cs.vtaboranskyi.assignment11;

import java.util.Arrays;

/**
 * File: Assignment11Part1.java.
 * Program which receives a math expression and (optionally) variables it may contain
 * and calculates its result. Typical operations ("+", "-", "*", "/", "^"), brackets ("(", ")")
 * and functions (sin, cos, tan, atan, log2, log10, sqrt) are supported.
 * Numbers, variables and their names may be negative.
 * Multiplication symbol "*" must be screened if program is invoked via command line.
 * All brackets contain an expression must be opened and closed.
 * Specifications are in materials of level #11, Task "Advanced calculator".
 */
public class Assignment11Part1 {
    public static void main(String[] args) {
        try {
            // The solution to the input expression.
            double result = Calculator.calculate(FormulaAssembler.assemble(args));
            System.out.println("Result for " + Arrays.toString(args) + " is: " + result);
        } catch (ValidationException e) {
            e.printCause();
        }
    }
}
