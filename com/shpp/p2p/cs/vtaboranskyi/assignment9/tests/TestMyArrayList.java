package com.shpp.p2p.cs.vtaboranskyi.assignment9.tests;

import com.shpp.p2p.cs.vtaboranskyi.assignment9.utilities.MyArrayList;
import java.util.Iterator;

/**
 * Tests MyArrayList class by applying its methods in different ways to several parametrized types.
 */
public class TestMyArrayList {

    public static void main(String[] args) {
        testIntegers();
//        testDoubles();
//        testStrings();
//        testObjects();
//        testExceptions();
    }

    /**
     * Tests TestPerson objects:
     * <ul>
     *     <li>fills a collection, shows its size,</li>
     *     <li>uses <code>Iterator</code> to remove inappropriate elements, shows a collection and its size,</li>
     *     <li>shows elements' presence,</li>
     *     <li>checks if queried element's index are correct</li>
     *     <li>clears a collection and shows changes</li>
     *     <li>sets new element, differs size from capacity</li>
     * </ul>
     */
    private static void testObjects() {
        // Filling collection with elements.
        TestPerson person1 = new TestPerson("Jane", 34);
        TestPerson person2 = new TestPerson("Mia", 22);
        TestPerson person3 = new TestPerson("Karel", 53);
        TestPerson person4 = new TestPerson("Fred", 12);
        TestPerson person5 = new TestPerson("Ruth", 64);
        TestPerson person6 = new TestPerson("Max", 37);
        TestPerson person7 = new TestPerson("George", 10);

        MyArrayList<TestPerson> personsList =
                new MyArrayList<>(person1, person2, person3, person4, person5, person6, person7);
        System.out.println("Persons List:\n" + personsList);

        // Iterator usage.
        Iterator<TestPerson> personIterator = personsList.iterator();
        TestPerson currentPerson;
        while (personIterator.hasNext()) {
            currentPerson = personIterator.next();
            if (currentPerson != null && currentPerson.getAge() < 18)
                personIterator.remove();
        }
        System.out.println("\nPersons List after filtration:\n" + personsList);
        System.out.println("\nPersons List's size is: " + personsList.size()); // 5

        System.out.println("\nCheck [Max, 37] presence: " +
                personsList.contains(new TestPerson("Max", 37))); // true
        System.out.println("Check [Ann, 22] presence: " +
                personsList.contains(new TestPerson("Ann", 22))); // false

        System.out.println("Check indexes correspondence:");
        for (int i = 0; i < personsList.size(); i++) {
            System.out.println("Iteration number = " + i +
                    "; indexOf() result = " + personsList.indexOf(personsList.get(i))); // 0, 1, 2, 3, 4 for both
        }

        personsList.clear();
        System.out.println("\nPerson's List after clearing:\n" + personsList);
        System.out.println("Person's List's size after clearing: " + personsList.size());

        personsList.set(5, new TestPerson("Ryan", 27));
        System.out.println("\nNew element is set at 5th index in 'empty' Person's List:\n" + personsList);
        System.out.println("Person's List's size now (nulls are excluded): " + personsList.size());
        System.out.println("New element's index: " + personsList.indexOf(new TestPerson("Ryan", 27)) +
                " as capacity (inner array's length) is: " + personsList.getCapacity());
    }

    /**
     * Tests Strings.
     * <ul>
     *     <li>fills a collection, shows elements,</li>
     *     <li>sets new element, shows a collection,</li>
     *     <li>uses <code>Iterator</code> to remove inappropriate elements</li>
     * </ul>
     */
    private static void testStrings() {
        MyArrayList<String> customersList =
                new MyArrayList<>("Gina", "Paul", "Sabrina", "Misty", "Sean", "Marco", "Aaron", "Kyle");
        System.out.println("Customers list:\n" + customersList);

        System.out.println("First element: " + customersList.get(0));
        System.out.println("Last element: " + customersList.get(customersList.size() - 1));

        System.out.println("Index of Misty: " + customersList.indexOf("Misty")); // 3
        System.out.println("Index of Sam: " + customersList.indexOf("Sam")); // -1

        customersList.set(4, "Jacob");
        System.out.println("Customers list after set():\n" + customersList);

        // Iterator usage.
        Iterator<String> customersListIterator = customersList.iterator();
        String customer;
        while (customersListIterator.hasNext()) {
            customer = customersListIterator.next();
            if (customer != null && customer.length() == 4)
                customersListIterator.remove();
        }
        System.out.println("Customers list after filtration:\n" + customersList);
    }

