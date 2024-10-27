package com.shpp.cs.a.lectures.collections;

import java.util.*;

public class Tests {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list.get(9));

        List<Integer> newList = Collections.unmodifiableList(list);
        System.out.println(newList);
        //newList.add(10);
        //System.out.println(newList.get(10));

        LinkedList<Integer> ll = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            ll.add(i+1);
        }
        System.out.println(ll.size());

        LinkedList<String> names = new LinkedList<>();
        names.add("Peter");
        names.add("John");
        names.add("Andy");
        names.add("Kyle");

//        for (String name : names)
//            names.add("NV");
//        System.out.println(names.pollLast());

        //-------------------------//

        long st1 = System.currentTimeMillis();
        String[] n1 = new String[100000000];
        for (int i = 0; i < n1.length; i++) {
            n1[i] = "Hey";
        }
        System.out.println(System.currentTimeMillis() - st1);

        long st2 = System.currentTimeMillis();
        ArrayList<String> n2 = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            n2.add("Hey");
        }
        System.out.println(System.currentTimeMillis() - st2);

        AbstractList<Integer> abstractList = new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return 0;
            }

            @Override
            public int size() {
                return 0;
            }
        };

    }
}
