package com.shpp.cs.a.lectures.midexam.examBall;

import acm.graphics.GObject;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BouncingBallsGame extends WindowProgram implements BallsConstants {
    double ballDiameter, ballRadius, xCentered, yCentered;
    int colorValue = 255;
    MouseEvent pressEvent;
    boolean isMousePressed = false;
    GObject selectedObject;
    double velocityY = 0;

    @Override
    public void run() {
        addMouseListeners();
        while (true) {
            doAction();
        }
    }

    public void doAction() {
        if (!isMousePressed) {
            pause(1);
        }
        if (selectedObject != null) {
            reverseDropBall();
        } else if (isMousePressed) {
            GOval ball = createBall();
            add(ball);
            while (isMousePressed && ballDiameter < MAX_BALL_DIAMETER - BALL_SIZE_STEP) {
                pause(50);
                makeBallBigger(ball);
            }
            dropBall(ball);
        }
    }

    public void dropBall(GOval ball) {
        double dy = 0;
        while (!isMousePressed) {
            ball.move(0, dy);
            dy += 0.4;
            pause(40);
            if ((ball.getY() + ball.getHeight()) >= getHeight() && (dy > 0)) {
                dy *= -0.5;
            }
        }
    }

    public void makeBallBigger(GOval ball) {
        ballDiameter += BALL_SIZE_STEP;
        xCentered -= BALL_SIZE_STEP / 2;
        yCentered -= BALL_SIZE_STEP / 2;
        ball.setBounds(xCentered, yCentered, ballDiameter, ballDiameter);
        colorValue -= 5;
        ball.setColor(new Color(colorValue, colorValue, colorValue));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        selectedObject = getElementAt(e.getX(), e.getY());
        pressEvent = e;
        if (selectedObject == null) {
            isMousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
    }

    public void reverseDropBall() {
        if (selectedObject != null) {
            velocityY = -10;
            while (true) {
                selectedObject.move(0, velocityY);
                velocityY += 0.4;
                pause(40);
                if (selectedObject.getY() <= 0 && velocityY < 0) {
                    velocityY *= -0.7;
                }
                if (selectedObject.getY() >= getHeight()) {
                    break;
                }
            }
        }
    }

    public GOval createBall() {
        ballDiameter = MIN_BALL_DIAMETER;
        xCentered = pressEvent.getX() - ballDiameter / 2;
        yCentered = pressEvent.getY() - ballDiameter / 2;
        GOval ball = new GOval(xCentered, yCentered, ballDiameter, ballDiameter);
        ball.setColor(new Color(colorValue, colorValue, colorValue));
        ball.setFilled(true);
        return ball;
    }
}