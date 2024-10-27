package com.shpp.cs.a.lectures.lambdasExercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       //AnonymousClasses.sortStrArrays();
        LambdaSorting.sortStrArrays();
    }
}

class LambdaSorting {
    public static void sortStrArrays() {
        String[] array1 = {"мама", "мыла", "раму"};
        String[] array2 = {"я", "очень", "люблю", "java"};
        String[] array3 = {"мир", "труд", "май"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

        arrays.sort((o1, o2) -> o1.length - o2.length);

        for (String[] arr : arrays)
            System.out.println(Arrays.toString(arr));
    }
}

class AnonymousClasses {
    public static void sortStrArrays() {
        String[] array1 = {"мама", "мыла", "раму"};
        String[] array2 = {"я", "очень", "люблю", "java"};
        String[] array3 = {"мир", "труд", "май"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

//        arrays.sort(new Comparator<String[]>() {
//            @Override
//            public int compare(String[] o1, String[] o2) {
//                return o1.length - o2.length;
//            }
//        });

        arrays.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o2.length - o1.length;
            }
        });

        for (String[] arr : arrays)
            System.out.println(Arrays.toString(arr));
    }
}
