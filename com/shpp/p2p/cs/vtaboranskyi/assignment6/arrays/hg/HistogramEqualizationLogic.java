package com.shpp.p2p.cs.vtaboranskyi.assignment6.arrays.hg;

/**
 * File: HistogramEqualizationLogic.java.
 * Program which creates abstract histograms
 * (simple and cumulative) of image's luminances.
 * Then evenly equalizes all luminances' values
 * within diapason of 0 - 255.
 * Specifications are in materials of level #6,
 * Task 6, Part 2.
 */
public class HistogramEqualizationLogic {

    // Pixel's luminance maximum possible value.
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        // Numbers of pixels with certain luminance within diapason of 0 - 255.
        int[] histogram = new int[256];

        for (int[] luminanceRow : luminances) {
            for (int singleLuminance : luminanceRow) {
                // Certain luminance value increased every time matching pixel is met.
                histogram[singleLuminance]++;
            }
        }
        return histogram;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        // Cumulative values of an image simple histogram.
        int[] cumulative = new int[histogram.length];

        // Assigning 1st value to simplify loop-assigning.
        cumulative[0] = histogram[0];

        for (int i = 1; i < histogram.length; i++) {
            cumulative[i] = histogram[i] + cumulative[i - 1];
        }

        return cumulative;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        /* As all pixels' rows lengths are equal,
        we can get any as a multiplier. */
        return luminances.length * luminances[0].length;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        int[][] equalizedLuminances = new int[luminances.length][luminances[0].length];
        int[] cumulative = cumulativeSumFor(histogramFor(luminances));
        int totalPixels = totalPixelsIn(luminances);

        for (int i = 0; i < luminances.length; i++) {
            for (int j = 0; j < luminances[i].length; j++) {
                // Pixels number with luminance <= luminances[i][j].
                double fractionSmaller =  (double) cumulative[luminances[i][j]] / totalPixels;

                // Equalized single luminance value.
                equalizedLuminances[i][j] = (int) (MAX_LUMINANCE * fractionSmaller);
            }
        }
        return equalizedLuminances;
    }
}
