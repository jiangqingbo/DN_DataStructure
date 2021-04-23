package com.huyunit.dn.datastructure.lesson15;

import org.junit.Test;

/**
 * Created by Jett on 2019/1/2.
 */

public class 八皇后 {
    @Test
    public void test(){
        eightQueens();

    }
    //下标表示行号    值表示列号
    public static int[] array=new int[8];
    /**
     * 处理八个皇后的问题
     */
    public static void eightQueens(){
        eightQueens(0);
    }
    public static void eightQueens(int row){
        //如果有结果了就退出
        if(row==8){
            printResult();
            System.out.println("---------------");
            return;
        }

        //开始从第一列到最后一列一个个放入
        for(int col=0;col<8;col++){
            array[row]=col;
            if(judge(row)){//判断是否可以放入
                eightQueens(row+1);//就开始下一行
            }
        }
    }

    /**
     * 判断当前列放入的位置是否和以前放过的内容有冲突
     */
    public static boolean judge(int n){//n=4
        for(int i=0;i<n;i++){
            //条件1  array[i]==array[n]  在一列上
            //条件2  abs(n-i)==abs(array[n]-array[i])
            if(array[i]==array[n] || Math.abs(n-i)==Math.abs(array[n]-array[i])){//有冲突
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
}











