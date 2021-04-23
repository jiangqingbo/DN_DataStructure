package com.huyunit.dn.datastructure.lesson14;

import org.junit.Test;

import static com.huyunit.dn.datastructure.lesson14.Kruskal2.MAX_WEIGHT;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testKruskal(){
        int[][] matrix = new int[][]{
                {0,  50, 60,  I,  I,  I,  I},
                {50,  0,  I, 65, 40,  I,  I},
                {60,  I,  0, 52,  I,  I, 45},
                {I,  65, 52,  0, 50, 30, 42},
                {I,  40,  I, 50,  0, 70,  I},
                {I,   I,  I, 30, 70,  0,  I},
                {I,   I, 45, 42,  I,  I,  0}
        };

        Kruskal kruskal = new Kruskal();
        kruskal.init(matrix);
        Kruskal.Edge[] result = kruskal.kruskal();

        int lengh = 0;
        for(int i = 0; i < result.length; i++) {
            if(null != result[i]) {
                lengh += result[i].weight;
            }
        }
        System.out.println("最小生成树的权重之和" + lengh);
        char[] chars = new char[matrix.length];
        chars[0] = 'A';
        chars[1] = 'B';
        chars[2] = 'C';
        chars[3] = 'D';
        chars[4] = 'E';
        chars[5] = 'F';
        chars[6] = 'G';

        for (int i = 0; i < result.length; i++) {
            if(null != result[i]) {
                System.out.printf("(%s, %s)---> %d \n", chars[result[i].start], chars[result[i].end], matrix[result[i].start][result[i].end]);
            }
        }
    }

    @Test
    public void test2() {
        Kruskal2 graph = new Kruskal2(7);
        int[] v0 = new int[]{0, 50, 60, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT};
        int[] v1 = new int[]{50, 0, MAX_WEIGHT, 65, 40, MAX_WEIGHT, MAX_WEIGHT};
        int[] v2 = new int[]{60, MAX_WEIGHT, 0, 52, MAX_WEIGHT, MAX_WEIGHT, 45};
        int[] v3 = new int[]{MAX_WEIGHT, 65, 52, 0, 50, 30, 42};
        int[] v4 = new int[]{MAX_WEIGHT, 40, MAX_WEIGHT, 50, 0, 70, MAX_WEIGHT};
        int[] v5 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 30, 70, 0, MAX_WEIGHT};
        int[] v6 = new int[]{MAX_WEIGHT, MAX_WEIGHT, 45, 42, MAX_WEIGHT, MAX_WEIGHT, 0};
        graph.matrix[0] = v0;
        graph.matrix[1] = v1;
        graph.matrix[2] = v2;
        graph.matrix[3] = v3;
        graph.matrix[4] = v4;
        graph.matrix[5] = v5;
        graph.matrix[6] = v6;
        graph.kruskal();
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public static final int I = 100;
    int[][] array = new int[][]{
            {0, 1, 5, I, I, I, I, I, I},
            {1, 0, 3, 7, 5, I, I, I, I},
            {5, 3, 0, I, 1, 7, I, I, I},
            {I, 7, I, 0, 2, I, 3, I, I},
            {I, 5, 1, 2, 0, 3, 6, 9, I},
            {I, I, 7, I, 3, 0, I, 5, I},
            {I, I, I, 3, 6, I, 0, 2, 7},
            {I, I, I, I, 9, 5, 2, 0, 4},
            {I, I, I, I, I, I, 7, 4, 0}
    };

    public void dijkstar() {
        int k = 0;//表示当前正要处理的顶点Vk

        //初始化相关的信息
        int[] path = new int[9];
        int[] weight = array[0];
        //定义一个数组来存放U和V两个集合的信息
        int[] flag = new int[9];
        flag[0] = 1;
        //开始逻辑,求VO到某个顶点的最短路径
        for (int v = 1; v < 9; v++) {
            //在能走的路径中找到最短的一条
            int min = I;
            for (int i = 0; i < 9; i++) {
                if (flag[i] == 0 && weight[i] < min) {
                    k = i;//K为U集合到V集合中找到的顶点
                    min = weight[i];//min找到了最小值的位置
                }
            }
            //从这个最短的路径对应的顶点开始找下一轮
            flag[k] = 1;
            //修正当前最短路径
            for (int i = 0; i < 9; i++) {
                //如果经过V顶点的路径比现在的路径短，新更新
                if (flag[i] == 0 && (min + array[k][i]) < weight[i]) {
                    weight[i] = min + array[k][i];//修改路径长度
                    path[i] = k;//保存前驱
                }
            }
        }
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < weight.length; i++) {
            System.out.print(weight[i] + " ");
        }

        System.out.println();
        //打印结果
        int v = 8;
        while (v != 0) {
            System.out.print(path[v]);
            v = path[v];
        }
    }

    @Test
    public void test() {
        dijkstar();
    }
}











