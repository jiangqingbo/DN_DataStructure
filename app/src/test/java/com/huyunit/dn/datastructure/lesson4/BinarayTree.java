package com.huyunit.dn.datastructure.lesson4;

/**
 * Created by Jett on 2018/12/3.
 */

public class BinarayTree {

    Node<String> root;

    public BinarayTree(String data) {
        root = new Node<>(data, null, null);
    }

    public void createTree() {
        Node<String> nodeB = new Node<String>("B", null, null);
        Node<String> nodeC = new Node<String>("C", null, null);
        Node<String> nodeD = new Node<String>("D", null, null);
        Node<String> nodeE = new Node<String>("E", null, null);
        Node<String> nodeF = new Node<String>("F", null, null);
        Node<String> nodeG = new Node<String>("G", null, null);
        Node<String> nodeH = new Node<String>("H", null, null);
        Node<String> nodeJ = new Node<String>("J", null, null);
        Node<String> nodeI = new Node<String>("I", null, null);
        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeC.leftChild = nodeE;
        nodeC.rightChild = nodeF;
        nodeD.leftChild = nodeG;
        nodeD.rightChild = nodeH;
        nodeE.rightChild = nodeJ;
        nodeH.leftChild = nodeI;

    }

    /**
     * 中序访问树的所有节点
     */
    public void midOrderTraverse(Node root) {//逻辑
        if (root == null) {
            return;
        }
        midOrderTraverse(root.leftChild);//逻辑
        System.out.println("mid:" + root.data);//输出
        midOrderTraverse(root.rightChild);//逻辑
    }

    /**
     * 前序访问树的所有节点  Arrays.sort();
     */
    public void preOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        System.out.println("pre:" + root.data);
        preOrderTraverse(root.leftChild);
        preOrderTraverse(root.rightChild);
    }

    /**
     * 后序访问树的所有节点
     */
    public void postOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.leftChild);
        postOrderTraverse(root.rightChild);
        System.out.println("post:" + root.data);
    }

    /**
     * 节点
     */
    public class Node<T> {
        T data;
        Node<T> leftChild;
        Node<T> rightChild;

        public Node(T data, Node<T> leftChild, Node<T> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

}











