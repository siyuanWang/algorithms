package com.wsy.learn.algorithms.middle;

public class ExchangeNode {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        while (node1 != null) {
//            System.out.println(node1.val);
//            node1 = node1.next;
//        }

        Node node = swapPairs(node1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    static Node swapPairs(Node head) {
        Node result = new Node(0);
        result.next = head;
        Node pre = result; // pre -> head
        while (pre.next != null && pre.next.next != null) {
            // node1 是当前要反转的第一个结点  pre -> node1 -> node2 -> node3
            Node node1 = pre.next;
            // node2 是当前要反转的第二个结点
            Node node2 = pre.next.next;
            // 第二个结点放到第一个位置  pre -> node2
            pre.next = node2;
            // 第三个结点放在第二个结点后面 node2 -> node3
            node1.next = node2.next;
            // 接第一第二个  pre -> node2 -> node1
            node2.next = node1;
            pre = pre.next.next;
        }
        return result.next;
    }

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
