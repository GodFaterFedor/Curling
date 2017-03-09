package com.coursework.curling.models;

public class HashSet<T> {
    private HashMap<T, Object> container = new HashMap<T, Object>();

    public boolean add(T element) {
        return container.put(element, null);
    }

    public boolean contains(T element) {
        return container.containsKey(element);
    }

    public boolean remove(T element) {
        return container.remove(element);
    }

    public int size() {
        return container.size();
    }

    public boolean isEmpry() {
        return container.isEmpty();
    }

    public void clear() {
        container.clear();
    }
}
