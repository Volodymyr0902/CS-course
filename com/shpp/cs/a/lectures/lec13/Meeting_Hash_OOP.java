package com.shpp.cs.a.lectures.lec13;

import com.shpp.cs.a.console.TextProgram;

import java.util.*;

public class Meeting_Hash_OOP extends TextProgram {

    @Override
    public void run() {
        HashMap<Long, ArrayList<String>> people = new HashMap<>();
        people.put(787482L, new ArrayList<>(Arrays.asList("John")));
        people.put(787482L, new ArrayList<>(Arrays.asList("Amy", "Gina", "Jack")));
        people.put(121313L, new ArrayList<>(Arrays.asList("Mary", "Nick")));
        people.put(378249L, new ArrayList<>(Arrays.asList("Peter")));
        println(people);
        println(people.size());

        long someLong = 787482;
        println(people.get(someLong));

        Set<String> uniques = new HashSet<>();
        uniques.add("John");
        uniques.add("Amy");
        uniques.add("Sean");
        uniques.add("John");
        println(uniques);

        people.put(null, null);
        println(people);

        HashMap nums = new HashMap<>();
        nums.put(0.9, 87 - 2);
        nums.put("hjvh", "hbhbh");
        int prim = 10;
        int prim2 = 20;
        nums.put(prim, prim2);
        println(nums);

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("vhjvh", 3232);
        map.put("jghg", 129);
        println(map);

        if (map instanceof Map) {
            println("It's Map");
        }
        if (map instanceof HashMap) {
            println("And HashMap too!");
        }

        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String id = iter.next();
            if (map.get(id) == 3232)
                iter.remove();
        }
        println(map);

        map.put("some", 4143);
        map.put("hello", 2432);
        map.put("world", 7435);

        map = Collections.unmodifiableMap(map);
        map.put("csc", 5253);
        println(map);

        // must not change the map while iterating it by forEach
//        for (Map.Entry<String,Integer> pair : map.entrySet()) {
//            if (pair.getValue().equals(2432))
//                map.remove(pair.getKey());
//        }

    }

    // abstract static void go(); illegal

}






