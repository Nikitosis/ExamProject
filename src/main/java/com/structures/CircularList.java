package com.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class CircularList <T>{
    private class Node <T>{
        public Node nextNode;
        public Node prevNode;
        public T data;
        public Node() {}
        public Node(Node prevNode, Node nextNode, T data) {
            this.prevNode = prevNode;
            this.nextNode = nextNode;
            this.data = data;
        }
    }

    private class CLIterator implements ListIterator<T> {
        private Node<T> curNode;

        CLIterator(int index) {
            curNode = atIndex(index);
        }

        @Override
        public boolean hasNext() {
            return curNode.nextNode != null;
        }

        @Override
        public T next() {
            curNode = curNode.nextNode;
            return curNode.data;
        }

        @Override
        public void remove() {
            if(size==0) {
                return;
            }
            if(size==1) {
                head = null;
                size--;
                return;
            }

            Node nextNode = curNode.nextNode;
            Node prevNode = curNode.prevNode;

            prevNode.nextNode = nextNode;
            nextNode.prevNode = prevNode;

            if(head == curNode) {
                head = curNode.nextNode;
            }

            curNode = nextNode;

            size--;
        }

        @Override
        public void set(T e) {
            curNode.data = e;
        }

        @Override
        public void add(T e) {
            if(size == 0) {
                Node newNode = new Node(null, null, e);
                head = newNode;
                size++;
                return;
            }
            Node nextNode = curNode.nextNode;
            Node newNode = new Node(curNode, nextNode, e);
            nextNode.prevNode = newNode;
            curNode.nextNode = newNode;
            size++;
        }

        @Override
        public T get() {
            return curNode.data;
        }
    }

    private Node head;
    private int size;

    private Node atIndex(int index) {
        boolean isBackward = index < 0;
        index = Math.abs(index);
        if(index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node curNode = head;

        if(isBackward) {
            for(int i=0; i<index; i++) {
                curNode = curNode.prevNode;
            }
        } else {
            for(int i=0; i<index; i++) {
                curNode = curNode.nextNode;
            }
        }
        return curNode;
    }

    public ListIterator<T> iterator() {
        return new CLIterator(0);
    }

    public void addLast(T data) {
        if(size == 0) {
            head = new Node(null, null, data);;
            head.nextNode = head;
            head.prevNode = head;
            size++;
            return;
        }
        Node prevNode = head.prevNode;
        Node newNode = new Node(prevNode, head, data);
        prevNode.nextNode = newNode;
        head.prevNode = newNode;
        size++;
    }

    public void addFirst(T data) {
        if(size == 0) {
            head =  new Node(null, null, data);;
            head.nextNode = head;
            head.prevNode = head;
            size++;
            return;
        }
        Node prevNode = head.prevNode;
        Node newNode = new Node(prevNode, head, data);
        prevNode.nextNode = newNode;
        head.prevNode = newNode;
        //change head reference
        head = newNode;
        size++;
    }

    public void add(int index, T data) {
        if(index == 0) {
            addFirst(data);
            return;
        }

        Node curNode = atIndex(index);
        Node nextNode = curNode.nextNode;
        Node newNode = new Node(curNode, nextNode, data);
        newNode.nextNode = nextNode;
        newNode.prevNode = curNode;

        nextNode.prevNode = newNode;
        curNode.nextNode = nextNode;
        size++;
    }

    public T get(int index) {
        return (T)atIndex(index).data;
    }

    public void remove(int index) {
        Node curNode = atIndex(index);
        Node prevNode = curNode.prevNode;
        Node nextNode = curNode.nextNode;

        prevNode.nextNode = nextNode;
        nextNode.prevNode = prevNode;
        if(curNode == head) {
            head = curNode.nextNode;
        }
        size--;
    }

    public void set(int index, T data) {
        Node curNode = atIndex(index);
        curNode.data = data;
    }

    public int size() {
        return size;
    }
}
