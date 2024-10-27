package com.shpp.p2p.cs.vtaboranskyi.assignment3;

import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

import acm.util.RandomGenerator;

/**
 * File: Assignment3Part6.java.
 * Draws a car on a road with a red traffic light,
 * turns it green and moves car to the end of the
 * road showing exhaust smoke.
 */
public class Assignment3Part6 extends WindowProgram {

    // The default width and height of the window.
    public static final int APPLICATION_WIDTH = 1200;
    public static final int APPLICATION_HEIGHT = 700;

    // The height of the road on Y axis and haf of this value, respectively.
    public static final double ROAD_HEIGHT = 100;
    public static final double HALF_ROAD_HEIGHT = ROAD_HEIGHT / 2;

    // The length of the road central markup line element
    public static final double MARKUP_LINE_EL_LENGTH = ROAD_HEIGHT / 3;
    // The length between two of these
    public static final double MARKUP_LINE_GAP_LENGTH = MARKUP_LINE_EL_LENGTH;

    // The size/diameter of a car's wheel
    public static final double WHEEL_SIZE = HALF_ROAD_HEIGHT / 2;

    // The width and the height of the car body rectangle, respectively.
    public static final double CAR_BODY_WIDTH = WHEEL_SIZE * 4;
    public static final double CAR_BODY_HEIGHT = WHEEL_SIZE;

    // The width and the height of the car cockpit oval, respectively.
    public static final double CAR_COCKPIT_WIDTH = WHEEL_SIZE * 3;
    public static final double CAR_COCKPIT_HEIGHT = WHEEL_SIZE * 2;

    // The width and the height of the traffic light's pillar, respectively.
    public static final double PILLAR_WIDTH = WHEEL_SIZE / 4;
    public static final double PILLAR_HEIGHT = HALF_ROAD_HEIGHT * 1.5;

    // The width and the height of the traffic light's block, respectively.
    public static final double TRAFFIC_LIGHT_WIDTH = PILLAR_WIDTH * 3;
    public static final double TRAFFIC_LIGHT_HEIGHT = WHEEL_SIZE * 1.5;
    // The quarter of the traffic light's height.
    public static final double QUARTER_TRAFFIC_LIGHT_HEIGHT = TRAFFIC_LIGHT_HEIGHT / 4;

    // The size/diameter of any traffic light's lamp.
    public static final double LAMP_SIZE = TRAFFIC_LIGHT_WIDTH * 0.5;
    public static final double HALF_LAMP_SIZE = LAMP_SIZE / 2;

    // The period between lights changes and lights changes and car's first move.
    public static final double LAMP_PAUSE = 1000;

    // The size/diameter of any smoke circle and their quantity, respectively.
    public static final double SMOKE_CIRCLE_SIZE = ROAD_HEIGHT / 10;
    public static final int SMOKE_CIRCLES_NUM = 10;

    // The additional distance the car overcomes every next move, except the main one.
    public static final double CAR_ACCELERATION = 0.25;

    // The period between car's moves.
    public static final double MOVING_PAUSE = 1000.0 / 45;


    @Override
    public void run() {
        // The distance between the window's bottom edge and the road's bottom edge.
        double gapToBottom = getHeight() / 10;
        drawRoad(gapToBottom);

        // The car's parts.
        GObject[] car = drawCar(gapToBottom);

        // The exact time before the animation starts.
        long startTime = System.currentTimeMillis();

        resetTrafficLight(drawTrafficLights(gapToBottom));
        moveCar(car);

        // The exact time after the animation finishes.
        println(System.currentTimeMillis() - startTime);
    }

    /**
     * Makes the car move to the edge of the road,
     * step by step, increasing the move distance on every
     * iteration by car's acceleration.
     * Shows smoke circles while the car's moving at
     * different position on every iteration.
     *
     * @param car An array of GObjects/car's parts
     */
    private void moveCar(GObject[] car) {
        // The distance to overcome on this iteration on X axis.
        double dx = 0;

        // The number of single moves the whole car has made.
        double frames = 0;

        // car[1] always stands for car's body.
        while (car[1].getX() < getWidth() - (car[1].getWidth() + dx)) {
            for (int i = 0; i < car.length; i++) {
                car[i].move(dx, 0);
            }

            // An array of smoke circles.
            GOval[] smoke = showSmoke(car[1]);

            dx = car[1].getX() < getWidth() / 2 ? dx + CAR_ACCELERATION : dx - CAR_ACCELERATION;
            pause(MOVING_PAUSE);

            removeSmoke(smoke);
            frames++;
        }

        println(frames);
    }


