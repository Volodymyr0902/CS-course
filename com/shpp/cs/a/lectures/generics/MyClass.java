package com.shpp.cs.a.lectures.generics;

public class MyClass<T> {
    private T obj;

    public MyClass (T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
