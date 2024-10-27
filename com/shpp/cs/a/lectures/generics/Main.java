package com.shpp.cs.a.lectures.generics;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyClass<String> str = new MyClass<>("Hello");
        System.out.println(str.getObj());

        MyClass<int[]> intArr = new MyClass<>(new int[] {3,5,4,7,93,3});
        System.out.println(Arrays.toString(intArr.getObj()));

        //-----------------//

        Double[] dArray = {2.33, 5.21, 7.44};
        Integer[] intArray = {4,21, 54, 9};
        Character[] charArray = {'o', 'f', 'q', '?'};
        String[] strArray = {"Hello", "my", "dear", "friends"};
        printArr(dArray);
    }

    // Generic method
    public static <T> void printArr(T[] arr) {
        Arrays.stream(arr).forEach(System.out::println);
    }
}
