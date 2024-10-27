package com.shpp.cs.a.lectures.midexam.examBall;

import acm.graphics.GObject;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

// При натисканні мишкою на вікно на місці кліку повинен з’явитися м’ячик.
// Відповідно можна створювати багато м’ячиків, котрі мають падати донизу незалежно один від одного,
// підкоряючись законам гравітації, і після контакту з землею підстрибувати вгору від землі
// (зрозуміло що не настільки високо, як впали).
//
// Розмір м’ячика залежить від того, як швидко відпускається кнопка миші - чим довше тримаємо, тим більше розмір.
// Колір м’ячика теж від цього залежить. Найменший м’ячик - (майже) білий. Чим довше тримаємо, тим чорніше.
//
// При кліці на м’ячик (після його створення) він повинен почати “падати” вгору і так само
// самостійно підстрибнути від верхнього краю, як раніше підстрибував від “землі”.

public class Balls extends WindowProgram implements BallsConstants {

    double ballDiameter, xCentered, yCentered;
    int colorValue = 255;
    MouseEvent pressEvent;
    boolean isMousePressed = false;
    boolean isMouseClicked = false;
    GObject selectedObject;

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

        if (isMouseClicked) {
            System.out.println("from if");
            reverseDropBall();
            isMouseClicked = false;
        }

        if (isMousePressed) {
            GOval ball = createBall();
            add(ball);
            System.out.println("hey");
            while (isMousePressed && ballDiameter < MAX_BALL_DIAMETER - BALL_SIZE_STEP) {
                pause(50);
                makeBallBigger(ball);
            }
            dropBall(ball);
            colorValue = 255;
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
        System.out.println("mousePressed");
        selectedObject = getElementAt(e.getX(), e.getY());
        pressEvent = e;
        if (selectedObject == null) {
            isMousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
        if (selectedObject != null) {
            isMouseClicked = true;
        }
        isMousePressed = false;
        System.out.println(isMouseClicked);
    }

    public void reverseDropBall() {
        double dy = 0;
        while (!isMousePressed) {
            selectedObject.move(0, dy);
            dy -= 0.4;
            pause(40);
            if (selectedObject.getY() <= 0 && dy < 0) {
                dy *= -0.5;
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
