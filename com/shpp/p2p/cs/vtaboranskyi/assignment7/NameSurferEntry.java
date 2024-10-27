package com.shpp.p2p.cs.vtaboranskyi.assignment7;

import java.util.*;

/**
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */
public class NameSurferEntry implements NameSurferConstants {

    // The single's entry's attributes: name and decades ranks set, respectively.
    private String name;
    private int[] rank;

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file. Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     *
     * @param line Single line from the DB, extracted by a query.
     */
    public NameSurferEntry(String line) {
        // All values separated by spaces are divided.
        String[] splitLine = line.split(" ");

        // Now we divide ranks set from the name value.
        int[] rank = new int[splitLine.length - 1];
        for (int i = 0; i < splitLine.length - 1; i++) {
            rank[i] = Integer.parseInt(splitLine[i + 1]);
        }

        // First element is always the name value.
        this.name = splitLine[0];
        this.rank = rank;
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade. The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE. If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return decade >= 0 && decade < NDECADES ? rank[decade] : 0;
    }

    /**
     * Returns a string that makes it easy
     * to see the value of a NameSurferEntry.
     * Firstly, ranks are joined in a string with commas;
     * secondly, they're split into a string array,
     * then joined back in a normal string array representation.
     */
    public String toString() {
        return this.name + " " + String.join("", Arrays.toString(this.rank).split(","));
    }
}

