package com.wsy.learn.algorithms.tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 求二叉树任意节点的距离
 * lca是两个节点最小公共祖先
 * Dist(a,b) = Dist(root,a) + Dist(root,b) - 2*Dist(root,lca)
 */
public class BinaryTreeNodeLength {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        void add(int val) {
            if (this.val < val) {
                if (this.right != null) {
                    this.right.add(val);
                } else {
                    this.right = new TreeNode(val);
                }
            } else {
                if (this.left != null) {
                    this.left.add(val);
                } else {
                    this.left = new TreeNode(val);
                }
            }
        }

        @Override
        public String toString() {
            return "node.val=" + val;
        }
    }

    /**
     * 返回最近公共祖先
     *
     * @param root 根节点
     * @param a
     * @param b
     * @return
     */
    static TreeNode lca(TreeNode root, TreeNode a, TreeNode b) {
        if (hasNode(root.left, a) && hasNode(root.left, b)) {
            return lca(root.left, a, b);
        }
        if (hasNode(root.right, a) && hasNode(root.right, b)) {
            return lca(root.right, a, b);
        }
        return root;
    }

    static List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while(count > 0){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
                count--;
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 判断root为根的树，是否包括节点p
     *
     * @param root
     * @param p
     * @return
     */
    static boolean hasNode(TreeNode root, TreeNode p) {
        if (root == null) return false;
        if (root == p)
            return true;
        boolean exist = hasNode(root.left, p);
        if (!exist) {
            exist = hasNode(root.right, p);
        }
        return exist;
    }

    /**
     * 返回节点的层数
     * 层数也就是节点到root的距离
     *
     * @param a
     * @return
     */
    static int treeNodeLevel(TreeNode root, TreeNode a, int level) {
        if (root == null) return -1;
        if (a == root)
            return level;
        int l = treeNodeLevel(root.left, a, level + 1);
        if (l == -1) {
            l = treeNodeLevel(root.right, a, level + 1);
        }
        return l;
    }

    static int distTwoNode(TreeNode root, TreeNode a, TreeNode b) {
        TreeNode nearestParent = lca(root, a, b);
        if(nearestParent == null) return 0;
        int distA = treeNodeLevel(root, a, 0);
        int distB = treeNodeLevel(root, b, 0);
        int distNear = treeNodeLevel(root, nearestParent, 0);
        return distA + distB - 2 * distNear;
    }

    /**
     * 二叉树中序遍历
     *
     * @param node
     * @return
     */
    static void inOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }

    /**
     * 根据值查询出对应的树节点
     *
     * @param root
     * @param val
     * @return
     */
    static TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) {
            return root;
        }
        TreeNode node = findNode(root.left, val);
        if (node == null) {
            node = findNode(root.right, val);
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.add(4);
        root.add(5);
        root.add(6);
        root.add(8);
        root.add(9);
        root.add(10);

        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        list.forEach(x -> System.out.print(x + ","));

//        System.out.println(findNode(root, 9).toString());
        System.out.println(hasNode(root, findNode(root, 5)));

        System.out.println("lca =" + lca(root, findNode(root, 7), findNode(root, 5)));

        System.out.println("root level = " + treeNodeLevel(root, findNode(root, 9), 0));

        System.out.println("root dist = " + distTwoNode(root, findNode(root, 6), findNode(root, 10)));

        System.out.println(JSON.toJSONString(levelOrder(root)));
    }
}
