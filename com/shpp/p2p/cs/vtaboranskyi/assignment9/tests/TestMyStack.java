package com.shpp.p2p.cs.vtaboranskyi.assignment9.tests;

import com.shpp.p2p.cs.vtaboranskyi.assignment9.utilities.MyStack;

/**
 * Tests MyStack class by applying its methods in different ways to several parametrized types.
 * Methods which test parametrized types (except the one for exceptions) have common approach:
 * <ul>
 *     <li>fill a stack, show it and its size,</li>
 *     <li>search present and absent elements indexes</li>
 *     <li>peek and remove stack's top</li>
 *     <li>remove stack's tops one by one, show the result</li>
 * </ul>
 */
public class TestMyStack {
    public static void main(String[] args) {
        testIntegers();
//        testDoubles();
//        testStrings();
//        testObjects();
//        testExceptions();
    }

    /**
     * Tests TestPerson objects:
     */
    private static void testObjects() {
        // Filling stack with elements.
        TestPerson person1 = new TestPerson("Jane", 34);
        TestPerson person2 = new TestPerson("Mia", 22);
        TestPerson person3 = new TestPerson("Karel", 53);
        TestPerson person4 = new TestPerson("Fred", 12);
        TestPerson person5 = new TestPerson("Ruth", 64);
        TestPerson person6 = new TestPerson("Max", 37);
        TestPerson person7 = new TestPerson("George", 10);

        MyStack<TestPerson> personsStack = new MyStack<>(person1, person2, person3, person4, person5, person6, person7);
        System.out.println("Person's stack after filling:\n" + personsStack);
        System.out.println("Person's stack size after filling: " + personsStack.size());

        System.out.println("\nSearch [Ruth, 64] element's index, must be 4: " +
                personsStack.search(new TestPerson("Ruth", 64))); // 4
        System.out.println("Search [Liam, 15] element's index, must be -1: " +
                personsStack.search(new TestPerson("Liam", 15))); // -1

        System.out.println("\nThe element to be removed the first: " + personsStack.peek()); // [George, 10]
        System.out.println("Removed one element from stack: " + personsStack.pop()); // [George, 10]

        System.out.println("\nTry iteratively remove stack's tops:");
        while (!personsStack.isEmpty()) {
            System.out.println("Stack's top to be removed now is " + personsStack.pop());
        }

        System.out.println("\nPerson's stack after emptying:\n" + personsStack);
        System.out.println("Person's stack size after emptying: " + personsStack.size()); // 0
    }

    /**
     * Tests Strings.
     */
    private static void testStrings() {
        MyStack<String> strStack = new MyStack<>();
        strStack.push("Gina");
        strStack.push("Paul");
        strStack.push("Sabrina");
        strStack.push("Misty");
        strStack.push("Sean");
        strStack.push("Marco");
        strStack.push("Aaron");
        strStack.push("Kyle");

        System.out.println("Strings stack filled:\n" + strStack);

        System.out.println("\nSearch 'Sabrina' element's index, must be 2: " + strStack.search("Sabrina")); // 2
        System.out.println("Search 'Mary' element's index, must be -1: " + strStack.search("Mary")); // -1

        System.out.println("\nThe element to be removed the first: " + strStack.peek()); // Kyle
        System.out.println("Removed one element from stack: " + strStack.pop()); // Kyle

        System.out.println("\nTry iteratively remove stack's tops:");
        while (!strStack.isEmpty()) {
            System.out.println("Stack's top to be removed now is " + strStack.pop());
        }

        System.out.println("\nStrings stack after emptying:\n" + strStack);
        System.out.println("Strings stack size after emptying: " + strStack.size()); // 0
    }

    /**
     * Tests Doubles.
     */
    private static void testDoubles() {
        MyStack<Double> doubleStack = new MyStack<>();
        for (int i = 0; i < 10; i++) {
            doubleStack.push(i * 3.14);
        }

        System.out.println("Double's stack after filling:\n" + doubleStack);

        System.out.println("\nSearch '3.14' element's index, must be 1: " + doubleStack.search(3.14)); // 1
        System.out.println("Search '4.14' element's index, must be -1: " + doubleStack.search(4.14)); // -1

        System.out.println("\nThe element to be removed the first: " + doubleStack.peek()); // 28.26
        System.out.println("Removed one element from stack: " + doubleStack.pop()); // 28.26

        System.out.println("\nTry to empty the stack using LIFO:");
        while (!doubleStack.isEmpty()) {
            System.out.print(doubleStack.pop() + " ");
        }

        System.out.println("\n\nDouble's stack after emptying:\n" + doubleStack);
        System.out.println("Double's stack size after emptying: " + doubleStack.size()); // 0
    }

    /**
     * Tests Integers.
     * Also tests the constructor with another queue passed in.
     */
    private static void testIntegers() {
        MyStack<Integer> intStack = new MyStack<>();
        for (int i = 0; i < 10; i++) {
            intStack.push(i);
        }
        System.out.println("Integer's stack:\n" + intStack);
        System.out.println("Integer's stack size after filling: " + intStack.size()); // 10

        System.out.println("\nSearch '5' element's index, must be 5: " + intStack.search(5)); // 1
        System.out.println("Search '25' element's index, must be -1: " + intStack.search(25)); // -1

        System.out.println("\nThe element to be removed the first: " + intStack.peek()); // 9
        System.out.println("Removed one element from stack: " + intStack.pop()); // 9

        System.out.println("\nInteger's stack after one removal:\n" + intStack);
        System.out.println("Integer's stack size after one removal: " + intStack.size()); // 9

        MyStack<Integer> intStackCopy = new MyStack<>(intStack);
        System.out.println("\nCopied stack:\n" + intStackCopy);

        System.out.println("\nTry to empty the stack using LIFO:");
        while (!intStack.isEmpty()) {
            System.out.print(intStack.pop() + " ");
        }

        System.out.println("\n\nInteger's stack after emptying:\n" + intStack);
        System.out.println("Integer's stack size after emptying: " + intStack.size()); // 0
        System.out.println("\nCopy must still contain elements:\n" + intStackCopy);
    }

    /**
     * Test accessing empty stack's tops; overfilling a stack.
     * Integer's wrapper is an arbitrary choice.
     */
    private static void testExceptions() {
        MyStack<Integer> intStack = new MyStack<>();

        try {
            System.out.println("Try to remove empty stack's top: ");
            System.out.print(intStack.pop());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException caught.");
        }

        try {
            System.out.println("\nTry to get empty stack's top: ");
            System.out.print(intStack.peek());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException caught.");
        }

        System.out.println("\nTry to add new 1 000 000 elements...");
        for (int i = 0; i < 1_000_000; i++) {
            intStack.push(i);
        }
        System.out.println("Integer's stack size: " + intStack.size()); // 1 000 000
    }
}

