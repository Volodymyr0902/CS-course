package com.shpp.cs.a.lectures.lec11;

import acm.graphics.GImage;
import com.shpp.cs.a.graphics.WindowProgram;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;

public class ImageShow extends WindowProgram {

    public static final String IMG_SOURCE = "istockphoto-902929330-612x612.jpeg";

    JButton clear, show;
    JSlider imgSize;
    GImage img;

    @Override
    public void init() {
        clear = new JButton("CLEAR");
        add(clear, NORTH);

        show = new JButton("SHOW");
        add(show, NORTH);

        add(new JLabel("SET SIZE: "), SOUTH);
        imgSize = new JSlider(0, Math.min(getWidth(), getHeight()), getWidth() / 2);
        add(imgSize, SOUTH);

        addActionListeners();
    }

    @Override
    public void run() {
        img = new GImage(IMG_SOURCE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            removeAll();
        } else if (e.getSource() == show) {
            img.setSize(imgSize.getValue(), imgSize.getValue());
            img.setLocation(getWidth() / 2.0 - img.getWidth() / 2,
                    getHeight() / 2.0 - img.getHeight() / 2);
            add(img);
        }
    }
}
