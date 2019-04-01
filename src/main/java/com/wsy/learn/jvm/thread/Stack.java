package com.wsy.learn.jvm.thread;

public class Stack<T> {
    private Object[] array = new Object[10];
    private int size;

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T t) {
            this.value = t;
        }
    }

    public void push(T t) {
        array[size++] = t;
    }

    public T pop() {
        Object value = getElementAt(size - 1);
        return (T) value;
    }

    public Object getElementAt(int index) {
        if(index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object ele = array[index];
        int j = size - index - 1;
        if(j > 0) {
            System.arraycopy(array, index + 1, array, index, j);
        }
        size--;
        array[size] = null;
        return ele;
    }

    public Object[] getOriginArray() {
        return array;
    }
}
