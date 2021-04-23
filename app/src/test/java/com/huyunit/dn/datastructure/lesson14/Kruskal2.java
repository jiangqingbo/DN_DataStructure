package com.huyunit.dn.datastructure.lesson14;

/**
 * Created by Jett on 2018/12/28.
 */

public class Kruskal2 {
    public int verticeSize;// 顶点的大小
    public int[] vertices; // 顶点集合
    public int[][] matrix; // 邻接矩阵
    public int edgeSize;
    public static final int MAX_WEIGHT = 0xFFF8;
    public Edge[] edges;

    public Kruskal2(int verticeSize) {
        this.verticeSize = verticeSize;
        matrix = new int[verticeSize][verticeSize];
    }

    public static class Edge{
        int start;//起点
        int end;//终点
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

    }

    /**
     * 获取所有的边
     * @return
     */
    private Edge[] getEdges() {
        int index = 0;
        Edge[] edges = new Edge[verticeSize * verticeSize];
        for(int i = 0;i < verticeSize; i++) {
            for(int j = 0; j < verticeSize;j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != MAX_WEIGHT) {
                    edges[index++] = new Edge(i, j, matrix[i][j]);
                }
            }
        }
        edgeSize = index;
        return edges;
    }

    private void sortEdge(Edge[] cur_edge, int size) {
        for(int i = 0; i < size; i++) {
            for (int j = i+1; j < size; j++) {
                if (edges[i].weight > edges[j].weight) {
                    Edge tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

    /**
     * 核心算法
     */
    public void kruskal(){
        edges=getEdges();
        int index=0;
        Edge[] cur_edge=edges;//存放排序后的边数组
        Edge[] rets=new Edge[edgeSize];//用来存放结果
        sortEdge(cur_edge,edgeSize);
        //定义一个数组，用来存放连通分量,用来表示连通关系的
        //下标用来表示起点,值表示终点
        int[] edge_temp=new int[edgeSize];
        for(int i=0;i<edgeSize;i++){
            int p1=cur_edge[i].start;
            int p2=cur_edge[i].end;
            int m=getEnd(edge_temp,p1);
            int n=getEnd(edge_temp,p2);
            //如果m和n没有连接在一起，他们就不相等
            //如果相等就有回路
            if(m!=n){
                rets[index++]=cur_edge[i];
                if(m>n){
                    int temp=n;
                    n=m;m=temp;
                }
                edge_temp[m]=n;
            }
        }
        int lengh = 0;
        for(int i = 0; i < index; i++) {
            lengh+= rets[i].weight;
        }
        System.out.println("最小生成树的权重之和" + lengh);
        char[] chars = new char[verticeSize];
        chars[0] = 'A';
        chars[1] = 'B';
        chars[2] = 'C';
        chars[3] = 'D';
        chars[4] = 'E';
        chars[5] = 'F';
        chars[6] = 'G';

        for (int i = 0; i < index; i++) {
            System.out.printf("(%s, %s)---> %d \n",chars[rets[i].start], chars[rets[i].end], matrix[rets[i].start][rets[i].end]);
        }
    }

    private int getEnd(int[] edge_temp, int p) {
        int i=p;
        while(edge_temp[i]!=0){
            i=edge_temp[i];
        }
        return i;
    }
}










