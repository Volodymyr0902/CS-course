package com.shpp.p2p.cs.vtaboranskyi.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * File: Assignment2Part5.java.
 * Program which draws centered matrix of
 * equal boxes with equal spaces between them.
 * Specifications are in materials of level #2,
 * Task 2, Problem 5.
 */
public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 50;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    /*
    * The width of boxes in a row with no spaces between and
    * spaces with no boxes in a row, respectively.
     */
    private static final double BOXES_WIDTH = NUM_COLS * BOX_SIZE;
    private static final double SPACING_WIDTH_IN_ROW = (NUM_COLS - 1) * BOX_SPACING;

    /* The width of a completed row of boxes and spaces between them. */
    private static final double ROW_WIDTH = BOXES_WIDTH + SPACING_WIDTH_IN_ROW;

    /*
    * The height of boxes in a column with no spaces between and
    * spaces with no boxes in a column, respectively.
     */
    private static final double BOXES_HEIGHT = NUM_ROWS * BOX_SIZE;
    private static final double SPACING_HEIGHT_IN_COL = (NUM_ROWS - 1) * BOX_SPACING;

    /* The height of a completed column of boxes and spaces. */
    private static final double COL_HEIGHT = BOXES_HEIGHT + SPACING_HEIGHT_IN_COL;

    /* Constants setting absolute program size in pixels. */
    public static final int APPLICATION_WIDTH = 890;
    public static final int APPLICATION_HEIGHT = 450;

    @Override
    public void run() {
        // Variables setting X and Y offset to the upper left
        // aka first box of the matrix.
        double firstBoxXOffset = getWidth()/2 - ROW_WIDTH/2;
        double firstBoxYOffset = getHeight()/2 - COL_HEIGHT/2;
        buildMatrix(firstBoxXOffset, firstBoxYOffset, BOX_SIZE);
    }

    /**
     * Builds a matrix of equal boxes with
     * equal spaces between.
     * @param x The X coordinate of the upper left box.
     * @param y The Y coordinate of the upper left box.
     * @param boxSize Width and height of a box.
     */
    private void buildMatrix(double x, double y, double boxSize) {
        for (int i = 0; i < NUM_ROWS; i++) {
            buildBoxesRow(x, y, boxSize);
            // Increases Y coordinate to continue next row below.
            y += boxSize + BOX_SPACING;
        }
    }

    /**
     * Builds a row of equal black boxes with equal spaces
     * between them, showing them one by one.
     * @param x The X coordinate of the most left box in actual row.
     * @param y The Y coordinate of the most left box in actual row.
     * @param boxSize Width and height of a box.
     */
    private void buildBoxesRow(double x, double y, double boxSize) {
        for (int j = 0; j < NUM_COLS; j++) {
            GRect box = new GRect(x, y, boxSize, boxSize);
            box.setFilled(true);
            box.setFillColor(Color.BLACK);
            add(box);
            // Increases X coordinate to continue next box right to the actual.
            // Receives value of firstBoxXOffset at every iteration.
            x += boxSize + BOX_SPACING;
        }
    }

}
