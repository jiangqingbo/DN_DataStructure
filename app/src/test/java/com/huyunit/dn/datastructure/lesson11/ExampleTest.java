package com.huyunit.dn.datastructure.lesson11;

import org.junit.Test;

import static com.huyunit.dn.datastructure.lesson11.Graph.MAX_WEIGHT;

/**
 * author: bobo
 * create time: 2018/12/24 4:52 PM
 * email: jqbo84@163.com
 */
public class ExampleTest {

    @Test
    public void testGraph(){
        Graph graph=new Graph(5);
        int[] a0=new int[]{0,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,6};
        int[] a1=new int[]{9,0,3,MAX_WEIGHT,MAX_WEIGHT};
        int[] a2=new int[]{2,MAX_WEIGHT,0,5,MAX_WEIGHT};
        int[] a3=new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0,1};
        int[] a4=new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0};
        graph.getMatrix()[0]=a0;
        graph.getMatrix()[1]=a1;
        graph.getMatrix()[2]=a2;
        graph.getMatrix()[3]=a3;
        graph.getMatrix()[4]=a4;
        System.out.println("--------------- 入度出度 -----------------");
        System.out.println(graph.getInDegree(2));
        System.out.println(graph.getOutDegree(2));

//        Graph graph = new Graph(5);
//        int[] v0 = new int[]{0, 1, 1, MAX_WEIGHT, MAX_WEIGHT};
//        int[] v1 = new int[]{MAX_WEIGHT, 0, MAX_WEIGHT, 1, MAX_WEIGHT};
//        int[] v2 = new int[]{MAX_WEIGHT, MAX_WEIGHT, 0, MAX_WEIGHT, MAX_WEIGHT};
//        int[] v3 = new int[]{1, MAX_WEIGHT, MAX_WEIGHT, 0, MAX_WEIGHT};
//        int[] v4 = new int[]{MAX_WEIGHT, MAX_WEIGHT, 1, MAX_WEIGHT, 0};
//        graph.getMatrix()[0] = v0;
//        graph.getMatrix()[1] = v1;
//        graph.getMatrix()[2] = v2;
//        graph.getMatrix()[3] = v3;
//        graph.getMatrix()[4] = v4;
//        System.out.println("--------------- 深度优先 -----------------");
//        graph.dfs();
//        System.out.println("--------------- 广度优先 -----------------");
//        graph.bfs();

    }
}
