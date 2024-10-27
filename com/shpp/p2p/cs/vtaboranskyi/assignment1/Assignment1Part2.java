package com.shpp.p2p.cs.vtaboranskyi.assignment1;

/*
 * File: Assignment1Part2.java.
 *
 * Program in which Karel builds columns of the house
 * with stones.
 * Specifications are in materials of level #1,
 * Task 1, Problem 2.
 */
public class Assignment1Part2 extends SuperKarel {
    @Override
    public void run() throws Exception {
        buildAllColumns();
    }

    /*
     * Makes Karel build first column if needed, then moves
     * him down to check if the next column exists and
     * if so moves him to its floor and repeats.
     *
     * Background: Karel is in the Southwest corner of the world
     * on the floor of the 1st column. No column is finished.
     *
     * Result: Karel is in the Southeast corner of the world. All
     * columns are built.
     */
    public void buildAllColumns() throws Exception {
        buildOneColumn();
        goToTheGround();
        if (leftIsClear()) {
            moveToNextColumn();
            buildAllColumns();
        }
    }

    /*
     * Turns Karel to the ceiling, checks if a cell of
     * the column is empty, if so puts a stone on it,
     * then moves Karel if front is clear;
     * repeats until Karel reaches the ceiling.
     *
     * Background: Karel is facing East on the floor
     * of the 1st/5th/9th/etc. column which may be not
     * finished yet.
     *
     * Result: Karel is facing North at the top of the
     * already built column.
     */
    public void buildOneColumn() throws Exception {
        turnLeft();
        while (frontIsClear() || noBeepersPresent()) {
            putStoneInEmptyCell();
            if (frontIsClear()) {
                move();
            }
        }
    }

    /*
     * Turns Karel to the bottom of the column and
     * moves him there.
     *
     * Background: Karel is facing North at the top
     * of a column.
     *
     * Result: Karel is facing South at the bottom
     * of a column.
     */
    public void goToTheGround() throws Exception {
        turnAround();
        while (frontIsClear()) {
            move();
        }
    }

    /*
     * Turns Karel to the next column and moves him
     * to its bottom.
     *
     * Background: Karel is facing South at the bottom
     * of a column, next column exists.
     *
     * Result: Karel is facing East at the bottom of
     * the next column.
     */
    public void moveToNextColumn() throws Exception {
        turnLeft();
        // Next possible column is always 4 cells away.
        for (int i = 0; i < 4; i++) {
            move();
        }
    }

    /*
     * Checks if the cell Karel is on has a stone
     * and if not so makes him put one.
     *
     * Background: Karel is on any cell of a column
     * without a stone on it.
     *
     * Result: Karel is on the same position, a stone
     * appears on this cell.
     */
    public void putStoneInEmptyCell() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }

}
