package com.shpp.p2p.cs.vtaboranskyi.assignment13;

import acm.graphics.GImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is used for finding the number of silhouettes on an image.
 * <p>
 * A silhouette is a group of pixels which have RGB values within allowed diapason and are located inextricably.
 * The rest pixels not belonging to such silhouette must be either background or another silhouette.
 * <p>
 * The only method available outside is <code>findSilhouettes()</code> which does exactly described calculation.
 */
public class SilhouettesCounter implements SilhouettesConstants {

    /**
     * Mixed RGB values of the current image's pixels.
     */
    private int[][] pixels;

    /**
     * Abstract reflection of pixels matrix to keep track of the ones already parsed.
     */
    private boolean[][] visited;

    /**
     * The container for adjacent pixels of the same shade.
     */
    private Queue<int[]> pixelsToVisit;

    /**
     * The background's color's red component.
     */
    private int backRed;

    /**
     * The only constructor which must receive third-party programme's argument(s).
     * Then initializes all the class members.
     *
     * @param args Other programme's argument(s).
     */
    public SilhouettesCounter(String[] args) {
        String fileName = isGivenLink(args) ? args[0] : DEFAULT_FILENAME;
        this.pixels = makeGray(fileName);
        this.visited = new boolean[pixels.length][pixels[0].length];
        this.backRed = getBackgroundRed();
        this.pixelsToVisit = new LinkedList<>();
    }

    /**
     * Checks if received argument(s) isn't empty or null.
     *
     * @param args Other programme's argument(s).
     * @return <code>True</code>, if arguments array isn't empty and null, so the first element.
     */
    private boolean isGivenLink(String[] args) {
        return args != null && args.length != 0 && args[0] != null;
    }

