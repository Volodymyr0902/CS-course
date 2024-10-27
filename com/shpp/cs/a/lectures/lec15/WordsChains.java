package com.shpp.cs.a.lectures.lec15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordsChains {

    public static final String VOCAB_LINK = "en-dictionary.txt";
    public static final Random r = new Random();

    public static void main(String[] args) {
        ArrayList<String> vocab = readVocab();
        while (true) {
            String input = askWord();
            ArrayList<String> matches;
            while (isWord(input) && !(matches = findMatches(vocab, input)).isEmpty()) {
                String randomMatch = matches.size() > 1 ? matches.get(r.nextInt(matches.size() - 1)) : matches.getFirst();
                System.out.println(randomMatch.toUpperCase());
                input = randomMatch;
                matches.clear();
            }
            System.out.println();
        }
    }

    private static boolean isWord(String input) {
        return input.length() > 1 && input.matches("[a-zA-Z]+");
    }

    private static ArrayList<String> findMatches(ArrayList<String> vocab, String input) {
        ArrayList<String> matches = new ArrayList<>();
        for (String vocabWord : vocab)
            if (vocabWord.startsWith(input.substring(input.length() - 2)))
                matches.add(vocabWord);
        return matches;
    }

    private static String askWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter starting word: ");
        return scanner.nextLine().toUpperCase();
    }

    private static ArrayList<String> readVocab() {
        ArrayList<String> vocab = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(VOCAB_LINK));
            String vocabWord = "";
            while ((vocabWord = br.readLine()) != null) {
                vocab.add(vocabWord.toUpperCase());
            }
        } catch (IOException e) {
            System.out.println("Failed to read the vocabulary");
        }
        return vocab;
    }
}
