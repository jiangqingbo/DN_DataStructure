package com.huyunit.dn.datastructure.lesson5;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleTest {
    @Test
    public void addition_isCorrect() throws Exception {
        int[] array=new int[]{1,7,4,9,3,2,6,5,8};
        int key=35;
        quickSort(array,0,array.length-1);

//        System.out.println(binarySearch(array,0,array.length,key));
        for (int i : array) {
            System.out.print(i+" ");
        }
    }
    /**
     * 二分查找
     * 前提：数据是已经排好序
     */
    public static int binarySearch(int[] array,int fromIndex,int toIndex,int key){
        int low=fromIndex;
        int high=toIndex-1;
        while(low<=high){
            int mid=(low+high)/2;//取中间
            int midVal=array[mid];
            if(key>midVal){//去右边找
                low=mid+1;
            }else if(key<midVal){//去左边找
                high=mid-1;
            }else{
                return mid;
            }
        }
        return -(low+1);//low+1表示找不到时停在了第low+1个元素的位置
    }

    /**
     * 快速排序  31  21  59  68  12  40
     *         x=31
     * @param array
     * @param begin
     * @param end
     */
    //
    public static void quickSort(int[] array,int begin,int end){
        if(end-begin<=0) return;
        int x=array[begin];
        int low=begin;//0
        int high=end;//5
        //由于会从两头取数据，需要一个方向
        boolean direction=true;
        L1:
        while(low<high){
            if(direction){//从右往左找
                for(int i=high;i>low;i--){
                    if(array[i]<=x){
                        array[low++]=array[i];
                        high=i;
                        direction=!direction;
                        continue L1;
                    }
                }
                high=low;//如果上面的if从未进入，让两个指针重合
            }else{
                for(int i=low;i<high;i++){
                    if(array[i]>=x){
                        array[high--]=array[i];
                        low=i;
                        direction=!direction;
                        continue L1;
                    }
                }
                low=high;
            }
        }
        //把最后找到的值 放入中间位置
        array[low]=x;
        //开始完成左右两边的操作
        quickSort(array,begin,low-1);
        quickSort(array,low+1,end);
    }

    @Test
    public void test(){
        int[] array=new int[]{2,1,6,4,3,9,8,10,7,5};
//        merge(array,0,4,7);
        mergeSort(array,0,array.length-1);
        for (int i : array) {
            System.out.print(i+" ");
        }
    }

    /**
     * 归并排序
     * @param array
     * @param left
     * @param right
     */
    public static void mergeSort(int array[],int left,int right){
        if(left==right){
            return;
        }else{
            int mid=(left+right)/2;
            mergeSort(array,left,mid);
            mergeSort(array,mid+1,right);
            merge(array,left,mid+1,right);
        }
    }

    /**
     * 归并排序
     *
     * 0    4   7
     * 1  2  5  9 === 3  4  10  11
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] array,int left,int mid,int right){
        int leftSize=mid-left;
        int rightSize=right-mid+1;
        //生成数组
        int[] leftArray=new int[leftSize];
        int[] rightArray=new int[rightSize];
        //填充数据
        for(int i=left;i<mid;i++){
            leftArray[i-left]=array[i];
        }
        for(int i=mid;i<=right;i++){
            rightArray[i-mid]=array[i];
        }
        //合并
        int i=0;
        int j=0;
        int k=left;
        while(i<leftSize && j<rightSize){
            if(leftArray[i]<rightArray[j]){
                array[k]=leftArray[i];
                k++;i++;
            }else{
                array[k]=rightArray[j];
                k++;j++;
            }
        }
        while(i<leftSize){
            array[k]=leftArray[i];
            k++;i++;
        }
        while(j<rightSize){
            array[k]=rightArray[j];
            k++;j++;
        }
    }
}








