package com.huyunit.dn.datastructure.lesson8;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * author: bobo
 * create time: 2018/12/14 9:34 PM
 * email: jqbo84@163.com
 */
public class HuffmanTree<T> {

    TreeNode<T> root;

    /**
     * 构建哈夫曼树
     *
     * @param list
     */
    public void createHuffmanTree(ArrayList<TreeNode<T>> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        while (list.size() > 1) {
            Collections.sort(list);
            TreeNode left = list.get(list.size() - 1);
            TreeNode right = list.get(list.size() - 2);
            TreeNode parent = new TreeNode("P", left.weight + right.weight);
            parent.leftChild = left;
            left.parent = parent;
            parent.rightChild = right;
            right.parent = parent;
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        root = list.get(0);
    }

    /**
     * 一层一层取出数据，通过队列入队的方式存储形式
     *
     * @param root
     */
    public void showHuffman(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        //入队
        list.offer(root);
        while (!list.isEmpty()) {
            //出对
            TreeNode node = list.pop();
            if (!node.data.equals("P")) { //如果是创建的结点P，就不打印了
                System.out.println(node.weight + " = " + node.data);
            }
            //左子树不为空，则入队
            if (node.leftChild != null) {
                list.offer(node.leftChild);
            }
            //右子树不为空，则入队
            if (node.rightChild != null) {
                list.offer(node.rightChild);
            }
        }
    }

    /**
     * 获取哈夫曼编码
     *
     * @param node
     */
    public String getHuffmanCode(TreeNode node) {
        TreeNode tNode = node;
        //定义一个栈来排序用
        Stack<String> stack = new Stack<>();
        while (tNode != null && tNode.parent != null) {
            //left 0  right 1
            if (tNode.parent.leftChild == tNode) {
                stack.push("0");
            } else if (tNode.parent.rightChild == tNode) {
                stack.push("1");
            }
            tNode = tNode.parent;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 结点，和二叉树一样
     *
     * @param <T>
     */
    public static class TreeNode<T> implements Comparable<TreeNode<T>> {
        T data;
        TreeNode<T> leftChild;
        TreeNode<T> rightChild;
        TreeNode<T> parent;
        //权重
        int weight;

        public TreeNode(T data, int weight) {
            this.data = data;
            this.weight = weight;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
        }

        @Override
        public int compareTo(@NonNull TreeNode<T> o) {
            if (this.weight > o.weight) {
                return -1;
            } else if (this.weight < o.weight) {
                return 1;
            }
            return 0;
        }
    }

}
