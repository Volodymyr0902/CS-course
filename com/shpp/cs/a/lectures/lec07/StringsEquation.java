package com.shpp.cs.a.lectures.lec07;

import com.shpp.cs.a.console.TextProgram;

public class StringsEquation extends TextProgram {
    @Override
    public void run() {
        String x = "vo1";
        String y = "vo" + Integer.parseInt(readLine());
        if (x.equals(y)) {
            println("Equal");
        } else {
            println("Not equal");
        }
    }
}
