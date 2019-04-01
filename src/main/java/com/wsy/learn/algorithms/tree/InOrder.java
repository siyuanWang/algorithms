package com.wsy.learn.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 中序遍历
 */
public class InOrder {
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.add(5);
        root.add(4);
        root.add(8);
        root.add(9);
        root.add(10);
        root.add(11);
        root.add(6);

        inOrder(root);

        for(int v : list) {
            System.out.print(v + ",");
        }
    }

    /**
     * 中序遍历
     * 默认就是排好序的从小到大
     * @param t
     */
    static void inOrder(TreeNode t) {
        if(t == null)
            return;
        inOrder(t.getLeft());
        list.add(t.getValue());
        inOrder(t.getRight());
    }

    /**
     * 前序遍历
     * 没有进行排序，输出原始的顺序
     * @param t
     */
    static void preOrder(TreeNode t) {
        if(t == null)
            return;
        list.add(t.getValue());
        preOrder(t.getLeft());
        preOrder(t.getRight());
    }

    /**
     * 后续遍历
     * @param t
     */
    static void afterOrder(TreeNode t) {
        if(t == null)
            return;
        afterOrder(t.getLeft());
        afterOrder(t.getRight());
        list.add(t.getValue());
    }

    static class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private int value;

        /**
         * 插入一个节点
         * @param value
         */
        public void add(int value) {
            if(value < this.value) {
                if(this.left != null) {
                    this.left.add(value);
                } else {
                    this.left = new TreeNode(value);
                }
            } else {
                if(this.right != null) {
                    this.right.add(value);
                } else {
                    this.right = new TreeNode(value);
                }
            }
        }

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
