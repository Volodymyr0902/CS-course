package com.shpp.cs.a.lectures.lambdasExercises;

// Задача 3. Написать функциональный интерфейс с методом, который принимает число
// и возвращает булево значение. Написать реализацию такого интерфейса в виде лямбда-выражения,
// которое возвращает true если переданное число делится без остатка на 13.

public class Task3 {
    public static void main(String[] args) {
        NumChecker c = a -> a % 13 == 0;
        System.out.println(c.checkNum(24));
    }
}

interface NumChecker {
    boolean checkNum(int a);
}
