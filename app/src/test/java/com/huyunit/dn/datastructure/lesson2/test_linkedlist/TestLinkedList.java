package com.huyunit.dn.datastructure.lesson2.test_linkedlist;

import org.junit.Test;

/**
 * author: bobo
 * create time: 2018/12/11 3:32 PM
 * email: jqbo84@163.com
 */
public class TestLinkedList {

    @Test
    public void testLinkedList(){
        LinkedList<Integer> linkedList=new LinkedList<>();
        linkedList.add(0,4);
        linkedList.add(0,1);
        linkedList.add(0,2);
        linkedList.add(0,3);//43  66 12
        linkedList.add(0,66);
        linkedList.add(1,88);
        //0:66    1:4    2:3    3:1    4:2
//        linkedList.remove(0);

//        linkedList.remove(3);

        LinkedList<Integer> linkedList2=new LinkedList<>();
        linkedList2.add(5);
        linkedList2.add(7);
        linkedList2.add(9);

        linkedList.addAll(linkedList2);

        for (int i = 0; i < linkedList.size; i++) {
            System.out.print(i+":"+linkedList.get(i)+"    ");
        }
    }

}
