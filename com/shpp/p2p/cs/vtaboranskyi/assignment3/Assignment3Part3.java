package com.shpp.p2p.cs.vtaboranskyi.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * File: Assignment3Part3.java.
 * Program which receives two numbers
 * and raises first to the power of
 * second.
 * Specifications are in materials of level #3,
 * Task 3, Problem 3.
 */
public class Assignment3Part3 extends TextProgram {

    @Override
    public void run() {
        // Any number to be raised.
        double baseNumber = readDouble("Enter base number: ");
        // Any integer, value of the exponent.
        int exponentNumber = readInt("Enter exponent number: ");

        // First received number raised to the power of second.
        double raisedBaseNumber = raiseToPower(baseNumber, exponentNumber);
        println(baseNumber + " raised to " + exponentNumber + " is " + raisedBaseNumber);
    }

    /**
     * Compares base number to 0 and raises it to
     * the power of second in a respective way.
     * @param base The number to be raised to power.
     * @param exponent The value of the power.
     * @return The base number raised to power.
     */
    private double raiseToPower(double base, int exponent) {
        // Duplicates base number to save its initial value.
        double initBase = base;
        if (exponent == 0) {
            base = 1;
        } else if (exponent > 0) {
            base = raiseBase(base, exponent, initBase);
        } else {
            // As a^-n = 1 / a^n
            exponent = -exponent;
            base = 1 / raiseBase(base, exponent, initBase);
        }
        return base;
    }

    /**
     * Raises directly base number to
     * the power of second.
     * @param base The number to be raised, which != 0.
     * @param exponent The value of the power, positive.
     * @param initBase Duplication of the @param base.
     * @return The base number raised to power.
     */
    private double raiseBase(double base, int exponent, double initBase) {
        for (int i = 1; i < exponent; i++) {
            base *= initBase;
        }
        return base;
    }
}
