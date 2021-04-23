package com.huyunit.dn.datastructure.lesson12;

import org.junit.Test;

/**
 * author: bobo
 * create time: 2018/12/26 1:21 PM
 * email: jqbo84@163.com
 */
public class KMP {

    /**
     * bobo
     *
     * @param dest
     * @return
     */
    public static int[] getKmpNext(String dest) {
        char[] pattern = dest.toCharArray();
        int[] result = new int[pattern.length];
        int j = 0;
        result[0] = j;
        for (int i = 1; i < pattern.length; i++) {// 2.i++
            while (j > 0 && pattern[i] != pattern[j]) {// 3.不同：j = next[j - 1];
                j = result[j - 1];
            }
            if (pattern[i] == pattern[j]) {// 3.相同：j++
                j++;
            }
            result[i] = j; // 1. next[i] = j
        }
        return result;
    }

    /**
     * jett
     *
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        char[] cs = dest.toCharArray();
        int[] next = new int[cs.length];
        int j = 0;
        next[0] = j;
        for (int i = 1; i < cs.length; i++) {
            while (j > 0 && cs[i] != cs[j]) {
                j = next[j - 1];
            }
            if (cs[i] == cs[j]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * 同学
     *
     * @param dest
     * @return
     */
    public static int[] getKMPNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //不同的情况
            if (dest.charAt(i) != dest.charAt(j)) {
                //j回退直到==0，或是找到相同
                while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                    j = next[j - 1];
                }
                if (dest.charAt(i) == dest.charAt(j)) {
                    next[i] = j + 1;
                    j++;
                } else if (j == 0) {
                    next[i] = 0;
                }
            } else {
                //相同
                next[i] = j + 1;
                j++;
            }
        }
        return next;
    }

    /**
     * bilibili
     *
     * @param pattern
     * @return
     */
    private static int[] computeTemporaryArray(char pattern[]) {
        int[] lps = new int[pattern.length];
        int index = 0;
        for (int i = 1; i < pattern.length; ) {
            if (pattern[i] == pattern[index]) {
                lps[i] = index + 1;
                index++;
                i++;
            } else {
                if (index != 0) {
                    index = lps[index - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * 查询子序列的匹配成功后，返回原字符串的位置
     * @param src
     * @param dest
     * @return 返回位置
     */
    public static int kmp(String src, String dest) {
        int[] next = getKmpNext(dest);
        char[] s1 = src.toCharArray();
        char[] s2 = dest.toCharArray();
        for (int i = 0, j = 0; i < s1.length; i++) {
            while (j > 0 && s1[i] != s2[j]) {
                j = next[j - 1];
            }
            if (s1[i] == s2[j]) {
                j++;
            }
            if (j == s2.length) {
                return i - j + 1;
            }
        }
        return 0;
    }

    @Test
    public void testKmp() {
        String dest = "ababcaba";

        int[] tempArray = getKmpNext(dest);
        System.out.print("next: ");
        for (int i : tempArray) {
            System.out.print(i + " ");
        }
        System.out.println();

        String src = "ababcabcbababcabacaba";
        int index = kmp(src, dest);
        System.out.println("index = " + index);
    }

}
