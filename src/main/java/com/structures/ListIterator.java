package com.structures;

public interface ListIterator <T> {
    boolean hasNext();
    T next();
    void remove();
    void set(T e);
    void add(T e);
    T get();
}
