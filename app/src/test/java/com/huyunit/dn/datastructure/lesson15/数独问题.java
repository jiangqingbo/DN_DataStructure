package com.huyunit.dn.datastructure.lesson15;

import org.junit.Test;

/**
 * Created by Jett on 2019/1/2.
 */

public class 数独问题 {
    @Test
    public void test(){
        sudoku();
    }
    public static int[][] result=new int[9][9];
//    public static int[][] result=new int[][]{
//            {8, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 3, 6, 0, 0, 0, 0, 0},
//            {0, 7, 0, 0, 9, 0, 2, 0, 0},
//            {0, 5, 0, 0, 0, 7, 0, 0, 0},
//            {0, 0, 0, 0, 4, 5, 7, 0, 0},
//            {0, 0, 0, 1, 0, 0, 0, 3, 0},
//            {0, 0, 1, 0, 0, 0, 0, 6, 8},
//            {0, 0, 8, 5, 0, 0, 0, 1, 0},
//            {0, 9, 0, 0, 0, 0, 4, 0, 0}
//    };
    public static void sudoku(){
        sudoku(0,0);
    }
    public static void sudoku(int i,int j){
        if(i==8 && j==9){
            printResult();
            return;
        }
        if(j==9){//横着放的时候，如果到了最右边，就回到下一行的第一个
            i++;
            j=0;
        }
        if(result[i][j]==0){//如果是空格
            for (int k = 1; k <= 9; k++) {
                if(judge(i,j,k)){
                    result[i][j]=k;
                    sudoku(i,j+1);
                    //让前一次的格子还原
                    result[i][j]=0;
                }
            }
        }else{
            sudoku(i,j+1);
        }
    }

    /**
     *判断当前位置上是否可以放入数字
     */
    public static boolean judge(int row,int col,int number){
        //判断行和列不重复
        for (int i = 0; i < 9; i++) {
            if(result[row][i]==number || result[i][col]==number){
                return false;
            }
        }
        //判断自已所在的宫里面没有重复值
        int tempRow=row/3;
        int tempCol=col/3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(result[tempRow*3+i][tempCol*3+j]==number){
                    return false;
                }
            }
        }
        return true;
    }

    public static void printResult(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }
}
