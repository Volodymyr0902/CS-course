package com.shpp.cs.a.lectures.lec15;

public class ManyMains extends MoreMains{
    public static void main(String[] args) {
        System.out.println("From ManyMains");
    }

    public static void printHey() {
        System.out.println("Hey!");
    }
}

class MoreMains {
    public static void printHey() {
        System.out.println("Hello");
    }
}