    /**
     * Tests Doubles.
     * <ul>
     *     <li>fills a collection, shows elements,</li>
     *     <li>clears a collection and shows changes</li>
     * </ul>
     */
    private static void testDoubles() {
        MyArrayList<Double> doubleList = new MyArrayList<>(0);
        for (int i = 0; i < 20; i++) {
            doubleList.add(i * 3.14);
        }

        System.out.println("Created Double's list with initial 0 capacity:\n" + doubleList);

        System.out.println("Double's list is empty: " + doubleList.isEmpty());
        doubleList.clear();
        System.out.println("Double's list is empty: " + doubleList.isEmpty());
    }

    /**
     * Tests Integers.
     * <ul>
     *     <li>fills a collection, shows elements,</li>
     *     <li>uses <code>Iterator</code> to remove inappropriate elements</li>
     *     <li>shows elements' presence</li>
     *     <li>makes copy of a collection, shows their sizes</li>
     *     <li>sums up all the elements</li>
     *     <li>clears a collection and shows changes</li>
     *     <li>fills collection again and shows result</li>
     * </ul>
     */
    private static void testIntegers() {
        MyArrayList<Integer> intList = new MyArrayList<>();
        for (int i = 0; i < 100; i++)
            intList.add(i);
        System.out.println("Created Integer's list:\n" + intList);

        // Iterator usage
        Iterator<Integer> intIterator = intList.iterator();
        while (intIterator.hasNext()) {
            Integer num = intIterator.next();
            if (num != null && num % 2 != 0) {
                intIterator.remove();
            }
        }
        System.out.println("\nRemoved odd Integers:\n" + intList);

        System.out.println("\nInteger's list contains 0: " + intList.contains(0)); // true
        System.out.println("Integer's list contains 100: " + intList.contains(100)); // false

        MyArrayList<Integer> intListCopy = new MyArrayList<>(intList);
        System.out.println("\nCopy of Integer's list:\n" + intListCopy);

        System.out.println("\nOriginal list's size: " + intList.size()); // 50
        System.out.println("Copied list's size: " + intListCopy.size()); // 50

        int sumIntListCopy = 0;
        for (int i = 0; i < intListCopy.size(); i++) {
            sumIntListCopy += intListCopy.get(i);
        }
        System.out.println("\nSum of copy: " + sumIntListCopy);

        intListCopy.clear();
        System.out.println("\nCopy after clearing: " + intListCopy);

        for (int i = 0; i < intList.size(); i++) {
            intListCopy.add(2 * i);
        }
        System.out.println("\nCopy after refilling:\n" + intListCopy);
    }

    /**
     * Test exceptional cases which must throw exceptions. Integer's wrapper is an arbitrary choice.
     * <ul>
     *     <li>checks elements with indexes out of a collection's bounds</li>
     *     <li>tries element reassigning(set()) and removal out of bounds</li>
     *     <li>tries unchecked Iterator's next()</li>
     *     <li>checks collection's dynamical increasing property</li>
     * </ul>
     */
    private static void testExceptions() {
        MyArrayList<Integer> intList = new MyArrayList<>(1, 2, 3, 4, 5);

        try {
            System.out.println("\nTry to get index -1 element: ");
            System.out.print(intList.get(-1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException caught.");
        }

        try {
            System.out.println("\nTry to get an element beyond upper bound: ");
            System.out.print(intList.get(100));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException caught.");
        }

        try {
            System.out.println("\nTry to set an element beyond upper bound: ");
            intList.set(100, 100);
            System.out.println("Must never be print");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException caught.");
        }

        try {
            System.out.println("\nTry to remove an element beyond upper bound: ");
            intList.remove(100);
            System.out.println("Must never be print");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException caught.");
        }

        intList.clear();

        System.out.println("\nTry Iterator's next() without hasNext() check in an empty collection:");
        Iterator<Integer> integerIterator = intList.iterator();
        try{
            System.out.println(integerIterator.next());
            System.out.println("Must never be print");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException caught.");
        }

        System.out.println("\nTry to add new 1 000 000 elements...");
        for (int i = 0; i < 1_000_000; i++) {
            intList.add(i);
        }
        System.out.println("Integer's list size: " + intList.size()); // 1 000 000
    }
}
