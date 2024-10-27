package com.shpp.p2p.cs.vtaboranskyi.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.util.ArrayList;

/**
 * File: Assignment5Part2.java.
 * Program which repeatedly receives two
 * digits lines and calculates their sum,
 * no matter how long they are.
 * Specifications are in materials of level #5,
 * Task 5, Problem 2.
 */
public class Assignment5Part2 extends TextProgram {

    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number: ");
            String n2 = readLine("Enter second number: ");
            // Checks if both lines only contain digits.
            if (n1.chars().allMatch(Character::isDigit) &&
                    n2.chars().allMatch(Character::isDigit)) {
                println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
                println();
            } else {
                println("Numbers must be positive integers!");
            }
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        /*
        * Define the longer and the smaller strings.
        * If these have equal length it doesn't badly affect
        * the algorithm: by default n1 is smaller and n2 is longer.
        */
        String longerNum = getLongerNum(n1, n2);
        String smallerNum = getSmallerNum(n1, n2);

        // The difference between lengths of given strings.
        int lengthDifference = longerNum.length() - smallerNum.length();
        if (lengthDifference != 0) {
            smallerNum = prefillSpaces(smallerNum, lengthDifference);
        }

        // Digits of the strings sum value put in the list in correct order.
        ArrayList<Integer> sum = makeSumList(longerNum, smallerNum);
        return reverseSum(sum);
    }

    /**
     * Creates a list of digits of the strings sum value:
     * starting from the end calculates sum of 2 symmetric
     * digits of the strings (with same indexes), increasing
     * every next grade if needed. Empty spaces from the
     * smaller string (if such exist) aren't considered.
     * At the end creates a new grade if needed.
     * @param longerNum The longer digits string.
     * @param smallerNum The smaller digits string.
     * @return A reversed list of digits of strings' sum.
     */
    private ArrayList<Integer> makeSumList(String longerNum, String smallerNum) {
        ArrayList<Integer> sum = new ArrayList<>();

        // The sum of previous symmetric pair of digits.
        int prevSum = 0;

        for (int i = 0; i < longerNum.length(); i++) {
            char firstChar = longerNum.charAt(longerNum.length() - 1 - i);
            char secondChar = smallerNum.charAt(smallerNum.length() - 1 - i);
            // Checks if smaller string's symbol is digit or an empty space.
            int charsSum = Character.isDigit(secondChar) ? (firstChar - '0') + (secondChar - '0') : firstChar - '0';

            if (prevSum >= 10)
                charsSum++;

            sum.add(charsSum >= 10 ? charsSum - 10 : charsSum);
            prevSum = charsSum;
        }

        // Creates new grade.
        if (prevSum >= 10)
            sum.add(1);

        return sum;
    }

    /**
     * Reverses a list of digits and puts
     * new sequence into a string.
     * @param sum A reversed list of digits of strings' sum.
     * @return A string of digits of initial numbers' sum in correct order.
     */
    private String reverseSum(ArrayList<Integer> sum) {
        String result = "";
        for (int i = sum.size() - 1; i >= 0; i--) {
            result += sum.get(i);
        }
        return result;
    }

    /**
     * Moves smaller number's digits so that
     * both number's last digits have same index
     * (their lengths become equal)
     * and fills the beginning of the smaller
     * number with empty spaces.
     * @param smallerNum The smaller digits string.
     * @param lengthDiff The difference between strings' lengths.
     * @return The smaller number with spaces at the beginning.
     */
    private String prefillSpaces(String smallerNum, int lengthDiff) {
        for (int i = 0; i < lengthDiff; i++) {
            smallerNum = " " + smallerNum;
        }
        return smallerNum;
    }

    /**
     * Defines the smaller digits string of two.
     * By default, 2nd string becomes "smaller".
     * @param n1 The first number.
     * @param n2 The second number.
     * @return The smaller string.
     */
    private String getSmallerNum(String n1, String n2) {
        return n2.length() < n1.length() ? n2 : n1;
    }

    /**
     * Defines the longer digits string of two.
     * By default, 1st string becomes "longer".
     * @param n1 The first number.
     * @param n2 The second number.
     * @return The longer string.
     */
    private String getLongerNum(String n1, String n2) {
        return n1.length() > n2.length() ? n1 : n2;
    }

}