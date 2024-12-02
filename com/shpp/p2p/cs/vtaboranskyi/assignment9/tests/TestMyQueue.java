package com.shpp.p2p.cs.vtaboranskyi.assignment9.tests;

import com.shpp.p2p.cs.vtaboranskyi.assignment9.utilities.MyQueue;

/**
 * Tests MyQueue class by applying its methods in different ways to several parametrized types.
 * Methods which test parametrized types (except the one for exceptions) have common approach:
 * <ul>
 *     <li>fill a queue, show it and its size,</li>
 *     <li>peek and remove queue's head</li>
 *     <li>remove queue's heads one by one, show the result</li>
 * </ul>
 */
public class TestMyQueue {
    public static void main(String[] args) {
        testIntegers();
//        testDoubles();
//        testStrings();
//        testObjects();
//        testExceptions();
    }

    /**
     * Tests TestPerson objects.
     */
    private static void testObjects() {
        // Filling queue with elements.
        TestPerson person1 = new TestPerson("Jane", 34);
        TestPerson person2 = new TestPerson("Mia", 22);
        TestPerson person3 = new TestPerson("Karel", 53);
        TestPerson person4 = new TestPerson("Fred", 12);
        TestPerson person5 = new TestPerson("Ruth", 64);
        TestPerson person6 = new TestPerson("Max", 37);
        TestPerson person7 = new TestPerson("George", 10);

        MyQueue<TestPerson> personsQueue = new MyQueue<>(person1, person2, person3, person4, person5, person6, person7);
        System.out.println("Person's queue after filling:\n" + personsQueue);
        System.out.println("Person's queue size after filling: " + personsQueue.size());

        System.out.println("\nThe element to be removed the first: " + personsQueue.peek()); // [Jane, 34]
        System.out.println("Removed one element from queue: " + personsQueue.poll()); // [Jane, 34]

        System.out.println("\nTry iteratively remove queue's heads:");
        while (!personsQueue.isEmpty()) {
            System.out.println("Queue's head to be removed now is " + personsQueue.poll());
        }

        System.out.println("\nPerson's queue after emptying:\n" + personsQueue);
        System.out.println("Person's queue size after emptying: " + personsQueue.size()); // 0
    }

    /**
     * Tests Strings.
     */
    private static void testStrings() {
        MyQueue<String> strQueue = new MyQueue<>();
        strQueue.offer("Gina");
        strQueue.offer("Paul");
        strQueue.offer("Sabrina");
        strQueue.offer("Misty");
        strQueue.offer("Sean");
        strQueue.offer("Marco");
        strQueue.offer("Aaron");
        strQueue.offer("Kyle");

        System.out.println("Strings queue filled:\n" + strQueue);

        System.out.println("\nThe element to be removed the first: " + strQueue.peek()); // Gina
        System.out.println("Removed one element from queue: " + strQueue.poll()); // Gina

        System.out.println("\nTry iteratively remove queue's heads:");
        while (!strQueue.isEmpty()) {
            System.out.println("Queue's head to be removed now is " + strQueue.poll());
        }

        System.out.println("\nStrings queue after emptying:\n" + strQueue);
        System.out.println("Strings queue size after emptying: " + strQueue.size()); // 0
    }

    /**
     * Tests Doubles.
     */
    private static void testDoubles() {
        MyQueue<Double> doubleQueue = new MyQueue<>();
        for (int i = 0; i < 10; i++) {
            doubleQueue.offer(i * 3.14);
        }

        System.out.println("Double's queue after filling:\n" + doubleQueue);

        System.out.println("\nThe element to be removed the first: " + doubleQueue.peek()); // 0.0
        System.out.println("Removed one element from queue: " + doubleQueue.poll()); // 0.0

        System.out.println("\nTry to empty the queue using FIFO:");
        while (!doubleQueue.isEmpty()) {
            System.out.print(doubleQueue.poll() + " ");
        }

        System.out.println("\n\nDouble's queue after emptying:\n" + doubleQueue);
        System.out.println("Double's queue size after emptying: " + doubleQueue.size()); // 0
    }

    /**
     * Tests Integers.
     * Also tests the constructor with another queue passed in.
     */
    private static void testIntegers() {
        MyQueue<Integer> intQueue = new MyQueue<>();
        for (int i = 0; i < 10; i++) {
            intQueue.offer(i);
        }
        System.out.println("Integer's queue:\n" + intQueue);
        System.out.println("Integer's queue size after filling: " + intQueue.size()); // 10

        System.out.println("\nThe element to be removed the first: " + intQueue.peek()); // 0
        System.out.println("Removed one element from queue: " + intQueue.poll()); // 0

        System.out.println("\nInteger's queue after one removal:\n" + intQueue);
        System.out.println("Integer's queue size after one removal: " + intQueue.size()); // 9

        MyQueue<Integer> intQueueCopy = new MyQueue<>(intQueue);
        System.out.println("\nCopied queue:\n" + intQueueCopy);

        System.out.println("\nTry to empty the queue using FIFO:");
        while (!intQueue.isEmpty()) {
            System.out.print(intQueue.poll() + " ");
        }

        System.out.println("\n\nInteger's queue after emptying:\n" + intQueue);
        System.out.println("Integer's queue size after emptying: " + intQueue.size()); // 0
        System.out.println("\nCopy must still contain elements:\n" + intQueueCopy);
    }

    /**
     * Tests accessing empty queue's head; overfilling a queue.
     * Integer's wrapper is an arbitrary choice.
     */
    private static void testExceptions() {
        MyQueue<Integer> intQueue = new MyQueue<>();
        System.out.println("Try to remove empty queue's head: " + intQueue.poll()); // null
        System.out.println("Try to get empty queue's head: " + intQueue.peek()); // null

        System.out.println("\nTry to add new 1 000 000 elements...");
        for (int i = 0; i < 1_000_000; i++) {
            intQueue.offer(i);
        }
        System.out.println("Integer's queue size: " + intQueue.size()); // 1 000 000
    }
}
