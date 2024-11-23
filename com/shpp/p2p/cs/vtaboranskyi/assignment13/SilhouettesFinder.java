package com.shpp.p2p.cs.vtaboranskyi.assignment13;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This utility class is used for finding the number of silhouettes on an image.
 * <p>
 * A silhouette is a group of pixels which have RGB values within allowed diapason and are located inextricably.
 * The rest pixels not belonging to such silhouette must be either background or another silhouette.
 * <p>
 * The only method available outside is <code>findSilhouettes()</code> which does exactly described calculation.
 */
public class SilhouettesFinder implements SilhouettesConstants {

    /**
     * Parses the pixels matrix, where the outer loop stands for the rows and the inner one
     * for the columns. Every step current pixel is marked as visited.
     * Every time non-background pixel is met, and it's been unvisited the BFS process starts.
     * Such process is saved as a silhouette if only it had enough pixels, that's evaluated by the coefficient.
     * Then the iteration continues.
     *
     * @return The number of silhouettes found on the current image.
     */
    public static int findSilhouettes(String[] args) {
        // All image's pixels in matrix, already binarized.
        int[][] preparedPixels = ImageManager.prepareImage(args); // todo ex field

        // Pixels' parsing tracking matrix.
        boolean[][] visitedPixels = new boolean[preparedPixels.length][preparedPixels[0].length]; // todo ex field
        int silhouettes = 0;

        for (int row = 0; row < preparedPixels.length; row++) {
            for (int col = 0; col < preparedPixels[0].length; col++) {

                if (!visitedPixels[row][col] && TonesManager.isNotBackgroundInBinary(preparedPixels, row, col))
                    if (areEnoughPxs(row, col, preparedPixels, visitedPixels))
                        silhouettes++;

                visitedPixels[row][col] = true;
            }
        }

        return silhouettes;
    }

    /**
     * Checks if parsed cluster has enough pixels to be treated a silhouette.
     *
     * @param row Pixels cluster's start row.
     * @param col Pixels cluster's start column.
     * @param preparedPixels All image's pixels.
     * @param visitedPixels Pixels tracking matrix.
     * @return <code>True</code>, if such cluster is large enough.
     */
    private static boolean areEnoughPxs(int row, int col, int[][] preparedPixels, boolean[][] visitedPixels) {
        return startBFS(row, col, preparedPixels, visitedPixels) >
                SILHOUETTE_PERCENTAGE_OF_TOTAL_PXS * ImageManager.getTotalPixels(preparedPixels);
    }

    /**
     * Calculates number of pixels which are adjacent and have similar shade using
     * iterative BFS algorithm and a queue for pixels as nodes (checks pixels level by level).
     *
     * @param row Current row in matrix.
     * @param col Current column in matrix.
     * @return The number of similar adjacent pixels.
     */
    private static int startBFS(int row, int col, int[][] pixels, boolean[][] visitedPixels) {
        // Container for same cluster's pixels to be checked.
        Queue<int[]> pixelsToVisit = new LinkedList<>(); // todo ex field
        pixelsToVisit.offer(new int[]{row, col});
        int pixelsCounter = 0;

        while (!pixelsToVisit.isEmpty()) {
            // Row and column in pixels matrix indicating a pixel to be checked.
            int currRow = pixelsToVisit.peek()[0];
            int currCol = pixelsToVisit.peek()[1];
            pixelsToVisit.poll();
            pixelsCounter = addAdjacentPixels(currRow, currCol, pixelsCounter, pixels, visitedPixels, pixelsToVisit);
        }

        return pixelsCounter;
    }

    /**
     * Check if 8 pixels adjacent to the current one all-around are within image's
     * dimensions and are non-background, then adds all the appropriate to the queue.
     *
     * @return Counter value, possibly increased.
     */
    private static int addAdjacentPixels(int row, int col, int pixelsCounter,
                                         int[][] pixels, boolean[][] visitedPixels, Queue<int[]> pixelsToVisit) {
        for (int i = 0; i < ADJACENT_PXS_NUM; i++) {
            AdjacentPixel adjPixel = new AdjacentPixel(row, col, i); // todo instead of arrays
            int deltaRow = adjPixel.getRow();
            int deltaCol = adjPixel.getCol();

            if (adjPixel.isPixelWithinMatrix(pixels)) {
                if (!visitedPixels[deltaRow][deltaCol] &&
                        TonesManager.isNotBackgroundInBinary(pixels, deltaRow, deltaCol)) { // todo condition
                    visitedPixels[deltaRow][deltaCol] = true;
                    pixelsToVisit.offer(new int[]{deltaRow, deltaCol});
                    pixelsCounter++;
                }
            }
        }

        return pixelsCounter;
    }
}
