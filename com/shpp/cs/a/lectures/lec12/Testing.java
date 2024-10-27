package com.shpp.cs.a.lectures.lec12;

import java.util.ArrayList;
import java.util.Arrays;

public class Testing {

    public static void main(String[] args) {
        System.out.println(syllablesInWord("lalelilolulyl"));

        String text = "helloworld";
        String cut = text.substring(1);
        System.out.println(cut);
    }

    /**
     * Given a string representing a single word, estimates the number of syllables
     * in that word by counting the number of groups of vowels, except for isolated
     * e's at the end of the word.
     *
     * @param word The word in question
     * @return An estimate of the number of syllables as described above.
     */
    public static int syllablesInWord(String word) {
        ArrayList<Character> vowels = new ArrayList<>(Arrays.asList('a', 'o', 'u', 'e', 'i'));
        int syllablesCounter = 0;

        for (int i = 0; i < word.length(); i++) {
            if (vowels.contains(word.charAt(i))) {
                syllablesCounter++;
            }
            if (word.charAt(i) != word.charAt(word.length() - 1) &&
                    word.charAt(i) == word.charAt(i + 1)) {
                i++;
            }
        }

        if (word.charAt(word.length() -1) == 'e')
            syllablesCounter--;

        return syllablesCounter;
    }


    /**
     * Given two numeric strings (strings consisting purely of digits) representing two
     * numbers n1 and n2, returns a numeric string representing their sum. The input
     * strings don't have to be the same length, but each will represent a nonnegative
     * integer.
     *
     * @param n1 The string encoding of the first number
     * @param n2 The string encoding of the second number.
     * @return A string encoding of their sum.
     */
    //private String addNumericStrings(String n1, String n2)


    /**
     * Given as input a string of three letters and a list of strings, returns all
     * words in English that match that word according to the rules of the “license
     * plate game” (that is, all words that contain all of the letters in the string
     * 'letters' in the order in which they appear).
     *
     * @param letters A string of three letters.
     * @param allWords A list of all the strings to test.
     * @return A list of all the English words matching the given letter patter.
     */
    //private ArrayList<String> allMatchesFor(String letters, ArrayList<String> allWords)


    /**
     * Tokenizes the input CSV file line and returns all the fields in that line.
     *
     * @param line A line from a CSV file.
     * @return A list of all the tokens in that line, with any external quotation marks
     * removed.
     */
    //private ArrayList<String> fieldsIn(String line)
}
