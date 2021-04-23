package com.huyunit.dn.datastructure.lesson2.test_linkedlist;

/**
 * 这是自定义一个双向链表
 */
public class MyLinkedList<T> {

    // 头节点
    Node<T> first;
    // 尾节点
    Node<T> last;
    // 链表大小
    int size;

    public MyLinkedList() {

    }

    // 添加一个节点数据放在最后
    public void add(T t) {
        // 首先，构造一个节点数据结构
        // 这里因为是添加在最后的一个数据，那么前一个节点自然就是原来节点的尾节点
        // 数据则为t，尾节点为空
        Node node = new Node(last, t, null);
        // 先声明一个属性，保存尾节点的值
        Node<T> save = last;
        // 然后将原尾节点属性赋值为新添加的数据节点
        last = node;
        // 这里需要判断一下原来的尾节点是否为空，也即原链表中数据是否为空
        if (save == null) { // 如果为空，那么头节点也等于新添加的节点
            first = node;
        } else { // 否则，原来的尾节点的后一个节点复制为新添加的节点
            save.after = node;
        }
        // 链表大小加一
        size++;
    }

    // 添加一个节点数据放在指定位置
    public void add(int index, T t) {
        if (index < 0 || index > size) return;
        if (index==size) {
            add(t);
        } else {
            // 需要拿到跟index位置相关的两个节点
            // 这里拿到原index位置的节点，那么在插入这个新节点后，原位置相当于变成了新节点的后节点
            Node<T> after = getIndexNode(index);
            // 这里拿到原index位置的节点的前节点
            Node<T> before = after.before;
            // 创建一个新节点，这里就可以直接给新节点复制前后的节点
            Node<T> insert = new Node<T>(before, t, after);
            // 判断一下原节点的前节点是否为空
            if (before==null) { // before等于Null说明该添加的节点添加在头节点
                // 那么原头节点应该赋值为新节点
                first = insert;
                // 那么原尾节点的前节点应该赋值为新节点
                after.before = insert;
            }else {
                // 那么原前节点的后节点应该赋值为新节点
                before.after = insert;
                // 原后节点的前节点应该赋值为新节点
                after.before = insert;
            }
            // 链表大小加一
            size++;
        }
    }

    // 添加数据集
    public void addAll(MyLinkedList<T> lists){
        if(lists==null) return;
        // 这里我还没看源码，直接使用循环单个添加了
        // 这里倒序添加的，如果下标从0开始增加，最后顺序是反过来的，在研究下
        for (int i = lists.size-1; i >= 0; i--) {
            add(lists.get(i));
        }
    }

    // 删除最后一个并返回删除对象
    public T remove(){
        if (size==0) return null;
        return remove(getIndexNode(size-1));
    }

    // 删除指定节点
    public void remove(int index){
        if (index < 0 || index > size || size==0) return;
        // 找到需要删除的下标对应的节点
        remove(getIndexNode(index));
    }

    // 删除指定数据
    public boolean remove(T t){
        // 从链表头节点开始找，一直到链表结束或者找到为空的数据为止
        for (Node<T> node = first; node != null; node = node.after) {
            // 数据很可能放了个null
            if ((t==null && node.data==t) || (t!=null && t.equals(node.data))) {
                remove(node);
                return true;
            }
        }
        return false;
    }

    // 删除指定节点
    private T remove(Node<T> remove) {
        // 取出该节点的前一个节点
        Node<T> before = remove.before;
        // 取出该节点的后一个节点
        Node<T> after = remove.after;
        // 判断前一个节点是否为空
        if (before==null) { // 为空说明删除的是头节点
            // 那么头节点赋值为删除节点的后一个节点
            first = remove.after;
        }else{
            // 删除节点的前一个节点的后节点赋值为删除节点的后一个节点
            before.after = remove.after;
        }
        if (after==null) { // 为空说明删除的是尾节点
            // 那么尾节点赋值为删除节点的前一个节点
            last = remove.before;
        }else{
            // 删除节点的后一个节点的前节点赋值为删除节点的前一个节点
            after.before = remove.before;
        }
        size--;
        return remove.data;
    }

    // 修改指定位置的节点数据
    public void set(int index, T t){
        if (index < 0 || index > size || size==0) return;
        // 找到需要修改的下标对应的节点
        Node<T> node = getIndexNode(index);
        // 直接修改数据
        node.data = t;
    }

    // 查找指定位置的节点的数据
    public T get(int index) {
        if (index < 0 || index > size) return null;
        return getIndexNode(index).data;
    }

    // 查找指定位置的节点
    private Node<T> getIndexNode(int index) {
        // 判断要拿的下标靠前还是靠后
        if (index < size / 2) { // 表示靠后，这里可以写成 size>>1 表示二进制右移一位，也即除以2，右移多位，比如2，则除以4，3，则除以8
            // 拿到头节点
            Node<T> node = first; // 注意这一句就代表了index=0的情况了
            // 循环index次
            for (int i = 0; i < index; i++) { // 一旦进入循环说明index>=1
                node = node.after;
            }
            return node;
        } else {
            // 拿到尾节点
            Node<T> node = last; // 注意这一句就代表了index=size-1的情况了
            // 循环size-1-index次
            for (int i = size - 1; i > index; i--) { // 一旦进入循环说明index>=1
                node = node.before;
            }
            return node;
        }
    }

    /**
     * 链表中的一个结点
     */
    public static class Node<T> {
        // 该节点当前数据
        T data;
        // 该节点前一个节点
        Node<T> before;
        // 该节点后一个节点
        Node<T> after;

        public Node(Node<T> before, T data, Node<T> after) {
            this.before = before;
            this.data = data;
            this.after = after;
        }
    }

}
