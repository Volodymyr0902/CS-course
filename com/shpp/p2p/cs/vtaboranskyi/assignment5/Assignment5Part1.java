package com.shpp.p2p.cs.vtaboranskyi.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * File: Assignment5Part1.java.
 * Program which repeatedly receives words
 * and shows how many syllables it consists of.
 * Specifications are in materials of level #5,
 * Task 5, Problem 1.
 */
public class Assignment5Part1 extends TextProgram {

    // List of all English vowels.
    public static final ArrayList<Character> VOWELS = new ArrayList<>(Arrays.asList('a', 'o', 'u', 'e', 'i', 'y'));

    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        // As list of vowels only includes lower case letters.
        word = word.toLowerCase();

        // Initial number of syllables in the word.
        int syllablesCounter = 0;

        for (int i = 0; i < word.length(); i++) {
            if (VOWELS.contains(word.charAt(i)) &&
                    !vowelBefore(word, i) &&
                    !isLastE(word, i)) {
                syllablesCounter++;
            }
        }

        // In case at least 1 vowel was found.
        if (syllablesCounter > 0) {
            return syllablesCounter;
        }

        // In case no vowels are found.
        return 1;
    }

    /**
     * Checks if actual letter in the word (not the first one)
     * doesn't have a vowel previous neighbour.
     *
     * @param word              A string containing a single word.
     * @param actualLetterIndex The index of the letter processed.
     * @return True if the previous letter is vowel.
     */
    private boolean vowelBefore(String word, int actualLetterIndex) {
        return actualLetterIndex > 0 && VOWELS.contains(word.charAt(actualLetterIndex - 1));
    }

    /**
     * Checks if the letter in process is either
     * the last one or is 'e'.
     *
     * @param word              A string containing a single word.
     * @param actualLetterIndex The index of the letter processed.
     * @return True if the letter in process is either
     * the last one or is 'e'.
     */
    private boolean isLastE(String word, int actualLetterIndex) {
        return actualLetterIndex == word.length() - 1 && word.charAt(actualLetterIndex) == 'e';
    }
}
