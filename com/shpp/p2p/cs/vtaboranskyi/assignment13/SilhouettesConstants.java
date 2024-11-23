package com.shpp.p2p.cs.vtaboranskyi.assignment13;

/**
 * This interface is a set of constants for <code>SilhouettesCounter</code> class.
 * <p>
 * Depending on specific images particularities, <code>SILHOUETTE_PERCENTAGE_OF_TOTAL_PXS</code>
 * value may be changed to filter silhouettes' size.
 * </p>
 */
public interface SilhouettesConstants {

    /**
     * The name of the file used when no other is received or the queried one doesn't exist.
     */
    String DEFAULT_FILENAME = "assets/whb.jpeg";

    /**
     * Tone's values diapason indicate affiliation to a similar tone.
     */
    int TONES_DIFFERENCE_THRESHOLD = 20; // todo

    /**
     * The percentage minimal part of total pixels number a pixels
     * cluster must consist of to be considered a silhouette. //todo
     */
    double SILHOUETTE_PERCENTAGE_OF_TOTAL_PXS = 0.002;

    /**
     * The proportional red component part for a pixel's grayscale.
     */
    double RED_GRAYSCALE_COEFFICIENT = 0.3; // todo

    /**
     * The proportional green component part for a pixel's grayscale.
     */
    double GREEN_GRAYSCALE_COEFFICIENT = 0.6;

    /**
     * The proportional blue component part for a pixel's grayscale.
     */
    double BLUE_GRAYSCALE_COEFFICIENT = 0.1;

    /**
     * The number of pixels nearby another one which can be checked for similarity.
     */
    int ADJACENT_PXS_NUM = 8;

    /**
     * Max pixel's alpha value to treat it as transparent.
     */
    int TRANSPARENCY_MAX_THRESHOLD = 127;

    /**
     * Max R/G/B component's value.
     */
    int MAX_RGB_VALUE = 255;

    /**
     * Min R/G/B component's value.
     */
    int MIN_RGB_VALUE = 0;
}
