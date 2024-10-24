package com.spar3chang3;

public class Queue<T> {
    private final DoubleLinkedList<T> list;

    public Queue() {
        list = new DoubleLinkedList<>();
    }

    public Queue(T data) {
        list = new DoubleLinkedList<>(data);
    }

    public Queue(T... data) {
        list = new DoubleLinkedList<>(data);
    }

    public void enqueue(T data) {
        list.add(data);
    }

    public void enqueue(T... data) { //add from left to right
        list.add(data);
    }

    public T dequeue() {
        return list.removeGet(0);
    }

    public T[] dequeueAll() {
        T[] data = (T[]) new Object[list.size()];
        for (int i=0;i<list.size();i++) {
            data[i] = list.removeGet(0);
        }
        return data;
    }

    public T peek() {
        return list.get(0);
    }

    public void clear() {
        list.removeAll();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
