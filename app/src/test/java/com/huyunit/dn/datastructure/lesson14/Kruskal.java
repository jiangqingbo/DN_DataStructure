package com.huyunit.dn.datastructure.lesson14;

import org.junit.Test;

/**
 * 最小生成树，两种方法实现（克鲁斯卡尔（kruskal）算法、普里姆（prim）算法）
 * author: bobo
 * create time: 2019/1/21 3:58 PM
 * email: jqbo84@163.com
 */
public class Kruskal {
    public int verticeSize;// 顶点的大小
    public int[][] matrix; // 邻接矩阵
    public int edgeSize;    //边的大小
    public Edge[] edges;

    public static final int I = 0xFFF8;

    public void init(int[][] matrix) {
        this.matrix = matrix;
        this.verticeSize = matrix.length;
    }

    /**
     * 获取所有的边
     *
     * @return
     */
    private Edge[] getEdges() {
        int index = 0;
        Edge[] edges = new Edge[verticeSize * verticeSize];
        for (int i = 0; i < verticeSize; i++) {
            for (int j = 0; j < verticeSize; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != I) {
                    edges[index++] = new Edge(i, j, matrix[i][j]);
                }
            }
        }
        edgeSize = index;
        return edges;
    }

    /**
     * 边的权重进行排序
     *
     * @param cur_edge
     * @param size
     */
    private void sortEdge(Edge[] cur_edge, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (edges[i].weight > edges[j].weight) {
                    Edge tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

    /**
     * 克鲁斯卡尔 核心算法
     */
    public Edge[] kruskal() {
        edges = getEdges();
        int index = 0;
        Edge[] curEdges = edges;//存放排序后的边数组
        Edge[] rets = new Edge[edgeSize];//用来存放结果
        sortEdge(curEdges, edgeSize);//边的权重进行排序
        //定义一个数组，用来存放连通分量,用来表示连通关系的
        //下标用来表示起点,值表示终点
        int[] tempEdge = new int[edgeSize];
        for (int i = 0; i < edgeSize; i++) {
            int p1 = curEdges[i].start;
            int p2 = curEdges[i].end;
            int m = getEnd(tempEdge, p1);
            int n = getEnd(tempEdge, p2);
            //如果m和n没有连接在一起，他们就不相等
            //如果相等就有回路
            if (m != n) {
                rets[index++] = curEdges[i];
                if (m > n) {
                    int temp = n;
                    n = m;
                    m = temp;
                }
                tempEdge[m] = n;
            }
        }
        return rets;
    }

    /**
     * 获取当前节点的最后一个节点，也是当前链中最大值
     *
     * @param edges
     * @param p
     * @return
     */
    private int getEnd(int[] edges, int p) {
        int i = p;
        while (edges[i] != 0) {
            i = edges[i];
        }
        return i;
    }

    @Test
    public void main(){
        int[][] m = new int[][]{
                {0,  50, 60,  I,  I,  I,  I},
                {50,  0,  I, 65, 40,  I,  I},
                {60,  I,  0, 52,  I,  I, 45},
                {I,  65, 52,  0, 50, 30, 42},
                {I,  40,  I, 50,  0, 70,  I},
                {I,   I,  I, 30, 70,  0,  I},
                {I,   I, 45, 42,  I,  I,  0}
        };

        init(m);
        Edge[] result = kruskal();

        int lengh = 0;
        for(int i = 0; i < result.length; i++) {
            if(null != result[i]){
                lengh+= result[i].weight;
            }
        }
        System.out.println("最小生成树的权重之和" + lengh);
        char[] chars = new char[m.length];
        chars[0] = 'A';
        chars[1] = 'B';
        chars[2] = 'C';
        chars[3] = 'D';
        chars[4] = 'E';
        chars[5] = 'F';
        chars[6] = 'G';

        for (int i = 0; i < result.length; i++) {
            if(null != result[i]){
                System.out.printf("(%s, %s)---> %d \n",chars[result[i].start], chars[result[i].end], matrix[result[i].start][result[i].end]);
            }
        }
    }


    class Edge {
        int start; //起点
        int end; //终点
        int weight; //边的权重

        public Edge(){}

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
