package com.shpp.cs.a.lectures.lec08;

public class DataTypesChanging {
    public static void main(String[] args) {
        int x = 128;
        byte y = (byte) x;
        System.out.println(y);

        byte a = 2;
        // при операціях літерали завжди стають int
        // відтак float int = float
        // якщо є long завжди вийде long
        a = (byte) (a * 2);
        System.out.println(a);

        // array memory setting
        int[] month_days = new int[10];
        month_days[0] = 31;
        System.out.println(month_days[0]);

        // initialization of array
        String[] names = {"John", "Peter", "Andy"};
        System.out.println(names[1]);

        // середнє арифметичне
        int[] someNums = {23, 45, 11, 18, 44};
        double sum = 0;
        for (int i = 0; i < someNums.length; i++) {
            sum += someNums[i];
        }
        double average = sum / someNums.length;
        System.out.println(average);

    }
}
