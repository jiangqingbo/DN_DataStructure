package com.huyunit.dn.datastructure.lesson3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleTest {
    @Test
    public void main() throws Exception {
        assertEquals(4, 2 + 2);
        fun(3);
        System.out.println();
        System.out.println("----------------------");
        System.out.println("阶乘：" + fact(5));
        System.out.println("------fibonacciSequence-----");

        for (int i = 1; i < 9; i++) {
            System.out.print(fibonacciSequence(i) + "  ");
        }

        System.out.println();

        System.out.println("fibonacciSequence(8) = " + fibonacciSequence(8));
        System.out.println("----------- 汉诺塔算法 -----------");
        hanoi(3, 1, 2, 3);
    }

    /**
     * 测试递归的思想
     */
    public void fun(int n) {  //3
        System.out.print(n + "  ");
        if (n < 0) {
            return;
        } else {
            fun(n - 1);
            System.out.print(n + "  ");
        }
    }

    /**
     * 1*2*3*4*5....     n!
     * 5!  = 5*4!      4! =  4*3!
     */
    public int fact(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }

    /**
     * fibonacciSequence数列   8=7+6   7=6+5  6=5+4
     * 1   1  2  3  5  8   13  21  34  55  89  144......
     * 返回第n项
     */
    public int fibonacciSequence(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            int k = fibonacciSequence(n - 1) + fibonacciSequence(n - 2);
            return k;
        }
    }

    /**
     * 中序算法
     *
     * @param n      盘子的个数
     * @param start  开始的柱子
     * @param middle 中介柱子
     * @param end    结果柱子
     */
    public static void hanoi(int n, int start, int middle, int end) {
        if (n <= 1) {
            System.out.println(start + "----->" + end);
        } else {
            hanoi(n - 1, start, end, middle);
            System.out.println(start + "----->" + end);
            hanoi(n - 1, middle, start, end);
        }
    }
}










