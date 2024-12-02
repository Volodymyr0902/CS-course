package com.shpp.p2p.cs.vtaboranskyi.assignment9.utilities;

/**
 * This class represents a queue data structure, which works on the "FIFO" principle:
 * the first element added to a queue must be removed from it first of all.
 * Based on MyLinkedList collection as for implementing a queue it's only needed
 * to change a reference, without indexes reassigning for the rest of a collection.
 *
 * @param <T> Any object as a parameter type.
 */
public class MyQueue<T> extends MyLinkedList<T> {

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        super();
    }

    /**
     * Constructs a queue as copy of a given one.
     *
     * @param queue A queue to be copied.
     */
    public MyQueue(MyQueue<T> queue) {
        super(queue);
    }

    /**
     * Constructs a queue of given elements.
     *
     * @param elements Varargs same type elements.
     */
    @SafeVarargs
    public MyQueue(T... elements) {
        super(elements);
    }

    /**
     * Adds an element to the end (tail) of a queue.
     *
     * @param element An element to be added.
     */
    public void offer(T element) {
        add(element);
    }

    /**
     * Removes the first element (head) of a queue.
     *
     * @return The removed element's value.
     */
    public T poll() {
        try {
            return removeByIndex(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Retrieves the first element (head) of a queue.
     *
     * @return The queue's first element.
     */
    public T peek() {
        try {
            return get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}

