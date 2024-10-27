package com.shpp.p2p.cs.vtaboranskyi.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * File: Assignment3Part2.java.
 * Program which receives user data and
 * if it's an integer greater than 0, checks
 * whether it's even or odd, does
 * calculations and repeats from last step
 * until it equals 1.
 * Specifications are in materials of level #3,
 * Task 3, Problem 2.
 */
public class Assignment3Part2 extends TextProgram {

    @Override
    public void run() {
        try {
            // User data
            int userNumber = readInt("Enter a number: ");
            checkNumber(userNumber);
        } catch (Exception e) {
            // In case user data type is not integer
            println("Input number must be an integer!");
        }
    }

    /**
     * Checks if user number is greater than 0,
     * if so proceeds calculation, else
     * shows the warning.
     * @param num An integer received from user
     */
    private void checkNumber(int num) {
        if (num > 0) {
            calcToOne(num);
            println("The end!");
        } else {
            println("Input number must be greater than 0!");
        }
    }

    /**
     * Changes user number value until
     * it equals 1.
     * @param num A user's integer greater than 0.
     */
    private void calcToOne(int num) {
        do {
            num = refactorNum(num);
        } while (num != 1);
    }

    /**
     * Reports the changes based on the integer's
     * actual value (even or odd),
     * assigns new value to it and returns it.
     * @param num Any integer greater than 0
     * @return Refactored integer
     */
    private int refactorNum(int num) {
        if (isNumberEven(num)) {
            println(num + " is even so I take half: " + num / 2);
            num /= 2;
        } else {
            println(num + " is odd so I make 3n + 1: " + (num * 3 + 1));
            num = num * 3 + 1;
        }
        return num;
    }

    /**
     * Checks if number is even.
     * @param num Any integer greater than 0.
     * @return True if the remainder of dividing the number by 2
     * equals 0, false otherwise.
     */
    private boolean isNumberEven(int num) {
        return num % 2 == 0;
    }
}
