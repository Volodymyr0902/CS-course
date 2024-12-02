package com.shpp.p2p.cs.vtaboranskyi.assignment9.tests;

import com.shpp.p2p.cs.vtaboranskyi.assignment9.utilities.MyLinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Tests MyLinkedList class by applying its methods in different ways to several parametrized types.
 */
public class TestMyLinkedList {

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
     *     <li>sets new element, shows size again,</li>
     *     <li>uses <code>Iterator</code> to remove inappropriate elements</li>
     *     <li>shows elements' presence</li>
     *     <li>Removes elements by indexes (edge are taken on purpose) and by value, shows size change</li>
     *     <li>clears a collection and shows changes</li>
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

        MyLinkedList<TestPerson> personsList =
                new MyLinkedList<>(person1, person2, person3, person4, person5, person6, person7);
        System.out.println("Persons List:\n" + personsList);
        System.out.println("Person's List's size: " + personsList.size()); // 7

        personsList.set(5, new TestPerson("Ryan", 27));
        System.out.println("\nNew element is set (inserted) at 5th index:\n" + personsList);
        System.out.println("Person's List's size after element insertion: " + personsList.size()); // 8

        // Iterator usage.
        Iterator<TestPerson> personIterator = personsList.iterator();
        TestPerson currentPerson;
        while (personIterator.hasNext()) {
            currentPerson = personIterator.next();
            if (currentPerson != null && currentPerson.getAge() < 18)
                personIterator.remove();
        }
        System.out.println("\nPersons List after filtration:\n" + personsList);

        System.out.println("\nCheck [Max, 37] presence: " +
                personsList.contains(new TestPerson("Max", 37))); // true
        System.out.println("Check [Ann, 22] presence: " +
                personsList.contains(new TestPerson("Ann", 22))); // false

        System.out.println("\nPersons List's size is: " + personsList.size()); // 6

        System.out.println("\nRemoved first element: " + personsList.removeByIndex(0)); // [Jane, 34]
        System.out.println("Now first element must be: " + personsList.get(0)); // [Mia, 22]
        System.out.println("Removed last element: " + personsList.removeByIndex(personsList.size() - 1)); // [Max, 37]
        System.out.println("Now last element must be: " + personsList.get(personsList.size() - 1)); // [Ryan, 27]
        System.out.println("\nCollection after removal by indexes:\n" + personsList);

        System.out.println("\nTrying remove [Karel, 53]: " +
                personsList.removeByValue(new TestPerson("Karel", 53)));
        System.out.println("Collection after removal by value:\n" + personsList); // [Karel, 53]

        System.out.println("\nCheck size after removals: " + personsList.size()); //3

