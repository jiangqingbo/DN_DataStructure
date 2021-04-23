package com.huyunit.dn.datastructure.lesson11;

import java.util.LinkedList;

/**
 * author: bobo
 * create time: 2018/12/22 10:24 PM
 * email: jqbo84@163.com
 */
public class Graph {
    //顶点集合
    private int[] vertices;
    //图的边的信息
    private int[][] matrix;

    private int verticesSize;

    public static final int MAX_WEIGHT = Integer.MAX_VALUE;

    private boolean[] isVisited;

    public Graph() {
    }

    public Graph(int verticesSize) {
        this.verticesSize = verticesSize;
        this.vertices = new int[verticesSize];
        this.matrix = new int[verticesSize][verticesSize];
        this.isVisited = new boolean[verticesSize];
        for (int i = 0; i < verticesSize; i++) {
            vertices[i] = i;
        }
    }

    /**
     * 计算V1到V2的权度（路径长度）
     */
    public int getWeight(int v1, int v2) {
        int weight = matrix[v1][v2];
        return (weight == 0 ? 0 : (weight == MAX_WEIGHT ? -1 : weight));
    }

    /**
     * 获取顶点
     *
     * @return
     */
    public int[] getVertices() {
        return vertices;
    }

    /**
     * 获取图的边的信息数组
     * @return
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * 计算出度, 横向计算
     */
    public int getOutDegree(int v) {
        int count = 0;
        for (int i = 0; i < verticesSize; i++) {
            if (matrix[v][i] != 0 && matrix[v][i] != MAX_WEIGHT) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * 计算入度， 纵向计算
     */
    public int getInDegree(int v) {
        int count = 0;
        for (int i = 0; i < verticesSize; i++) {
            if (matrix[i][v] != 0 && matrix[i][v] != MAX_WEIGHT) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * 获取第一个邻接点
     */
    public int getFirstNeightBor(int v){
        for (int i = 0; i < verticesSize; i++) {
            if(matrix[v][i] > 0 && matrix[v][i] != MAX_WEIGHT){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取到顶点 v 的邻接点的index到下一个邻接点
     */
    public int getNextNeightBor(int v, int index){
        for (int i = index + 1; i < verticesSize; i++) {
            if(matrix[v][i] > 0 && matrix[v][i] != MAX_WEIGHT){
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先(很像二叉树的前序排序算法)
     */
    public void dfs(){
        for (int i = 0; i < verticesSize; i++) {
            if(!isVisited[i]){
                System.out.println("Visited Vertice = " + i);
                dfs(i);
            }
        }
    }

    public void dfs(int i){
        isVisited[i] = true;
        int v = getFirstNeightBor(i);
        while (v != -1) {
            if(!isVisited[v]){
                System.out.println("Visited Vertice = " + v);
                dfs(v);
            }
            v = getNextNeightBor(i, v);
        }
    }

    /**
     * 广度优先（有点像二叉树的第四种排序算法）
     */
    public void bfs(){
        for (int i = 0; i < verticesSize; i++) {
            isVisited[i] = false;
        }
        for (int i = 0; i < verticesSize; i++) {
            if(!isVisited[i]){
                isVisited[i] = true;
                System.out.println("visited vertice = " + i);
                bfs(i);
            }
        }
    }

    public void bfs(int i){
        LinkedList<Integer> queue = new LinkedList<>();
        //找到第一个邻接点
        int fn = getFirstNeightBor(i);
        if(fn == -1){
            return;
        }
        if(!isVisited[fn]){
            isVisited[fn] = true;
            System.out.println("visited vertice = " + fn);
            queue.offer(fn);
        }
        //开始把后面的邻接点都入队
        int next = getNextNeightBor(i, fn);
        while (next != -1){
            if(!isVisited[next]){
                isVisited[next] = true;
                System.out.println("visited vertice = " + next);
                queue.offer(next);
            }
            next = getNextNeightBor(i, next);
        }
        //从队列中取出来一个，重复之前的操作
        while (!queue.isEmpty()){
            int p = queue.poll();
            bfs(p);
        }
    }

}
