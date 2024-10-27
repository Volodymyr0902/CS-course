package com.shpp.cs.a.lectures.lec15;

import java.util.*;

public class CouponsCollector {
    public static void main(String[] args) {
        Random r = new Random();
        HashMap<Integer, Integer> dice = new HashMap<>();
        fillDice(dice);
        int attemptsNum = 0;
        int[] roundsResults = new int[100];

        for (int i = 0; i < 100; i++) {
            while (dice.containsValue(0)) {
                rollDice(dice, r);
                attemptsNum++;
                System.out.println(dice);
            }
            System.out.println("This times it took " + attemptsNum + " attempts to get all numbers at least once.");
            fillDice(dice);
            roundsResults[i] = attemptsNum;
            attemptsNum = 0;
        }

        OptionalDouble averageIndex = Arrays.stream(roundsResults).average();
        OptionalInt maxFlipsNum = Arrays.stream(roundsResults).max();
        System.out.println("Average index is " + averageIndex.getAsDouble());
        System.out.println("Max flips number is " + maxFlipsNum.getAsInt());
    }

    private static void fillDice(HashMap<Integer, Integer> dice) {
        for (int i = 1; i < 7; i++) {
            dice.put(i, 0);
        }
    }

    private static void rollDice(HashMap<Integer, Integer> dice, Random r) {
        int rollResult = r.nextInt(1, 7);
        int oldValue = dice.get(rollResult);
        dice.replace(rollResult, ++oldValue);
    }
}
