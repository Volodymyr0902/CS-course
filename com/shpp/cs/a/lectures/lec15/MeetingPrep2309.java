package com.shpp.cs.a.lectures.lec15;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class MeetingPrep2309 {
    public static void main(String[] args) throws Exception {
        Annotations a = new Annotations();
        //a.loopThem();
        //Recursion.recurse();

        // BigInteger
        int maxInt = Integer.MAX_VALUE;
        System.out.println(maxInt);
        long maxLong = Long.MAX_VALUE;
        System.out.println(maxLong);
        BigInteger bInt = new BigInteger("75785784274878384247578897987789789");
        BigInteger bInt2 = new BigInteger("4239308943844283984349374874384376");
        System.out.println(bInt.add(bInt2));
        System.out.println(myStringToInteger("7798"));

        // Memory complexity
        Character[] chars = new Character[1000000];
        Arrays.fill(chars, 'a');
        String[] chars4 = new String[chars.length];
        for (int i = 0; i < chars.length; i++)
            chars4[i] = String.valueOf(chars[i]);
        String chars3 = String.join("", chars4);
        long fm;
        System.out.println(fm = Runtime.getRuntime().freeMemory());
        ArrayList<Character> char5 = new ArrayList<>(Arrays.asList(chars));
        System.out.println(fm - Runtime.getRuntime().freeMemory());

        // Finally
        System.out.println(finallyTester(89));

        // StringBuffer/StringBuilder
        String text = "Hello";
        StringBuilder stringBuilder = new StringBuilder(text);
        stringBuilder.append(" world!");
        stringBuilder.delete(5, 11);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.replace(3, 5, "icopter");
        stringBuilder.insert(stringBuilder.length(), " flies");
        stringBuilder = new StringBuilder(stringBuilder.substring(0, 10));
        stringBuilder.reverse();
        System.out.println(stringBuilder);

        // Division by 0
        System.out.println(1.0 / 0.0);

        // 32bit/64bit
        String osArch = System.getProperty("os.arch");
        String jvmBitVersion = System.getProperty("sun.arch.data.model");
        System.out.println(osArch + " " + jvmBitVersion);

        // Mutable/immutable
        ImmutableThing it = new ImmutableThing(new StringBuilder("I'm immutable!"));
        System.out.println(it.getImmutableStr());

        // Auto double casting
        System.out.println(5 * 0.1);

        // RegEx
        System.out.println(regEx());
        String text2 = "36738haha";
        if (text2.matches("\\d+\\D+"))
            System.out.println("Match!");
        else
            System.out.println("No match(");

        System.out.println("This is normal message");
        System.err.println("This is error message");

        // Arithmetic Exception
        BigDecimal bd1 = new BigDecimal("11");
        BigDecimal bd2 = new BigDecimal("17");
        bd1 = bd1.divide(bd2, 5, RoundingMode.DOWN);
        System.out.println(bd1);

        // Reflection
        PrivateThings pr = new PrivateThings(8, "John");
//        System.out.println(pr.name);
//        pr.printData();
        try {
            Field fieldName = pr.getClass().getDeclaredField("name");
            fieldName.setAccessible(true);
            fieldName.set(pr, "Peter");
            String actualName = (String) fieldName.get(pr);
            System.out.println(actualName);
            Method data = pr.getClass().getDeclaredMethod("printData");
            data.setAccessible(true);
            data.invoke(pr);
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        }

        // Lambdas
        Runnable r = () -> System.out.println("I'm lambda");
        r.run();
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm anonymous class");
            }
        };
        r2.run();
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        // Реалізує Function (перетворює дані); Consumer (дії з обʼєктами)
        list.stream().map(x -> x * x).forEach(System.out::print);
        int sum = list.stream().map(x -> x * x).reduce((x, y) -> x + y).get();
        System.out.println(sum);
        // Реалізує Predicate
        list.stream().filter(x -> x % 2 == 0).forEach(System.out::println);
    }

    public static boolean regEx() {
        String str = "hey 04";
        return str.matches("[a-zA-z]+\\s[0-9]{2,}");
    }

    public static int finallyTester(int a) {
        try {
            if (a > 10)
                throw new Exception();
            return a;
        } catch (Exception e) {
            System.out.println("Catch block");
            //System.exit(0);
        } finally {
            System.out.println("Finally block");
        }

        return 0;
    }

    public static int myStringToInteger(String str) {
        int answer = 0, factor = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            answer += (str.charAt(i) - '0') * factor;
            factor *= 10;
        }
        return answer;
    }
}

class PrivateThings {
    private int number;
    private String name;

    public PrivateThings() {
    }

    public PrivateThings(int number, String name) {
        this.number = number;
        this.name = name;
    }

    private void printData() {
        System.out.println(this.number + " " + this.name);
    }
}

class ImmutableThing {
    private final StringBuilder immutableStr;

    public ImmutableThing(final StringBuilder text) {
        this.immutableStr = new StringBuilder(text);
    }

    public final StringBuilder getImmutableStr() {
        return immutableStr;
    }
}

class Recursion {
    public static void showText() {
        System.out.println("Hi there!");
    }

    public static void recurse() throws InterruptedException {
        showText();
        Thread.sleep(1000);
        if (true)
            recurse();
    }
}

class Annotations {
    @ImportantThing
    public void annotated() {
        String text = "Hello";
        System.out.println(text);
    }

    public void loopThem() throws InvocationTargetException, IllegalAccessException {
        for (Method m : this.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(ImportantThing.class)) {
                m.invoke(this);
            }
        }
    }
}
