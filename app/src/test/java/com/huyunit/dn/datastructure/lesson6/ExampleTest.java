package com.huyunit.dn.datastructure.lesson6;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleTest {
    @Test
    public void addition_isCorrect() throws Exception {
        SearchBinaryTree tree=new SearchBinaryTree();
        //5  2  7  3  4  1  6
        int[] array=new int[]{5,2,7,3,4,1,6};

        for (int i : array) {
            tree.put(i);
        }
        tree.midOrderTraverse(tree.root);

        for(int i=0;i<array.length-1;i++){
            SearchBinaryTree.TreeNode node=tree.searchNode(array[i]);
            tree.delNode(node);
        }

        System.out.println();
        System.out.println("----");
        tree.midOrderTraverse(tree.root);
//        System.out.println(node.data);

    }

    @Test
    public void testBinarySortTree(){
        BinarySortTree<Integer> tree = new BinarySortTree();
        //5  2  7  3  4  1  6
        int[] array=new int[]{5,2,7,3,4,1,8,6,9};
        for (int i = 0; i < array.length; i++) {
            int i1 = array[i];
            tree.put(i1);
        }
        tree.middleOrderTraseval();

        System.out.println();

        tree.deleteNode(tree.get(7));

        tree.middleOrderTraseval();

        System.out.println();

        for (int i : array) {
            tree.middleOrderTraseval();
            System.out.println("------------------------------");
            tree.deleteNode(tree.get(i));
        }

        System.out.println();
        for (int i = 0; i < array.length; i++) {
            int i1 = array[i];
            tree.put(i1);
        }
        tree.middleOrderTraseval();

    }

    @Test
    public void testOtherBinaryTree(){
        OtherBinaryTree<Integer> tree = new OtherBinaryTree();
        int[] array = new int[]{5,2,7,3,4,1,8};
        for (int i : array) {
            tree.add(i);
        }

        tree.midOrderTraseval();

        System.out.println();

        tree.delete(5);



//        for (int i : array) {
//            tree.midOrderTraseval();
//            System.out.println("-----------------------");
//            tree.delete(i);
//        }
//        for (int i : array) {
//            tree.add(i);
//        }
        tree.midOrderTraseval();
    }
}








