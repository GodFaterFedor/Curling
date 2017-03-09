package com.coursework.curling.models;


import java.util.NoSuchElementException;

public class TreeSet<T extends Comparable<T>> {
    private class Node {
        private T data;
        private Node left = null, right = null;
        private int height = 1;

        private Node(T element) {
            this.data = element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    ", height=" + height +
                    '}';
        }
    }

    private Node root = null;
    private int size = 0;

    public boolean add(T element) {
        try {
            this.root = add(this.root, element);
        } catch (ArrayStoreException ex) {
            return false;
        }
        this.size++;
        return true;
    }

    public boolean contains(T element) {
        Node node = find(this.root, element);
        return node != null && node.data == element;
    }

    public T first() {
        if (this.root == null) {
            return null;
        }
        Node node = findMin(this.root);
        return node != null ? node.data : null;
    }

    public T last() {
        if (this.root == null) {
            return null;
        }
        Node node = findMax(this.root);
        return node != null ? node.data : null;
    }

    public boolean remove(T element) {
        try {
            remove(this.root, element);
        } catch (NoSuchElementException ex) {
            return false;
        }
        this.size--;
        return true;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        return view(this.root);
    }

    private Node find(Node root, T element) {
        while (root != null && root.data != element) {
            if (element.compareTo(root.data) < 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    private String view(Node root) {
        if (root == null) {
            return "";
        }
        String result = view(root.left);
        result += root.data.toString() + " ";
        result += view(root.right);
        return result;
    }

    private Node findMin( Node root) {
        return root.left != null ? findMin(root.left) : root;
    }

    private Node findMax( Node root) {
        return root.right != null ? findMax(root.right) : root;
    }

    private void setHeight( Node root) {
        int left = root.left != null ? root.left.height : 0;
        int right = root.right != null ? root.right.height : 0;
        root.height = left > right ? left + 1 : right + 1;
    }

    private Node rotateLeft( Node root) {
        Node tmp = root.right;
        root.right = tmp.left;
        tmp.left = root;
        setHeight(root);
        setHeight(tmp);
        return tmp;
    }

    private Node rotateRight( Node root) {
        Node tmp = root.left;
        root.left = tmp.right;
        tmp.right = root;
        setHeight(root);
        setHeight(tmp);
        return tmp;
    }

    private int getHeightDifference( Node root) {
        int left = root.left != null ? root.left.height : 0;
        int right = root.right != null ? root.right.height : 0;
        return right - left;
    }

    private Node balance( Node root) {
        setHeight(root);
        if (getHeightDifference(root) == 2) {
            if (getHeightDifference(root.right) < 0) {
                root.right = rotateRight(root.right);
            }
            root = rotateLeft(root);
        }
        if (getHeightDifference(root) == -2) {
            if (getHeightDifference(root.left) > 0) {
                root.left = rotateLeft((root.left));
            }
            root = rotateRight(root);
        }
        return root;
    }

    private Node add(Node root, T element) {
        if (root == null) {
            return new Node(element);
        }
        if (element == root.data) {
            throw new ArrayStoreException();
        }
        if (element.compareTo(root.data) < 0) {
            root.left = add(root.left, element);
        } else {
            root.right = add(root.right, element);
        }
        return balance(root);
    }

    private Node deleteMin( Node root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        return balance(root);
    }

    private Node remove(Node root, T element) {
        if (root == null) {
            throw new NoSuchElementException();
        }
        if (element.compareTo(root.data) < 0) {
            root.left = remove(root.left, element);
        } else if (element.compareTo(root.data) > 0) {
            root.right = remove(root.right, element);
        } else {
            Node left = root.left;
            Node right = root.right;
            if (right == null) {
                return left;
            }
            Node min = findMin(right);
            min.right = deleteMin(right);
            min.left = left;
            return balance(min);
        }
        return balance(root);
    }
}
