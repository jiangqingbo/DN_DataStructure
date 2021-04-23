package com.huyunit.dn.datastructure.lesson18;

import org.junit.Test;

/**
 * Created by Jett on 2019/1/7.
 */

public class AOV网与拓扑排序 {
    VertexNode[] graphAdjList = new VertexNode[9];
    //etv(Earliest Time Of Vertex) 事件最早发生时间，顶点最早发生时间
    int[] etv = new int[9];
    //ltv(Latest Time Of Vertex)   事件最晚发生时间，顶点最晚发生时间
    int[] ltv = new int[9];
    //ete(Earliest Time Of Edge)  活动最早开始时间，边最早开始时间
    int[] ete = new int[9];
    //lte(Latest Time Of Edge)     活动最晚开始时间，边最晚开始时间
    int[] lte = new int[9];

    int[] stack2 = new int[9];
    int top2 = 0;

    @Test
    public void test() {
        EdgeNode a = new EdgeNode(3, 5, null);
        EdgeNode a2 = new EdgeNode(2, 4, a);
        EdgeNode a3 = new EdgeNode(1, 6, a2);
        graphAdjList[0] = new VertexNode(0, 1, a3);

        EdgeNode b1 = new EdgeNode(4, 1, null);
        graphAdjList[1] = new VertexNode(1, 2, b1);

        EdgeNode c1 = new EdgeNode(4, 1, null);
        graphAdjList[2] = new VertexNode(1, 3, c1);

        EdgeNode d1 = new EdgeNode(5, 2, null);
        graphAdjList[3] = new VertexNode(1, 4, d1);

        EdgeNode e1 = new EdgeNode(7, 5, null);
        EdgeNode e2 = new EdgeNode(6, 7, e1);
        graphAdjList[4] = new VertexNode(2, 5, e2);

        EdgeNode f2 = new EdgeNode(7, 4, null);
        graphAdjList[5] = new VertexNode(1, 6, f2);

        EdgeNode f3 = new EdgeNode(8, 2, null);
        graphAdjList[6] = new VertexNode(1, 7, f3);

        EdgeNode f4 = new EdgeNode(8, 4, null);
        graphAdjList[7] = new VertexNode(2, 8, f4);

        graphAdjList[8] = new VertexNode(2, 9, null);
        criticalPath();
    }

    /**
     * 拓扑排序
     */
    public void topologicalSort() {
        int top = 0;//栈顶指针
        int[] stack = new int[9];//用来存放入度为0的顶点
        //循环得到入度为0的所有顶点
        for (int i = 0; i < graphAdjList.length; i++) {
            if (graphAdjList[i].in == 0) {
                stack[++top] = i;
            }
        }
        //开始算法的逻辑
        while (top != 0) {
            int getTop = stack[top--];//出栈一个
//            System.out.print("  "+graphAdjList[getTop].data);
            //保存拓扑序列顺序
            stack2[top2++] = getTop;

            //更新当前输出节点所有的出边（后继顶点）
            for (EdgeNode e = graphAdjList[getTop].first; e != null; e = e.next) {
                int k = e.data;
                //入度减一
                graphAdjList[k].in--;
                if (graphAdjList[k].in == 0) {
                    stack[++top] = k;
                }
                //计算顶点的最早开始时间
                if ((etv[getTop] + e.weight) > etv[k]) {
                    etv[k] = etv[getTop] + e.weight;
                }

            }

        }


    }

    public void criticalPath() {
        topologicalSort();
        //初始化ltv都为汇点时间
        for (int i = 0; i < 9; i++) {
            ltv[i] = etv[8];
        }
        //从汇点开始倒过来计算ltv
        while (top2 > 0) {
            int getTop = stack2[--top2];//从汇点开始
            for (EdgeNode e = graphAdjList[getTop].first; e != null; e = e.next) {
                int k = e.data;
                if (ltv[k] - e.weight < ltv[getTop]) {
                    ltv[getTop] = ltv[k] - e.weight;
                }
            }
        }
        //通过etv和ltv计算出ete和lte
        for (int i = 0; i < 9; i++) {
            for (EdgeNode e = graphAdjList[i].first; e != null; e = e.next) {
                int k = e.data;
                ete[i] = etv[i];//边的最早时间，就是顶点的最早时间
                lte[i] = ltv[k] - e.weight;//ltv[k]里面已经是选的最小的权重
                if (ete[i] == lte[i]) {
                    System.out.println(graphAdjList[i].data + " " + graphAdjList[k].data + " " + e.weight);
                }

            }
        }

    }


    /**
     * 边表结点
     */
    class EdgeNode {
        int data;
        int weight;
        EdgeNode next;

        public EdgeNode(int data, int weight, EdgeNode next) {
            this.data = data;
            this.weight = weight;
            this.next = next;
        }

        public EdgeNode(int data, EdgeNode next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 顶点表结点
     */
    class VertexNode {
        int in;//入度
        int data;
        EdgeNode first;

        public VertexNode(int in, int data, EdgeNode first) {
            this.in = in;
            this.data = data;
            this.first = first;
        }
    }
}