    /**
     * Makes shown smoke disappear.
     *
     * @param smoke An array of smoke circles.
     */
    private void removeSmoke(GOval[] smoke) {
        for (int i = 0; i < smoke.length; i++) {
            remove(smoke[i]);
        }
    }

    /**
     * Shows exhaust smoke circles behind the car's body.
     *
     * @param carBody The car's body rectangle.
     * @return An array of smoke circles.
     */
    private GOval[] showSmoke(GObject carBody) {
        RandomGenerator rgen = RandomGenerator.getInstance();
        GOval[] smoke = new GOval[SMOKE_CIRCLES_NUM];

        for (int i = 0; i < smoke.length; i++) {
            // Smoke circle's coordinates on X and Y axles, respectively.
            double smokeXOffset = carBody.getX() - rgen.nextDouble(0, WHEEL_SIZE);
            double smokeYOffset = carBody.getY() + rgen.nextDouble(0, carBody.getHeight());

            smoke[i] = drawOval(smokeXOffset, smokeYOffset, SMOKE_CIRCLE_SIZE, SMOKE_CIRCLE_SIZE, Color.BLACK, Color.DARK_GRAY);
        }
        return smoke;
    }

    /**
     * Makes the traffic light's upper lamp
     * turn off and turns on the down lamp.
     *
     * @param lamps The array of traffic light's lamps.
     */
    private void resetTrafficLight(GOval[] lamps) {
        // To see the change.
        pause(LAMP_PAUSE);
        // Upper and down lamps, respectively.
        lamps[0].setFillColor(Color.LIGHT_GRAY);
        lamps[1].setFillColor(Color.GREEN);
        // To set gap between the change and car's first move.
        pause(LAMP_PAUSE);

    }

    /**
     * Draws the traffic light's pillar at the bottom
     * edge of the road, the block at the top of the
     * pillar and two lamps inside the block, the upper
     * one is turned on/red.
     *
     * @param gap The distance between the window's bottom edge and the road's bottom edge.
     * @return The array of traffic light's lamps.
     */
    private GOval[] drawTrafficLights(double gap) {
        // Traffic light's pillar's initial coordinates on X and Y axles, respectively.
        double pillarXOffset = WHEEL_SIZE * 6;
        double pillarYOffset = getHeight() - gap - PILLAR_HEIGHT;

        // Traffic light's block's initial coordinates on X and Y axles, respectively.
        double trafficLightXOffset = pillarXOffset - WHEEL_SIZE / 2;
        double trafficLightYOffset = pillarYOffset;

        // Traffic light's lamps' initial coordinates on X and Y axles, respectively.
        double lampXOffset = trafficLightXOffset + TRAFFIC_LIGHT_WIDTH / 2 - HALF_LAMP_SIZE;
        double upperLampYOffset = trafficLightYOffset + QUARTER_TRAFFIC_LIGHT_HEIGHT - HALF_LAMP_SIZE;
        double downLampYOffset = trafficLightYOffset + QUARTER_TRAFFIC_LIGHT_HEIGHT * 3 - HALF_LAMP_SIZE;

        // Draws traffic light's pillar and block, respectively.
        drawRect(pillarXOffset, pillarYOffset, PILLAR_WIDTH, PILLAR_HEIGHT, Color.BLACK, Color.DARK_GRAY);
        drawRect(trafficLightXOffset, trafficLightYOffset, TRAFFIC_LIGHT_WIDTH, TRAFFIC_LIGHT_HEIGHT, Color.BLACK, Color.DARK_GRAY);

        // Traffic light's upper and down lamps, respectively.
        GOval upperLamp = drawOval(lampXOffset, upperLampYOffset, LAMP_SIZE, LAMP_SIZE, Color.BLACK, Color.RED);
        GOval downLamp = drawOval(lampXOffset, downLampYOffset, LAMP_SIZE, LAMP_SIZE, Color.BLACK, Color.LIGHT_GRAY);

        GOval[] lamps = {upperLamp, downLamp};
        return lamps;
    }

