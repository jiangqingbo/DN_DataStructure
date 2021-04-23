package com.huyunit.dn.datastructure.lesson13;

import org.junit.Test;

/**
 * author: bobo
 * create time: 2018/12/28 5:59 PM
 * email: jqbo84@163.com
 */
public class Floyd {

    public static final int I = 100;

    //邻接距阵
    public static int[][] d = new int[][]{
            {0, 2, 1, 5},
            {2, 0, 4, I},
            {1, 4, 0, 3},
            {5, I, 3, 0}
    };
    //路径
    public static int[][] p=new int[][]{
            {0,1,2,3},
            {0,1,2,3},
            {0,1,2,3},
            {0,1,2,3}
    };

    /**
     * 多源最短路径算法
     */
    public static void floyd(){
        for (int k = 0; k < d.length; k++) {
            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < d[i].length; j++) {
                    if(d[i][j]>d[i][k] + d[k][j]){
                        d[i][j] = d[i][k] + d[k][j];
                        //记录下每次修改后的路径
                        p[i][j] = p[i][k];
                    }
                }
            }
        }
    }

    /**
     * 打印修改后的矩阵
     */
    public static void printArray(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }


    public static void printShortPath(){
        int k = 0;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                System.out.println("V" + i +"->V"+j+", weight: "+ d[i][j] + ", path: " + i);
                k = p[i][j];
                while(k!=j){
                    System.out.println("->"+k);
                    k=p[k][j];
                }
            }
            
        }
    }


    @Test
    public void main(){
        floyd();
        System.out.println("-------邻接矩阵--------");
        printArray(d);
        System.out.println("-------记录路径数组-----");
        printArray(p);

        System.out.println("-------打印每个节点到另个节点到路径-----");
        printShortPath();
    }

}
