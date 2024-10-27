package com.shpp.p2p.cs.vtaboranskyi.assignment9;

public class MyQueue<T> extends MyLinkedList<T> {
    public void offer(T element) {
        add(element);
    }

    public T poll() {
        try {
            return removeByIndex(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public T peek() {
        try {
            return get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}

