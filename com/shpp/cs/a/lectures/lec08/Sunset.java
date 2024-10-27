package com.shpp.cs.a.lectures.lec08;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Sunset extends WindowProgram {

    private static final Color SUN_COLOR = Color.YELLOW;
    private static final Color SKY_COLOR = Color.CYAN;
    private static final Color GROUND_COLOR = Color.GREEN;


    @Override
    public void run() {
        double sunSize = Math.min(getWidth() / 8, getHeight() / 8);
        double sunXOffset = getWidth() / 2 - sunSize / 2;
        double sunYOffset = getHeight() / 2 - sunSize / 2;

        GOval sun = drawSun(sunXOffset, sunYOffset, sunSize, sunSize, SUN_COLOR);
        drawSceneParts();
        startSunset(sun);
    }

    private void startSunset(GOval sun) {
        while (sun.getY() < getHeight() * 0.75) {
            sun.move(0, 1);
            pause(40);
        }
    }

    private void drawSceneParts() {
        double skyXOffset = 0;
        double skyYOffset = 0;
        double skyWidth = getWidth();
        double skyHeight = getHeight() * 0.75;

        double groundXOffset = 0;
        double groundYOffset = skyHeight;
        double groundWidth = getWidth();
        double groundHeight = getHeight() * 0.25;

        GRect sky = drawHorizonPart(skyXOffset, skyYOffset, skyWidth, skyHeight, SKY_COLOR);
        sky.sendBackward();
        GRect ground = drawHorizonPart(groundXOffset, groundYOffset, groundWidth, groundHeight, GROUND_COLOR);
        ground.sendForward();
    }

    private GRect drawHorizonPart(double x, double y, double width, double height, Color color) {
        GRect horizonPart = new GRect(x, y, width, height);
        horizonPart.setFilled(true);
        horizonPart.setColor(color);
        add(horizonPart);
        return horizonPart;
    }

    private GOval drawSun(double x, double y, double width, double height, Color color) {
        GOval sun = new GOval(x, y, width, height);
        sun.setFilled(true);
        sun.setColor(color);
        add(sun);
        return sun;
    }
}
