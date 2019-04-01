package com.wsy.learn.algorithms.simple;

import java.util.Stack;

public class RevertInt {
//    public static void main(String[] args) {
//        String num = "123";
//        char[] chars = num.toCharArray();
//        Node sol = new Node();
//        Node head = sol;
//        for(char s : chars) {
//            sol.next = new Node();
//            sol.next.val = s;
//            sol = sol.next;
//        }
//        Node curr = head;
//        int index = 1;
//        while(curr != null) {
//            if(curr.val != null) {
//                chars[chars.length - index++] = curr.val;
//            }
//            curr = curr.next;
//        }
//
//        System.out.println(new String(chars));
//    }

    static class Node {
        Character val;
        Node next;
    }

    static void withStatck(int num) {
        String str = num + "";
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        for(char s : chars) {
            if(s != '-') {
                stack.push(s);
            } else {
                i++;
            }
        }

        while(!stack.empty()) {
            chars[i++] = stack.pop();
        }
        System.out.println(new String(chars));
    }

    public static void main(String[] args) {
        withStatck(-320);
    }
}
