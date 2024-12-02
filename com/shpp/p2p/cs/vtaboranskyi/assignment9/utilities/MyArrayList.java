package com.shpp.p2p.cs.vtaboranskyi.assignment9.utilities;

import java.util.Iterator;

/**
 * This class represents a collection, which uses an array to hold its elements.
 * The inner array is dynamical, so it gets enlarged when more space is needed.
 * This class has methods for different manipulations with the collection, such as:
 * <ul>
 *     <li>add/remove its elements,</li>
 *     <li>get an element by index/index by element's value,</li>
 *     <li>set their values to different,</li>
 *     <li>check if collection contains some element or is empty,</li>
 *     <li>clear the collection,</li>
 *     <li>iterate though collection,</li>
 *     <li>get collection's content as a string.</li>
 * </ul>
 * <p>Note: the use of a "collection" term within this class only refers to its instance.</p>
 *
 * @param <T> Any object as a parameter type.
 */
@SuppressWarnings("unchecked")
public class MyArrayList<T> {

    /**
     * The elements number a just instantiated collection may contain at most.
     */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Contains all the elements whenever added to a collection.
     */
    protected T[] container;

    /**
     * The elements number a collection may contain at most at certain point of time.
     */
    private int capacity;

    /**
     * Indicates the index of the next added element; always equals 0 after collection's instantiation.
     */
    private int nextIndex;

    /**
     * Constructs an empty collection (inner array) with the <code>INITIAL_CAPACITY</code>.
     */
    public MyArrayList() {
        this.capacity = INITIAL_CAPACITY;
        this.container = (T[]) new Object[this.capacity];
        this.nextIndex = 0;
    }

    /**
     * Constructs an empty collection (inner array) with a given capacity or with the value 1.
     *
     * @param capacity The size of the collection's inner array.
     */
    public MyArrayList(int capacity) {
        this.capacity = capacity > 0 ? capacity : 1;
        this.container = (T[]) new Object[this.capacity];
        nextIndex = 0;
    }

    /**
     * Constructs a collection (inner array) as a copy of a given list with the capacity trimmed to its size.
     *
     * @param list The list to be copied.
     */
    public MyArrayList(MyArrayList<T> list) {
        this.capacity = list.size();
        this.container = (T[]) new Object[this.capacity];
        for (int i = 0; i < list.size(); i++) {
            this.container[i] = list.get(i);
        }
        this.nextIndex = this.capacity;
    }

    /**
     * Constructs a collection (inner array) of given elements.
     *
     * @param elements Varargs same type elements.
     */
    public MyArrayList(T... elements) {
        this.capacity = INITIAL_CAPACITY;
        this.container = (T[]) new Object[this.capacity];
        this.nextIndex = 0;
        for (T element : elements)
            this.container[nextIndex++] = element;
    }

    /**
     * Adds given element to a collection at index of:
     * <ul>
     *     <li>0 - in case no element was added before,</li>
     *     <li>the next to the one of the previously added element</li>
     * </ul>
     * The second case is also valid for the situation when the current inner array is filled,
     * so the new one with the current's doubled capacity is created with all the elements copied,
     * starting from the 0 index.
     *
     * @param newElement An element to be added to a collection.
     */
    public void add(T newElement) {
        if (nextIndex < capacity) {
            this.container[nextIndex] = newElement;
            nextIndex++;
        } else {
            capacity *= 2;
            T[] enlarged = (T[]) new Object[capacity];
            copyValues(enlarged);
            enlarged[nextIndex] = newElement;
            nextIndex++;
            this.container = enlarged;
        }
    }

    /**
     * Sequentially copies values from a collection's current inner array to a local one.
     *
     * @param enlarged Local array with the double capacity value, relatively to the inner array.
     */
    private void copyValues(T[] enlarged) {
        for (int i = 0; i < container.length; i++)
            enlarged[i] = container[i];
    }

