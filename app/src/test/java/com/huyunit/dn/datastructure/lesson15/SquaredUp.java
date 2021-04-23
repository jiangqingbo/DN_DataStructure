package com.huyunit.dn.datastructure.lesson15;

import org.junit.Test;

/**
 * 九宫格，必须是奇数列
 * <p>
 * author: bobo
 * create time: 2019/1/22 3:18 PM
 * email: jqbo84@163.com
 */
public class SquaredUp {

    /**
     * 九宫格的回溯法
     *
     * @param array
     * @return
     */
    public int[][] squaredUp(int[][] array) {
        int n = array.length;
        int x = 1; //要填入的数据
        //定义起始位置
        int row = 0;
        int col = n / 2;
        array[row][col] = x;

        while (x < n * n) {
            //在选择下一位置的时候，先记录下现在的位置
            int tempRow = row, tempCol = col;
            //向右上移动
            row--;
            if (row < 0) { //如果上面超出边界了，那么row赋值为：右边最下面的一行，即：n-1
                row = n - 1;
            }

            col++;
            if (col == n) { //如果右边超出边界了，那么col还原为：1
                col = 0;
            }

            //下一个值
            x++;

            if (array[row][col] == 0) { //如果没有填值，则直接赋值
                array[row][col] = x;
            } else { //如果已经填值，就放在当前位置的下面
                //还原
                row = tempRow;
                col = tempCol;
                row++;
                array[row][col] = x;
            }
        }
        return array;
    }

    @Test
    public void main() {
        int n = 5;
        int[][] array = new int[n][n];
        array = squaredUp(array);

        System.out.println("5 * 5 的九宫格算法矩阵图：");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
