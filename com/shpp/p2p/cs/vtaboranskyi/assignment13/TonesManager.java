package com.shpp.p2p.cs.vtaboranskyi.assignment13;

import acm.graphics.GImage;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This utility class is a set of methods for defining an image's background tone,
 * any pixel's tone and checking a pixel's affiliation to image's background.
 */
public class TonesManager implements SilhouettesConstants {

    /**
     * Retrieves pixel's "red" component, actually received as a shade-of-gray/black/white one.
     *
     * @param row Current pixel's row.
     * @param col Current pixel's column.
     * @param pixels Grayscale/binarized pixels matrix.
     * @return Pixel's declared "red" value.
     */
    static int getPixelTone(int row, int col, int[][] pixels) {
        return GImage.getRed(pixels[row][col]);
    }

    /**
     * Finds the most frequent value of background color of pixels matrix.
     * Assumed, the edge pixels frame mostly consist exactly of "background" pixels.
     *
     * @param pixels Grayscale pixels matrix.
     * @return The background's color's red component.
     */
    static int getBackgroundTone(int[][] pixels) {
        // The container for the frame's pixels tones.
        ArrayList<Integer> perimeterTones = new ArrayList<>();
        parseTopAndBottom(pixels, perimeterTones); // todo
        parseLeftAndRight(pixels, perimeterTones); // todo
        return getMostFrequentTone(perimeterTones); // todo
    }

    /**
     * Checks if a pixel is a background's part in a grayscale pixel's matrix.
     *
     * @param pixels Grayscale pixel's matrix.
     * @param row Current pixel's row.
     * @param col Current pixel's column.
     * @param backgroundTone Background's shade of gray.
     * @return <code>True</code>, if a pixel is within background's tones diapason.
     */
    static boolean isBackgroundInGrayscale(int[][] pixels, int row, int col, int backgroundTone) {
        return Math.abs(getPixelTone(row, col, pixels) - backgroundTone) < TONES_DIFFERENCE_THRESHOLD;
    }

    /**
     * Checks if a pixel is a background's part in a binary pixel's matrix.
     *
     * @param pixels Binary pixel's matrix.
     * @param row Current pixel's row.
     * @param col Current pixel's column.
     * @return <code>True</code>, if a pixel isn't white.
     */
    static boolean isNotBackgroundInBinary(int[][] pixels, int row, int col) {
        return getPixelTone(row, col, pixels) != MAX_RGB_VALUE;
    }

    /**
     * Retrieves the most frequent gray tone in pixels matrix's perimeter frame.
     *
     * @param perimeterTones Perimeter pixels frame's tones.
     * @return The most frequent tone in matrix's perimeter frame.
     */
    private static Integer getMostFrequentTone(ArrayList<Integer> perimeterTones) {
        return Collections.max(perimeterTones, (el1, el2) -> {
            // The frequencies of two elements in the list.
            int freq1 = Collections.frequency(perimeterTones, el1);
            int freq2 = Collections.frequency(perimeterTones, el2);
            return Integer.compare(freq1, freq2);
        });
    }

    /**
     * Collects tones values from pixels matrix's the most left and right columns.
     *
     * @param pixels Grayscale pixels matrix.
     * @param perimeterTones Container for perimeter pixels frame's tones.
     */
    private static void parseLeftAndRight(int[][] pixels, ArrayList<Integer> perimeterTones) {
        for (int i = 1; i < pixels.length; i++) {
            perimeterTones.add(getPixelTone(i, 0, pixels));
            perimeterTones.add(getPixelTone(i, pixels[0].length - 1, pixels));
        }
    }

    /**
     * Collects tones values from pixels matrix's the upper and the bottom rows.
     *
     * @param pixels Grayscale pixels matrix.
     * @param perimeterReds Container for perimeter pixels frame's tones.
     */
    private static void parseTopAndBottom(int[][] pixels, ArrayList<Integer> perimeterReds) {
        for (int i = 0; i < pixels[0].length; i++) {
            perimeterReds.add(getPixelTone(0, i, pixels));
            perimeterReds.add(getPixelTone(pixels.length - 1, i, pixels));
        }
    }
}
