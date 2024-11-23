package com.shpp.p2p.cs.vtaboranskyi.assignment13;

/**
 * This class is responsible for defining a pixel's row and column
 * in an image's pixels matrix adjacent to the received ones.
 * Methods responsible for this have synchronized logic, so specific
 * iteration always indicates specific adjacent pixel:
 * <ul>
 * <li>0 - upper,</li>
 * <li>1 - bottom,</li>
 * <li>2 - left,</li>
 * <li>3 - right,</li>
 * <li>4 - left upper,</li>
 * <li>5 - right upper,</li>
 * <li>6 - left bottom,</li>
 * <li>7 - right bottom</li>
 * </ul>
 */
public class AdjacentPixel {

    /**
     * Adjacent pixel's row and column in pixels matrix, respectively.
     */
    private final int row, col;

    public AdjacentPixel(int row, int col, int iteration) {
        this.row = getAdjacentRow(row, iteration);
        this.col = getAdjacentCol(col, iteration);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     * Checks if adjacent pixel's row and column exist within image's pixels matrix.
     *
     * @param pixels Image's pixels matrix.
     * @return <code>True</code>, if both queried row and column are within matrix.
     */
    public boolean isPixelWithinMatrix(int[][] pixels){
        return row >= 0 && row < pixels.length && col >= 0 && col < pixels[0].length;
    }

    /**
     * Picks a row adjacent to the current, depending on iteration number.
     *
     * @param row The current row.
     * @param iteration The current iteration number.
     * @return An adjacent row.
     */
    private int getAdjacentRow(int row, int iteration) {
        return switch (iteration) {
            case 0, 4, 5 -> row - 1;
            case 1, 6, 7 -> row + 1;
            case 2, 3 -> row;
            default -> -1;
        };
    }

    /**
     * Picks a column adjacent to the current, depending on iteration number.
     *
     * @param col The current column.
     * @param iteration The current iteration number.
     * @return An adjacent column.
     */
    private int getAdjacentCol(int col, int iteration) {
        return switch (iteration) {
            case 0, 1 -> col;
            case 2, 4, 6 -> col - 1;
            case 3, 5, 7 -> col + 1;
            default -> -1;
        };
    }
}