    /**
     * Draws a car step by step, the cockpit,
     * the body and the wheels.
     *
     * @param gap The distance between the window's bottom edge and the road's bottom edge.
     * @return The array of car's parts.
     */
    private GObject[] drawCar(double gap) {
        // Wheels' initial coordinates on X and Y axles, respectively.
        double firstWheelXOffset = WHEEL_SIZE;
        double wheelYOffset = getHeight() - gap - HALF_ROAD_HEIGHT + WHEEL_SIZE / 2;
        double secondWheelXOffset = firstWheelXOffset + WHEEL_SIZE * 2;

        // Car's body initial coordinates on X and Y axles, respectively.
        double carBodyXOffset = firstWheelXOffset / 2;
        double carBodyYOffset = getHeight() - gap - HALF_ROAD_HEIGHT;

        // Car's cockpit initial coordinates on X and Y axles, respectively.
        double carCockpitXOffset = carBodyXOffset;
        double carCockpitYOffset = carBodyYOffset - CAR_BODY_HEIGHT;

        GOval cockpit = drawOval(carCockpitXOffset, carCockpitYOffset, CAR_COCKPIT_WIDTH, CAR_COCKPIT_HEIGHT, Color.BLACK, Color.DARK_GRAY);
        GRect carBody = drawRect(carBodyXOffset, carBodyYOffset, CAR_BODY_WIDTH, CAR_BODY_HEIGHT, Color.BLACK, Color.BLUE);
        GOval firstWheel = drawOval(firstWheelXOffset, wheelYOffset, WHEEL_SIZE, WHEEL_SIZE, Color.BLACK, Color.ORANGE);
        GOval secondWheel = drawOval(secondWheelXOffset, wheelYOffset, WHEEL_SIZE, WHEEL_SIZE, Color.BLACK, Color.ORANGE);

        GObject[] car = {cockpit, carBody, firstWheel, secondWheel};
        return car;
    }

    /**
     * Draws the road/rectangle and the markup line
     * in its center from the left window's edge to the right one.
     *
     * @param gap The distance between the window's bottom edge and the road's bottom edge.
     */
    private void drawRoad(double gap) {
        drawRect(0, getHeight() - gap - ROAD_HEIGHT, getWidth(), ROAD_HEIGHT, Color.BLACK, Color.LIGHT_GRAY);
        drawDashLine(0, getHeight() - gap - HALF_ROAD_HEIGHT, MARKUP_LINE_EL_LENGTH, getHeight() - gap - HALF_ROAD_HEIGHT);
    }


    /**
     * Draws a dash line starting from any point
     * and finishing with a window's edge.
     *
     * @param x1 Initial single line's coordinate on X axis.
     * @param y1 Initial single line's coordinate on Y axis.
     * @param x2 Final single line's coordinate on X axis.
     * @param y2 Final single line's coordinate on Y axis.
     */
    private void drawDashLine(double x1, double y1, double x2, double y2) {
        while (x1 < getWidth()) {
            GLine line = new GLine(x1, y1, x2, y2);
            line.setColor(Color.WHITE);
            add(line);
            x1 += MARKUP_LINE_EL_LENGTH + MARKUP_LINE_GAP_LENGTH;
            x2 += MARKUP_LINE_EL_LENGTH + MARKUP_LINE_GAP_LENGTH;
        }
    }

    /**
     * Draws an oval with received parameters.
     *
     * @param x      Initial oval's coordinate on X axis.
     * @param y      Initial oval's coordinate on Y axis.
     * @param width  Oval's width.
     * @param height Oval's height.
     * @param border Oval's borderline color.
     * @param inner  Oval's inner field's color.
     * @return An oval just added to the window.
     */
    private GOval drawOval(double x, double y, double width, double height, Color border, Color inner) {
        GOval oval = new GOval(x, y, width, height);
        oval.setColor(border);
        oval.setFilled(true);
        oval.setFillColor(inner);
        add(oval);
        return oval;
    }

    /**
     * Draws a rectangle with received parameters.
     *
     * @param x      Initial rectangle's coordinate on X axis.
     * @param y      Initial rectangle's coordinate on Y axis.
     * @param width  Rectangle's width.
     * @param height Rectangle's height.
     * @param border Rectangle's borderline color.
     * @param inner  Rectangle's inner field's color.
     * @return A rectangle just added to the window.
     */
    private GRect drawRect(double x, double y, double width, double height, Color border, Color inner) {
        GRect rect = new GRect(x, y, width, height);
        rect.setColor(border);
        rect.setFilled(true);
        rect.setFillColor(inner);
        add(rect);
        return rect;
    }
}
