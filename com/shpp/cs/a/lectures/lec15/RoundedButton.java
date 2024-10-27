package com.shpp.cs.a.lectures.lec15;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getModel().isArmed() ? Color.GRAY : Color.LIGHT_GRAY);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.add(new RoundedButton("Click Me"));
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}