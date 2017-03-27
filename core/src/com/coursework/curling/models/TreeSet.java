package com.coursework.curling.models;

public class TreeSet<T extends Comparable<T>> {

    private AvlTree<T> tree;

    public TreeSet() {
        this.tree = new AvlTree<T>();
    }

    public boolean add(T element) {
        if (this.tree.contains(element)) {
            return false;
        } else {
            this.tree.insert(element);
            return true;
        }
    }

    public boolean remove(T element) {
        if (this.tree.contains(element)) {
            this.tree.remove(element);
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        this.tree.makeEmpty();
    }

    @Override
    public String toString() {
        return this.tree.serializePrefix();
    }


}
