package com.shpp.cs.a.lectures.lambdasExercises;

// Написать функциональный интерфейс с методом, который принимает две строки и возвращает тоже строку.
// Написать реализацию такого интерфейса в виде лямбды, которая возвращает ту строку, которая длиннее.

public class Task4 {
    public static void main(String[] args) {
        StringAnalyser sa = (a, b) -> a.length() > b.length() ? a : b;
        System.out.println(sa.analyse("Hey", "Hello"));
    }
}

interface StringAnalyser {
    String analyse(String a, String b);
}