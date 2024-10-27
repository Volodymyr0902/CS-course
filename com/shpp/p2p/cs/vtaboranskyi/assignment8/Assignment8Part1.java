package com.shpp.p2p.cs.vtaboranskyi.assignment8;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Assignment8Part1 extends WindowProgram {

    // Window dimensions in pxs.
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;

    // Total number of rectangles to be spawned
    public static final int RECTS_NUM = 16;

    // Single rectangle's width and height.
    public static final double RECT_SIZE = 20;

    // Part of the whole way rectangle overcome during one move.
    public static final double RECT_MOTION_STEP = 0.05;

    // Period between two scene's changes.
    public static final int PAUSE_TIME = 100;

    // Groups of rectangles which act differently
    private ArrayList<GRect> approachWhenPressed;
    private ArrayList<GRect> approachWhenNotPressed;

    // Flag for mousePressed event.
    boolean mousePressed = false;

    // Cursor's location after mouse's move.
    MouseEvent moved;

    public void run() {
        addMouseListeners();
        approachWhenPressed = new ArrayList<>();
        approachWhenNotPressed = new ArrayList<>();
        createRectangles();
        drawRectangles();

        while (true) {
            // Gets absolute cursor coordinates initially, after first mouse move pick relative.
            double mouseX = moved == null ? MouseInfo.getPointerInfo().getLocation().getX() : moved.getX();
            double mouseY = moved == null ? MouseInfo.getPointerInfo().getLocation().getY() : moved.getY();

            if (!mousePressed) {
                for (GRect rect : approachWhenPressed)
                    moveRectsAway(mouseX, mouseY, rect);
                for (GRect rect : approachWhenNotPressed)
                    approachRects(mouseX, mouseY, rect);
            } else {
                for (GRect rect : approachWhenPressed)
                    approachRects(mouseX, mouseY, rect);
                for (GRect rect : approachWhenNotPressed)
                    moveRectsAway(mouseX, mouseY, rect);
            }
            pause(PAUSE_TIME);
        }
    }

    /**
     * Gets distance between rectangle's offset points
     * and cursor, calculates move's direction and
     * distance, executes the move, if the rectangles
     * haven't reached the cursor.
     * @param mouseX Cursor's location on X axis.
     * @param mouseY Cursor's location on Y axis.
     * @param rect Rectangle to be moved.
     */
    private void approachRects(double mouseX, double mouseY, GRect rect) {
        double xDistance = Math.max(mouseX, rect.getX()) - Math.min(mouseX, rect.getX());
        double yDistance = Math.max(mouseY, rect.getY()) - Math.min(mouseY, rect.getY());
        double dx = xDistance * RECT_MOTION_STEP;
        double dy = yDistance * RECT_MOTION_STEP;
        dx = rect.getX() > mouseX ? -dx : dx;
        dy = rect.getY() > mouseY ? -dy : dy;

        if (getElementAt(mouseX, mouseY) == null)
            rect.move(dx, dy);
    }

    /**
     * Gets distance between rectangle's offset points
     * and cursor, calculates move's direction and
     * distance, executes the move, if the rectangle haven't
     * reached  window's edge.
     * @param mouseX Cursor's location on X axis.
     * @param mouseY Cursor's location on Y axis.
     * @param rect Rectangle to be moved.
     */
    private void moveRectsAway(double mouseX, double mouseY, GRect rect) {
        if (isNotAtEdge(rect)) {
            double xDistance = Math.max(mouseX, rect.getX()) - Math.min(mouseX, rect.getX());
            double yDistance = Math.max(mouseY, rect.getY()) - Math.min(mouseY, rect.getY());
            double dx = xDistance * RECT_MOTION_STEP;
            double dy = yDistance * RECT_MOTION_STEP;
            dx = rect.getX() > mouseX ? dx : -dx;
            dy = rect.getY() > mouseY ? dy : -dy;
            rect.move(dx, dy);
        }
    }

    /**
     * Checks if rectangle is at the edge of the window.
     * @param rect Rectangle to be checked.
     * @return True if rectangle is within the window.
     */
    private boolean isNotAtEdge(GRect rect) {
        return rect.getX() > 0 && rect.getY() > 0 &&
                rect.getX() + RECT_SIZE < getWidth() && rect.getY() + RECT_SIZE < getHeight();
    }

    /**
     * Draws groups of rectangles.
     */
    private void drawRectangles() {
        for (GRect rect : approachWhenPressed)
            add(rect);
        for (GRect rect : approachWhenNotPressed)
            add(rect);
    }

    /**
     * Fills groups of rectangles, one
     * after another, using flag.
     */
    public void createRectangles() {
        boolean firstGroup = true;

        for (int i = 0; i < RECTS_NUM; i++) {
            GRect rect = createSingleRect();

            if (firstGroup)
                approachWhenPressed.add(rect);
            else
                approachWhenNotPressed.add(rect);

            firstGroup = !firstGroup;
        }
    }

    /**
     * Creates single rectangle with no belonging
     * with random coordinates within the window.
     * @return Rectangle just created.
     */
    private GRect createSingleRect() {
        double xOffset, yOffset;
        Random random = new Random();
        xOffset = random.nextDouble(getWidth() - RECT_SIZE);
        yOffset = random.nextDouble(getHeight() - RECT_SIZE);
        GRect rect = new GRect(xOffset, yOffset, RECT_SIZE, RECT_SIZE);
        rect.setFilled(true);
        return rect;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // Activates flag for pressing.
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        // Deactivates flag for pressing.
        mousePressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        // Saves last relative cursor position after last mouse's move.
        moved = mouseEvent;
    }
}
