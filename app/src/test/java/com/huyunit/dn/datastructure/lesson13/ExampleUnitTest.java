package com.huyunit.dn.datastructure.lesson13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    public static final int I = 100;
    //邻接距阵
    public static int[][] d = new int[][]{
            {0, 2, 1, 5},
            {2, 0, 4, I},
            {1, 4, 0, 3},
            {5, I, 3, 0}
    };
    public static int[][] p=new int[][]{
            {0,1,2,3},
            {0,1,2,3},
            {0,1,2,3},
            {0,1,2,3}
    };
    public static void floyd(){
        for (int k = 0; k < d.length; k++) {
            for(int i=0;i<d.length;i++){
                for (int j = 0; j < d.length; j++) {
                    if(d[i][j]>d[i][k]+d[k][j]){
                        d[i][j]=d[i][k]+d[k][j];
                        //记录下路径
                        p[i][j]=p[i][k];
                    }
                }
            }
        }
    }

    public static void printShortPath(){
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                System.out.println("V"+i+"->V"+j+" weight:"+d[i][j]+" path:"+i);
                int k=p[i][j];//取到当前位置是第几次推出来的
                while(k!=j){
                    System.out.println("->"+k);
                    k=p[k][j];
                }
                System.out.println();
            }
        }
    }
    @Test
    public void test(){
        floyd();
//        printArray(d);
//        System.out.println("+++++=+++========");
//        printArray(p);
        printShortPath();
    }
}