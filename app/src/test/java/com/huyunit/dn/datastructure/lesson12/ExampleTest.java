package com.huyunit.dn.datastructure.lesson12;

import org.junit.Test;

import java.util.Stack;

/**
 * author: bobo
 * create time: 2018/12/24 9:26 PM
 * email: jqbo84@163.com
 */
public class ExampleTest {

    @Test
    public void testLCS() {
        //getLCS("abcbdab", "bdcaba");
        int[][] array = LCS.fillinLCS("abcbdab", "bdcaba");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println("---------------------------");

        String lcs = LCS.getLCS("abcbdab", "bdcaba", true);
        System.out.println("lcs = " + lcs);
    }

    /**
     * 比较两个大小，返回大值
     */
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static void getLCS(String x, String y){
        char[] s1 = x.toCharArray();
        char[] s2 = y.toCharArray();
        int[][] array = new int[x.length() + 1][y.length() + 1];
        //先把第一行和第一列填零
        for (int i = 0; i < array[0].length; i++) {
            array[0][i] = 0;
        }
        for (int i = 0; i < array.length; i++) {
            array[i][0] = 0;
        }

        //使用动态规划的方式填入数据
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                if(s1[i-1] == s2[j-1]){
                    array[i][j] = array[i-1][j-1] + 1;
                } else {
                    array[i][j] = max(array[i-1][j], array[i][j-1]);
                }
            }
        }

        //打印
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }

        //找出最长公共子序列
        Stack result = new Stack();
        int i = x.length() - 1;
        int j = y.length() - 1;
        while ((i >= 0) && (j >= 0)) {
            if(s1[i] == s2[j]){
                result.push(s1[i]);
                i--;
                j--;
            } else {
                //注意数组和String中的位置有一位差
                if(array[i+1][j+1-1] > array[i+1-1][j+1]){
                    i--;
                } else{
                    j--;
                }
            }
        }
        System.out.println("--------------------------------");
        while (!result.isEmpty()){
            System.out.print(result.pop() + "  ");
        }
    }

}
