package com.shpp.p2p.cs.vtaboranskyi.assignment9;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> {

    protected Node<T> first;
    protected Node<T> last;
    private int size;
    private int iteratorIndex;

    private static class Node<T> {
        T element;
        Node<T> previous;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    public MyLinkedList() {
        this.size = 0;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            newNode.next = null;
            newNode.previous = null;
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
            last = newNode;
        }
        size++;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        Node<T> newNode = new Node<>(element);

        if (index == 0)
            add(element);

        if (index == size) {
            last.next = newNode;
            last = newNode;
        }

        Node<T> oldNode = first;
        for (int i = 0; i < index; i++) {
            oldNode = oldNode.next;
        }

        Node<T> oldPrevious = oldNode.previous;
        oldPrevious.next = newNode;
        oldNode.previous = newNode;
        newNode.previous = oldPrevious;
        newNode.next = oldNode;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.element;
    }

    public T removeByIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> scope = first;
        if (index == 0)
            first = first.next;

        for (int i = 0; i < index; i++) {
            scope = scope.next;
        }

        Node<T> scopePrev = scope.previous;
        Node<T> scopeNext = scope.next;
        if (scopePrev != null)
            scopePrev.next = scopeNext;
        if (scopeNext != null)
            scopeNext.previous = scopePrev;

        size--;
        return scope.element;
    }

    public T removeByValue(T element) {
        Node<T> scope = first;
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

        Node<T> scopePrev = scope.previous;
        Node<T> scopeNext = scope.next;
        if (scopePrev != null)
            scopePrev.next = scopeNext;
        if (scopeNext != null)
            scopeNext.previous = scopePrev;

        size--;
        return scope.element;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean contains(T element) {
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element))
                return true;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<T> iterator() {
        iteratorIndex = 0;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iteratorIndex < size;
            }

            @Override
            public T next() {
                return get(iteratorIndex++);
            }

            @Override
            public void remove() {
                removeByIndex(iteratorIndex - 1);
            }
        };
    }

    @Override
    public String toString() {
        String stringList = "[";
        Node<T> current = first;
        if (current == null)
            return stringList + "]";
        if (size == 1)
            return stringList + current.element + "]";

        while (current.next != null) {
            stringList += current.element + ", ";
            current = current.next;
        }
        return stringList + current.element + "]";
    }

}
