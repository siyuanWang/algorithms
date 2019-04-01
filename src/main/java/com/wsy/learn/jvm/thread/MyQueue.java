package com.wsy.learn.jvm.thread;

public class MyQueue<T> {
    /**
     * 队列头
     */
    private Node<T> first;
    /**
     * 队列尾
     */
    private Node<T> last;
    /**
     * 队列长度
     */
    private long size;


    private static class Node<T> {
        T item;
        Node<T> next;
        Node(T item) {
            this.item = item;
        }
    }

    public boolean offer(T o) {

        addLast(o);
        return true;
    }

    private void addLast(T o) {
        Node<T> element = new Node<>(o);
        Node<T> l = last;
        last = element;
        if(l == null) {
            first = element;
        } else {
            l.next = element;
        }
        size++;
    }

    public T poll() {
        if(size == 0) {
            return null;
        }

        return unLinkFirst();
    }

    private T unLinkFirst() {
        if(first == null) {
            return null;
        }
        T element = first.item;
        first.item = null;//help gc
        first = first.next;
        size--;
        return element;
    }

    public T peek() {
        if(size == 0) {
            return null;
        }
        return first.item;
    }
}
