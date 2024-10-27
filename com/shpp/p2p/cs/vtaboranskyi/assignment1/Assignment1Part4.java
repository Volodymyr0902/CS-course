package com.shpp.p2p.cs.vtaboranskyi.assignment1;

/*
 * File: Assignment1Part4.java.
 *
 * Program in which Karel builds a checkerboard.
 * Specifications are in materials of level #1,
 * Task 1, Problem 4.
 */
public class Assignment1Part4 extends SuperKarel {
    @Override
    public void run() throws Exception {
        makeCheckerBoard();
    }

    /*
     * Makes Karel turn an empty rectangular
     * world into a checkerboard with beepers.
     *
     * Background: Karel is facing East in the Southwest
     * corner of a world with no beepers in it.
     *
     * Result: Karel is facing East in the Northeast corner
     * of a checkerboard of beepers and there is one on the
     * Southwest cell.
     */
    public void makeCheckerBoard() throws Exception {
        faceEast();
        makeOddCheckerRow();
        checkAndMakeEvenRow();
        checkAndMakeOddRow();
    }

    /*
     * Makes Karel fill an odd row (1, 3, 5, etc. from South)
     * with beepers in staggered order starting from the first cell.
     *
     * Background: Karel is facing East on the Western cell
     * of an odd row with no beepers on it.
     *
     * Result: Karel is facing East on the Eastern cell of the
     * same row of cells filled with beepers in staggered order.
     */
    public void makeOddCheckerRow() throws Exception {
        putBeeper();
        // Checks next even cell and moves Karel
        if (frontIsClear()) {
            move();
            // Checks next odd cell, moves Karel and repeats the method
            if (frontIsClear()) {
                move();
                makeOddCheckerRow();
            }
        }
    }

    /*
     * Checks if next row exists and if so makes Karel come back
     * to the Western cell of a row he is on, then move to the
     * next one, turn East and fill it with beepers in staggered even order.
     *
     * Background: Karel is facing East on the Eastern cell of
     * an odd row already filled with beepers.
     *
     * Result: Karel has same direction and position but on the next
     * even row which also checked.
     */
    public void checkAndMakeEvenRow() throws Exception {
        if (leftIsClear()) {
            comeBackToStart();
            moveToNextRow();
            faceEast();
            makeEvenCheckerRow();
        }
    }

    /*
     * Checks if next row exists and if so makes Karel come back
     * to the Western cell of a row he is on, then move to the
     * next one, turn East and continue building a checkerboard
     * starting from an odd row.
     *
     * Background: Karel is facing East on the Eastern cell of
     * an even row already filled with beepers.
     *
     * Result: Karel has same direction and position but on the next
     * odd or even (if it exists) row which is also checked.
     */
    public void checkAndMakeOddRow() throws Exception {
        if (leftIsClear()) {
            comeBackToStart();
            moveToNextRow();
            makeCheckerBoard();
        }
    }

    /*
     * Makes Karel move to the Western cell of a row.
     *
     * Background: Karel is facing East on the Eastern cell
     * of a row.
     *
     * Result: Karel is facing West on the Western cell of
     * the same row.
     */
    public void comeBackToStart() throws Exception {
        turnAround();
        moveToWall();
    }

    /*
     * Makes Karel move to the wall in the direction he
     * is facing.
     *
     * Background: Any Karel's position and direction.
     *
     * Result: Karel has same direction on the last cell
     * of a row in the direction he was facing.
     */
    public void moveToWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

    /*
     * Makes Karel move one cell northward.
     *
     * Background: Karel is facing West on the Western cell
     * of any row and the one exists.
     *
     * Result: Karel is facing North one cell northward to
     * the one he was on before the method was called.
     */
    public void moveToNextRow() throws Exception {
        turnRight();
        move();
    }

    /*
     * Makes Karel face East.
     *
     * Background: any Karel's position and direction.
     *
     * Result: Karel is facing East on the same position
     * before the method was invoked.
     */
    public void faceEast() throws Exception {
        while (notFacingEast()) {
            turnLeft();
        }
    }

    /*
     * Makes Karel fill an even row (2, 4, 6, etc. from South)
     * with beepers in staggered order starting from the second cell.
     *
     * Background: Karel is facing East on the Western cell of any
     * even row with no beepers on it.
     *
     * Result: Karel is facing East on the Eastern cell of this very
     * row which is already filled with beepers.
     */
    public void makeEvenCheckerRow() throws Exception {
        while (frontIsClear()) {
            move();
            putBeeper();
            if (frontIsClear()) {
                move();
            }
        }
    }

}


