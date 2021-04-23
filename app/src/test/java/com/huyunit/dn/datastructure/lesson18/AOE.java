package com.huyunit.dn.datastructure.lesson18;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * author: bobo
 * create time: 2019/1/11 5:36 PM
 * email: jqbo84@163.com
 */
public class AOE<T> {

    @Test
    public void main() {
        VertexNode[] graphAdjList = new VertexNode[9];
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

        List<Integer> list = (List<Integer>) topologicalSort(graphAdjList);
        System.out.print("拓扑序列：");
        for (Integer integer : list) {
            System.out.print(integer + "  ");
        }
        System.out.println();

        //打印关键路径
        criticalPath(graphAdjList);

        System.out.print("顶点最早发生时间: ");
        for (int i = 0; i < LENGHT; i++) {
            System.out.print(etv[i] + "  ");
        }
        System.out.println();

        System.out.print("顶点最晚发生时间: ");
        for (int i = 0; i < LENGHT; i++) {
            System.out.print(ltv[i] + "  ");
        }
        System.out.println();

        System.out.print("边最早发生的时间: ");
        for (int i = 0; i < LENGHT; i++) {
            System.out.print(ete[i] + "  ");
        }
        System.out.println();

        System.out.print("边最晚发生的时间: ");
        for (int i = 0; i < LENGHT; i++) {
            System.out.print(lte[i] + "  ");
        }
        System.out.println();

        //打印关键路径
        System.out.println("关键路径: ");
        for (int i = 0; i < LENGHT; i++) {
            for (EdgeNode e = graphAdjList[i].first; e != null; e = e.next) {
                int index = e.index;
                ete[i] = etv[i];
                lte[i] = ltv[index] - e.weight;
                if (ete[i] == lte[i]) {
                    System.out.println("V(" + graphAdjList[i].data + ")  -> V(" + graphAdjList[index].data + ")  -> E(" + e.weight + ")");
                }
            }
        }

    }

    //根据上面的图来定义数组的长度
    int LENGHT = 9;

    // etv(Earliest Time Of Vertex) 事件最早发生时间，顶点最早发生时间
    int[] etv = new int[LENGHT];
    // ltv(Latest Time Of Vertex)   事件最晚发生时间，顶点最晚发生时间
    int[] ltv = new int[LENGHT];
    // ete(Earliest Time Of Edge)  活动最早开始时间，边最早开始时间
    int[] ete = new int[LENGHT];
    // lte(Latest Time Of Edge)     活动最晚开始时间，边最晚开始时间
    int[] lte = new int[LENGHT];

    int[] stack2 = new int[LENGHT];

    int top2 = 0;

    /**
     * 拓扑排序算法
     *
     * @param graphAdjList 拓扑图数组集
     * @return
     */
    public List<T> topologicalSort(VertexNode[] graphAdjList) {
        int top = 0; //栈顶指针, 对应索引值
        int[] stack = new int[LENGHT];//用来存放入度为0的顶点，数组效率最高，存放顶点的下标索引值
        //循环得到入度为0的所有顶点
        for (int i = 0; i < graphAdjList.length; i++) {
            VertexNode vertexNode = graphAdjList[i];
            if (vertexNode.in == 0) {
                stack[++top] = i;
            }
        }

        //开始算法的逻辑
        List<T> resultList = new ArrayList<>();
        while (top != 0) {
            int getTop = stack[top--];
            resultList.add((T) graphAdjList[getTop].data);
            //保存拓扑序列顺序
            stack2[top2++] = getTop;

            //更新当前输出节点所有的出边（后继顶点）
            for (EdgeNode e = graphAdjList[getTop].first; e != null; e = e.next) {
                int index = e.index;
                //入度减一
                graphAdjList[index].in--;
                if (graphAdjList[index].in == 0) {
                    stack[++top] = index;
                }

                // 1. 计算顶点的最早开始时间
                if (etv[index] < (etv[getTop] + e.weight)) {
                    etv[index] = etv[getTop] + e.weight;
                }
            }
        }
        return resultList;
    }

    /**
     * 关键路径算法
     */
    public void criticalPath(VertexNode[] graphAdjList) {
//        List<T> topologicalSortList = topologicalSort(graphAdjList);
        //初始化顶点最晚发生时间(ltv)都为汇点时间
        for (int i = 0; i < LENGHT; i++) {
            ltv[i] = etv[LENGHT - 1];
        }

        //从汇点开始倒过来计算 顶点的最晚开始时间(ltv)
        while (top2 > 0) {
            int getTop = stack2[--top2];//从汇点开始
            for (EdgeNode e = graphAdjList[getTop].first; e != null; e = e.next) {
                int index = e.index;
                // 2. 计算顶点的最晚开始时间
                if (ltv[index] - e.weight < ltv[getTop]) {
                    ltv[getTop] = ltv[index] - e.weight;
                }
            }
        }

        //通过 etv 和 ltv 计算出ete 和 lte
        for (int i = 0; i < LENGHT; i++) {
            for (EdgeNode e = graphAdjList[i].first; e != null; e = e.next) {
                int index = e.index;
                ete[i] = etv[i];// 3. 边最早的时间 ete，就是顶点最早时间 etv
                lte[i] = ltv[index] - e.weight; // 4. 计算边最晚发生时间，ltv[index]里面已经是选的最小的权重
//                if(ete[i]==lte[i]){
//                    System.out.println(graphAdjList[i].data+" "+graphAdjList[index].data+" "+e.weight);
//                }
            }
        }
    }

    /**
     * 边表节点
     */
    class EdgeNode {
        int index; //用来存放顶点的下标索引值
        int weight; //存放边的权重值
        EdgeNode next;

        public EdgeNode(int index, EdgeNode next) {
            this.index = index;
            this.next = next;
        }

        public EdgeNode(int index, int weight, EdgeNode next) {
            this.index = index;
            this.weight = weight;
            this.next = next;
        }
    }

    /**
     * 顶点表节点
     */
    class VertexNode<T> {
        int in;//入度
        T data;
        EdgeNode first;

        public VertexNode(int in, T data, EdgeNode first) {
            this.in = in;
            this.data = data;
            this.first = first;
        }
    }
}
