package com.shpp.p2p.cs.vtaboranskyi.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * File: Assignment3Part1.java.
 * Program which finds out if one has done enough
 * exercises to maintain cardiovascular health and
 * low blood pressure level based on input time
 * spent every day on these.
 * Specifications are in materials of level #3,
 * Task 3, Problem 1.
 */
public class Assignment3Part1 extends TextProgram {

    // The number of days in a normal week.
    public static final int WEEK_DAYS = 7;

    /*
    * The minimum amount of time one has to exercise a day
    * to maintain cardiovascular and blood pressure
    * metrics, respectively, in minutes.
     */
    public static final int CARDIOVASCULAR_SCOPE_MINS = 30;
    public static final int BLOOD_PRESSURE_SCOPE_MINS = 40;

    /*
    * The minimum number of days one has to exercise a week
    * to maintain cardiovascular and blood pressure
    * metrics, respectively.
     */
    public static final int CARDIOVASCULAR_SCOPE_DAYS = 5;
    public static final int BLOOD_PRESSURE_SCOPE_DAYS = 3;

    @Override
    public void run() {
        // Numbers of days, when exercises goals were reached.
        double[] daysDone = getDaysDone();

        // Comments on the info about time spent on exercises.
        String[] result = calcResult(daysDone[0], daysDone[1]);
        showResult(result);
    }

    /**
     * Shows the result about user's success in maintaining
     * cardiovascular health and low blood pressure,
     * respectively.
     * @param result Comments on how user exercised for every goal
     */
    private void showResult(String[] result) {
        println("\nCardiovascular health:\n\t" + result[0]);
        println("Blood pressure:\n\t" + result[1]);
    }

    /**
     * Calculates how many days user is lacking to reach
     * every goal, then compares the number of days with
     * enough exercises to the minimums and gives th result
     * based on the comparison.
     * @param cardiovascularDaysDone The number of the days with enough exercises
     *                               to maintain cardiovascular health.
     * @param bloodPressureDaysDone The number of the days with enough exercises
     *                              to maintain low blood pressure.
     * @return An array of the comments on how user exercised for every goal.
     */
    private String[] calcResult(double cardiovascularDaysDone, double bloodPressureDaysDone) {
        double cardiovascularDaysLack = CARDIOVASCULAR_SCOPE_DAYS - cardiovascularDaysDone;
        String cardioResult = cardiovascularDaysDone < CARDIOVASCULAR_SCOPE_DAYS ?
                "You needed to train hard for at least " +  (int)cardiovascularDaysLack + " more day(s) a week!" :
                "Great job! You've done enough exercise for cardiovascular health.";

        double bloodPressureDaysLack = BLOOD_PRESSURE_SCOPE_DAYS - bloodPressureDaysDone;
        String bloodPressureResult = bloodPressureDaysDone < BLOOD_PRESSURE_SCOPE_DAYS ?
                "You needed to train hard for at least " + (int)bloodPressureDaysLack + " more day(s) a week!" :
                "Great job! You've done enough exercise to keep a low blood pressure.";

        String[] results = {cardioResult, bloodPressureResult};
        return results;
    }

    /**
     * Asks user how many minutes have they exercised every day of
     * the week and remembers the number of the days, when they
     * exercised enough for each of 2 goals.
     * @return An array of days numbers when these goals were reached.
     */
    private double[] getDaysDone() {
        double cardiovascularDaysDone = 0;
        double bloodPressureDaysDone = 0;

        for (int i = 1; i <= WEEK_DAYS; i++) {
            double dayExerciseTime = readDouble("How many minutes did you do on day " + i + "? ");
            if (dayExerciseTime >= CARDIOVASCULAR_SCOPE_MINS) {
                cardiovascularDaysDone++;
            }
            if (dayExerciseTime >= BLOOD_PRESSURE_SCOPE_MINS) {
                bloodPressureDaysDone++;
            }
        }

        double[] daysDone = {cardiovascularDaysDone, bloodPressureDaysDone};
        return daysDone;
    }

}
