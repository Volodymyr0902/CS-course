package com.shpp.cs.a.lectures.OOP;

public class Counter {
    public static int count;

    public void increaseIt() {
        count++;
        System.out.println("Counter value now is " + count);
    }

    public static class IntCounter {
        public void decreaseIt() {
            count--;
            System.out.println("Counter value now is " + count);
        }
    }
}
