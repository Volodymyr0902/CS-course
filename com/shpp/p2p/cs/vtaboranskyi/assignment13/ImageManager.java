package com.shpp.p2p.cs.vtaboranskyi.assignment13;

import acm.graphics.GImage;

/**
 * This utility class is used for preparing an image so that it can be parsed
 * for counting silhouettes. This includes converting to grayscale and then to binary,
 * strictly in such sequence.
 */
public class ImageManager implements SilhouettesConstants {

    /**
     * Prepares an image for silhouettes' searching.
     *
     * @param args Other programme's argument(s).
     * @return Prepared (binarized) pixels matrix.
     */
    public static int[][] prepareImage(String[] args) {
        String fileName = checkFilename(args);
        return toBinary(toGrayscale(new GImage(fileName).getPixelArray()));
    }

    /**
     * Checks if received argument(s) isn't empty or null.
     *
     * @param args Other programme's argument(s).
     * @return Filename from CLI if such was input, else the default one.
     */
    private static String checkFilename(String[] args) { //todo
        return args != null && args.length != 0 && args[0] != null ? args[0] : DEFAULT_FILENAME;
    }

    /**
     * Fades current image's colors so that they all are shades of gray,
     * then assigns such value to every RGB component.
     * Besides, transparent pixels are always converted to white color.
     *
     * @param pixels Image's pixels matrix.
     * @return A pixels matrix with all color components converted to grayscale.
     */
    private static int[][] toGrayscale(int[][] pixels) { //todo
        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[row].length; ++col) {

                if (GImage.getAlpha(pixels[row][col]) <= TRANSPARENCY_MAX_THRESHOLD) {
                    pixels[row][col] = GImage.createRGBPixel(MAX_RGB_VALUE, MAX_RGB_VALUE, MAX_RGB_VALUE);
                } else {
                    int grayed = getGrayed(row, col, pixels);
                    pixels[row][col] = GImage.createRGBPixel(grayed, grayed, grayed);
                }
            }
        }

        return pixels;
    }

    /**
     * Converts pixel's color to a shade of gray, adding proportional components parts of original values.
     *
     * @param row Current pixel's row.
     * @param col Current pixel's column.
     * @param pixels Image's pixels matrix.
     * @return Grayed component value.
     */
    private static int getGrayed(int row, int col, int[][] pixels) {
        return (int) (RED_GRAYSCALE_COEFFICIENT * (double) GImage.getRed(pixels[row][col]) +
                GREEN_GRAYSCALE_COEFFICIENT * (double) GImage.getGreen(pixels[row][col]) +
                BLUE_GRAYSCALE_COEFFICIENT * (double) GImage.getBlue(pixels[row][col]));
    }

    /**
     * Converts grayscale pixels matrix to binary one,
     * where background ones are marked white and non-background - black.
     *
     * @param pixels Grayscale pixels matrix.
     * @return Binary pixels matrix.
     */
    private static int[][] toBinary(int[][] pixels) {
        int backgroundTone = TonesManager.getBackgroundTone(pixels);
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                if (TonesManager.isBackgroundInGrayscale(pixels, row, col, backgroundTone))
                    pixels[row][col] = GImage.createRGBPixel(MAX_RGB_VALUE, MAX_RGB_VALUE, MAX_RGB_VALUE);
                else
                    pixels[row][col] = GImage.createRGBPixel(MIN_RGB_VALUE, MIN_RGB_VALUE, MIN_RGB_VALUE);
            }
        }
        return pixels;
    }

    /**
     * Calculates total number of pixels a matrix consists of.
     *
     * @return Total pixels number.
     */
    static int getTotalPixels(int[][] pixels) {
        return pixels.length * pixels[0].length;
    }
}
