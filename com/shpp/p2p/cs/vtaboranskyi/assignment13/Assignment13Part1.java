package com.shpp.p2p.cs.vtaboranskyi.assignment13;

// check input
// hardcode pxs to coefficient
// diagonal back color search
// a bit stuck silhouettes
// decompose
// try OOP
// comments
// more tests

import acm.graphics.GImage;
import java.util.*;

public class Assignment13Part1 implements SilhouettesConstants {

    /**
     * Mixed RGB values of the current image's pixels.
     */
    public static int[][] pixels;

    /**
     * Abstract reflection of pixels matrix to keep track of the ones already parsed.
     */
    public static boolean[][] visited;

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

                if (!(i == 0 && j == 0) && !visited[i][j] && isSameTone(prevRed, red, backRed))
                    if (startBFS(i, j, backRed) > MIN_OBJECT_PXS)
                        silhouettes++;

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
            perimeterReds.add(GImage.getRed(pixels[pixels.length - 1][i]));
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
                int grayed = (int) (RED_COEFFICIENT * (double) GImage.getRed(pixels[row][col]) +
                        GREEN_COEFFICIENT * (double) GImage.getGreen(pixels[row][col]) +
                        BLUE_COEFFICIENT * (double) GImage.getBlue(pixels[row][col]));

                // Equalizing current pixel by assigning equal components.
                pixels[row][col] = GImage.createRGBPixel(grayed, grayed, grayed);
            }
        }

        return pixels;
    }

    private static int startBFS(int row, int col, int backRed) {
        int pixelsCounter = 0;

        Queue<int[]> pixelsToVisit = new LinkedList<>();
        pixelsToVisit.offer(new int[]{row, col});

        while (!pixelsToVisit.isEmpty()) {
            int currRow = pixelsToVisit.peek()[0];
            int currCol = pixelsToVisit.peek()[1];
            int red = GImage.getRed(pixels[currRow][currCol]);
            pixelsToVisit.poll();

            for (int i = 0; i < 4; i++) {
                int dRow = currRow + ROW_ROUTES[i];
                int dCol = currCol + COL_ROUTES[i];

                if (dRow >= 0 && dRow < pixels.length && dCol >= 0 && dCol < pixels[0].length) {
                    int nextRed = GImage.getRed(pixels[dRow][dCol]);

                    if (!visited[dRow][dCol] && isSameTone(red, nextRed, backRed)) {
                        visited[dRow][dCol] = true;
                        pixelsToVisit.offer(new int[]{dRow, dCol});
                        pixelsCounter++;
                    }
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
    private static boolean isSameTone(int currRed, int nextRed, int backRed) {
        return Math.abs(nextRed - currRed) < VISIBILITY_THRESHOLD && Math.abs(nextRed - backRed) > VISIBILITY_THRESHOLD;
    }

}
