package com.shpp.p2p.cs.vtaboranskyi.assignment12;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import com.shpp.cs.a.graphics.WindowProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Assignment12Part1 extends WindowProgram {

    public static final int VISIBLE_DIAPASON = 10;
    public static final int MIN_OBJECT_PXS = 100;

    public static int silhouettes;
    public static int recursionDepth;

    public static int[][] pixels;
    public static boolean[][] visited;

    public void run() {
        silhouettes = 0;
        recursionDepth = 0;

        GImage img = new GImage("stars.png");
        setSize((int) img.getWidth(), (int) img.getHeight());
        add(img);

        GImage grayed = toGrayscale(img);
        add(grayed);

        visited = new boolean[pixels.length][pixels[0].length];
        System.out.println("Total pxs number: " + pixels.length * pixels[0].length);

        int prevRed = 0, prevGreen = 0, prevBlue = 0;
        int red, green, blue;

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                red = GImage.getRed(pixels[i][j]);
                green = GImage.getGreen(pixels[i][j]);
                blue = GImage.getBlue(pixels[i][j]);


                if (!(i == 0 && j == 0) && !visited[i][j] && (Math.abs(red - prevRed) > VISIBLE_DIAPASON ||
                        Math.abs(green - prevGreen) > VISIBLE_DIAPASON ||
                        Math.abs(blue - prevBlue) > VISIBLE_DIAPASON)) {

                    startDFS(i, j, prevRed, prevGreen, prevBlue);
                    if (recursionDepth > MIN_OBJECT_PXS) {
                        silhouettes++;
                        System.out.println("Object " + silhouettes + ": " + recursionDepth);
                    }
                    recursionDepth = 0;
                }

                prevRed = red;
                prevGreen = green;
                prevBlue = blue;
                visited[i][j] = true;
            }
        }

        System.out.println("Found " + silhouettes + " silhouettes on the image.");
    }

    public static GImage toGrayscale(GImage image) {
        int[][] pixels = image.getPixelArray();

        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[row].length; ++col) {
                int intensity = (int) (0.3 * (double) GImage.getRed(pixels[row][col]) + 0.6 * (double) GImage.getGreen(pixels[row][col]) + 0.1 * (double) GImage.getBlue(pixels[row][col]) + 0.5D);
                pixels[row][col] = GImage.createRGBPixel(intensity, intensity, intensity);
            }
        }

        return new GImage(pixels);
    }

    private void startDFS(int i, int j, int prevRed, int prevGreen, int prevBlue) {

        if (i > 0 && !visited[i - 1][j]) {
            int upperRed = GImage.getRed(pixels[i - 1][j]);
            int upperGreen = GImage.getGreen(pixels[i - 1][j]);
            int upperBlue = GImage.getBlue(pixels[i - 1][j]);

            if (Math.abs(upperRed - prevRed) < VISIBLE_DIAPASON &&
                    Math.abs(upperGreen - prevGreen) < VISIBLE_DIAPASON &&
                    Math.abs(upperBlue - prevBlue) < VISIBLE_DIAPASON) {
                visited[i - 1][j] = true;
                recursionDepth++;
                startDFS(i - 1, j, upperRed, upperGreen, upperBlue);
            }
        }

        if (i < pixels.length - 1 && !visited[i + 1][j]) {
            int bottomRed = GImage.getRed(pixels[i + 1][j]);
            int bottomGreen = GImage.getGreen(pixels[i + 1][j]);
            int bottomBlue = GImage.getBlue(pixels[i + 1][j]);

            if (Math.abs(bottomRed - prevRed) < VISIBLE_DIAPASON &&
                    Math.abs(bottomGreen - prevGreen) < VISIBLE_DIAPASON &&
                    Math.abs(bottomBlue - prevBlue) < VISIBLE_DIAPASON) {
                visited[i + 1][j] = true;
                recursionDepth++;
                startDFS(i + 1, j, bottomRed, bottomGreen, bottomBlue);
            }
        }

        if (j > 0 && !visited[i][j - 1]) {
            int leftRed = GImage.getRed(pixels[i][j - 1]);
            int leftGreen = GImage.getGreen(pixels[i][j - 1]);
            int leftBlue = GImage.getBlue(pixels[i][j - 1]);

            if (Math.abs(leftRed - prevRed) < VISIBLE_DIAPASON &&
                    Math.abs(leftGreen - prevGreen) < VISIBLE_DIAPASON &&
                    Math.abs(leftBlue - prevBlue) < VISIBLE_DIAPASON) {
                visited[i][j - 1] = true;
                recursionDepth++;
                startDFS(i, j - 1, leftRed, leftGreen, leftBlue);
            }
        }

        if (j < pixels[i].length - 1 && !visited[i][j + 1]) {
            int rightRed = GImage.getRed(pixels[i][j + 1]);
            int rightGreen = GImage.getGreen(pixels[i][j + 1]);
            int rightBlue = GImage.getBlue(pixels[i][j + 1]);

            if (Math.abs(rightRed - prevRed) < VISIBLE_DIAPASON &&
                    Math.abs(rightGreen - prevGreen) < VISIBLE_DIAPASON &&
                    Math.abs(rightBlue - prevBlue) < VISIBLE_DIAPASON) {
                visited[i][j + 1] = true;
                recursionDepth++;
                startDFS(i, j + 1, rightRed, rightGreen, rightBlue);
            }
        }
    }
}
