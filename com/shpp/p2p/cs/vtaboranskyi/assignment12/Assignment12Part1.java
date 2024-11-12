package com.shpp.p2p.cs.vtaboranskyi.assignment12;

import acm.graphics.GImage;
import java.util.ArrayList;
import java.util.OptionalDouble;

/**
 * File: Assignment12Part1.java.
 * Program which parses an image as a matrix of pixels and finds number of silhouettes on it
 * (pixels groups, which color is different from the background's), using recursive DFS algorithm.
 * The path to the tested image file must be set as the first argument after class name
 * while starting program via command line. Otherwise, the default image will be parsed.
 * Depending on specific images particularities, the constants below may be tuned to filter
 * silhouettes' size and the color's threshold.
 * Besides, the stack size must be increased to support recursive DFS within this program.
 * Specifications are in materials of level #12, Task "Silhouettes, part 1".
 */
public class Assignment12Part1 {

    /**
     * The name of the file used when no other is received or the queried one doesn't exist.
     */
    public static final String DEFAULT_FILENAME = "test.jpg";

    /**
     * Minimum pixels number as the difference between two pixels'
     * red components values letting treat them as different.
     */
    public static final int VISIBILITY_THRESHOLD = 20;

    /**
     * Minimum pixels number any solid object must consist of to be treated as a silhouette.
     */
    public static final int MIN_OBJECT_PXS = 100;

    /**
     * Mixed RGB values of the current image's pixels.
     */
    public static int[][] pixels;

    /**
     * Abstract reflection of pixels matrix to keep track of the ones already parsed.
     */
    public static boolean[][] visited;

    /**
     * The stack size for a single silhouette parsing.
     */
    public static int recursionDepth = 0;

    public static void main(String[] args) {
        String fileName = args.length != 0 ? args[0] : DEFAULT_FILENAME;
        pixels = makeGray(new GImage(fileName));
        visited = new boolean[pixels.length][pixels[0].length];

        // The background's color's red component.
        int backRed = getBackgroundRed();

        // The number of silhouettes found on the current image.
        int silhouettes = findSilhouettes(backRed);

        System.out.println("Found " + silhouettes + " silhouettes on the image.");
    }

    /**
     * Parses the pixels matrix, where the outer loop stands for the rows and the inner one
     * for the columns. Every step red component is reassigned and current pixel is marked as visited.
     * Every time different color's pixel is met, and it's been unvisited the recursive dfs is started.
     * Such process is saved as an object if only it had enough pixels.
     * Then the recursion stack size is nullified and the iteration continues.
     *
     * @param backRed The background's color's red component.
     * @return The number of silhouettes found on the current image.
     */
    private static int findSilhouettes(int backRed) {
        int silhouettes = 0;

        // The red component value of the previous pixel and of the current one, respectively.
        int prevRed = 0;
        int red;

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                red = GImage.getRed(pixels[i][j]);

                if (!(i == 0 && j == 0) && !visited[i][j] && isSameTone(prevRed, red, backRed)) {
                    startDFS(i, j, red, backRed);
                    if (recursionDepth > MIN_OBJECT_PXS)
                        silhouettes++;
                    recursionDepth = 0;
                }

                prevRed = red;
                visited[i][j] = true;
            }
        }

        return silhouettes;
    }

    /**
     * Finds the average value of background color of pixels matrix.
     * Assumed, the edge pixels frame mostly consist exactly of "background" pixels.
     *
     * @return The background's color's red component.
     */
    private static int getBackgroundRed() {
        // The container for the frame's pixels red component values
        ArrayList<Integer> perimeterReds = new ArrayList<>();

        // Parsing the upper and the bottom rows.
        for (int[] row : pixels) {
            perimeterReds.add(GImage.getRed(row[0]));
            perimeterReds.add(GImage.getRed(row[row.length - 1]));
        }

        // Parsing the left and the right columns, the corner pixels excluded.
        for (int i = 1; i < pixels[0].length - 1; i++) {
            perimeterReds.add(GImage.getRed(pixels[0][i]));
            perimeterReds.add(GImage.getRed(pixels[pixels.length-1][i]));
        }

        // The average value of all the parsed pixels.
        OptionalDouble avr = perimeterReds.stream().mapToInt(x -> x).average();

        return avr.isPresent() ? (int) avr.getAsDouble() : 0;
    }

    /**
     * Fades current image's colors so that they all are shades of gray.
     *
     * @param image An image to be parsed.
     * @return A pixels matrix with all color components converted and equalized.
     */
    private static int[][] makeGray(GImage image) {
        // Original pixels matrix.
        int[][] pixels = image.getPixelArray();

        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[row].length; ++col) {
                // The sum of original value proportionally multiplied by coefficients.
                int grayed = (int) (0.3 * (double) GImage.getRed(pixels[row][col]) +
                        0.6 * (double) GImage.getGreen(pixels[row][col]) +
                        0.1 * (double) GImage.getBlue(pixels[row][col]));

                // Equalizing current pixel by assigning equal components.
                pixels[row][col] = GImage.createRGBPixel(grayed, grayed, grayed);
            }
        }

        return pixels;
    }

    /**
     * Starts/continues recursive DFS if pixel of color within allowed diapason
     * is met one pixel up, down, left or right, sequentially.
     * Every step marks current pixels as visited and increases current recursion
     * process depth index to keep track of it, that is method's main purpose.
     *
     * @param row The current pixels matrix row number.
     * @param col The current pixels matrix column number.
     * @param red The current pixel's red component value.
     * @param backRed The background's color's red component.
     */
    private static void startDFS(int row, int col, int red, int backRed) {
        visited[row][col] = true;
        recursionDepth++;

        // Pixel one step up.
        if (row > 0 && !visited[row - 1][col]) {
            int upperRed = GImage.getRed(pixels[row - 1][col]);

            if (isSameTone(red, upperRed, backRed))
                startDFS(row - 1, col, upperRed, backRed);
        }

        // Pixel one step down.
        if (row < pixels.length - 1 && !visited[row + 1][col]) {
            int bottomRed = GImage.getRed(pixels[row + 1][col]);

            if (isSameTone(red, bottomRed, backRed))
                startDFS(row + 1, col, bottomRed, backRed);
        }

        // Pixel one step left.
        if (col > 0 && !visited[row][col - 1]) {
            int leftRed = GImage.getRed(pixels[row][col - 1]);

            if (isSameTone(red, leftRed, backRed))
                startDFS(row, col - 1, leftRed, backRed);
        }

        // Pixel one step right.
        if (col < pixels[row].length - 1 && !visited[row][col + 1]) {
            int rightRed = GImage.getRed(pixels[row][col + 1]);

            if (isSameTone(red, red, backRed))
                startDFS(row, col + 1, rightRed, backRed);
        }
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
    private static boolean isSameTone(int currRed, int nextRed, int backRed) {
        return Math.abs(nextRed - currRed) < VISIBILITY_THRESHOLD && Math.abs(nextRed - backRed) > VISIBILITY_THRESHOLD;
    }

}
