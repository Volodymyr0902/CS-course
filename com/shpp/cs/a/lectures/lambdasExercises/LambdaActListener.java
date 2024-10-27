package com.shpp.cs.a.lectures.lambdasExercises;

import javax.swing.*;
import java.awt.*;

public class LambdaActListener {
    public static void main(String[] args) {
        MyFrame f1 = new MyFrame();
    }
}

class MyFrame extends JButton{
    private JButton b1, b2;

    public MyFrame() {
        b1 = new JButton("Click Me!");
        b1.setBounds(100, 100, 200, 100);
        b1.addActionListener((l -> b1.setForeground(Color.RED)));
        this.add(b1);

        b2 = new JButton("And Me!");
        b2.setBounds(100, 200, 200, 100);
        b2.addActionListener((l -> b2.setForeground(Color.BLUE)));
        this.add(b2);

        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
    }
}
