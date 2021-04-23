package com.huyunit.dn.datastructure.lesson6;

/**
 * 手写二叉排序树
 * author: bobo
 * create time: 2018/12/14 11:09 AM
 * email: jqbo84@163.com
 */
public class BinarySortTree<T extends Comparable> {

    //二叉树跟节点
    private Node<T> root;
    //二叉树大小
    private int size;

    /**
     * 新增一个节点
     *
     * @param data
     * @return
     */
    public Node put(T data) {
        Node<T> newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return newNode;
        }
        Node<T> parent = null;
        Node<T> node = root;
        while (node != null) {
            parent = node;
            if (node.data.compareTo(data) > 0) {//小于跟节点就往左查询
                node = node.leftChild;
            } else if (node.data.compareTo(data) < 0) {//大于跟节点就往右查询
                node = node.rightChild;
            } else {//是重复值 就不理会了
                return node;
            }
        }
        if (parent.data.compareTo(data) > 0) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        newNode.parent = parent;

        size++;

        return newNode;
    }

    public int getSize() {
        return size;
    }

    public Node get(T data) {
        if (root == null) {
            return null;
        }
        Node<T> node = root;
        while (node != null) {
            if (node.data.compareTo(data) > 0) {
                node = node.leftChild;
            } else if (node.data.compareTo(data) < 0) {
                node = node.rightChild;
            } else {
                return node;
            }
        }
        return null;
    }

    public void middleOrderTraseval() {
        middleOrderTraseval(root);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void middleOrderTraseval(Node<T> node) {
        if (node == null) {
            return;
        }
        middleOrderTraseval(node.leftChild);
        System.out.print(node.data + "    ");
        middleOrderTraseval(node.rightChild);
    }

    public void deleteNode(Node<T> node) {
        if (node == null) {
            return;
        }
        Node<T> left = node.leftChild;
        Node<T> right = node.rightChild;
        Node<T> parent = node.parent;
        //第一种情况：node是叶子节点
        if (left == null && right == null) {
            if (parent == null) {
                root = null;
            } else {
                if (parent.leftChild == node) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
                node.parent = null;
            }
        }
        //第二种情况：node只有左节点，没有右节点的情况
        else if (left != null && right == null) {
            if (parent == null) {
                root = left;
            } else {
                if (parent.leftChild == node) {
                    parent.leftChild = left;
                } else {
                    parent.rightChild = left;
                }
            }
            left.parent = parent;
            node.leftChild = null;
            node.parent = null;
        }
        //第三种情况：node只有右节点，没有左节点的情况
        else if (left == null && right != null) {
            if (parent == null) {
                root = right;
            } else {
                if (parent.leftChild == node) {
                    parent.leftChild = right;
                } else {
                    parent.rightChild = right;
                }
            }
            right.parent = parent;
            node.rightChild = null;
            node.parent = null;
        }
        //第四种情况：node左右节点都有的情况
        else if (left != null && right != null) {
            Node<T> leftMinNode = getLeftMinNode(right);
            // 1. 把右节点最小的节点接上node的左节点
            leftMinNode.leftChild = left;
            left.parent = leftMinNode;
            // 2. 如果右节点最小的节点，如果有右节点，则把右节点接上父节点
            Node<T> leftMinNodeParent = leftMinNode.parent;
            if (leftMinNode.rightChild != null) {
                if (leftMinNodeParent != node) {
                    leftMinNodeParent.leftChild = leftMinNode.rightChild;
                    leftMinNode.rightChild.parent = leftMinNodeParent;
                }
            } else {
                //没有右节点，则要把最小节点的父节点的左节点赋空
                leftMinNodeParent.leftChild = null;
            }
            // 3. 把右节点最小节点接上node的右节点上
            if (leftMinNode != right) {
                leftMinNode.rightChild = right;
            }
            right.parent = leftMinNode;
            // 4. 接上node的父节点
            if (parent == null) {
                root = leftMinNode;
            } else {
                if (parent.leftChild == node) {
                    parent.leftChild = leftMinNode;
                } else {
                    parent.rightChild = leftMinNode;
                }
            }
            leftMinNode.parent = parent;
            node.leftChild = null;
            node.rightChild = null;
            node.parent = null;
        }
        size--;
    }

    /**
     * 获取当前节点的左边最小的值，也就是最左边的节点
     *
     * @param node
     * @return
     */
    public Node<T> getLeftMinNode(Node<T> node) {
        if (node == null) {
            return null;
        }
        Node currentRoot = node;
        while (currentRoot.leftChild != null) {
            currentRoot = currentRoot.leftChild;
        }
        return currentRoot;
    }

    /**
     * 使用孩子双亲法表示法来定义树（实际双向链表）
     * @param <T> 对象
     */
    public class Node<T extends Comparable> {
        T data;
        Node<T> leftChild;
        Node<T> rightChild;
        Node<T> parent;

        public Node(T data) {
            this.data = data;
            leftChild = null;
            rightChild = null;
            parent = null;
        }
    }

}
