package com.shpp.p2p.cs.vtaboranskyi.assignment1;

/*
 * File: Assignment1Part1.java
 *
 * Program in which Karel collects a newspaper.
 * Specifications are in materials of level #1,
 * Task 1, Problem 1.
 */
public class Assignment1Part1 extends SuperKarel {

    /*
     * Background: Karel is in Northwest corner of the house
     * facing East. The newspaper is behind the doorstep.
     *
     * Result: Karel has the same position and direction
     * before the method was called and has collected the newspaper.
     */
    @Override
    public void run() throws Exception {
        goForNewspaper();
        grabNewspaper();
        comeBackToStart();
    }

    /*
     * Makes Karel move to the newspaper.
     *
     * Background: Karel has the same position and direction
     * before the program was initiated. The newspaper is behind
     * the doorstep.
     *
     * Result: Karel is on the cell where the newspaper is facing East.
     */
    public void goForNewspaper() throws Exception {
        moveUntilBlocked();
        turnRight();
        while (leftIsBlocked()) {
            move();
        }
        turnLeft();
        crossDoorstep();
    }

    /*
     * Makes Karel pick the newspaper.
     *
     * Background: Karel is at the newspaper's initial location.
     *
     * Result: Karel has collected the newspaper and remain on
     * the same cell with the very direction before the method
     * was invoked.
     */
    public void grabNewspaper() throws Exception {
        pickBeeper();
    }

    /*
     * Makes Karel move to the start position.
     *
     * Background: Karel is facing West on the cell
     * where the newspaper is/was.
     *
     * Result: Karel is in the Northwest corner of the house.
     */
    public void comeBackToStart() throws Exception {
        turnAround();
        crossDoorstep();
        turnRight();
        moveUntilBlocked();
        turnLeft();
        moveUntilBlocked();
        turnAround();
    }

    /*
     * Moves Karel until front is blocked.
     *
     * Background: Karel is on any cell with any direction.
     *
     * Result: Karel is facing wall/end of the world.
     */
    public void moveUntilBlocked() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

    /*
     * Makes Karel cross the doorstep of the house.
     *
     * Background: Karel is on the cell right/left to the doorstep
     * of the house facing West/East relatively.
     *
     * Result: Karel on the opposite side of the doorstep before
     * the method was called but with the same direction.
     */
    public void crossDoorstep() throws Exception {
        move();
        move();
    }

}
