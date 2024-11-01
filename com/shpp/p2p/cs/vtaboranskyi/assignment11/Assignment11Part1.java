package com.shpp.p2p.cs.vtaboranskyi.assignment11;

// improve code style
// comments

import java.util.Arrays;

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
