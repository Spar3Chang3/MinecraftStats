package com.spar3chang3;

public class DoubleLinkedList<T> {

    private Node<T> root;
    private Node<T> tail;
    private int index = 0;

    public DoubleLinkedList(T data) {
        root = new Node<>(data);
        tail = root;
    }

    @SafeVarargs
    public DoubleLinkedList(T... data) {
        Class<?> type = data[0].getClass();
        root = null;
        tail = null;
        for (T dataItem : data) {
            if (dataItem.getClass().equals(type)) {
                add(dataItem);
            } else {
                throw new IllegalArgumentException(String.format("Data %s is not of initial type %s", dataItem.getClass().getName(), type.getName()));
            }
        }
    }

    public DoubleLinkedList() {
        root = null;
        tail = null;
    }

    public T get(int index) {
        if (index > this.index) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds for size %d", index, this.index));
        }

        Node<T> trav = root;

        for (int i = 0; i < index; i++) {
            trav = trav.getNext();
        }

        return trav.getValue();
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (root == null) {
            root = newNode;
            tail = root;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            index++;
        }
    }

   // ! @SafeVarargs
    public void add(T... data) {
        for (T t : data) {
            add(t);
        }
    }

    public void remove(int index) {
        if (index > this.index) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds for size %d", index, this.index));
        }

        Node<T> trav = root;


        for (int i = 0; i < index; i++) {
            trav = trav.getNext();
        }

        trav.getPrev().setNext(trav.getNext());
        this.index--; //I am amazing at naming things
    }

    public void remove(T data) { //removes the first instance of a given value

        Node<T> trav = root;

        while (! (trav.getValue().equals(data))) {
            if (trav.getNext() == null) {
                return;
            }
            trav = trav.getNext();
        }


        trav.getPrev().setNext(trav.getNext());
        this.index--;
    }

    public T removeGet(int index) {
        if (index > this.index) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds for size %d", index, this.index));
        }

        if (index == 0) {
            T data = root.getValue();
            root = root.getNext();
            this.index--;
            return data;
        } else if (index == this.index) {
            T data = tail.getValue();
            tail = tail.getPrev();
            tail.setNext(null);
            this.index--;
            return data;
        }

        Node<T> trav = root;

        assert trav.getNext() != null;
        for (int i = 0; i < index; i++) {
            trav = trav.getNext();
        }

        trav.getPrev().setNext(trav.getNext());
        this.index--;
        return trav.getValue();
    }

    public int removeLocate(T data) {
        int index = 0;
        Node<T> trav = root;

        while (!(trav.getValue().equals(data))) {
            if (trav.getNext() == null) {
                return -1;
            }
            trav = trav.getNext();
            index++;
        }

        trav.getPrev().setNext(trav.getNext());
        this.index--;
        return index;
    }

    public void removeAll() {
        root = null;
        tail = null;
        index = 0;
    }

    public int getIndex(T data) {
        int index = 0;
        Node<T> trav = root;

        while (!(trav.getValue().equals(data))) {
            if (trav.getNext() == null) {
                return -1;
            }
            trav = trav.getNext();
            index++;
        }

        return index;
    }

    public int size() {
        return index + 1;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> trav = root;
        while (trav != null) {
            sb.append(trav.getValue());
            if (trav.getNext() != null) {
                sb.append(", ");
            }
            trav = trav.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}