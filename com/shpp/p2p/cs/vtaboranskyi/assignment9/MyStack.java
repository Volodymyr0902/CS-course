package com.shpp.p2p.cs.vtaboranskyi.assignment9;

public class MyStack<T> extends MyArrayList<T> {

    public void push(T element) {
        add(element);
    }

    public T pop() {
        T element = (T) get(size() - 1);
        remove(size() - 1);
        return element;
    }

    public T peek() {
        return (T) get(size() - 1);
    }

    public int search(T element) {
        return indexOf(element);
    }

}
