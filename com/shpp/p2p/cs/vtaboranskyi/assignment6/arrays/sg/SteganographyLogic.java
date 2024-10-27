package com.shpp.p2p.cs.vtaboranskyi.assignment6.arrays.sg;

import acm.graphics.*;

/**
 * File: SteganographyLogic.java.
 * Program which can reveal image message
 * hidden inside any other image and
 * hide such secret message inside another image.
 * Main concept is using changed pixels' red channels'
 * odd/even values, which respectively correspond
 * to secret message's black/white pixels.
 * Specifications are in materials of level #6,
 * Task 6, Part 1.
 */
public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        // Source image RGBA values.
        int[][] maskPixels = source.getPixelArray();

        // Boolean values corresponding to red channel values.
        boolean[][] secretPixels = new boolean[maskPixels.length][maskPixels[0].length];

        for (int i = 0; i < maskPixels.length; i++) {
            for (int j = 0; j < maskPixels[i].length; j++) {
                // Pixel's red channel value.
                int red = GImage.getRed(maskPixels[i][j]);
                secretPixels[i][j] = red % 2 != 0;
            }
        }

        return secretPixels;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        // Source image RGBA values.
        int[][] pixels = source.getPixelArray();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                // Pixel's channel's values sequentially extracted.
                int red = GImage.getRed(pixels[i][j]);
                int green = GImage.getGreen(pixels[i][j]);
                int blue = GImage.getBlue(pixels[i][j]);

                // Slightly changed red channel value to follow the scheme.
                int newRed = reassignRed(message[i][j], red);

                // Visually same pixel but possibly with the new red channel value.
                pixels[i][j] = GImage.createRGBPixel(newRed, green, blue);
            }
        }

        return new GImage(pixels);
    }

    /**
     * Makes red channel value:
     * odd (if it wasn't so), so that it corresponds
     * to black pixel of the secret image;
     * even - thus it stands for the white one.
     * @param secretPixel True if corresponding pixel is paint black.
     * @param red Original pixel's red channel's value.
     * @return Modified pixel's red channel's value.
     */
    private static int reassignRed(boolean secretPixel, int red) {
        if (secretPixel) {  // true - black - odd
            return red % 2 == 0 ? red + 1 : red;
        } else {  // false - white - even
            return red % 2 != 0 ? red - 1 : red;
        }
    }
}
