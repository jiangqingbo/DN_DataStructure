package com.huyunit.dn.datastructure.lesson1;

import org.junit.Test;

/**
 * author: bobo
 * create time: 2018/12/11 10:32 AM
 * email: jqbo84@163.com
 */
public class ExampleTest {

    @Test
    public void test(){
        //做 a b 算法
        int a = 5;
        int b = 6;
        //1. 可读性做好
        int t = a; a = b; b = t;
        System.out.println("1.a = " + a + ", b = " + b);

        //2. 加减法
        a = a + b; //11
        b = a - b; //11-6=5
        a = a - b; //11-5=6
        System.out.println("2.a = " + a + ", b = " + b);

        //3. 性能最优（没有可读性）  无人机、跑步机
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("3.a = " + a + ", b = " + b);

    }

    public int testSearch(int[] array, int des){
        for (int i = 0; i < array.length; i++) {
            if(array[i] == des) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 顺序表插入一个值
     *
     * 优点：
     *    尾插效率高，支持随机访问。
     * 缺点：
     *    中间插入或者删除效率低。
     * 应用：
     *   数组
     *    ArrayList
     *
     * @param array
     * @param x
     * @param index
     */
    public void testInsert(int[] array, int x, int index){
        for (int i = array.length; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = x;
    }

    public void testDelete(int[] array, int index){
        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = 0;  // C语言：\0 填充
    }

    @Test
    public void testCards(){
        Cards[] array=new Cards[]{
                new Cards(3,2),new Cards(2,9),new Cards(1,7),
                new Cards(3,5),new Cards(4,3)
        };
//        Arrays.sort(array);//100行以上
        //蛮力法，这种算法，在数据量<=5 的时候，效率是最高的
        bubbleSort(array);
        for (Cards cards : array) {
            System.out.println(cards.toString());
        }
    }

    @Test
    public void testSort(){
        int[] array=new int[]{3,2,5,8,1,9,4,6,7};

        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println("\n");
        bubbleSort(array);
        selectSort(array);
        for (int i : array) {
            System.out.print(i+" ");
        }

    }

    /**
     * 蛮力法，冒泡排序
     * @param array
     */
    public static void bubbleSort(int[] array){
        //3 1 5 8 2 9 4 6 7    n*(n-1)/2    n
        for(int i=array.length-1;i>0;i--) {
            boolean flag=true;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag=false;
                }
            }
            if(flag){
                break;
            }
        }
    }

    /**
     * 蛮力法，冒泡排序
     * @param array
     */
    public static void bubbleSort(Cards[] array){  //3-5个数据  78
        //1 2 3 4 5 9 4 6 7    n*(n-1)/2   n
        for(int i=array.length-1;i>0;i--) {
            boolean flag=true;
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[j+1])>0) {
                    Cards temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag=false;
                }
            }
            if(flag){
                break;
            }
        }
    }

    /**
     * 选择排序法
     * @param array
     */
    public static void selectSort(int[] array){
        for(int i=0;i<array.length-1;i++) {
            int index = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[index]) {
                    index = j;
                }
            }
            //{1,2,5,8,3,9,4,6,7};
            if(index!=i) {//如果已经是最小的，就不需要交换
                int temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }
    }

}
