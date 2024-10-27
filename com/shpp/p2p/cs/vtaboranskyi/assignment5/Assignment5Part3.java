package com.shpp.p2p.cs.vtaboranskyi.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * File: Assignment5Part3.java.
 * Program which reads the English dictionary;
 * then  asks user for any 3 letters and shows
 * list of words from the dictionary which contain
 * all these letters in the received order but
 * other letters may be between those the program
 * is looking for.
 * Specifications are in materials of level #5,
 * Task 5, Problem 3.
 */
public class Assignment5Part3 extends TextProgram {

    // Link to the file containing words.
    public static final String DICTIONARY_FILE = "en-dictionary.txt";

    @Override
    public void run() {
        // List of all the words from the dictionary.
        ArrayList<String> words = readDictionary();
        while (!words.isEmpty()) {
            // Case is ignored.
            String inputLetters = readLine("\nEnter 3 letters: ").toLowerCase();
            getMatchingWords(inputLetters, words);
        }
    }

    /**
     * If input data is correct, shows list of words containing
     * all 3 letters in the correct order, if such words exist.
     * @param inputLetters Three letters in lower case.
     * @param words List of words from the dictionary.
     */
    private void getMatchingWords(String inputLetters, ArrayList<String> words) {
        if (areThreeLetters(inputLetters)) {
            ArrayList<String> matches = makeMatchesList(inputLetters, words);
            if (matches.isEmpty()) {
                println("No matches are found!");
            } else {
                showMatchesList(matches);
            }
        } else {
            println("Symbols must be 3 and all letters!");
        }
    }

    /**
     * Checks if input string is only letters of length 3.
     * @param inputLetters Three letters in lower case.
     * @return True if conditions are confirmed.
     */
    private boolean areThreeLetters(String inputLetters) {
        return inputLetters.chars().allMatch(Character::isLetter) && inputLetters.length() == 3;
    }

    /**
     * Shows list of words matching letters
     * in the correct order in the console.
     * @param matches List of words contain input letters in correct order.
     */
    private void showMatchesList(ArrayList<String> matches) {
        println("Managed to find " + matches.size() + " matches:");
        for (int i = 0; i < matches.size(); i++) {
            println(i + 1 + ". " + matches.get(i));
        }
    }

    /**
     * Goes through the list of the dictionary words
     * and saving them to finally show.
     * @param inputLetters Three letters in lower case.
     * @param words List of words from the dictionary.
     * @return List of words contain input letters in correct order.
     */
    private ArrayList<String> makeMatchesList(String inputLetters, ArrayList<String> words) {
        ArrayList<String> matches = new ArrayList<>();

        // Input letters are divided into separate strings for readability.
        String letter1 = String.valueOf(inputLetters.charAt(0));
        String letter2 = String.valueOf(inputLetters.charAt(1));
        String letter3 = String.valueOf(inputLetters.charAt(2));

        for (String word : words) {
            addMatchingWord(word, letter1, letter2, letter3, matches);
        }

        return matches;
    }

    /**
     * Checks if actual word from the dictionary matches
     * all the requirements: firstly looks up for the first
     * input letter and if such was found looks up for the
     * second one in the word part after 1st letter. If
     * 2nd one was also found looks up for the 3rd one in
     * the word part after the 2nd letter. If the last
     * letter is also found, adds word to the list.
     * @param word Any word from the dictionary.
     * @param letter1 First input letter.
     * @param letter2 Second input letter.
     * @param letter3 Third input letter.
     * @param matches List of words contain input letters in correct order.
     */
    private void addMatchingWord(String word, String letter1, String letter2, String letter3, ArrayList<String> matches) {
        // Unchecked part of actual word.
        String cutWord;
        if (word.contains(letter1)) {
            cutWord = word.substring(word.indexOf(letter1) + 1);
            if (cutWord.contains(letter2)) {
                cutWord = cutWord.substring(cutWord.indexOf(letter2) + 1);
                if (cutWord.contains(letter3)) {
                    matches.add(word);
                }
            }
        }
    }

    /**
     * Reads file with one word in a line
     * saving them in the list.
     * @return List of words from the file.
     */
    private ArrayList<String> readDictionary() {
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(DICTIONARY_FILE));
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word);
            }
        } catch (IOException e) {
            println("Failed to read the dictionary!");
        }
        return words;
    }
}
