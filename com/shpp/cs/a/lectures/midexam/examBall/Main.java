package com.shpp.cs.a.lectures.midexam.examBall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static acm.util.JTFTools.pause;

public class Main {

    public static void main(String[] args) {
        MyProg myProg = new MyProg();
        myProg.setDefaultCloseOperation(MyProg.EXIT_ON_CLOSE);
        myProg.setSize(600, 600);
        myProg.setLayout(new FlowLayout());
        myProg.setVisible(true);

//        while (true) {
//            if (!isMousePressed)
//                pause(1);
//            if (isMousePressed) {
//                doAction();
//            }
//        }
    }
}

class MyProg extends JFrame implements MouseListener {

    int controller = 0;
    boolean isMousePressed = false;
    JLabel label;
    long startTime = 0;

    MyProg() {
        this.label = new JLabel("Hey");
        label.setSize(200, 400);
        label.setOpaque(true);
        label.setBackground(Color.RED);
        label.addMouseListener(this);
        add(label);
    }

    public void doAction() {
        System.out.println("Controller is: " + controller);
        pause(500);
        controller++;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");
        startTime = System.currentTimeMillis();
        isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
        long clickDuration = System.currentTimeMillis() - startTime;
        System.out.println(clickDuration);
        isMousePressed = false;
        controller = 0;
        System.out.println();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
