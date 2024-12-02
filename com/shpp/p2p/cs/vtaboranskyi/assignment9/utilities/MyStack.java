package com.shpp.p2p.cs.vtaboranskyi.assignment9.utilities;

/**
 * This class represents a stack data structure, which works on the "LIFO" principle:
 * the last element added to a queue must be removed from it first of all.
 * <p>
 * Arbitrarily based on MyArrayList collection as for implementing a stack it's only needed
 * to remove last inner array's element in this case. For MyLinkedList implementation
 * it would only require to nullify the pre-last element's reference.
 * Nevertheless, searching by index would take more time for MyLinkedList, because it would
 * require to iterate though a collection from the start to the element of a given index,
 * one by one, as this collection doesn't use any inner array for indexation.
 * </p>
 *
 * @param <T> Any object as a parameter type.
 */
public class MyStack<T> extends MyArrayList<T> {

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        super();
    }

    /**
     * Constructs a stack as copy of a given one.
     *
     * @param queue A stack to be copied.
     */
    public MyStack(MyStack<T> queue) {
        super(queue);
    }

    /**
     * Constructs a stack of given elements.
     *
     * @param elements Varargs same type elements.
     */
    @SafeVarargs
    public MyStack(T... elements) {
        super(elements);
    }

    /**
     * Adds an element to the end (top) of a stack.
     *
     * @param element An element to be added.
     */
    public void push(T element) {
        add(element);
    }

    /**
     * Removes the last added element (top) from a stack.
     *
     * @return The removed element's value.
     */
    public T pop() {
        T element = get(size() - 1);
        remove(size() - 1);
        return element;
    }

    /**
     * Retrieves the last added element (top) of a stack.
     *
     * @return The last element of a stack.
     */
    public T peek() {
        return get(size() - 1);
    }

    /**
     * Searches the first match to a given element in a stack to retrieve its index.
     *
     * @param element An element to be searched.
     * @return The index of given element in a stack.
     */
    public int search(T element) {
        return indexOf(element);
    }
}
