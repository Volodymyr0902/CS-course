package com.shpp.cs.a.lectures.lec15;

import java.util.Arrays;

public class ArrTesting {
    public static void main(String[] args) {
        int[] a = {3, 5, 1, 6, 4};
        int[] b = a;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        Arrays.fill(a, 1);

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        int x = 5;
        int y = x;
        System.out.println(x);
        System.out.println(y);

        x = 1;
        System.out.println(x);
        System.out.println(y);

        int[] arr = new int[5];
        for (int e : arr)  e = 9;
        for (int e : arr) System.out.println(e);
    }
}
