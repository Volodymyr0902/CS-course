package com.shpp.p2p.cs.vtaboranskyi.assignment2;

import com.shpp.cs.a.console.TextProgram;

/**
 * File Assignment2Part1.java.
 * A console program which receives 3 number
 * and gives roots of its quadratic equation.
 * Specifications are in materials of level #2,
 * Task 2, Problem 1.
 */
public class Assignment2Part1 extends TextProgram {

    @Override
    public void run() {
        double a = readDouble("Please enter a: ");
        double b = readDouble("Please enter b: ");
        double c = readDouble("Please enter c: ");
        solveQuadraticEquation(a, b, c);
    }

    /**
     * Method finds discriminant based on received numbers,
     * then its square root and roots of the quadratic equation.
     *
     * @param a The first input value
     * @param b The second input value
     * @param c The third input value
     */
    private void solveQuadraticEquation(double a, double b, double c) {
        // Math formula is below
        double discrim = (b * b) - 4 * a * c;
        double squareRootOfDiscrim = Math.sqrt(discrim);
        findRootsOfEquation(a, b, discrim, squareRootOfDiscrim);
    }

    /**
     * If @param a doesn't have value of 0, method compares
     * the discriminant to 0, calculates roots of
     * quadratic equation if those exist and types
     * relevant result in the console.
     *
     * @param a The first input value
     * @param b The second input value
     * @param discrim The discriminant of actual quadratic equation
     * @param sqRt The discriminant's square root
     */
    private void findRootsOfEquation(double a, double b, double discrim, double sqRt) {
        if (a != 0) {
            if (discrim > 0) {
                double x1 = (-b + sqRt) / (2 * a);
                double x2 = (-b - sqRt) / (2 * a);
                println("There are two roots: " + x1 + " and " + x2);
            } else if (discrim == 0) {
                double x = (-b + sqRt) / 2 * a;
                println("There is one root: " + x);
            } else {
                println("There are no real roots");
            }
        } else {
            println("A must not equal 0");
        }
    }
}
