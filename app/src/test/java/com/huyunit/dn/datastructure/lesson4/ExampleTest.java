package com.huyunit.dn.datastructure.lesson4;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleTest {
    @Test
    public void addition_isCorrect() throws Exception {
        BinarayTree binarayTree = new BinarayTree("A");
        binarayTree.createTree();
        binarayTree.midOrderTraverse(binarayTree.root);
        binarayTree.preOrderTraverse(binarayTree.root);
        binarayTree.postOrderTraverse(binarayTree.root);
    }
}