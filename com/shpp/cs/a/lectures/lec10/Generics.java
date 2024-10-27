package com.shpp.cs.a.lectures.lec10;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello!");
        //list.add(123);
        for (Object str : list) {
            System.out.println(str);
        }
    }
}
