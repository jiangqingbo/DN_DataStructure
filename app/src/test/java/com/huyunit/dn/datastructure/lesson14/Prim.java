package com.huyunit.dn.datastructure.lesson14;

import org.junit.Test;

/**
 * author: bobo
 * create time: 2019/1/21 4:26 PM
 * email: jqbo84@163.com
 */
public class Prim {

    public static final int I = 0xFFF8;

    int SIZE = 7;//m为节点的个数

    //邻接矩阵
    int[][] G = new int[][]{
            {0,  28, I,  I,  I,  10,  I},
            {28,  0, 16, I, I,  I,  14},
            {I,  16,  0, 12,  I,  I, I},
            {I,  I, 12,  0, 22, I, 18},
            {I,  I,  I, 22,  0, 25,  24},
            {10,   I,  I, I, 25,  0,  I},
            {I,   14, I, 18,  24,  I,  0}
    };

    //最短路径数组, 对应的前驱索引值
    int[] path = new int[SIZE];

    //最短权重数组, 存放每次到另个顶点的修改后的权重值，先修改第一行V0
    int[] weight;

    //最小生成树的权重之和
    int minTree;

    /**
     * 普里姆核心算法
     */
    public void prim() {
        int k = 0;//表示当前正要处理的顶点Vk

        //初始化权重数组
        weight = G[0];

        //定义一个数组来存放集合U 和集合V 两个集合的顶点的信息
        boolean[] flag = new boolean[SIZE];
        //先从第一个顶点开始，所以先直接存放在U集合一边
        flag[0] = true;
        //开始逻辑,求VO到某个顶点的最短路径
        for (int v = 1; v < SIZE; v++) {
            //在能走的路径中找到最短的一条，也就是在集合V 中找
            int min = I;
            for (int i = 0; i < SIZE; i++) {
                if (!flag[i] && weight[i] < min) {
                    k = i;//K为U集合到V集合中找到的顶点
                    min = weight[i];//min找到了最小值的位置
                }
            }

            //如果min = I 表示已经不再有点可以加入最小生成树中
            if(min == I){
                break;
            }

            minTree += min;

            //找到最短路径后，把它存放在集合U中，然后从这个最短的路径对应的顶点开始找下一轮
            flag[k] = true;

            //path
            path[v] = k;

            for (int i = 0; i < SIZE; i++) {
                if(!flag[i] && weight[i] > G[k][i]){
                    weight[i] = G[k][i];   //更新可更新边的权值
                }
            }
        }
    }

    @Test
    public void main(){
        //普里姆算法
        prim();

        //打印
        System.out.print("最短路径：");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
        System.out.print("最短权重：");
        for (int i = 0; i < weight.length; i++) {
            System.out.print(weight[i] + " ");
        }

        System.out.println();
        System.out.println("最小生成树的权重之和 = " + minTree);
    }

}
