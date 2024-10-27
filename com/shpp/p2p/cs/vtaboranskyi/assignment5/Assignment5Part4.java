package com.shpp.p2p.cs.vtaboranskyi.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * File: Assignment5Part4.java.
 * Program which shows list of CSV file
 * certain column values.
 * Specifications are in materials of level #5,
 * Task 5, Problem 4.
 */
public class Assignment5Part4 extends TextProgram {

    // Path to CSV file from content root.
    public static final String FILE_LINK = "food-origins.csv";

    // Position of a column, starting from 0.
    public static final int COLUMN_INDEX = 1;

    @Override
    public void run() {
        println(extractColumn(FILE_LINK, COLUMN_INDEX));
    }

    /**
     * Puts all the value from a certain CSV file
     * column in a list if such file exists.
     *
     * @param filename    Path to CSV file from content root.
     * @param columnIndex Position of a column, starting from 0.
     * @return List of the values from asked column.
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> columnValues = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            // A values row from the file and its one comma-separated value, respectively.
            String row;

            // Reads the file row by row, adding certain column values to the list.
            while ((row = br.readLine()) != null) {
                String singleValue = extractSingleValue(columnIndex, row);
                if (singleValue != null) {
                    columnValues.add(singleValue);
                } else
                    // In case asked column doesn't exist.
                    return null;
            }

            br.close();
            return columnValues;
        } catch (IOException e) {
            println("Failed to read the file!");
        }

        // In case file doesn't exist.
        return null;
    }

    /**
     * Looks for asked column and
     * saves its value from a row.
     *
     * @param columnIndex Position of a column, starting from 0.
     * @param row         A row of comma-separated values.
     * @return A single comma-separated value.
     */
    private String extractSingleValue(int columnIndex, String row) {
        // Commas found number before asked column value.
        int commasFound = 0;
        // Actual asked value 1st index (we take 1st row value by default).
        int valueIndexInRow = 0;

        // For columnIndex > 0.
        if (commasFound != columnIndex) {
            valueIndexInRow = findExactComma(row, commasFound, columnIndex);
            // In case certain comma (value) wasn't found.
            if (valueIndexInRow == 0) {
                println("Column " + columnIndex + " doesn't exist!");
                return null;
            }
        }

        return combineSingleValue(row, valueIndexInRow);
    }

    /**
     * Fills a string with certain value characters,
     * considering if it has quotes.
     *
     * @param row             A row of comma-separated values.
     * @param valueIndexInRow First index of actual value in asked column.
     * @return A value from asked column.
     */
    private String combineSingleValue(String row, int valueIndexInRow) {
        if (row.charAt(valueIndexInRow) == '"') {
            return combineQuotesValue(valueIndexInRow, row);
        }
        return combineNoQuotesValue(valueIndexInRow, row);
    }

    /**
     * Fills a string with characters of
     * a value with no quotes.
     *
     * @param valueIndexInRow First index of actual value in asked column.
     * @param row             A row of comma-separated values.
     * @return A value from asked column.
     */
    private String combineNoQuotesValue(int valueIndexInRow, String row) {
        String singleValue = "";
        // Until or comma is met or the line is over.
        while (valueIndexInRow != row.length() && row.charAt(valueIndexInRow) != ',') {
            singleValue += row.charAt(valueIndexInRow);
            valueIndexInRow++;
        }
        return singleValue;
    }

    /**
     * Fills a string with characters of
     * a value in quotes.
     *
     * @param valueIndexInRow First index of actual value in asked column.
     * @param row             A row of comma-separated values.
     * @return A value from asked column.
     */
    private String combineQuotesValue(int valueIndexInRow, String row) {
        String singleValue = "";
        // To miss quotes symbol.
        valueIndexInRow++;
        // Until or the line is over or separating comma is met.
        while (valueIndexInRow + 1 != row.length() &&
                !(row.charAt(valueIndexInRow) == '"' && row.charAt(valueIndexInRow + 1) == ',')) {
            singleValue += row.charAt(valueIndexInRow);
            valueIndexInRow++;
        }
        return singleValue;
    }


    /**
     * Looks for a separating comma before
     * value in asked column if such exists.
     *
     * @param row         A row of comma-separated values.
     * @param commasFound Commas found number before asked column value.
     * @param columnIndex Position of a column, starting from 0.
     * @return First index of value in asked column or 0 if such doesn't exist.
     */
    private int findExactComma(String row, int commasFound, int columnIndex) {
        // Indicator for quotes.
        boolean quotesOpened = false;

        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == '"') {
                quotesOpened = !quotesOpened;
            }
            // Separating comma indicated.
            if (!quotesOpened && row.charAt(i) == ',') {
                commasFound++;
            }
            // Certain comma is found.
            if (commasFound == columnIndex) {
                return ++i;
            }
        }
        // Certain comma isn't found.
        return 0;
    }

}
