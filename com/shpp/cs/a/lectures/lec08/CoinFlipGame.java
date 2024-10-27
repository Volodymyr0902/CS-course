package com.shpp.cs.a.lectures.lec08;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class CoinFlipGame extends TextProgram {

    int firstPlayerCoins = 5;
    int secondPlayerCoins = 5;
    public static final String PLAYER_1 = "John";
    public static final String PLAYER_2 = "Amy";

    @Override
    public void run() {

        int iters = 0;
        boolean firstFlips = true;
        while (firstPlayerCoins != 0 || secondPlayerCoins == 0) {
            println(PLAYER_1 + " has " + firstPlayerCoins + " coins and "
                    + PLAYER_2 + " has " + secondPlayerCoins + " coins.");

            if (firstFlips) {
                int[] stepResult = flipCoin(firstPlayerCoins, secondPlayerCoins);
                firstPlayerCoins = stepResult[0];
                secondPlayerCoins = stepResult[1];
            } else {
                int[] stepResult = flipCoin(secondPlayerCoins, firstPlayerCoins);
                firstPlayerCoins = stepResult[1];
                secondPlayerCoins = stepResult[0];
            }
            firstFlips = !firstFlips;
            iters++;
        }
        println(iters);
        println((firstPlayerCoins == 0 ? PLAYER_1 : PLAYER_2) + " wins!");
    }

    private int[] flipCoin(int actualPlayer, int anotherPlayer) {
        RandomGenerator rgen = RandomGenerator.getInstance();
        boolean x = rgen.nextBoolean();
        if (x) {
            actualPlayer -= 1;
            anotherPlayer += 1;
        }
        int[] newCount = {actualPlayer, anotherPlayer};
        return newCount;
    }

}