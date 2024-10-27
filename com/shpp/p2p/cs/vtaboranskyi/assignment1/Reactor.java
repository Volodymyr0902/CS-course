package com.shpp.p2p.cs.vtaboranskyi.assignment1;

/*
 * File: Assignment1Part1.java
 * Program in which Karel clears nuclear reactor.
 * Specs are in section document #1.
 */
public class Reactor extends SuperKarel {
    @Override
    public void run() throws Exception {
        removeGarbage();
    }

    /*
     * Checks if the cell Karel is on doesn't have beepers on it and walls
     * on the right and on the left and if so moves Karel to these sides to
     * remove beepers from them; then if there is no obstacle on the cell
     * next to the one where Karel was before the method is invoked an if so
     * moves Karel to it.
     *
     * Background: Karel is on the Western cell of the central line
     * facing east, beepers, walls or empty cell are right or left to him.
     *
     * Result: Karel is facing East on the Eastern cell of the central line
     * and all the beepers are removed according to the conditions.
     *
     */
    private void removeGarbage() throws Exception{
        if (noBeepersPresent() && leftIsClear() && rightIsClear()) {
            checkAndClearColumn();
        }
        if (frontIsClear()) {
            move();
            removeGarbage();
        }
    }

    /*
     * Checks if there are beepers right or left to Karel and moves him
     * there to remove them.
     *
     * Background: Karel is on a cell of the central line with no beepers
     * on it but with beepers right or/and left to him.
     *
     * Result: Karel has same position and direction before the method was
     * invoked but with no beepers in a column.
     */
    private void checkAndClearColumn() throws Exception{
        clearLeft();
        clearRight();
    }

    /*
     * Moves Karel to the Northern cell next to the one before the method
     * was called to remove beepers there and come back.
     *
     * Background: Karel is on a cell of the central line facing East
     * with no beepers on it but some may be left and/or right to him.
     *
     * Result: Karel is facing South on the same position before
     * the method was called with no beepers behind him.
     */
    private void clearLeft() throws Exception {
        turnLeft();
        move();
        while(beepersPresent()) {
            pickBeeper();
        }
        turnAround();
        move();
    }

    /*
     * Moves Karel to the Southern cell next to the one before the method
     * was invoked to clear beepers there and return.
     *
     * Background: Karel is on a cell of the central line facing Southern
     * cell where beepers may be.
     *
     * Result: Karel is facing east on the central line with no beepers
     * right to him.
     */
    private void clearRight() throws Exception {
        move();
        while (beepersPresent()) {
            pickBeeper();
        }
        turnAround();
        move();
        turnRight();
    }
}
