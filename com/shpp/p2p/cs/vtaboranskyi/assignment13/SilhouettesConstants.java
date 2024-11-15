package com.shpp.p2p.cs.vtaboranskyi.assignment13;

public interface SilhouettesConstants {
    /**
     * The name of the file used when no other is received or the queried one doesn't exist.
     */
    public static final String DEFAULT_FILENAME = "assets/8.png";

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
     * The proportional red component part for a pixel's grayscale.
     */
    public static final double RED_COEFFICIENT = 0.3;

    /**
     * The proportional green component part for a pixel's grayscale.
     */
    public static final double GREEN_COEFFICIENT = 0.6;

    /**
     * The proportional blue component part for a pixel's grayscale.
     */
    public static final double BLUE_COEFFICIENT = 0.1;

    /**
     * The set of possible direction changes on Y axis, for switching a row.
     */
    public static final int[] ROW_ROUTES = {-1, 1, 0, 0};

    /**
     * The set of possible direction changes on X axis, for switching a column.
     */
    public static final int[] COL_ROUTES = {0, 0, -1, 1};
}
