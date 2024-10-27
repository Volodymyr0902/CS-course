package com.shpp.cs.a.lectures.lec12;

import java.util.HashMap;
import java.util.Map;

public class HashMapIntro {
    public static void main(String[] args) {
        HashMap<String, Integer> audit = new HashMap<>();
        audit.put("Peter", 456890);
        audit.put("John", 379723);
        audit.put("Sean", 924013);

        // перебираємо категоризовані пари
        for (Map.Entry<String, Integer> pair : audit.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }
        System.out.println();

        // перебираємо ключі
        for (String name : audit.keySet()) {
            System.out.println(name);
        }
        System.out.println();

        // перебираємо значення
        for (Integer number : audit.values()) {
            System.out.println(number);
        }
    }
}

// map.keySet()
// map.values()
// map.entrySet()
// map.containsKey(key)
// map.containsValue(value)
// map.remove(key)
// map.size()
// map.clear
// map1.putAll(map2)

// TreeMap - keeps entries sorted in num/alphabetic order
// LinkedHashMap - keeps entries sorted in adding order