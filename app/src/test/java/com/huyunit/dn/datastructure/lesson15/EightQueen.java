package com.huyunit.dn.datastructure.lesson15;

import org.junit.Test;

/**
 * 回溯法之八皇后
 * author: bobo
 * create time: 2019/1/22 4:14 PM
 * email: jqbo84@163.com
 */
public class EightQueen {

    public static int SIZE = 8;

    public static int[] array = new int[SIZE];

    /**
     * 八皇后算法
     */
    public void eightQueens() {
        eightQueens(0);
    }

    public void eightQueens(int row) {
        //如果有结果了就退出
        if(row == 8){
            printResult();
            System.out.println("---------------");
            return;
        }
        //开始从第一列到最后一列一个个放入
        for (int col = 0; col < SIZE; col++) {
            array[row] = col;
            if (judge(row)) { //判断是否可以放入
                eightQueens(row + 1); //就开始下一行
            }
        }
    }

    /**
     * 判断当前列放入的位置是否和以前放过的内容有冲突
     *
     * @param n
     * @return
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //条件1：array[n] == array[i]  表示在一列上
            //条件2：abs(array[n] - array[i]) == abs(n-i)  表示在对角线上
            if (array[n] == array[i] || Math.abs(array[n] - array[i]) == Math.abs(n - i)) {
                return false;
            }
        }
        return true;
    }

    public static void printResult(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }



    @Test
    public void main(){
        eightQueens();
    }
}
