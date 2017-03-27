package com.coursework.curling.models;

import java.util.Arrays;

public class HashMap<K, V> {

    private final int BASE = 37;

    private Node[] container;
    private int size = 0;

    public HashMap() {
        this.container = new Node[BASE];
    }

    public boolean put(K key, V value) {
        int position = key.hashCode() % BASE;
        Node<K, V> node = new Node<K, V>(key, value);
        if (this.container[position] == null) {
            this.container[position] = node;
            this.size++;
            return true;
        }
        Node<K, V> tmp = this.container[position];
        Node<K, V> parent = null;
        while (tmp.next != null && tmp.next.key != key) {
            parent = tmp;
            tmp = tmp.next;
        }
        if (parent == null) {
            this.container[position] = node;
            return false;
        }
        if (tmp.next != null) {
            node.next = tmp.next;
            parent.next = node;
            return false;
        }
        tmp.next = node;
        this.size++;
        return true;
    }

    public V get(K key) {
        Node<K, V> node = this.container[key.hashCode() % BASE];
        while (node != null && node.key != key) {
            node = node.next;
        }
        return node != null ? node.value : null;
    }

    public boolean remove(K key) {
        int position = key.hashCode() % BASE;
        Node<K, V> node = this.container[position];
        Node<K, V> parent = null;
        while (node != null && !node.key.equals(key)) {
            node = node.next;
            parent = node;
        }
        if (node == null) {
            return false;
        }
        if (parent == null) {
            this.container[position] = null;
            this.size--;
            return true;
        }
        parent.next = node.next;
        this.size--;
        return true;
    }

    public boolean containsKey(K key) {
        int position = key.hashCode() % BASE;
        Node<K, V> node = this.container[position];
        Node<K, V> parent = null;
        while (node != null && node.key != key) {
            node = node.next;
            parent = node;
        }
        return node != null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.container = new Node[BASE];
        this.size = 0;
    }

    @Override
    public String toString() {

        String string = "";

        for (Node node : container) {
            if (node != null) {
                if (!string.equals("")) {
                    string += ", ";
                }
                string += node.toString();
            }
        }

        return string;
    }

    public String getKeys() {

        String string = "";

        for (Node node : container) {
            if (node != null) {
                if (!string.equals("")) {
                    string += ", ";
                }
                string += node.getKey();
            }
        }

        return string;

    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node next = null;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {

            String string = key + ": " + value;
            if (next != null) {
                string += "next = " + next;
            }

            return string;

        }

        public String getKey() {
            return key.toString();
        }
    }
}