    /**
     * Retrieves a collection's element of a queried index.
     *
     * @param index An element's index in a collection.
     * @return This element's value.
     */
    public T get(int index) {
        return this.container[index];
    }

    /**
     * Changes an element's value to the given one.
     *
     * @param index   An element's index in a collection.
     * @param element New element's value to be set.
     */
    public void set(int index, T element) {
        this.container[index] = element;
    }

    /**
     * Removes an element of a specified index from a collection.
     *
     * @throws IndexOutOfBoundsException if given index is out of inner array's bounds.
     * @param index The index of an element to be removed.
     */
    public void remove(int index) {
        if (index < 0 || index >= container.length)
            throw new IndexOutOfBoundsException();
        while (index < this.container.length - 1) {
            this.container[index] = this.container[++index];
        }
        container[container.length - 1] = null;
    }

    /**
     * Removes all the elements from a collection.
     */
    public void clear() {
        for (int i = 0; i < container.length; i++) {
            this.container[i] = null;
        }
    }

    /**
     * Checks if a collection contains queried element.
     *
     * @param checkElement An element to be checked for presence.
     * @return <code>True</code>, if a collection contains queried element.
     */
    public boolean contains(T checkElement) {
        for (T element : container) {
            if (element != null && element.equals(checkElement))
                return true;
        }
        return false;
    }

    /**
     * Checks if a collection contains any not-null element.
     *
     * @return <code>True</code>, if a collection contains at least one not-null element.
     */
    public boolean isEmpty() {
        for (T element : container) {
            if (element != null)
                return false;
        }
        return true;
    }

    /**
     * Counts the number of not-null elements in a collection.
     *
     * @return The number of not-null elements a collection contains.
     */
    public int size() {
        int counter = 0;
        for (T element : this.container) {
            if (element != null)
                counter++;
        }
        return counter;
    }

    /**
     * @return Inner array's length.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Gets index of a first met element, specified by its value.
     *
     * @param element An element, whose index must be got.
     * @return The index of the first matching element or <code>-1</code> if a collection doesn't contain such.
     */
    public int indexOf(T element) {
        for (int i = 0; i < container.length; i++)
            if (container[i] != null && container[i].equals(element))
                return i;
        return -1;
    }

    /**
     * Creates an iterator with implemented methods to go through a collection.
     *
     * @return An iterator for a collection.
     */
    public Iterator<T> iterator() {
        return new Iterator<>() {
            // The index of the next element to be checked for presence.
            int iteratorIndex = -1;

            /**
             * Checks if current element of iteration is last in a collection.
             * <code>iteratorIndex</code> is incremented exactly here on purpose,
             * as every element goes checked before any manipulations.
             *
             * @return <code>True</code>, if current element is not last.
             */
            @Override
            public boolean hasNext() {
                return ++iteratorIndex < container.length;
            }

            /**
             * Retrieves an element next to the previously retrieved or
             * the first one if the method is invoked for the first time.
             * <p>Note: An element must be prior checked for presence by <code>hasNext()</code>.</p>
             *
             * @return The next element.
             */
            @Override
            public T next() {
                return container[iteratorIndex];
            }

            /**
             * Removes an element from a collection without missing any element
             * during proceeding iteration and causing concurrency exceptions.
             */
            @Override
            public void remove() {
                int remIndex = iteratorIndex;
                while (remIndex < container.length - 1) {
                    container[remIndex] = container[remIndex + 1];
                    remIndex++;
                }
                container[container.length - 1] = null;
                iteratorIndex--;
            }
        };
    }

    /**
     * Converts a collection's content to a string, enclosed in
     * square brackets with all not-null elements sequentially enlisted.
     *
     * @return A string representation of a collection.
     */
    public String toString() {
        StringBuilder stringList = new StringBuilder("[");
        for (T t : container) {
            if (t != null) {
                if (stringList.compareTo(new StringBuilder("[")) == 0) {
                    stringList.append(t);
                } else {
                    stringList.append(", ").append(t);
                }
            }
        }
        return stringList + "]";
    }

}