    /**
     * Parses the pixels matrix, where the outer loop stands for the rows and the inner one
     * for the columns. Every step red component is reassigned and current pixel is marked as visited.
     * Every time different color's pixel is met, and it's been unvisited the BFS process starts.
     * Such process is saved as an object if only it had enough pixels, that's evaluated by the coefficient.
     * Then the iteration continues.
     *
     * @return The number of silhouettes found on the current image.
     */
    public int findSilhouettes() {
        int silhouettes = 0;

        // The red component value of the previous pixel and of the current one, respectively.
        int prevRed = 0;
        int red;

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                red = GImage.getRed(pixels[i][j]);

                if (!(i == 0 && j == 0) && !visited[i][j] && isSameTone(prevRed, red, backRed))
                    if (startBFS(i, j) > SILHOUETTE_COEFFICIENT * getTotalPixels())
                        silhouettes++;

                prevRed = red;
                visited[i][j] = true;
            }
        }

        return silhouettes;
    }

    /**
     * Calculates total number of pixels a matrix consists of.
     *
     * @return Total pixels number.
     */
    private int getTotalPixels() {
        return pixels.length * pixels[0].length;
    }

    /**
     * Finds the most frequent value of background color of pixels matrix.
     * Assumed, the edge pixels frame mostly consist exactly of "background" pixels.
     *
     * @return The background's color's red component.
     */
    private int getBackgroundRed() {
        // The container for the frame's pixels red component values
        ArrayList<Integer> perimeterReds = new ArrayList<>();

        // Parsing the upper and the bottom rows.
        for (int i = 0; i < pixels[0].length; i++) {
            perimeterReds.add(GImage.getRed(pixels[0][i]));
            perimeterReds.add(GImage.getRed(pixels[pixels.length - 1][i]));
        }

        // Parsing the left and the right columns, the corner pixels excluded.
        for (int i = 1; i < pixels.length; i++) {
            perimeterReds.add(GImage.getRed(pixels[i][0]));
            perimeterReds.add(GImage.getRed(pixels[i][pixels[0].length - 1]));
        }

        return Collections.max(perimeterReds, (el1, el2) -> {
            // The frequencies of two elements in the list.
            int freq1 = Collections.frequency(perimeterReds, el1);
            int freq2 = Collections.frequency(perimeterReds, el2);
            return Integer.compare(freq1, freq2);
        });
    }

    /**
     * Fades current image's colors so that they all are shades of gray by multiplying its
     * RGB values by respective coefficients and assigning such value to every RGB component.
     *
     * @param filename The path to the image file.
     * @return A pixels matrix with all color components converted and equalized.
     */
    private int[][] makeGray(String filename) {
        pixels = new GImage(filename).getPixelArray();

        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[row].length; ++col) {
                // The sum of original value proportionally multiplied by coefficients.
                int grayed = (int) (RED_COEFFICIENT * (double) GImage.getRed(pixels[row][col]) +
                        GREEN_COEFFICIENT * (double) GImage.getGreen(pixels[row][col]) +
                        BLUE_COEFFICIENT * (double) GImage.getBlue(pixels[row][col]));

                // Equalizing current pixel by assigning equal components.
                pixels[row][col] = GImage.createRGBPixel(grayed, grayed, grayed);
            }
        }

        return pixels;
    }

    /**
     * Calculates number of pixels which are adjacent and have similar shade using
     * iterative BFS algorithm and a queue for pixels as nodes (checks pixels level by level).
     *
     * @param row Current row in matrix.
     * @param col Current column in matrix.
     * @return The number of similar adjacent pixels.
     */
    private int startBFS(int row, int col) {
        int pixelsCounter = 0;
        pixelsToVisit.offer(new int[]{row, col});

        while (!pixelsToVisit.isEmpty()) {
            // Row and column in pixels matrix indicating a pixel to be checked.
            int currRow = pixelsToVisit.peek()[0];
            int currCol = pixelsToVisit.peek()[1];

            // Red component of current pixel.
            int red = GImage.getRed(pixels[currRow][currCol]);
            pixelsToVisit.poll();
            pixelsCounter = addAdjacentPixels(currRow, currCol, red, pixelsCounter);
        }

        return pixelsCounter;
    }

    /**
     * Check if 4 pixels adjacent to the current one (up, down, left, right) are
     * of a shade within allowed diapason and adds all the appropriate to the queue.
     *
     * @param currRow Current pixel's row.
     * @param currCol Current pixel's column.
     * @param red Red component of current pixel.
     * @param pixelsCounter Counter for current pixels' cluster.
     * @return Counter value, possibly increased.
     */
    private int addAdjacentPixels(int currRow, int currCol, int red, int pixelsCounter) {
        for (int i = 0; i < ADJACENT_PXS_NUM; i++) {
            // Pixel number indicate shifts on each axis, respectively for Y and X.
            int dRow = currRow + ROW_ROUTES[i];
            int dCol = currCol + COL_ROUTES[i];

            if (dRow >= 0 && dRow < pixels.length && dCol >= 0 && dCol < pixels[0].length) {
                // Red component of an adjacent pixel.
                int nextRed = GImage.getRed(pixels[dRow][dCol]);

                if (!visited[dRow][dCol] && isSameTone(red, nextRed, backRed)) {
                    visited[dRow][dCol] = true;
                    pixelsToVisit.offer(new int[]{dRow, dCol});
                    pixelsCounter++;
                }
            }
        }

        return pixelsCounter;
    }

    /**
     * Checks if a pixel is within the same color diapason as another one and not a part of the background.
     * Actually, such component value only indicates a shade of gray, common for all the 3 components of one pixel.
     * The one evaluated is just the first one, that's why it refers to "red".
     *
     * @param currRed The "red" component of the current pixel.
     * @param nextRed The "red" component of an adjacent pixel.
     * @param backRed The background's color's red component.
     * @return True if next pixel is similar and isn't background's part.
     */
    private boolean isSameTone(int currRed, int nextRed, int backRed) {
        return Math.abs(nextRed - currRed) < VISIBILITY_THRESHOLD && Math.abs(nextRed - backRed) > VISIBILITY_THRESHOLD;
    }
}
