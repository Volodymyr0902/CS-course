package com.shpp.p2p.cs.vtaboranskyi.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * File: Assignment2Part2.java.
 * Program which creates a white rectangle, which
 * overlaps 4 circles.
 * Specifications are in materials of level #2,
 * Task 2, Problem 2.
 */
public class Assignment2Part2 extends WindowProgram {

    /*
     * Constants setting absolute program size in pixels.
     */
    public static final int APPLICATION_WIDTH = 890;
    public static final int APPLICATION_HEIGHT = 450;
    /*
     * Constants setting parameters to circles.
     */
    public static final int CIRCLE_RADIUS = Math.min(APPLICATION_WIDTH / 6, APPLICATION_HEIGHT / 6);
    public static final int CIRCLE_DIAMETER = CIRCLE_RADIUS * 2;

    @Override
    public void run() {
        createCircles(CIRCLE_DIAMETER);
        createRect(CIRCLE_RADIUS, CIRCLE_DIAMETER);
    }

    /*
     * Creates 4 equal circles, one in every corner.
     * @param d The diameter of a circle.
     */
    private void createCircles(int d) {
        createUpCircles(d);
        createDownCircles(d);
    }

    /**
     * Creates one circle in the left down corner of the window
     * and another one in the right down corner,
     * showing them one by one.
     *
     * @param d The diameter of a circle.
     */
    private void createDownCircles(int d) {
        for (int i = 0; i < 2; i++) {
            GOval circle = new GOval(
                    (getWidth() - d) * i, // x pos of the start point
                    getHeight() - d, // y pos of the start point
                    d,
                    d);
            showCircles(circle);
        }
    }

    /**
     * Creates one circle in the left up corner of the window
     * and another one in the right up corner,
     * showing them one by one.
     *
     * @param d The diameter of a circle.
     */
    private void createUpCircles(int d) {
        for (int i = 0; i < 2; i++) {
            GOval circle = new GOval(
                    (getWidth() - d) * i, // x pos of the start point
                    0, // y pos of the start point
                    d,
                    d);
            showCircles(circle);
        }
    }

    /**
     * Colors the inner fields of a circle
     * and shows it in the window.
     *
     * @param circle An object of the GOval class.
     */
    private void showCircles(GOval circle) {
        circle.setFilled(true);
        circle.setFillColor(Color.BLACK);
        add(circle);
    }

    /**
     * Creates a rectangle which overlaps circles,
     * so that its every corner is located in the center
     * of the relative circle. Then colors it whole white
     * and shows in the program's window.
     *
     * @param r The radius of any circle.
     * @param d The diameter of any circle.
     */
    private void createRect(int r, int d) {
        /*
         * Rectangle's width and height are calculated by subtracting
         * circles diameter from the window's width and height in order
         * to locate rectangle's corners correctly.
         */
        GRect square = new GRect(
                r,
                r,
                getWidth() - d,
                getHeight() - d);
        square.setColor(Color.WHITE);
        square.setFilled(true);
        square.setFillColor(Color.WHITE);
        add(square);
    }

}
