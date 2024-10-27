package com.shpp.p2p.cs.vtaboranskyi.assignment7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
public class NameSurferDataBase implements NameSurferConstants {

    // The mapped database for saving names as keys and respective ranks sets as values.
    private HashMap<String, String[]> namesDB;

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.
     * The constructor throws an error exception if the requested
     * file does not exist or if an error occurs as the file is being read.
     *
     * @param filename The name of the file containing the data.
     */
    public NameSurferDataBase(String filename) {
        // Same database, key and ranks set, respectively.
        HashMap<String, String[]> namesDB = new LinkedHashMap<>();
        String actualName;
        String[] actualRanks;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            // Single line read from the text file.
            String fileLine;

            while ((fileLine = br.readLine()) != null) {
                // Slices chars until first space is met.
                actualName = fileLine.substring(0, fileLine.indexOf(" ")).toLowerCase();
                // Cuts off name value with the first space, so that only ranks values remain.
                fileLine = fileLine.substring(fileLine.indexOf(" ") + 1);
                actualRanks = fileLine.split(" ");
                namesDB.put(actualName, actualRanks);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Failed to load database: " + e.getMessage());
        }

        this.namesDB = namesDB;
    }

    /**
     * Returns the NameSurferEntry associated
     * with this name, if one exists.
     * If the name does not appear in the database,
     * this method returns null.
     *
     * @param name Name queried by user.
     */
    public NameSurferEntry findEntry(String name) {
        // The ranks set for the queried name.
        String[] nameRanks = this.namesDB.get(name.toLowerCase());
        return nameRanks != null ? new NameSurferEntry(name + " " + String.join(" ", nameRanks)) : null;
    }
}



