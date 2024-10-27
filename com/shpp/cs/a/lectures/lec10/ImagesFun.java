package com.shpp.cs.a.lectures.lec10;

import acm.graphics.GImage;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;

public class ImagesFun extends WindowProgram {

    public static final String FILE_NAME = "placeholder.jpeg";
    GImage forest = null;

    public void run() {
        forest = new GImage(FILE_NAME);
        forest.setSize(getWidth(), getHeight());
        add(forest);
        //addMouseListeners();
        //setNight();
        //doPsycho();
        fade();
    }

    private void fade() {
        while (!isWhite(forest)) {
            forest = fadeOneStep(forest);
            forest.setSize(getWidth(), getHeight());
            add(forest);
            pause(10);
        }
        println("Now it's white!");
    }

    private boolean isWhite(GImage orig) {
        int[][] pixels = orig.getPixelArray();
        int pixelsNum = pixels[0].length * pixels.length;
        int pxWhiteCounter = 0;

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red = GImage.getRed(pixels[i][j]);
                int green = GImage.getGreen(pixels[i][j]);
                int blue = GImage.getBlue(pixels[i][j]);
                int rgb = red + green + blue;

                if (rgb == 255 * 3) {
                    pxWhiteCounter++;
                }
            }
        }

        return pixelsNum == pxWhiteCounter;
    }

    private GImage fadeOneStep(GImage orig) {
        int[][] pixels = orig.getPixelArray();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red = GImage.getRed(pixels[i][j]);
                int green = GImage.getGreen(pixels[i][j]);
                int blue = GImage.getBlue(pixels[i][j]);

                int newRed = red < 255 ? ++red : red;
                int newGreen = green < 255 ? ++green : green;
                int newBlue = blue < 255 ? ++blue : blue;

                pixels[i][j] = GImage.createRGBPixel(newRed, newGreen, newBlue);
            }
        }

        return new GImage(pixels);
    }

    private void doPsycho() {
        while (true) {
            removeAll();
            forest.setSize(getWidth(), getHeight());
            add(forest);
            forest = psychodelify(forest);
            pause(1000);
        }
    }

    private void setNight() {
        while (!isBlack(forest)) {
            forest = getDarker(forest);
            forest.setSize(getWidth(), getHeight());
            add(forest);
            pause(100);
        }
        println("Now it's dark!");
    }

    private GImage getDarker(GImage orig) {
        int[][] pixels = orig.getPixelArray();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red = GImage.getRed(pixels[i][j]);
                int green = GImage.getGreen(pixels[i][j]);
                int blue = GImage.getBlue(pixels[i][j]);

                int newRed = red > 0 ? red - 1 : red;
                int newGreen = green > 0 ? green - 1 : green;
                int newBlue = blue > 0 ? blue - 1 : blue;

                pixels[i][j] = GImage.createRGBPixel(newRed, newGreen, newBlue);
            }
        }

        return new GImage(pixels);
    }

    private boolean isBlack (GImage faded) {
        int[][] pixels = faded.getPixelArray();
        int pxBlackCounter = 0;
        int pixelsNum = pixels[0].length * pixels.length;

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red = GImage.getRed(pixels[i][j]);
                int green = GImage.getGreen(pixels[i][j]);
                int blue = GImage.getBlue(pixels[i][j]);
                int rgb = red + green + blue;

                if (rgb == 0) {
                    pxBlackCounter++;
                }
            }
        }

        return pxBlackCounter == pixelsNum;
    }

    public void mousePressed(MouseEvent e) {
        if (forest == getElementAt(e.getX(), e.getY())) {
            forest = disableGreen(forest);
            add(forest);
        }
    }

    private GImage disableGreen(GImage orig) {
        int[][] pixels = orig.getPixelArray();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red = GImage.getRed(pixels[i][j]);
                int green = GImage.getGreen(pixels[i][j]);
                int blue = GImage.getBlue(pixels[i][j]);

                pixels[i][j] = GImage.createRGBPixel(red, 0, blue);
            }
        }

        return new GImage(pixels);
    }

    private GImage psychodelify(GImage orig) {
        int[][] pixels = orig.getPixelArray();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red = GImage.getRed(pixels[i][j]); //green b
                int green = GImage.getGreen(pixels[i][j]); // blue r
                int blue = GImage.getBlue(pixels[i][j]); // red g

                pixels[i][j] = GImage.createRGBPixel(green, blue, red); //brg rgb
            }
        }

        return new GImage(pixels);
    }
}
