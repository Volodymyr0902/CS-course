package com.shpp.p2p.cs.vtaboranskyi.assignment1;

/*
 * File: Assignment1Part3.java.
 *
 * Program in which Karel puts a beeper
 * on the Southern central cell.
 *
 */
public class Assignment1Part3 extends SuperKarel {
    @Override
    public void run() throws Exception {
        findCenter();
    }

    /*
     * Makes Karel fill the Southern line with beepers
     * except first and the last ones, then remove all
     * of them and finally put a beeper on the central cell.
     *
     * Background: Karel is facing East in the Northwest
     * corner of the world with no beepers and walls.
     *
     * Result: Karel is facing East/West on the central cell
     * of the same line with a beeper on this cell.
     */
    public void findCenter() throws Exception {
        fillLinePartWithBeepers();
        removeAllBeepers();
        putBeeperOnCenter();
    }

    /*
     * Makes Karel fill the Southern line with beepers except
     * the first and the last cells.
     *
     * Background: Karel is facing East on the Western cell
     * of a line with no beepers on it yet.
     *
     * Result: Karel is on the Eastern cell of the actual beepers
     * line facing West.
     */
    public void fillLinePartWithBeepers() throws Exception {
        // Moves Karel on the first cell of future beepers line
        enterBeepersLine();
        while (frontIsClear()) {
            putBeeper();
            move();
        }
        // Moves Karel back to the beepers line
        turnAround();
        enterBeepersLine();
    }

    /*
     * Moves Karel one cell forward. The method is used to
     * move Karel on the Western or the Eastern cell of the
     * beepers line.
     *
     * Background: Karel is right to the edge of the beepers line
     * facing it.
     *
     * Result: Karel is on the cell next to the one he was on
     * in the direction he was facing before
     * the method was called.
     */
    public void enterBeepersLine() throws Exception {
        if (frontIsClear()) {
            move();
        }
    }

    /*
     * Makes Karel remove all put beepers
     */
    public void removeAllBeepers() throws Exception {
        findFurthestBeeper();
        // Karel removes this furthest beeper
        if (beepersPresent()) {
            pickBeeper();
        }
        checkNearBeeper();
        // If the beeper was found he repeats looking for the furthest.
        if (beepersPresent()) {
            removeAllBeepers();
        }
    }

    /*
     * Makes Karel find the beeper furthest to him in the
     * beepers line.
     *
     * Background: Karel is on the edge of the
     * possible beepers line facing the beeper he'll go to.
     */
    public void findFurthestBeeper() throws Exception {
        while (beepersPresent()) {
            move();
        }
        // Now Karel is on the opposite side of the beepers line,
        // the last beepers is behind him.
        turnAround();
        enterBeepersLine();
        // Now Karel is o the cell where this las beeper is.
    }


    /*
     * Makes Karel check if there are beepers on the cell he's facing,
     * he remembers there is none of them on the cell behind him.
     * If there is none of them Karel is on the central cell of the
     * Southern line.
     *
     * Background: Karel is on a beeper, there is none behind him
     * but some may be in front of him.
     *
     * Result: Karel is on the cell next to the one he was on
     * in the direction he was facing before
     * the method was called.
     */
    public void checkNearBeeper() throws Exception {
        enterBeepersLine();
    }

    /*
     * Makes Karel enter central cell and put a beeper on it.
     *
     * Background: Karel has collected all the beepers and is
     * on a cell right/left to the central, which is behind him.
     *
     * Result: Karel is on the central cell of the Southern
     * line with a beeper on it.
     */
    public void putBeeperOnCenter() throws Exception {
        turnAround();
        enterBeepersLine();
        putBeeper();
    }

}