package com.shpp.p2p.cs.vtaboranskyi.assignment9;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ListsTester {
    public static void main(String[] args) {
        MyArrayList<String> myAL = new MyArrayList<>();
        for (int i = 0; i < 24; i++) {
            myAL.add("Hi" + i);
        }
        System.out.println(myAL.get(0));
        System.out.println(myAL);

        myAL.set(1, "Hello");
        System.out.println(myAL);
        System.out.println(myAL.size());

        myAL.remove(10);
        System.out.println(myAL);
        System.out.println(myAL.size());

//        myAL.clear();
//        System.out.println(myAL);
//        System.out.println(myAL.size());

        MyArrayList<Integer> intList = new MyArrayList<>();
        for (int i = 0; i < 20; i++) {
            intList.add(i + 1);
        }
        System.out.println(intList);

        Iterator<Integer> intIterator = intList.iterator();
        while (intIterator.hasNext()) {
            Integer num = intIterator.next();
            if (num != null && num % 2 == 0) {
                intIterator.remove();
            }
        }
        System.out.println(intList);

        MyArrayList<String> names = new MyArrayList<String>(2);
        names.add("Peter");
        names.add("John");
        names.add("Maria");
        names.add("Karel");
        names.add("Sean");
        names.add("Andy");
        names.add("Tyler");
        System.out.println(names);

        Iterator<String> namesIterator = names.iterator();
        while (namesIterator.hasNext()) {
            String currentName = namesIterator.next();
            if (currentName != null && currentName.length() == 4) {
                namesIterator.remove();
            }
        }
        System.out.println(names);

        MyArrayList<Integer> myNums = new MyArrayList<>(intList);
        System.out.println(myNums);
        System.out.println(myNums.indexOf(5));
        System.out.println("\n");

        //--------------------------------//

       //LinkedList
        MyLinkedList<Integer> myLL = new MyLinkedList<>();
        myLL.add(5);
        myLL.add(34);
        myLL.add(11);
        myLL.add(83);
        myLL.add(1, 64);
        System.out.println(myLL.get(1));
        //myLL.clear();
        System.out.println(myLL);

        System.out.println(myLL.contains(5));
        System.out.println(myLL.size());
        System.out.println(myLL.isEmpty());

        System.out.println(myLL.removeByIndex(1));
        System.out.println(myLL);

        Iterator<Integer> llIterator = myLL.iterator();
        while (llIterator.hasNext())
            if (llIterator.next() == 83)
                llIterator.remove();
        System.out.println(myLL);
        System.out.println();

        //------------------------------------//

        MyStack<String> stack = new MyStack<>();
        stack.push("hey");
        stack.push("how");
        stack.push("are");
        stack.push("you");
        stack.push("there");
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.search("how"));
        System.out.println(stack);

        //------------------------------------//

        MyQueue<String> myQueue = new MyQueue<>();
        myQueue.offer("learn");
        myQueue.offer("collections");
        myQueue.offer("in");
        myQueue.offer("java");
        System.out.println(myQueue.poll());
        System.out.println(myQueue.peek());
        System.out.println(myQueue);

    }
}
