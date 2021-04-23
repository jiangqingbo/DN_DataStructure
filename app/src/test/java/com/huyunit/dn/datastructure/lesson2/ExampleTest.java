package com.huyunit.dn.datastructure.lesson2;

import org.junit.Test;

import java.util.LinkedList;

/**
 * author: bobo
 * create time: 2018/12/11 2:11 PM
 * email: jqbo84@163.com
 */
public class ExampleTest {

    @Test
    public void testMahjong (){
        LinkedList<Mahjong> list=new LinkedList<Mahjong>();
        list.add(new Mahjong(3,1));
        list.add(new Mahjong(2,3));
        list.add(new Mahjong(3,7));
        list.add(new Mahjong(1,1));
        list.add(new Mahjong(3,8));
        list.add(new Mahjong(2,2));
        list.add(new Mahjong(3,2));
        list.add(new Mahjong(1,3));
        list.add(new Mahjong(3,9));
        System.out.println("排序之前：" + list);
        radixSort(list);
        System.out.println("排序之后：" + list);
    }

    /**
     * 基数排序
     */
    public static void radixSort(LinkedList<Mahjong> list){
        //对应点数进行分组
        LinkedList[] rankList = new LinkedList[9];
        for (int i = 0; i < rankList.length; i++) {
            rankList[i] = new LinkedList();
        }
        //把数据一个个放到对应的组中
        while (list.size() > 0) {
            //取一个
            Mahjong m = list.remove();
            //放到组中，下标=点数减1
            rankList[m.rank - 1].add(m);
        }
        for (int i = 0; i < rankList.length; i++) {
            list.addAll(rankList[i]);
        }

        //花色进行分组
        LinkedList[] suitList = new LinkedList[3];
        for (int i = 0; i < suitList.length; i++) {
            suitList[i] = new LinkedList();
        }
        //把数据一个个放到组中
        while (list.size() > 0) {
            //取一个
            Mahjong m = list.remove();
            //放到组中，下标=点数减1
            suitList[m.suit - 1].add(m);
        }

        //把3个组合到一起
        for (int i = 0; i < suitList.length; i++) {
            list.addAll(suitList[i]);
        }

    }

}
