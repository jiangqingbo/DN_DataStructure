package com.huyunit.dn.datastructure.lesson12;

import java.util.Stack;

/**
 * 最长公共子序列
 * author: bobo
 * create time: 2018/12/27 1:54 PM
 * email: jqbo84@163.com
 */
public class LCS {

    /**
     * 比较大小，返回大的
     *
     * @param a
     * @param b
     * @return
     */
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * 使用动态规划的方式填入数据
     *
     * @param x
     * @param y
     * @return
     */
    public static int[][] fillinLCS(String x, String y) {
        char[] s1 = x.toCharArray();
        char[] s2 = y.toCharArray();
        int[][] array = new int[x.length() + 1][y.length() + 1];
        //先把第一行和第一列填上零
        for (int i = 0; i < array[0].length; i++) {
            array[0][i] = 0;
        }
        for (int i = 0; i < array.length; i++) {
            array[i][0] = 0;
        }
        //使用动态规划的方式填入数据
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                if (s1[i - 1] == s2[j - 1]) {//如果相等，左上角加1填入
                    array[i][j] = array[i - 1][j - 1] + 1;
                } else {//不等，取左和上的最大值
                    array[i][j] = max(array[i - 1][j], array[i][j - 1]);
                }
            }

        }
        return array;
    }

    /**
     * 从后往前找到最长公共子序列结果
     * @param x
     * @param y
     * @param left 表示当两个值相等当情况，true：取左边，false：取右边
     * @return
     */
    public static String getLCS(String x, String y, boolean left) {
        int[][] array = fillinLCS(x, y);
        Stack stack = new Stack();
        int i = x.length() - 1;
        int j = y.length() - 1;
        //从后往前找到结果
        while (i >= 0 && j >= 0) {
            if (x.charAt(i) == y.charAt(j)) {
                stack.push(x.charAt(i));
                i--;
                j--;
            } else {
                //注意数组和String中的位置有一位差
                if (array[i + 1][j + 1 - 1] > array[i + 1 - 1][j + 1]) {
                    if (left) j--;
                    else i--;
                } else {
                    if (left) i--;
                    else j--;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

}
