package com.huyunit.dn.datastructure.lesson9;

import org.junit.Test;

/**
 * author: bobo
 * create time: 2018/12/18 2:29 PM
 * email: jqbo84@163.com
 */
public class ExampleTest {

    @Test
    public void testAVLBTree(){
        Integer[] nums = {5,8,2,0,1,-2};
        AVLBTree<Integer> tree = new AVLBTree<>();
        for (int i = 0; i < nums.length; i++) {
            Integer num = nums[i];
            tree.insertElement(num);
        }
        long l1 = System.currentTimeMillis();
        tree.showAVLBTree(tree.getRoot());
        System.out.println("(System.currentTimeMillis() - l1) = " + (System.currentTimeMillis() - l1));
        System.out.println("----------------------------");
        long l2 = System.currentTimeMillis();
        tree.showAVL(tree.getRoot());
        System.out.println("(System.currentTimeMillis() - l2) = " + (System.currentTimeMillis() - l2));
    }

    @Test
    public void testRBTree(){
        Integer[] nums = {13,8,5,11,6,22,27,25,14,17};
        RBTree<Integer> tree = new RBTree<>();
        for (int i = 0; i < nums.length; i++) {
            Integer num = nums[i];
            tree.insertElement(num);
        }
        System.out.println("---------------打印插入数据--------------- ");
        tree.showRBTree(tree.getRoot());

        tree.remove(25);
        System.out.println("---------------------------------------- 删除: " + 25);
        tree.showRBTree(tree.getRoot());

        System.out.println("----------------循环删除----------------- ");
        for (Integer num : nums) {
            tree.remove(num);
            System.out.println("------------------------------------ 删除: " + num);
            tree.showRBTree(tree.getRoot());
        }

    }

}
