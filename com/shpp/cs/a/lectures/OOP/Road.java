package com.shpp.cs.a.lectures.OOP;

import java.util.Arrays;
import java.util.HashMap;

public class Road {
    public static void main(String[] args) {
        Car someCar = new Car("Audi", "R8", 2017, 80000.00);
        someCar.rev();
        someCar.move();
        System.out.println(Arrays.toString(someCar.getInfo()));

        Bike someBike = new Bike();
        someBike.move();

        Car defCar = new Car();
        System.out.println(Arrays.toString(defCar.getInfo()));

        Counter a = new Counter();
        //Counter.intCounter b = a.new intCounter();
        Counter.IntCounter b = new Counter.IntCounter();
        b.decreaseIt();

        Car nc = new Car();
        System.out.println(nc.getMake());

        Moveable m = new Moveable() {
            @Override
            public void move() {
                System.out.println("It's moving!");
            }
        };

        Man john = new Man("brown", "black", "John", 27, 533871);
        System.out.println(john.hashCode());
        Man smith = new Man("brown", "black", "John", 27, 533871);
        System.out.println(john.equals(smith));

        HashMap<Man,Integer> people = new HashMap<>();
        people.put(smith, smith.getDna());
        System.out.println(people.get(smith));
        smith.setDna(829267);
        System.out.println(people.get(smith));
        System.out.println(people.containsKey(john));
    }
}
