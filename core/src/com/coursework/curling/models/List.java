package com.coursework.curling.models;

public class List<T extends Comparable<T>> {
    private class Node{
        private T data;
        private Node prev, next;

        private Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void add(T element) {
        Node node = new Node(element, this.tail, null);
        if (this.tail != null) {
            this.tail.next = node;
        }
        if (this.head == null) {
            this.head = node;
        }
        this.tail = node;
        this.size++;
    }

    public T get(int index) {
        return findByIndex(index).data;
    }

    public void set(int index, T element) {
        Node node = findByIndex(index);
        Node newNode = new Node(element, node.prev, node);
        if (node.prev != null) {
            node.prev.next = newNode;
        }
        node.prev = newNode;
        this.size++;
    }

    public void remove(int index) {
        Node node = findByIndex(index);
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        this.size--;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int indexOf(T element) {
        Node iterator = this.head;
        int index = 0;
        while (iterator != null && iterator.data != element) {
            iterator = iterator.next;
            index++;
        }
        return iterator != null ? index : -1;
    }

    public void clear() {
        this.head = this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void sort() {
        sort(0, this.size - 1);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node findByIndex(int index) {
        checkIndex(index);
        Node iterator = this.head;
        while (index-- != 0) {
            iterator = iterator.next;
        }
        return iterator;
    }

    private void sort(int left, int right) {
        int mid = (left + right) / 2;
        int i = left;
        int j = right;
        do {
            while (this.get(i).compareTo(this.get(mid)) < 0) i++;
            while (this.get(j).compareTo(this.get(mid)) > 0) j--;
            if (i <= j) {
                T tmp = this.get(i);
                this.findByIndex(i).data = this.findByIndex(j).data;
                this.findByIndex(j).data = tmp;
                i++;
                j--;
            }
        } while (i <= j);
        if (left < j) sort(left, j);
        if (i <right) sort(i, right);
    }

    @Override
    public String toString() {
        Node iterator = this.head;
        String result = "";
        while (iterator != null) {
            result += iterator.data + " ";
            iterator = iterator.next;
        }
        return result.trim();
    }
}
