package com.shpp.p2p.cs.vtaboranskyi.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * File: Assignment3Part4.java.
 * Build pyramid of bricks, which every
 * higher layer is one brick more narrow
 * than the lower one.
 * Specifications are in materials of level #3,
 * Task 3, Problem 4.
 */
public class Assignment3Part4 extends WindowProgram {

    // The default width and height of the window.
    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 800;

    // The brick's width and height in px, respectively.
    public static final int BRICK_HEIGHT = 5;
    public static final int BRICK_WIDTH = 8;

    // The bricks' number the lowest pyramid row consists of,
    // that equals the total rows' number.
    public static final int BRICKS_IN_BASE = 100;
    public static final int PYRAMID_ROWS_NUM = BRICKS_IN_BASE;


    @Override
    public void run() {
        // The number of bricks to be built in the actual row.
        int bricksInRowExpected = BRICKS_IN_BASE;
        // The width of the actual bricks row in px.
        int bricksRowWidth = bricksInRowExpected * BRICK_WIDTH;
        // The initial coordinate of the actual row on X axis.
        int rowXOffset = getWidth() / 2 - bricksRowWidth / 2;
        // The initial coordinate of the actual row on Y axis.
        int rowYOffset = getHeight() - BRICK_HEIGHT;

        // Builds row by row reducing number of bricks in
        // the upper row to set its initial coordinates.
        for (int i = 0; i < PYRAMID_ROWS_NUM; i++) {
            buildBricksRow(rowXOffset, rowYOffset, bricksInRowExpected);
            bricksInRowExpected--;
            bricksRowWidth -= BRICK_WIDTH;
            rowXOffset = getWidth() / 2 - bricksRowWidth / 2;
            rowYOffset -= BRICK_HEIGHT;
        }
    }

    /**
     * Builds a row of bricks.
     * @param x Initial row coordinate on X axis.
     * @param y Initial row coordinate on Y axis.
     * @param bricksInRowExpected The number of bricks to be built in the actual row.
     */
    private void buildBricksRow(int x, int y, int bricksInRowExpected) {
        for (int bricksBuilt = 0; bricksBuilt < bricksInRowExpected; bricksBuilt++) {
            buildBrick(x, y, bricksBuilt);
        }
    }

    /**
     * Builds a single brick.
     * @param x Initial actual row coordinate on X axis.
     * @param y Initial actual row coordinate on Y axis.
     * @param bricksBuiltInRow The number of bricks already built in the actual row.
     */
    private void buildBrick(int x, int y, int bricksBuiltInRow) {
        // A brick's initial coordinate on X axis.
        int brickXOffset = x + BRICK_WIDTH * bricksBuiltInRow;
        GRect brick = new GRect(brickXOffset, y, BRICK_WIDTH, BRICK_HEIGHT);
        showColoredBrick(brick);
    }

    /**
     * Colors up brick's border and inner field
     * and shows it in the window.
     * @param brick Any built brick to be shown.
     */
    private void showColoredBrick(GRect brick) {
        brick.setColor(new Color(171, 100, 70));
        brick.setFilled(true);
        brick.setFillColor(new Color(253, 160, 54));
        add(brick);
    }

}
