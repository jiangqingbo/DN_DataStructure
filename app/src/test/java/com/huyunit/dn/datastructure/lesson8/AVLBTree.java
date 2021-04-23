package com.huyunit.dn.datastructure.lesson8;

/**
 * Created by Jett on 2018/12/14.
 */
public class AVLBTree<E extends Comparable<E>> {

    Node<E> root;
    int size;

    public class Node<E extends Comparable<E>> {
        E element;
        int balance = 0;//平衡因子
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    /**
     * 左旋转
     *
     * @param x
     */
    public void left_rotate(Node<E> x) {
        if (x == null) {
            return;
        }
        //先取到Y
        Node<E> y = x.right;
        //1.把𝞫作为X的右孩子
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        //2。把Y移到原来X的位置上
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else if (x.parent.right == x) {
                x.parent.right = y;
            }
        }
        //3.X作为Y的左孩子
        y.left = x;
        x.parent = y;
    }

    /**
     * 右旋转，就是和左旋转左相反的操作
     * @param x
     */
    public void right_rotate(Node<E> x) {
        if (x == null) {
            return;
        }
        //先取到Y
        Node<E> y = x.left;
        // 1. 把𝞫接上x的左结点上
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        // 2. 把y移到原来x位置上
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        // 3. 把x作为y的右结点
        y.right = x;
        x.parent = y;
    }

}
