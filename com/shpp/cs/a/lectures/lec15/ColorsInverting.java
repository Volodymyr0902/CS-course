package com.shpp.cs.a.lectures.lec15;

import acm.graphics.GImage;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;

public class ColorsInverting extends WindowProgram {

    private static final String imgLink = "The Dark Side of the Moon.jpeg";
    private GImage img = new GImage(imgLink);

    @Override
    public void run() {
        img.setSize(getWidth(), getHeight());
        add(img);
        addMouseListeners();
    }

    private GImage invertImage (GImage toInvert) {
        int[][] pixels = toInvert.getPixelArray();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red = GImage.getRed(pixels[i][j]);
                int green = GImage.getGreen(pixels[i][j]);
                int blue = GImage.getBlue(pixels[i][j]);

                int newRed = 255 - red;
                int newGreen = 255 - green;
                int newBlue = 255 - blue;
                pixels[i][j] = GImage.createRGBPixel(newRed, newGreen, newBlue);
            }
        }

        return new GImage(pixels);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (img == getElementAt(e.getX(), e.getY())) {
            img = invertImage(img);
            img.setSize(getWidth(), getHeight());
            add(img);
        }
    }
}
