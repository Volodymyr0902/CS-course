package com.shpp.cs.a.lectures.lec06;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class FallingBall extends WindowProgram {

    public static final double BALL_SIZE = 80;
    public static final double X_SPEED = 1.0;
    public static final double GRAVITY = 0.4;
    public static final double PAUSE_TIME = 1000.0 / 62;
    public static final double ELASTICITY = 0.7;

    @Override
    public void run() {
        GOval ball = createBall();
        dropBall(ball, X_SPEED);
    }

    private void dropBall(GOval ball, double dx) {
        double dy = 0;
        while (ballIsNotAtFinish(ball)) {
            ball.move(dx, dy);
            dy += GRAVITY;
            pause(PAUSE_TIME);
            if (ballReachesGround(ball) && dy > 0) {
                dy *= -ELASTICITY;
            }
        }
    }

    private boolean ballIsNotAtFinish(GOval ball) {
        return (ball.getX() + ball.getWidth()) < getWidth();
    }

    private boolean ballReachesGround(GOval ball) {
        return (ball.getY() + ball.getHeight()) >= getHeight();
    }

    private GOval createBall() {
        GOval ball = new GOval(0, 0, BALL_SIZE, BALL_SIZE);
        ball.setColor(Color.RED);
        ball.setFilled(true);
        add(ball);
        return ball;
    }
}
