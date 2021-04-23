package com.huyunit.dn.datastructure.lesson6;

/**
 * author: bobo
 * create time: 2018/12/14 1:26 PM
 * email: jqbo84@163.com
 */
public class OtherBinaryTree<T extends Comparable> {

    Node root;
    int size;

    public class Node<T extends Comparable> {
        T item;
        Node<T> leftChild;
        Node<T> rightChild;
        Node<T> parent;

        public Node(T item) {
            this.item = item;
        }
    }


    public int size() {
        return size;
    }

    public boolean add(T item) {
        Node<T> newNode = new Node<T>(item);
        if (root == null) {
            //如果是空树
            root = newNode;
            size++;
            return true;
        }
        Node node = root;
        Node parent = null;
        while (node != null) {
            parent = node;
            if (node.item.compareTo(item) == 0) {
                //去掉重复的，不进行添加操作
                return false;
            } else if (node.item.compareTo(item) > 0) {
                //如果加入的节点比做节点小
                node = node.leftChild;
            } else {
                //如果加入的节点比做节点大
                node = node.rightChild;
            }
        }
        if (parent.item.compareTo(item) > 0) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        newNode.parent = parent;
        size++;
        return true;
    }


    public Node get(T item) {
        if (root == null) {
            return null;
        }
        Node node = root;
        while (node != null) {
            if (node.item.compareTo(item) < 0) {
                node = node.rightChild;
            } else if (node.item.compareTo(item) > 0) {
                node = node.leftChild;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 采取中序遍历的方式对树进行遍历
     */
    public void midOrderTraseval() {
        midOrderTraseval(root);
    }

    private void midOrderTraseval(Node<T> node) {
        if (node == null) {
            return;
        }
        midOrderTraseval(node.leftChild);
        System.out.print(node.item + " ");
        midOrderTraseval(node.rightChild);
    }


    public Node<T> delete(T item) {
        Node<T> node = get(item);
        if (node != null) {
            delete(node);
        }
        return node;
    }

    private void delete(Node<T> node) {
        if (node == null) {
            return;
        }
        Node<T> left = node.leftChild;
        Node<T> right = node.rightChild;
        Node<T> parent = node.parent;
        //第一种情况 node 为叶子节点
        if (left == null && right == null) {
            if (parent == null) {
                root = null;
            } else {
                if (parent.leftChild == node) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            }
            node.parent = null;
        } else if (left != null && right == null) {
            //第二种情况，只有左节点，没有右节点
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
            node.parent = null;
            node.leftChild = null;
        } else if (left == null && right != null) {
            //第三种情况，只有右节点，没有左节点
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
            node.parent = null;
            node.rightChild = null;
        } else {
            //左右两边都有节点的
            Node lleftNode = getLeftChildNode(right);
            lleftNode.leftChild = left;
            left.parent = lleftNode;
            if (lleftNode.rightChild != null) {
                if (lleftNode.parent != node) {
                    lleftNode.parent.leftChild = lleftNode.rightChild;
                    lleftNode.rightChild.parent = lleftNode.parent;
                }
            }
            if (lleftNode != right) {
                lleftNode.rightChild = right;
            }
            right.parent = lleftNode;
            if (parent == null) {
                lleftNode.parent = null;
                root = lleftNode;
            } else {
                if (parent.leftChild == node) {
                    parent.leftChild = lleftNode;
                } else {
                    parent.rightChild = lleftNode;
                }
                lleftNode.parent = parent;
            }

            node.leftChild = null;
            node.rightChild = null;
            node.parent = null;
        }
        size--;
    }


    private Node<T> getLeftChildNode(Node<T> node) {
        Node<T> leftChild = node;
        Node<T> newNode = node;
        while (newNode != null) {
            leftChild = newNode;
            newNode = newNode.leftChild;
        }
        return leftChild;
    }

}
