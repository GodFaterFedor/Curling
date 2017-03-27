package com.coursework.curling.models;

public class HashSet<T> {
    private HashMap<T, Object> container = new HashMap<T, Object>();

    public boolean add(T element) {

        if (contains(element)) {
            return false;
        } else {
            return container.put(element, null);
        }
    }

    public boolean contains(T element) {
        return container.containsKey(element);
    }

    public boolean remove(T element) {

        if (contains(element)) {
            return container.remove(element);
        } else {
            return false;
        }
    }

    public int size() {
        return container.size();
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    public void clear() {
        container.clear();
    }

    @Override
    public String toString() {

        return container.getKeys();
    }
}
