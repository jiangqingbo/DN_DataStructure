package com.huyunit.dn.datastructure.lesson17;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * AOV网与拓扑排序
 * <p>
 * author: bobo
 * create time: 2019/1/9 3:42 PM
 * email: jqbo84@163.com
 */
public class TopologicalSort<T> {

    @Test
    public void main() {
        VertexNode[] graphAdjList = new VertexNode[15];
        EdgeNode a = new EdgeNode(3, null);
        EdgeNode a2 = new EdgeNode(2, a);
        EdgeNode a3 = new EdgeNode(1, a2);
        graphAdjList[0] = new VertexNode(0, 1, a3);
        graphAdjList[1] = new VertexNode(2, 2, null);
        EdgeNode b1 = new EdgeNode(9, null);
        EdgeNode b2 = new EdgeNode(8, b1);
        EdgeNode b3 = new EdgeNode(6, b2);
        EdgeNode b4 = new EdgeNode(5, b3);
        graphAdjList[2] = new VertexNode(2, 3, b4);
        EdgeNode c1 = new EdgeNode(7, null);
        EdgeNode c2 = new EdgeNode(9, c1);
        EdgeNode c3 = new EdgeNode(6, c2);
        graphAdjList[3] = new VertexNode(2, 4, c3);
        graphAdjList[4] = new VertexNode(1, 5, null);
        graphAdjList[5] = new VertexNode(1, 6, null);
        graphAdjList[6] = new VertexNode(3, 7, null);
        graphAdjList[7] = new VertexNode(1, 8, null);
        graphAdjList[8] = new VertexNode(1, 9, null);
        EdgeNode d1 = new EdgeNode(10, null);
        EdgeNode d2 = new EdgeNode(6, d1);
        graphAdjList[9] = new VertexNode(2, 10, d2);
        EdgeNode e1 = new EdgeNode(11, null);
        graphAdjList[10] = new VertexNode(1, 11, e1);
        graphAdjList[11] = new VertexNode(1, 12, null);
        EdgeNode f1 = new EdgeNode(3, null);
        EdgeNode f2 = new EdgeNode(13, f1);
        graphAdjList[12] = new VertexNode(0, 13, f2);
        EdgeNode g1 = new EdgeNode(14, null);
        EdgeNode g2 = new EdgeNode(1, g1);
        EdgeNode g3 = new EdgeNode(2, g2);
        graphAdjList[13] = new VertexNode(1, 14, g3);
        EdgeNode h1 = new EdgeNode(4, null);
        graphAdjList[14] = new VertexNode(1, 15, h1);
        //测试
        List<Integer> result = (List<Integer>) topologicalSort(graphAdjList);
        for (int i = 0; i < result.size(); i++) {
            System.out.print("  " + result.get(i));
        }
    }

    /**
     * 拓扑排序算法
     *
     * @param graphAdjList 拓扑图数组集
     * @return
     */
    public List<T> topologicalSort(VertexNode[] graphAdjList) {
        int top = 0; //栈顶指针, 对应索引值
        int[] stack = new int[15];//用来存放入度为0的顶点，数组效率最高，存放顶点的下标索引值
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
            //更新当前输出节点所有的出边（后继顶点）
            for (EdgeNode e = graphAdjList[getTop].first; e != null; e = e.next) {
                int index = e.index;
                //入度减一
                graphAdjList[index].in--;
                if (graphAdjList[index].in == 0) {
                    stack[++top] = index;
                }
            }
        }
        return resultList;
    }

    /**
     * 边表节点
     */
    class EdgeNode {
        int index; //用来存放顶点的下标索引值
        EdgeNode next;

        public EdgeNode(int index, EdgeNode next) {
            this.index = index;
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
