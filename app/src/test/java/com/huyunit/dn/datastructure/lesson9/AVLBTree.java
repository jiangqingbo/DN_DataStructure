package com.huyunit.dn.datastructure.lesson9;

import android.support.annotation.NonNull;

import com.huyunit.dn.datastructure.lesson2.test_linkedlist.LinkedList;

/**
 * 平衡二叉树
 *
 * author: bobo
 * create time: 2018/12/18 11:55 AM
 * email: jqbo84@163.com
 */
public class AVLBTree<E> {

    private static final int LH = 1;
    private static final int RH = -1;
    private static final int EH = 0;

    private Node<E> root;

    private int size;

    public Node<E> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    /**
     * 左旋转
     * @param x
     */
    public void leftRotate(Node<E> x){
        if(x == null){
            return;
        }
        // 1. 先取到 y 结点
        Node<E> y = x.rightChild;
        // 2. 把𝞫作为X的右孩子
        x.rightChild = y.leftChild;
        // 3. 判断y的左结点是否为null
        if(y.leftChild != null){
            y.leftChild.parent = x;
        }
        // 4. 把x的父结点赋给y的父结点
        y.parent = x.parent;
        // 5. 判断x的结点是否为root结点
        if(x.parent == null){
            root = y;
        } else {
            if(x.parent.leftChild == x){
                x.parent.leftChild = y;
            } else {
                x.parent.rightChild = y;
            }
        }
        // 6. 把x结点作为y的左结点
        y.leftChild = x;
        x.parent = y;
    }

    /**
     * 右旋转
     * @param x
     */
    public void rightRotate(Node<E> x){
        if(x == null){
            return;
        }
        Node<E> y = x.leftChild;
        x.leftChild = y.rightChild;
        if(y.rightChild != null){
            y.rightChild.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            root = y;
        } else {
            if(x.parent.leftChild == x){
                x.parent.leftChild = y;
            } else {
                x.parent.rightChild = y;
            }
        }
        y.rightChild = x;
        x.parent = y;
    }

    /**
     * 左平衡操作，即结点t的不平衡是因为左子树过深
     *
     * 1、如果新的结点插入到t的左孩子的左子树中，则直接进行右旋操作即可
     * 				t									    tl
     * 			   /  \			  右旋操作			    /        \
     * 			  tl   tr		------------->		  tll          t
     * 			 /  \								 /	\        /   \
     * 			tll  tlr						  lcll  lclr   tlr   tr
     * 		   /   \
     *      lcll   lclr
     *
     * 2、如果新的结点插入到t的左孩子的右子树中，则需要进行分情况讨论
     *
     * 	情况a：当t的左孩子的右子树根节点的balance = RIGHT_HIGH  -1
     * 			t						t						 	tlr
     * 		   /  \					   /  \						   /  \
     *        tl   6      左旋       tlr   6        右旋			 tl    t
     *       /  \  		------->     /  \        -------->       /    /  \
     *      3  tlr                 tl    5                      3    5    6
     *            \                /
     *             5			  3
     * 	情况b：当t的左孩子的右子树根节点的balance = LEFT_HIGH  1
     *
     * 			t						t						   tlr
     * 		   /  \					   /  \						   /  \
     *        tl    6     左旋     	tlr    6        右旋			  tl    t
     *        /  \  	------->     /           -------->       / \    \
     *       3  tlr                tl                           3   5    6
     *          /                 / \
     *         5   			  	 3   5
     *
     * 	情况c：当t的左孩子的右子树根节点的balance = EQUAL_HIGH  0
     *
     * 			t						t						    tlr
     * 		   /  \					   /  \						   /   \
     *        tl   7     左旋     	tlr    7        右旋		      tl    t
     *        /  \  	------->     / \         -------->       / \   / \
     *       3   tlr                tl   6                      3  5  6   7
     *           / \               / \
     *          5   6			  3	  5
     */
    public void leftBalance(Node<E> t){
        if(t == null){
            return;
        }
        Node<E> tl = t.leftChild;
        switch (tl.balance){
            case LH://1.新的结点插入到node的左结点的左子树中
                rightRotate(t);
                t.balance = EH;
                tl.balance = EH;
                break;
            case RH://2.新的结点插入到node的左结点的右子树中
                Node<E> tlr = tl.rightChild;
                switch (tlr.balance){
                    case LH:
                        t.balance = RH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    case RH:
                        t.balance = EH;
                        tl.balance = LH;
                        tlr.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    default:
                        break;
                }
                leftRotate(tl);
                rightRotate(t);
                break;
        }
    }

