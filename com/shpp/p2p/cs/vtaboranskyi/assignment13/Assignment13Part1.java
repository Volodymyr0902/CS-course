package com.shpp.p2p.cs.vtaboranskyi.assignment13;

import acm.util.ErrorException;

/**
 * File: Assignment13Part1.java.
 * Program which calculates silhouettes number on an image.
 * The path to the tested image file must be set as the first argument after class name
 * while starting program via command line. Otherwise, the default image will be parsed.
 * Specifications are in materials of level #13, Task "Silhouettes, part 2".
 */
public class Assignment13Part1 {
    public static void main(String[] args) {
        try {
            int silhouettes = SilhouettesFinder.findSilhouettes(args);
            System.out.println("Found " + silhouettes + " silhouette(s) on the image.");
        } catch (ErrorException e) {
            System.out.println("Failed to read the input file.");
        }
    }
}
