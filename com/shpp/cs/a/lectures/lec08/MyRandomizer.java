package com.shpp.cs.a.lectures.lec08;

public class MyRandomizer {
    public static void main(String[] args) {
        long x = System.currentTimeMillis();
        String y = String.valueOf(x);
        String a = y.substring(y.length() - 3);
        System.out.println(a);

        System.out.println(System.currentTimeMillis());
    }
}
