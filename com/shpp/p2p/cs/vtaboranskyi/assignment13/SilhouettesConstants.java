package com.shpp.p2p.cs.vtaboranskyi.assignment13;

/**
 * This interface is a set of constants for <code>SilhouettesCounter</code> class.
 * <p>
 * Depending on specific images particularities, <code>VISIBILITY_THRESHOLD</code> and
 * <code>SILHOUETTE_COEFFICIENT</code> value may be changed to filter silhouettes shade and size.
 * </p>
 */
public interface SilhouettesConstants {

    /**
     * The name of the file used when no other is received or the queried one doesn't exist.
     */
    String DEFAULT_FILENAME = "test.jpg";

    /**
     * Minimum pixels number as the difference between two pixels'
     * red components values letting treat them as different.
     */
    int VISIBILITY_THRESHOLD = 20;

    /**
     * The proportional minimal part of total pixels number a pixels
     * cluster must consist of to be considered a silhouette.
     */
    double SILHOUETTE_COEFFICIENT = 0.0002;

    /**
     * The proportional red component part for a pixel's grayscale.
     */
    double RED_COEFFICIENT = 0.3;

    /**
     * The proportional green component part for a pixel's grayscale.
     */
    double GREEN_COEFFICIENT = 0.6;

    /**
     * The proportional blue component part for a pixel's grayscale.
     */
    double BLUE_COEFFICIENT = 0.1;

    /**
     * The set of possible direction changes on Y axis, for switching a row.
     */
    int[] ROW_ROUTES = {-1, 1, 0, 0};

    /**
     * The set of possible direction changes on X axis, for switching a column.
     */
    int[] COL_ROUTES = {0, 0, -1, 1};

    /**
     * The number of pixels nearby any another which can be checked for similarity.
     */
    int ADJACENT_PXS_NUM = 4;
}
