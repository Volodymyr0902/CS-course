package com.shpp.cs.a.lectures.lec10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MeetingPreparation_arr_AL {
    public static void main(String[] args) {
        String[] cats = new String[3];
        cats[0] = "John";
        cats[1] = "Peter";
        cats[2] = "Felix";
        System.out.println(Arrays.toString(cats));
        cats[1] = null;
        System.out.println(Arrays.toString(cats));

        ArrayList<String> cats1 = new ArrayList<>(Arrays.asList(cats));
        System.out.println(cats1);
        cats1.remove(1);
        System.out.println(cats1);

        // It's not allowed to change elements (or add new) while iterating ArrayList
//        for (String cat1 : cats1) {
//            if (cat1.equals("Felix")) {
//                cats1.remove(cat1);
//            }
//        }
//        System.out.println(cats1);

//        Iterator<String> cats1Iter = cats1.iterator();
//        while (cats1Iter.hasNext()) {
//            String nextOne = cats1Iter.next();
//            if (nextOne.equals("John")) {
//                cats1Iter.remove();
//            }
//        }
//        System.out.println(cats1);

        cats1.add("Mark");
        cats1.add("John");
        cats1.add("John");
        System.out.println(cats1);
        for (int i = cats1.size() - 1; i >= 0; i--) {
            if (cats1.get(i).equals("John")) {
                cats1.remove(cats1.get(i));
            }
        }
        System.out.println(cats1);

        String[] catsArr = cats1.toArray(new String[0]);
        System.out.println(Arrays.toString(catsArr) + " From AL to arr ");
        System.out.println(catsArr.length);

        ArrayList<String> catsAL = new ArrayList<>(Arrays.asList(catsArr));
        System.out.println(catsAL + " From arr to AL");

        //------------------//

        Integer[] arr1 = new Integer[10];
        arr1[1] = 17;
        System.out.println(Arrays.toString(arr1));

        Arrays.fill(arr1, 15);
        System.out.println(Arrays.toString(arr1));

        arr1[4] = 28;
        arr1[1] = 9;
        arr1[0] = 67;
        Arrays.sort(arr1);
        int index = Arrays.binarySearch(arr1, 28);
        System.out.println(index);
        System.out.println(Arrays.toString(arr1));

        byte b = 8;
        Short sh = (short) b;
        System.out.println(sh);

        //--------------------------//
        System.out.println("//----------------//");

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 5, 5, 5, 5, 5, 5));
        System.out.println(numbers);
        int n1 = 2;
        Integer n2 = 5;
        numbers.remove(n1);
        System.out.println(numbers);
        numbers.remove(n2);
        System.out.println(numbers);

        int[] a[] = new int[10][10];
        a[0][0] = 6;

        int[] nums = new int[10];
        fillArr(nums);
        System.out.println(nums[2]);
        //System.out.println(nums[10]); // ArrayIndexOutOfBoundsException
        int[] nums2 = nums;
        Arrays.fill(nums, 1);
        System.out.println(Arrays.toString(nums2));

        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) arr[i] = 9;
        for (int e : arr) System.out.println(e);

        int[][] array = {{1, 2, 3}, {0, 0, 0,}};
        System.out.println(Arrays.deepToString(array));

        Byte[] Byte[] = {{0}};
        //System.out.println(Byte);
        System.out.println(Byte.class);
        System.out.println(Byte.length);
        //System.out.println(new Byte());

        Integer i = Integer.valueOf(8);
        Byte b1 = java.lang.Byte.valueOf((byte) 2);

        System.out.println(a1());
        System.out.println(b());
        System.out.println(c());
    }

    static int[][] a1() {
        return new int[0][];
    }

    static int[] b()[] {
        return new int[0][];
    }

    static int c()[][] {
        return new int[0][];
    }

    // Unnecessary to return filled array as values are automatically assigned.
    private static void fillArr(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 8;
        }
    }
}

// add(index, element) add an element in the index of ArrayList (== ArrayList.size() by default)
// set(index, element) replaces actual value with the new one
// indexOf() shows index of given element
// get() shows element value of given index
// contains() t/f if ArrayList contains such element
// clear() clears the entire ArrayList
// size() returns elements number
// remove(index/link) removes element without leaving a gap

// Arrays.asList(arrName) array to ArrayList
// arrListName.toArray(new type[length]) ArrayList to array

