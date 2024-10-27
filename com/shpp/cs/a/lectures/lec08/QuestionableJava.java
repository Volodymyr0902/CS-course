package com.shpp.cs.a.lectures.lec08;

import com.shpp.cs.a.console.TextProgram;

public class QuestionableJava extends TextProgram {
    public void run() {
        int marten = 137;
        int faye = 42;

        println("marten = " + marten);
        hannelore(faye);
        println("marten = " + marten);
        println("faye = " + faye);

        marten = angus(faye, marten + faye);
        println("marten = " + marten);
        marten = angus(marten, faye);
        println("marten = " + marten);
    }

    private void hannelore(int marten) {
        println("marten = " + marten);
        marten = 160;
    }

    private int angus(int martin, int faye) {
        int dora = faye - martin;
        println("dora = " + dora);
        return dora % 10;
    }
}