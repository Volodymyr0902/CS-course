package com.shpp.p2p.cs.vtaboranskyi.assignment3;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

/**
 * File: Assignment3Part5.java.
 * Program in which a lucky player
 * flips coin until earns $20.
 * Specifications are in materials of level #3,
 * Task 3, Problem 5.
 */
public class Assignment3Part5 extends TextProgram {

    // The amount of money a lucky player has to earn to finish playing.
    public static final int LUCKY_PLAYER_SCOPE = 20;

    @Override
    public void run() {
        // The initial amount of money on lucky players' count.
        int luckyPlayersCount = 0;
        // The number of games lucky player had to play to reach the scope.
        int gamesCount = startPlaying(luckyPlayersCount);
        println("It took " + gamesCount + " games to earn $20");
    }

    /**
     * Makes lucky player play until their total earn >= $20
     * and reports the total earn. Increases the number of games
     * played with every iteration/game.
     * @param luckyPlayersCount The initial amount of money on lucky players' count.
     * @return The number of games lucky player had to play to earn >= $20.
     */
    private int startPlaying(int luckyPlayersCount) {
        // The initial number of games lucky player will play.
        int gamesCount = 0;

        while (luckyPlayersCount < LUCKY_PLAYER_SCOPE) {
            luckyPlayersCount += playGame();
            println("Your total is $" + luckyPlayersCount);
            gamesCount++;
        }
        return gamesCount;
    }

    /**
     * Makes lucky player play a single game.
     * Creates a generator to define the result
     * of coin flipping at every iteration.
     * Increases the bet by its actual value until
     * the result is tails and returns this value.
     * @return The amount of money earned during one game.
     */
    private int playGame() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        // True stands for heads and false for tails.
        boolean coinFlipResult = rgen.nextBoolean();
        // Initial amount of money put on the table.
        int bet = 1;

        while (coinFlipResult) {
            bet += bet;
            coinFlipResult = rgen.nextBoolean();
        }

        println("This game you earned $ " + bet);
        return bet;
    }
}
