package com.shpp.p2p.cs.vtaboranskyi.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * File: Assignment2Part4.java.
 * Program which draws a centered flag of Germany which includes
 * 3 horizontal lines and shows its name in the right down corner
 * of the window.
 * Specifications are in materials of level #2,
 * Task 2, Problem 4.
 */

public class Assignment2Part4 extends WindowProgram {

    /*
     * Constants setting sizes of the whole flag.
     */
    public static final int FLAG_WIDTH = 400;
    public static final int FLAG_HEIGHT = 300;
    /*
     * Constants setting sizes to flag parts.
     */
    public static final int FLAG_LINE_HEIGHT = FLAG_HEIGHT / 3;
    public static final int HALF_FLAG_LINE_HEIGHT = FLAG_LINE_HEIGHT / 2;


    @Override
    public void run() {
        // Variable setting initial X offset to every flag line.
        int flagLineXOffset = getWidth() / 2 - FLAG_WIDTH / 2;
        drawUpperLine(flagLineXOffset);
        drawMiddleLine(flagLineXOffset);
        drawBottomLine(flagLineXOffset);
        typeFlagName("serif-25");
    }

    /**
     * Draws upper black line of the flag.
     *
     * @param flagLineXOffset The starting point to the line on X axis.
     */
    private void drawUpperLine(int flagLineXOffset) {
        drawFlagLine(
                flagLineXOffset,
                getHeight() / 2 - FLAG_HEIGHT / 2,
                FLAG_WIDTH,
                FLAG_LINE_HEIGHT,
                Color.BLACK);
    }

    /**
     * Draws middle red line of the flag below the black one.
     *
     * @param flagLineXOffset The starting point to the line on X axis.
     */
    private void drawMiddleLine(int flagLineXOffset) {
        drawFlagLine(
                flagLineXOffset,
                getHeight() / 2 - HALF_FLAG_LINE_HEIGHT,
                FLAG_WIDTH,
                FLAG_LINE_HEIGHT,
                Color.RED);
    }

    /**
     * Draws bottom yellow line of the flag below the red one.
     *
     * @param flagLineXOffset The starting point to the line on X axis.
     */
    private void drawBottomLine(int flagLineXOffset) {
        drawFlagLine(
                flagLineXOffset,
                getHeight() / 2 + HALF_FLAG_LINE_HEIGHT,
                FLAG_WIDTH,
                FLAG_LINE_HEIGHT,
                Color.YELLOW);
    }

    /**
     * Draws a flag line.
     *
     * @param x      The offset point to a flag line on X axis.
     * @param y      The offset point to a flag line on Y axis.
     * @param width  The width of a flag line in px.
     * @param height The height of a flag line in px.
     * @param color  Color of the border of every line and its inner field.
     */
    private GRect drawFlagLine(
            int x,
            int y,
            int width,
            int height,
            Color color) {
        GRect flagLine = new GRect(x, y, width, height);
        showColoredLine(flagLine, color);
        return flagLine;
    }

    /**
     * Colors up the border and the inner field of a flag
     * line, then shows it in the program window.
     *
     * @param flagLine An object of GRect class; a line of a flag.
     * @param color    Color of the border of every line and its inner field.
     */
    private void showColoredLine(GRect flagLine, Color color) {
        flagLine.setColor(color);
        flagLine.setFilled(true);
        flagLine.setFillColor(color);
        add(flagLine);
    }

    /**
     * Types the flag name in the right down corner of the window
     * using set font, font size with a gap of 10 px to window
     * edges.
     *
     * @param font The name and the size of the font.
     */
    private void typeFlagName(String font) {
        GLabel flagName = new GLabel("Flag of Germany");
        flagName.setFont(font);
        flagName.setLocation(getWidth() - flagName.getWidth(),
                getHeight() - flagName.getDescent());
        add(flagName);
    }

}
