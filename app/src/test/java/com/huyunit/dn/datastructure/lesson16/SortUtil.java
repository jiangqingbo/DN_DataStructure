package com.huyunit.dn.datastructure.lesson16;

import org.junit.Test;

/**
 * author: bobo
 * create time: 2019/1/24 10:04 AM
 * email: jqbo84@163.com
 */
public class SortUtil {

    /**
     * 插入排序
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int k = i;
            int target = array[k];
            while (k > 0 && target < array[k - 1]) {
                array[k] = array[k - 1];
                k--;
            }
            array[k] = target;
        }
    }

    /**
     * 希尔排序
     *
     * @param array
     * @param step
     */
    public static void shellSort(int[] array, int step) {
        for (int j = 0; j < step; j++) {
            //直接插入排序
            for (int i = j + step; i < array.length; i = i + step) {
                int k = i;
                int target = array[k];
                while (k > step - 1 && target < array[k - step]) {
                    array[k] = array[k - step];
                    k = k - step;
                }
                array[k] = target;
            }
        }
    }

    @Test
    public void main() {
        int[] array = {8, 5, 7, 3, 6, 4, 1, 2, 9};
        insertSort(array);
        System.out.print("插入排序：");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();

        int[] array2 = {8, 5, 7, 3, 6, 4, 1, 2, 9};
        shellSort(array2, 3);
        shellSort(array2, 1);

        System.out.print("希尔排序：");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + "  ");
        }
        System.out.println();
    }

}
