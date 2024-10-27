package com.shpp.cs.a.lectures.OOP;

public class Car implements Moveable{
    private static String make;
    private static String model;
    private static int year;
    private static double price;

    public Car() {
        this("BMW", "X5", 2018, 150000.00);
    }

    public Car(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        Car.make = make;
    }

    public void rev() {
        System.out.println("VROOOM");
    }

    public void move() {
        System.out.println("The car is moving");
    }

    public String[] getInfo() {
        String[] info = {make, model, String.valueOf(year), String.valueOf(price)};
        return info;
    }
}
