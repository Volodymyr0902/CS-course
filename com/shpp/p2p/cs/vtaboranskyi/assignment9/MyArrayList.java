package com.shpp.p2p.cs.vtaboranskyi.assignment9;

import java.util.Iterator;

public class MyArrayList<T> {

    protected T[] container;
    private int capacity;
    private int iteratorIndex;
    private int addIndex = 0;

    public MyArrayList() {
        capacity = 16;
        this.container = (T[])new Object[this.capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.container = (T[])new Object[this.capacity];
    }

    public MyArrayList(MyArrayList<T> array) {
        this.capacity = array.size();
        this.container = (T[])new Object[this.capacity];
        for (int i = 0; i < array.size(); i++) {
            this.container[i] = (T) array.get(i);
        }
    }

    public void add(T newElement) {
        if (addIndex < capacity) {
            this.container[addIndex] = newElement;
            addIndex++;
        } else {
            capacity *= 2;
            T[] enlarged = (T[])new Object[capacity];
            for (int i = 0; i < container.length; i++) {
                enlarged[i] = container[i];
            }
            enlarged[addIndex] = newElement;
            addIndex++;
            this.container = enlarged;
        }
    }

    public Object get(int index) {
        return this.container[index];
    }

    public void set(int index, T element) {
        this.container[index] = element;
    }

    public void remove(int index) {
        while (index < this.container.length - 1) {
            this.container[index] = this.container[++index];
        }
        container[container.length - 1] = null;
    }

    public void clear() {
        for (int i = 0; i < container.length; i++) {
            this.container[i] = null;
        }
    }

    public boolean contains(T checkElement) {
        for (T element : container) {
            if (element.equals(checkElement))
                return true;
        }
        return false;
    }

    public boolean isEmpty() {
        for (T element : container) {
            if (element != null)
                return false;
        }
        return true;
    }

    public int size() {
        int counter = 0;
        for (T element : this.container) {
            if (element == null)
                break;
            counter++;
        }
        return counter;
    }

    public int indexOf(T element) {
        for (int i = 0; i < container.length; i++)
            if (container[i].equals(element))
                return i;
        return -1;
    }

    public Iterator<T> iterator() {
        iteratorIndex = -1;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return ++iteratorIndex < container.length;
            }

            @Override
            public T next() {
                return container[iteratorIndex];
            }

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

    public String toString() {
        String stringList = "[";
        for (int i = 0; i < container.length; i++) {
            if (container[0] == null) {
                break;
            } else if (i < container.length - 1 && container[i + 1] == null || i == container.length - 1) {
                stringList += container[i];
                break;
            } else {
                stringList += container[i] + ", ";
            }
        }
        return stringList + "]";
    }

}