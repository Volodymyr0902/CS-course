package com.shpp.cs.a.lectures.lec12;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;

public class Sorting extends TextProgram {

    @Override
    public void run() {
        ArrayList<Integer> userNums = new ArrayList<>();
        int x;

        while((x = readInt("Enter next value or -1 when done: ")) != -1) {
            userNums.add(x);
        }

        while(!userNums.isEmpty()) {
            System.out.println(removeSmallest(userNums));
        }
    }

    private int removeSmallest(ArrayList<Integer> userNums) {
        int smallest = (int)Double.POSITIVE_INFINITY;
        int index = 0;

        for (Integer num : userNums) {
            if (num < smallest) {
                smallest = num;
                index = userNums.indexOf(num);
            }
        }

        userNums.remove(index);
        return smallest;
    }
}
