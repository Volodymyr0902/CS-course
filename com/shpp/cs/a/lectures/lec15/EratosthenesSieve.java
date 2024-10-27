package com.shpp.cs.a.lectures.lec15;

import java.util.ArrayList;

public class EratosthenesSieve {

    public static final int SCOPE_NUMBER = 1000;

    public static void main(String[] args) {
        ArrayList<Integer> allNumsToScope = new ArrayList<>();
        fillNumsList(allNumsToScope);

        for (int i = 0; i <= allNumsToScope.size() - 1; i++) {
            for (int j = i + 1; j <= allNumsToScope.size() - 1;) {
                if (allNumsToScope.get(j) % allNumsToScope.get(i) == 0) {
                    allNumsToScope.remove(j);
                } else {
                    j++;
                }
            }
        }

        System.out.println(allNumsToScope);
    }

    private static void fillNumsList(ArrayList<Integer> allNumsToScope) {
        for (int i = 2; i <= SCOPE_NUMBER; i++) {
            allNumsToScope.add(i);
        }
    }
}










//Iterator<Integer> iterator = allNumsToScope.iterator();
//            while (iterator.hasNext()) {
//        if (iterator.next() % allNumsToScope.get(i) == 0) {
//        iterator.remove();
//                }
//                        }
