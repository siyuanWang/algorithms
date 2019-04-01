package com.wsy.learn.algorithms.middle;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class addTowNumber {
    public static void main(String[] args) {
        ListNode node10 = new ListNode(2);
        ListNode node11 = new ListNode(4);
        ListNode node12 = new ListNode(3);
        node10.next = node11;
        node11.next = node12;

        ListNode node20 = new ListNode(5);
        ListNode node21 = new ListNode(6);
        ListNode node22 = new ListNode(4);
        node20.next = node21;
        node21.next = node22;

        ListNode result = addTwoNumbers(node10, node20);
        ListNode curr = result;
        while (curr != null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
//        ListNode next = null;
//
//        ListNode finalList = new ListNode(0);
//        int add = 0;
//        while((next = node10.next) != null) {
//            finalList.val = node10.val;
//            if(finalList.val >= 10) {
//                finalList.val = finalList.val - 10;
//                add = 1;
//            }
//            finalList.next = new ListNode(next.val + add);
//            node10 = next;
//        }
//
//        while((next = finalList.next) != null) {
//            System.out.print(finalList.val+"->");
//            finalList = next;
//        }
//
//        System.out.print(finalList.val);
    }

//
//    public static void addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode l2next = null;
//        ListNode e2 = l2;
//        ListNode e1 = l1;
//        while((l2next = e2.next) != null) {
//            e1.val = e1.val + e2.val;
//            e2 = l2next;
//            e1 = e1.next;
//        }
//
//        e1.val = e1.val + e2.val;
//    }

//

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode finalList = new ListNode(0);
        ListNode curr = finalList, p = l1, q = l2;
        int carry = 0;
        while (p != null || q != null) {
            int val1 = p == null ? 0 : p.val;
            int val2 = q == null ? 0 : q.val;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if(p != null)  p = p.next;
            if(q != null)  q = q.next;
        }

        if(carry > 0) {
            curr.next = new ListNode(carry);
        }

        return finalList.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
