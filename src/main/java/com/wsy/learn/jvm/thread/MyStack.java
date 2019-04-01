package com.wsy.learn.jvm.thread;

import com.alibaba.fastjson.JSON;

public class MyStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(JSON.toJSONString(stack.getOriginArray()));
        System.out.println(stack.pop());
        System.out.println(JSON.toJSONString(stack.getOriginArray()));
        System.out.println(stack.pop());
        System.out.println(JSON.toJSONString(stack.getOriginArray()));
        System.out.println(stack.pop());
        System.out.println(JSON.toJSONString(stack.getOriginArray()));
    }
}
