package com.shpp.p2p.cs.vtaboranskyi.assignment9.utilities;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a collection, which uses reference nodes objects to hold its elements.
 * This class has methods for different manipulations with the collection, such as:
 * <ul>
 *     <li>add/remove its elements,</li>
 *     <li>get an element by index,</li>
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
public class MyLinkedList<T> {

    /**
     * These contain the first and the last elements (permits creating a deque).
     */
    private Node<T> first;
    private Node<T> last;

    /**
     * This holds number of elements a collection consists of.
     */
    private int size;

    /**
     * This inner class represents an element of a collection.
     *
     * @param <T> Any object as a parameter type.
     */
    private static class Node<T> {
        /**
         * Current element's value.
         */
        T element;

        /**
         * The previous and the next element's objects.
         */
        Node<T> previous;
        Node<T> next;

        /**
         * Constructs a collection's element object.
         *
         * @param element The element's value.
         */
        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * Constructs an empty collection.
     */
    public MyLinkedList() {
        this.size = 0;
    }

    /**
     * Constructs a collection as copy of a given one.
     *
     * @param list A collection to be copied.
     */
    public MyLinkedList(MyLinkedList<T> list) {
        this.size = 0;
        for (int i = 0; i < list.size; i++)
            add(list.get(i));
    }

    /**
     * Constructs a collection of given elements.
     *
     * @param elements Varargs same type elements.
     */
    @SafeVarargs
    public MyLinkedList(T... elements) {
        this.size = 0;
        for (T element : elements)
            add(element);
    }

    /**
     * Adds given element to a collection.
     * In case a collection was empty both first and last elements will refer to this element,
     * else changes the last's element reference and sets given element as the last.
     * Always increases a collection's size.
     *
     * @param element An element's value to be added.
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            newNode.next = null;
            newNode.previous = null;
            first = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
        }
        last = newNode;
        size++;
    }

    /**
     * Inserts a new element in a collection at the queried index. Formally shifts all the next elements
     * by 1 index to the end of a collection, but, actually, only changes the references, so that
     * the new element at the given index refers to the old one as if it was next to it and to the previous
     * element of the old one, thus it becomes the previous to the new one.
     *
     * @throws IndexOutOfBoundsException if a collection doesn't contain element with a queried index.
     *                                   <p>Note: if queried index equals a collection's size, new
     *                                   element will be normally added as a new one.</p>
     * @param index The index of an element to be changed.
     * @param element The element's value to be set as the new one.
     */
    public void set(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        Node<T> newNode = new Node<>(element);

        if (index == 0)
            add(element);

        if (index == size) {
            last.next = newNode;
            last = newNode;
        }

        // Assigns the first element, then the one at the queried index.
        Node<T> oldNode = first;
        for (int i = 0; i < index; i++)
            oldNode = oldNode.next;

        Node<T> oldPrevious = oldNode.previous;
        oldPrevious.next = newNode;
        oldNode.previous = newNode;
        newNode.previous = oldPrevious;
        newNode.next = oldNode;
        size++;
    }

    /**
     * Retrieves a collection's element at the given index.
     *
     * @throws IndexOutOfBoundsException if a collection doesn't contain element with a queried index.
     * @param index The sequential number of element in a collection, relatively to the first one.
     * @return The element at the given index or <code>null</code>.
     */
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> result = first;
        for (int i = 0; i < index; i++)
            result = result.next;

        return result == null ? null : result.element;
    }

    /**
     * Removes an element from a collection, using its index. Changes its references, so that its previous
     * element refers to its next and vice versa. Always decreases the collection's size.
     *
     * @throws IndexOutOfBoundsException if a collection doesn't contain element with a queried index.
     * @param index The sequential number of element in a collection, relatively to the first one.
     * @return The removed element's value.
     */
    public T removeByIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        // The element to be removed.
        Node<T> scope = first;
        if (index == 0)
            first = first.next;

        for (int i = 0; i < index; i++)
            scope = scope.next;

        // The adjacent elements of the one to be removed.
        Node<T> scopePrev = scope.previous;
        Node<T> scopeNext = scope.next;
        if (scopePrev != null)
            scopePrev.next = scopeNext;
        if (scopeNext != null)
            scopeNext.previous = scopePrev;

        size--;
        return scope.element;
    }

    /**
     * Removes an element from a collection, using its value. Changes its references, so that its previous
     * element refers to its next and vice versa. Always decreases the collection's size.
     *
     * @throws NoSuchElementException if a collection doesn't contain element with a queried value.
     * @param element The value of the element to be removed.
     * @return The removed element's value.
     */
    public T removeByValue(T element) {
        // The element to be removed.
        Node<T> scope = first;

        // To simplify the case for the first collection's element.
        if (first.element.equals(element)) {
            first = first.next;
            size--;
            return scope.element;
        }

        for (int i = 0; i < size; i++) {
            if (scope.element.equals(element))
                break;
            scope = scope.next;
        }

        if (scope == null)
            throw new NoSuchElementException();

        // The adjacent elements of the one to be removed.
        Node<T> scopePrev = scope.previous;
        Node<T> scopeNext = scope.next;
        if (scopePrev != null)
            scopePrev.next = scopeNext;
        if (scopeNext != null)
            scopeNext.previous = scopePrev;

        size--;
        return scope.element;
    }

    /**
     * Removes all the elements from a collection by eliminating fields' references.
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Checks if a collection contains a given element by comparing its value to every of a collection.
     *
     * @param element An element to be checked for presence.
     * @return <code>True</code>, if a collection contains given element.
     */
    public boolean contains(T element) {
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element))
                return true;
            current = current.next;
        }
        return false;
    }

    /**
     * Retrieves the elements number a collection consists of, which equals its size.
     *
     * @return The collection's size.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if a collection contains at least one element.
     *
     * @return <code>True</code>, if a collection doesn't have any elements.
     */
    public boolean isEmpty() {
        return size == 0;
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
                return ++iteratorIndex < size;
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
                return get(iteratorIndex);
            }

            /**
             * Removes an element from a collection without missing any element
             * during proceeding iteration and causing concurrency exceptions.
             */
            @Override
            public void remove() {
                removeByIndex(iteratorIndex--);
            }
        };
    }

    /**
     * Converts a collection's content to a string, enclosed in
     * square brackets with all the elements sequentially enlisted.
     *
     * @return A string representation of a collection.
     */
    @Override
    public String toString() {
        StringBuilder stringList = new StringBuilder("[");
        Node<T> current = first;
        if (current == null)
            return stringList + "]";
        if (size == 1)
            return stringList.toString() + current.element + "]";

        while (current.next != null) {
            stringList.append(current.element).append(", ");
            current = current.next;
        }
        return stringList.toString() + current.element + "]";
    }

}
