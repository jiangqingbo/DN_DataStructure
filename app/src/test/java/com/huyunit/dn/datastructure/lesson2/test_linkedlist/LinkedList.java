package com.huyunit.dn.datastructure.lesson2.test_linkedlist;

/**
 * author: bobo
 * create time: 2018/12/11 2:40 PM
 * email: jqbo84@163.com
 */
public class LinkedList<E> {

    //头节点
    private Node<E> first;
    //尾节点
    private Node<E> last;
    //链表大小
    int size;

    public LinkedList() {
    }

    /**
     * 添加数据在最后
     */
    public void add(E e) {
        linkLast(e);
    }

    /**
     * 添加到最后
     *
     * @param e
     */
    private void linkLast(E e) {
        // 首先，构造一个节点数据结构
        // 这里因为是添加在最后的一个数据，那么前一个节点自然就是原来节点的尾节点
        // 数据则为e，尾节点为空
        Node<E> newNode = new Node<E>(last, e, null);
        // 先声明一个属性，保存尾节点的值
        Node<E> l = last;
        // 然后将新添加的节点数据赋给 原尾节点
        last = newNode;
        // 这里需要判断一下原来的尾节点是否为空，也就是原链表中数据是否为空
        if (l == null) { // 如果为空，那么头节点等于新添加的节点
            first = newNode;
        } else { // 否则，原来的尾节点的后一个节点复制为新添加的节点
            l.next = newNode;
        }
        // 链表大小加一
        size++;
    }

    public int size() {
        return size;
    }

    /**
     * 修改指定位置的节点数据
     *
     * @param index
     * @param t
     */
    public void set(int index, E t) {
        if (index < 0 || index > size || size == 0) return;
        // 找到需要修改的下标对应的节点
        Node<E> node = node(index);
        // 直接修改数据
        node.item = t;
    }

    /**
     * 查找指定位置的节点的数据
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            return null;
        }

        return node(index).item;
    }

    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    /**
     * 添加一个节点数据放在指定位置
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == size) {
            linkLast(e);
        } else {
            // 需要拿到跟index位置相关的两个节点
            // 这里拿到原index位置的节点，那么在插入这个新节点后，原位置相当于变成了新节点的后节点
            Node<E> target = node(index);
            // 这里拿到原index位置的节点的前节点
            Node<E> pre = target.prev;
            // 创建一个新节点，这里就可以直接给新节点复制前后的节点
            Node<E> newNode = new Node<E>(pre, e, target);
            // 判断一下原节点的前节点是否为空
            if (pre == null) {  // pre等于Null说明该添加的节点添加在头节点
                // 那么原头节点应该赋值为新节点
                first = newNode;
                // 那么原尾节点的前节点应该赋值为新节点
                target.prev = newNode;
            } else {
                // 那么原前节点的后节点应该赋值为新节点
                pre.next = newNode;
                // 原后节点的前节点应该赋值为新节点
                target.prev = newNode;
            }
            // 链表大小加一
            size++;
        }

    }

    /**
     * 添加数据集
     *
     * @param lists
     */
    public void addAll(LinkedList<E> lists) {
        if (lists == null) return;
        // 直接使用循环单个添加了
//        for (int i = 0; i < lists.size; i++) {
//            add(lists.get(i));
//        }
        addAll(size, lists);
    }

    public void addAll(int index, LinkedList<E> lists) {
        //获取添加的大小
        int numNew = lists.size();
        //定义前后节点
        Node<E> pred, next;
        if (index == size) {//当插入当节点是在原来的节点后面添加
            next = null;
            pred = last;
        } else { //插入的节点是在原来的节点的中间位置添加
            next = node(index);
            pred = next.prev;
        }

        //这里需要遍历插入集合的新节点添加前后节点，否则中间的数据的前后节点为空，造成空指针异常
        for (int i = 0; i < lists.size; i++) {
            E e = lists.get(i);
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null) {
                first = newNode;
            } else {
                pred.next = newNode;
            }
            pred = newNode;
        }

        if (next == null) {//从原来集合的尾节点插入
            last = pred;
        } else {//从原来集合的中间节点插入
            pred.next = next;
            next.prev = pred;
        }

        size += numNew;
    }

    /**
     * 删除最后一个并返回删除对象
     *
     * @return
     */
    public E remove() {
        if (size == 0) return null;
        return remove(node(size - 1));
    }

    /**
     * 删除元素
     *
     * @param index
     */
    public void remove(int index) {
        remove(node(index));
    }

    /**
     * 删除指定数据
     *
     * @param e
     * @return
     */
    public boolean remove(E e) {
        // 从链表头节点开始找，一直到链表结束或者找到为空的数据为止
        for (Node<E> node = first; node != null; node = node.next) {
            // 数据很可能放了个null
            if ((e == null && node.item == e) || (e != null && e.equals(node.item))) {
                remove(node);
                return true;
            }
        }
        return false;
    }

    /**
     * 删除指定数据
     *
     * @param p
     */
    private void unlinkNode(Node<E> p) {//index=2
        Node<E> pre = p.prev;
        Node<E> next = p.next;
        if (pre == null) {
            first = p.next;
        } else {
            pre.next = p.next;
        }
        if (next == null) {
            last = p.prev;
        } else {
            next.prev = p.prev;
        }
        size--;
    }

    /**
     * 删除指定节点
     *
     * @param p
     * @return
     */
    private E remove(Node<E> p) {
        // 取出该节点的前一个节点
        Node<E> pre = p.prev;
        // 取出该节点的后一个节点
        Node<E> next = p.next;
        // 判断前一个节点是否为空
        if (pre == null) { // 为空说明删除的是头节点
            // 那么头节点赋值为删除节点的后一个节点
            first = p.next;
        } else {
            // 删除节点的前一个节点的后节点赋值为删除节点的后一个节点
            pre.next = p.next;
        }
        if (next == null) { // 为空说明删除的是尾节点
            // 那么尾节点赋值为删除节点的前一个节点
            last = p.prev;
        } else {
            // 删除节点的后一个节点的前节点赋值为删除节点的前一个节点
            next.prev = p.prev;
        }
        size--;
        return p.item;
    }


    public static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

}
