package com.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularListTest {
    @Test
    void addTest() {
        CircularList<Integer> list = new CircularList<>();
        list.addLast(10);
        list.addLast(11);

        assertEquals(10, list.get(0));
        assertEquals(11, list.get(1));
    }

    @Test
    void getTest() {
        CircularList<Integer> list = new CircularList<>();
        list.addLast(10);
        list.addLast(11);
        list.addLast(12);
        list.addLast(13);

        assertEquals(11, list.get(1));
        assertEquals(12, list.get(-2));
    }

    @Test
    void setTest() {
        CircularList<Integer> list = new CircularList<>();
        list.addFirst(10);
        list.addLast(11);

        list.set(1, 50);

        assertEquals(10, list.get(0));
        assertEquals(50, list.get(1));
    }

    @Test
    void removeTest() {
        CircularList<Integer> list = new CircularList<>();
        list.addFirst(10);
        list.addLast(11);

        list.remove(0);
        assertEquals(1, list.size());
        assertEquals(11, list.get(0));
    }

    @Test
    void iteratorGet() {
        CircularList<Integer> list = new CircularList<>();
        list.addLast(10);
        list.addLast(11);
        list.addLast(12);
        list.addLast(13);

        Iterator<Integer> iterator = list.iterator();
        assertEquals(10, iterator.get());
    }

    @Test
    void iteratorNextPrev() {
        CircularList<Integer> list = new CircularList<>();
        list.addLast(10);
        list.addLast(11);
        list.addLast(12);

        Iterator<Integer> iterator = list.iterator();
        assertEquals(10, iterator.get());
        iterator.next();
        assertEquals(11, iterator.get());
        iterator.next();
        assertEquals(12, iterator.get());
        iterator.next();
        assertEquals(10, iterator.get());
    }
}
