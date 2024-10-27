package com.shpp.cs.a.lectures.lec09;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;

public class ArrayListLearning extends TextProgram {

    @Override
    public void run() {
        ArrayList<String> names = new ArrayList<String>();

        while (true) {
            String name = readLine("Type a name: ");
            if (name.equals("")) break;
            names.add(name);
        }

        String userName = readLine("Type your name");

        outer:
        while (true) {
            for (String name : names) {
                if (userName.equals(name)) {
                    println("I know you!");
                    break outer;
                }
            }
            println("I don't know you!");
            break;
        }

//        if (!names.contains(userName)) {
//            println("I don't know you!");
//        }

    }
}
