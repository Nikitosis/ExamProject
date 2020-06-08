package com.structures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class SPHashTable<K, V> implements HashTable<K, V> {
    private static final int DEFAULT_SIZE = 64;

    private class Node<K, V> {
        public K key;
        public V val;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    private Supplier<Collection<Node>> childCollectionCreator;
    private List<Collection<Node>> buckets;

    public SPHashTable(int size, Class collectionClass) {
        if(collectionClass == ArrayList.class) {
            childCollectionCreator = ArrayList::new;
        } else if (collectionClass == LinkedList.class) {
            childCollectionCreator = LinkedList::new;
        } else {
            this.childCollectionCreator = ArrayList::new;
        }
        initBucketsList(size);
    }

    public SPHashTable(int size) {
        this.childCollectionCreator = ArrayList::new;
        initBucketsList(size);
    }

    public SPHashTable() {
        this.childCollectionCreator = ArrayList::new;
        initBucketsList(DEFAULT_SIZE);
    }

    private void initBucketsList(int plusSize) {
        buckets = new ArrayList<>();
        for(int i=0;i<plusSize;i++) {
            buckets.add(childCollectionCreator.get());
        }
    }

    @Override
    public void put(K key, V val) {
        //if node already exists, then replace val
        Node foundNode = getNode(key);
        if(foundNode != null) {
            foundNode.val = val;
            return;
        }

        //if not exists then create new node
        Node newNode = new Node(key, val);
        int bucketIndex = getBucketIndexByKey(key);
        buckets.get(bucketIndex).add(newNode);
    }

    @Override
    public void remove(K key) {
        int bucketIndex = getBucketIndexByKey(key);
        Node foundNode = getNode(key);
        if(foundNode != null) {
            buckets.get(bucketIndex).remove(foundNode);
        }
    }

    @Override
    public V get(K key) {
        Node foundNode = getNode(key);
        if(foundNode == null) {
            return null;
        } else {
            return (V)foundNode.val;
        }
    }

    private Node getNode(K key) {
        int bucketIndex = getBucketIndexByKey(key);
        Collection<Node> bucket = buckets.get(bucketIndex);
        return bucket.stream()
                .filter(node -> node.key.equals(key))
                .findFirst()
                .orElse(null);
    }

    private int getBucketIndexByKey(K key) {
        return key.hashCode() % buckets.size();
    }
}
