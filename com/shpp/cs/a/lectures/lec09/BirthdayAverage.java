package com.shpp.cs.a.lectures.lec09;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class BirthdayAverage extends TextProgram {

    @Override
    public void run() {
        RandomGenerator rg = RandomGenerator.getInstance();
        int avg = 0;

        int[] daysInYear = new int[365];
        int peopleNum = 0;
        boolean found = false;

        while (!found) {
            int day = rg.nextInt(365);
            peopleNum++;
            daysInYear[day]++;
            if (daysInYear[day] == 2) {
                found = !found;
            }
        }

        println(peopleNum);

    }

}
