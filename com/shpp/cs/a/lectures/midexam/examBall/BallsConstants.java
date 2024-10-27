package com.shpp.cs.a.lectures.midexam.examBall;

public interface BallsConstants {
    /**
     * The width of the application window
     */
    public static final int APPLICATION_WIDTH = 800;

    /**
     * The height of the application window
     */
    public static final int APPLICATION_HEIGHT = 800;

    public static final int SHORTER_AXIS = Math.min(APPLICATION_WIDTH, APPLICATION_HEIGHT);

    public static final double MIN_BALL_DIAMETER = (double) SHORTER_AXIS / 100;

    public static final double MAX_BALL_DIAMETER = (double) SHORTER_AXIS / 2;

    public static final double BALL_SIZE_STEP = (double) SHORTER_AXIS / 100;

    public static final int BALL_SIZE_PAUSE = 100;

    public static final int BALL_COLOR_PAUSE = 10;
}
