package com.shpp.p2p.cs.vtaboranskyi.assignment6.arrays.tm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * File: ToneMatrixLogic.java.
 * Program which plays all chosen samples
 * in a column if such were turned on.
 * Specifications are in materials of level #6,
 * Task 6, Part 3.
 */
public class ToneMatrixLogic {

    // Maximum possible original samples number in a column.
    public static final int MAX_SAMPLES_NUMBER = 16;

    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        // Set of samples chosen to be played.
        ArrayList<double[]> samplesToPlay = collectTurnedOnSamples(toneMatrix, column, samples);

        // Silence/single original sample/mix of samples.
        result = fillFinalSample(samplesToPlay, result);

        return result;
    }

    /**
     * Builds list of original samples chosen
     * to be played, using boolean flags.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column The column number that is currently being played.
     * @param samples A sound sample corresponding to all notes currently being played.
     * @return List of chosen original samples in a column.
     */
    private static ArrayList<double[]> collectTurnedOnSamples(boolean[][] toneMatrix, int column, double[][] samples) {
        ArrayList<double[]> samplesToPlay = new ArrayList<>();
        for (int i = 0; i < MAX_SAMPLES_NUMBER; i++) {
            if (toneMatrix[i][column]) {
                samplesToPlay.add(samples[i]);
            }
        }
        return samplesToPlay;
    }

    /**
     * Converts list of samples to an array depending on its content:
     * - no samples chosen - all 0s - silence;
     * - 1 sample chosen - play it;
     * - >1 samples chosen - mix (normalize) them;
     *
     * @param samplesToPlay List of chosen original samples in a column.
     * @param result Empty array to become a converted sample.
     * @return A converted sample.
     */
    private static double[] fillFinalSample(ArrayList<double[]> samplesToPlay, double[] result) {
        switch (samplesToPlay.size()) {
            case 0:
                Arrays.fill(result, 0);
                return result;
            case 1:
                return samplesToPlay.getFirst();
            default:
                result = mixSamples(samplesToPlay);
                // In case mixed values aren't within diapason of [-1 to 1].
                if (Arrays.stream(result).max().getAsDouble() > 1 ||
                        Arrays.stream(result).min().getAsDouble() < -1) {
                    normalizeSample(result);
                }
                return result;
        }
    }

    /**
     * Divides all mix's values by its greatest modulus
     * to ensure every value is within diapason of [-1, 1].
     *
     * @param mixedSample New sample made of chosen ones.
     */
    private static void normalizeSample(double[] mixedSample) {
        double maxIntensity = findMaxIntensity(mixedSample);
        for (int i = 0; i < mixedSample.length; i++) {
            mixedSample[i] /= maxIntensity;
        }
    }

    /**
     * Finds the most intensive wave value, despite its arithmetical sign.
     *
     * @param mixedSample New sample made of chosen ones.
     * @return Sample with all the values within diapason of [-1, 1].
     */
    private static double findMaxIntensity(double[] mixedSample) {
        // Max and min mix's values, respectively.
        double max = Arrays.stream(mixedSample).max().getAsDouble();
        double min = Arrays.stream(mixedSample).min().getAsDouble();

        if (min >= 0) {
            // If min is positive, max must be the greatest.
            return max;
        } else {
            // The greatest wave value in positive dimension.
            double maxModulus = Math.max(max, -min);
            // Absolute greatest wave value.
            return maxModulus == -min ? min : max;
        }
    }

    /**
     * Creates new sample, sequentially adding original
     * samples values with same indexes.
     *
     * @param samplesToPlay List of chosen original samples in a column.
     * @return New sample based on chosen ones.
     */
    private static double[] mixSamples(ArrayList<double[]> samplesToPlay) {
        // As all original samples lengths are equal, we can get any of these.
        double[] mixedSample = new double[samplesToPlay.getFirst().length];

        for (double[] sample : samplesToPlay) {
            for (int i = 0; i < mixedSample.length; i++) {
                mixedSample[i] += sample[i];
            }
        }
        return mixedSample;
    }
}