    /**
     * 右平衡操作，即结点t的不平衡是因为右子树过深
     *
     * 1、如果新的结点插入到t的右孩子的右子树中，则直接进行左旋操作即可
     *
     * 		 	t											  tr
     * 		  /   \										   /     \
     * 		 l	   tr			   左旋操作 				  t       rr
     * 			 /   \			----------->             / \     /   \
     * 			rl    rr								l  rl  rrl    rrr
     * 				/   \
     * 			 rrl 	rrr
     *
     * 2、如果新的结点插入到t的右孩子的左子树中，则需要进行分情况讨论
     * 	情况a：当t的右孩子的左子树根节点的balance = LEFT_HIGH     1
     *
     *			t						t							trl
     *		   /  \					   /  \						   /   \
     *		  2   tr		tr右旋	  2	 trl		t左旋		  t    tr
     *			  /  \	   ------->		 /  \		------->	/  \     \
     * 		    trl	  5					6   tr				   2    6  	  5
     * 			/							 \
     * 		   6							  5
     * 情况b：当t的右孩子的左子树根节点的balance = RIGHT_HIGH    -1
     *
     *			t						t							trl
     *		   /  \					   /  \						   /   \
     *		  2   tr		tr右旋	  2	 trl	     t左旋		  t    tr
     *			  /  \	   ------->		   \		------->	 /     /  \
     * 			 trl  5					    tr				    2	  6	  5
     * 			  \						   /  \
     * 		   	   6					  6    5
     *
     * 情况C：当t的右孩子的左子树根节点的balance = EQUAL_HIGH    0
     *			t						t						    trl
     *		   /  \					   /  \						   /   \
     *		  2   tr		 右旋	  2	 trl 		 左旋		  t    tr
     *			  /  \	   ------->		  /  \		------->	 / \   / \
     * 		   trl	  5					 6   tr					2	6  7   5
     * 			/ \							 /  \
     * 		   6   7					    7    5
     *
     */
    public void rightBalance(Node<E> t){
        if(t == null){
            return;
        }
        Node<E> tr = t.rightChild;
        switch (tr.balance){
            case RH: //1.新的结点插入到node的右结点的右子树中
                leftRotate(t);
                t.balance = EH;
                tr.balance = EH;
                break;
            case LH: //2.新的结点插入到node的右结点的左子树中
                Node<E> trl = tr.leftChild;
                switch (trl.balance){
                    case RH:
                        t.balance = LH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    case LH:
                        t.balance = EH;
                        tr.balance = RH;
                        trl.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    default:
                        break;
                }
                rightRotate(tr);
                leftRotate(t);
                break;
        }
    }

    /**
     * 往平衡二叉树插入一个结点
     * @param element
     */
    public boolean insertElement(E element){
        Node<E> t = root;
        if(t == null){
            root = new Node<>(element, null);
            root.balance = 0;
            size = 1;
            return true;
        } else {
            int cmp = 0;
            Node<E> parent = null;
            Comparable<E> e = (Comparable<E>) element;
            //1. 查找可以插入的位置
            do {
                parent = t;
                cmp = e.compareTo(t.element);
                if(cmp < 0){
                    t = t.leftChild;
                } else if (cmp > 0){
                    t = t.rightChild;
                } else {
                    return false;
                }
            } while (t != null);
            //2. 查找结束，现在插入
            Node<E> child = new Node<>(element, parent);
            if(cmp < 0){
                parent.leftChild = child;
            } else {
                parent.rightChild = child;
            }
            //3. 插入结束，要开始检查平衡因子，回溯往回找，平衡因子绝对值是否等于2
            while (parent != null){
                cmp = e.compareTo(parent.element);
                if(cmp < 0){
                    parent.balance++;
                } else if(cmp > 0){
                    parent.balance--;
                }
                if (parent.balance == 0){//如果插入后还是平衡树，不用调整
                    break;
                }
                if(parent.balance == 2 || parent.balance == -2){
                    //出现了平衡的问题，需要修正
                    fixAfterInsertion(parent);
                    break;
                } else {
                    parent = parent.parent;
                }
            }
            size++;
        }

        return true;
    }

    /**
     * 修正插入结点后的平衡二叉树
     * @param parent
     */
    private void fixAfterInsertion(Node<E> parent) {
        if(parent.balance == 2){
            leftBalance(parent);
        }
        if(parent.balance == -2){
            rightBalance(parent);
        }
    }

    /**
     * 一层一层打印出数据
     * @param node
     */
    public void showAVLBTree(Node<E> node){
        if(node == null){
            return;
        }
        LinkedList<Node<E>> list = new LinkedList<>();
        list.add(node);
        while(list.size() > 0){
            Node<E> popNode = list.remove();
            System.out.println("popNode.element = " + popNode.element);
            if(popNode.leftChild != null){
                list.add(popNode.leftChild);
            }
            if(popNode.rightChild != null){
                list.add(popNode.rightChild);
            }
        }
    }

    /**
     * 一层一层打印出数据
     * @param root)
     */
    public void showAVL(Node<E> root){
        if(root == null){
            return;
        }
        java.util.LinkedList<Node<E>> list = new java.util.LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()){
            Node<E> node = list.pop();
            System.out.println("node.element = " + node.element);
            if(node.leftChild != null){
                list.offer(node.leftChild);
            }
            if(node.rightChild != null){
                list.offer(node.rightChild);
            }
        }
    }

    public class Node<E> implements Comparable<Node<E>> {
        E element;
        Node<E> leftChild;
        Node<E> rightChild;
        Node<E> parent;

        int balance = 0;//平衡因子

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        @Override
        public int compareTo(@NonNull Node<E> o) {
            if(this.balance > o.balance){
                return -1;
            } else if(this.balance < o.balance){
                return 1;
            }
            return 0;
        }
    }
}
