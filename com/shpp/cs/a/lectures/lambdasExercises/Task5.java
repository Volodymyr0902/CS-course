package com.shpp.cs.a.lectures.lambdasExercises;

// Написать функциональный интерфейс с методом, который принимает три дробных числа:
// a, b, c и возвращает тоже дробное число. Написать реализацию такого интерфейса
// в виде лямбда-выражения, которое возвращает дискриминант. Кто забыл, D = b^2 — 4ac.

// Используя функциональный интерфейс из задачи 5 написать лямбда-выражение,
// которое возвращает результат операции a * b^c.

public class Task5 {
    public static void main(String[] args) {
        DoublesAnalyser da = (a, b, c) -> b * b - 4 * a * c;
        System.out.println(da.analyse(2, 3, 1));

        DoublesAnalyser da2 = (a, b, c) -> a * Math.pow(b, c);
        System.out.println(da2.analyse(5, 4, 2));
    }
}

interface DoublesAnalyser {
    double analyse(double a, double b, double c);
}