package com.shpp.p2p.cs.vtaboranskyi.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * File: Assignment2Part3.java.
 * Program which draws two pawprints based on
 * received location params on x and y axles.
 * Specifications are in materials of level #2,
 * Task 2, Problem 3.
 */
public class Assignment2Part3 extends WindowProgram {
    /*
     * Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /*
     * The position of the heel relative to the upper-left
     * corner of the pawprint.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 520;

    public void run() {
        drawPawprint(0, 20);
        drawPawprint(80, 120);
    }

    /**
     * Draws a pawprint. The parameters should specify the upper-left corner of the
     * bounding box containing that pawprint.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double x, double y) {
        // Draws the heel of pawprint.
        createPawPart(x + HEEL_OFFSET_X, y + HEEL_OFFSET_Y, HEEL_WIDTH, HEEL_HEIGHT);
        // Draws the toes of a pawprint: left, middle and right respectively.
        createPawPart(x + FIRST_TOE_OFFSET_X, y + FIRST_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        createPawPart(x + SECOND_TOE_OFFSET_X, y + SECOND_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        createPawPart(x + THIRD_TOE_OFFSET_X, y + THIRD_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
    }

    /**
     * Creates any toe of a pawprint and
     * shows it in the window.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void createPawPart(double x, double y, double width, double height) {
        GOval toe = new GOval(x, y, width, height);
        showPawPart(toe);
    }

    /**
     * Colors a part of a paw and shows it in the
     * program window.
     *
     * @param pawPart Any toe or heel
     */
    private void showPawPart(GOval pawPart) {
        pawPart.setFilled(true);
        pawPart.setFillColor(Color.BLACK);
        add(pawPart);
    }
}