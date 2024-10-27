package com.shpp.cs.a.lectures.lec08;

import com.shpp.cs.a.console.TextProgram;

public class RetirementStrategies  extends TextProgram {

    private static final double SP_500_INDEX = 0.075;

    @Override
    public void run() {
        double retirementYear = readInt("What year do you plan to retire? ");
        double savingStartYear = readInt("What year do you plan to start saving? ");
        double yearSavingAmount = readInt("How much $ per year do you plan to save? ");
        double amountAtRetirement = 0;
        while (savingStartYear < retirementYear) {
            amountAtRetirement += yearSavingAmount;
            amountAtRetirement += amountAtRetirement * SP_500_INDEX;
            savingStartYear++;
        }
        println("In " + (int)retirementYear + ", you'd have around $" + (int)amountAtRetirement);
    }
}