        personsList.clear();
        System.out.println("\nPerson's List after clearing:\n" + personsList);
        System.out.println("Person's List's size after clearing: " + personsList.size()); // 0
    }

    /**
     * Tests Strings.
     * <ul>
     *     <li>fills a collection, shows elements,</li>
     *     <li>sets new element, shows a collection,</li>
     *     <li>uses <code>Iterator</code> to remove inappropriate elements</li>
     *     <li>removes elements by value/index, shows result</li>
     * </ul>
     */
    private static void testStrings() {
        MyLinkedList<String> customersList =
                new MyLinkedList<>("Gina", "Paul", "Sabrina", "Misty", "Sean", "Marco", "Aaron", "Kyle");
        System.out.println("Customers list:\n" + customersList);

        System.out.println("\nFirst element: " + customersList.get(0)); // Gina
        System.out.println("Last element: " + customersList.get(customersList.size() - 1)); // Kyle

        customersList.set(4, "Jacob");
        System.out.println("\nCustomers list after set():\n" + customersList);

        // Iterator usage.
        Iterator<String> customersListIterator = customersList.iterator();
        String customer;
        while (customersListIterator.hasNext()) {
            customer = customersListIterator.next();
            if (customer != null && customer.length() == 4)
                customersListIterator.remove();
        }
        System.out.println("\nCustomers list after filtration:\n" + customersList);

        System.out.println("\nRemove 'Marco': " + customersList.removeByValue("Marco"));
        System.out.println("After 'Marco' removal:\n" + customersList);
        System.out.println("\nRemove index 1 element: " + customersList.removeByIndex(1));
        System.out.println("After index 1 element removal:\n" + customersList);
    }

    /**
     * Tests Doubles.
     * <ul>
     *     <li>fills a collection, shows elements,</li>
     *     <li>removes elements by value/index, shows result</li>
     *     <li>clears a collection and shows changes</li>
     * </ul>
     */
    private static void testDoubles() {
        MyLinkedList<Double> doubleList = new MyLinkedList<>();
        for (int i = 0; i < 20; i++)
            doubleList.add(i * 3.14);

        System.out.println("Created Double's list:\n" + doubleList);

        System.out.println("\nTry to remove by value: " + doubleList.removeByValue(3.14 * 4)); // 12.56
        System.out.println("Double's list after removal:\n" + doubleList);

        System.out.println("\nTry to remove by index: " + doubleList.removeByIndex(8)); // 28.26
        System.out.println("Double's list after removal:\n" + doubleList);

        System.out.println("\nDouble's list is empty before clear(): " + doubleList.isEmpty()); // false
        doubleList.clear();
        System.out.println("Double's list is empty after clear(): " + doubleList.isEmpty()); // true
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
        MyLinkedList<Integer> intList = new MyLinkedList<>();
        for (int i = 0; i < 100; i++)
            intList.add(i);
        System.out.println("Created Integer's list:\n" + intList);

        // Iterator usage
        Iterator<Integer> intIterator = intList.iterator();
        while (intIterator.hasNext()) {
            Integer num = intIterator.next();
            if (num != null && num % 2 != 0)
                intIterator.remove();
        }
        System.out.println("\nRemoved odd Integers:\n" + intList);

        System.out.println("\nInteger's list contains 0: " + intList.contains(0)); // true
        System.out.println("Integer's list contains 100: " + intList.contains(100)); // false

        MyLinkedList<Integer> intListCopy = new MyLinkedList<>(intList);
        System.out.println("\nCopy of Integer's list:\n" + intListCopy);

        System.out.println("\nOriginal list's size: " + intList.size()); // 50
        System.out.println("Copied list's size: " + intListCopy.size()); // 50

        int sumIntListCopy = 0;
        for (int i = 0; i < intListCopy.size(); i++)
            if (intListCopy.get(i) != null)
                sumIntListCopy += intListCopy.get(i);
        System.out.println("\nSum of copy: " + sumIntListCopy); // 2450

        intListCopy.clear();
        System.out.println("\nCopy after clearing: " + intListCopy);

        for (int i = 0; i < intList.size(); i++)
            intListCopy.add(2 * i);
        System.out.println("\nCopy after refilling:\n" + intListCopy);
    }

    /**
     * Test exceptional cases which must throw exceptions. Integer's wrapper is an arbitrary choice.
     * <ul>
     *     <li>checks elements with indexes out of a collection's bounds</li>
     *     <li>tries insertion(set()) and removal out of bounds</li>
     *     <li>tries unchecked Iterator's next()</li>
     *     <li>checks collection's dynamical increasing property</li>
     * </ul>
     */
    private static void testExceptions() {
        MyLinkedList<Integer> intList = new MyLinkedList<>(1, 2, 3, 4, 5);

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
            System.out.println("\nTry to removeByIndex() an element beyond upper bound: ");
            System.out.print(intList.removeByIndex(100));
            System.out.println("Must never be print");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException caught.");
        }

        try {
            System.out.println("\nTry to removeByValue() an element beyond upper bound: ");
            System.out.print(intList.removeByValue(100));
            System.out.println("Must never be print");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException caught.");
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
