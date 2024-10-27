package com.shpp.cs.a.lectures.lec15;

public class TestRGB {
    public static void main(String[] args) {
        int a = 0;
        int b = 255;
        while (a < 256 || b > 0) {
            System.out.println(a + " " + b);
            a++;
            b--;
        }
    }
}
