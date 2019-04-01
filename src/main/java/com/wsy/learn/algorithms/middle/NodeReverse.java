package com.wsy.learn.algorithms.middle;

public class NodeReverse {
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    static Node reverse(Node head) {
        Node pre = head;
        Node cur = head.next;
        Node tmp = head.next.next;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        head.next = null;
        return pre;
    }

    /**
     * 删除链表的倒数第 n 个节点，并且返回链表的头结点
     *
     * @param head
     * @param n
     * @return
     */
    static Node removeNthFromEnd(Node head, int n) {
        int size = 0;
        Node f = head;
        while (f != null) {
            f = f.next;
            size++;
        }
        int front = size - n;
        size = 1;
        f = head;
        while (f != null) {
            if (size == front) {
                f.next = f.next.next;
                break;
            } else {
                size++;
                f = f.next;
            }
        }
        return head;
    }

    /**
     * 给定一个排序链表，删除重复元素，使得元素只保留一个
     * 输入：1->1->2->3
     * 输出：1->2->3
     *
     * @param head
     * @return
     */
    static Node removeRepeat(Node head) {
        Node mark = head;
        while(mark != null) {
            Node next = mark.next;
            if(next != null && next.val == mark.val) {
                mark.next = next.next;
            } else {
                mark = mark.next;
            }
        }

        return head;
    }

    /**
     * 给定一个排序链表，删除重复元素，使得元素不保留重复的
     * 输入：1->1->2->3
     * 输出：2->3
     *
     * @param head
     * @return
     */
    static Node removeRepeat1(Node head) {
        Node fummy = new Node(0);
        Node pre = fummy;
        Node cur = head;
        Node next = cur.next;
        boolean movePre = true;
        while(cur != null) {
            if(next != null && cur.val == next.val) {
                pre.next = next.next;
                next = next.next;
                movePre = false;
            } else {
                if(movePre) {
                    pre = pre.next;
                } else {
                    movePre = true;
                }
                cur = next;
                if(next != null) {
                    next = next.next;
                }
            }
        }

        return fummy.next;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

//        Node newList = reverse(node1);
//        while(newList != null) {
//            System.out.print(newList.val + "->");
//            newList = newList.next;
//        }

//        System.out.println("---remove---");
//        Node head = removeNthFromEnd(node1, 2);
//        while (head != null) {
//            System.out.print(head.val + "->");
//            head = head.next;
//        }

        Node head = removeRepeat1(node1);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}
