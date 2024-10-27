package com.shpp.p2p.cs.vtaboranskyi.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * File: Assignment2Part6.java.
 * Program which draws a caterpillar with given
 * number of equal segments which border color is
 * different from their inner field color.
 * Segment overlap one another.
 * Odd segments always have same position
 * on Y axis as even ones have but these
 * always have difference of equal value.
 * Specifications are in materials of level #2,
 * Task 2, Problem 6.
 */
public class Assignment2Part6 extends WindowProgram {

    // The number of segments the caterpillar has.
    public static final int SEGMENTS_COUNT = 8;

    // The radius and diameter of any segment, respectively.
    public static final int SEGMENT_RADIUS = 50;
    public static final int SEGMENT_DIAMETER = SEGMENT_RADIUS * 2;

    // The initial points of the first segment in the window
    // on X and Y axles, respectively.
    public static final int FIRST_SEGMENT_X_OFFSET = 0;
    public static final int FIRST_SEGMENT_Y_OFFSET = SEGMENT_RADIUS /2;

    // Constants setting absolute program size in pixels.
    public static final int APPLICATION_WIDTH = 890;
    public static final int APPLICATION_HEIGHT = 450;

    @Override
    public void run() {
        drawCaterpillar(
                FIRST_SEGMENT_X_OFFSET,
                FIRST_SEGMENT_Y_OFFSET,
                SEGMENTS_COUNT,
                SEGMENT_DIAMETER,
                SEGMENT_RADIUS);
    }

    /**
     * Draws a caterpillar:
     * defines initial position of actual segment
     * on X and Y axles, colors it, shows in the
     * window and repeats with the next segment
     * until the number of total amount of
     * segments is reached.
     * @param x The offset point of the first segment on X axle.
     * @param y The offset point of the first segment on Y axle.
     * @param segmentsNum The total number of segments.
     * @param d The diameter of a segment.
     * @param r The radius of a segment.
     */
    private void drawCaterpillar(
            int x,
            int y,
            int segmentsNum,
            int d,
            int r) {
        for (int i = 0; i < segmentsNum; i++) {
            // Sets initial position
            // to actual segment on Y axis
            int yActual = (i % 2 == 0) ? y : 0;
            GOval segment = new GOval(
                    x,
                    yActual,
                    d,
                    d);
            segment.setColor(Color.CYAN);
            segment.setFilled(true);
            segment.setFillColor(Color.MAGENTA);
            add(segment);
            // Increases next segment's position
            // on X axis by segment's radius
            x += r;
        }
    }

}
