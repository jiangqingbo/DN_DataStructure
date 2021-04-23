package com.huyunit.dn.datastructure.lesson8;

import org.junit.Test;

import java.util.ArrayList;

/**
 * author: bobo
 * create time: 2018/12/17 11:25 AM
 * email: jqbo84@163.com
 */
public class ExampleTest {

    @Test
    public void testHuffmanTree(){
        //添加结点数据
        ArrayList<HuffmanTree.TreeNode> list = new ArrayList<>();
        HuffmanTree.TreeNode<String> node = new HuffmanTree.TreeNode("good", 50);
        list.add(node);
        list.add(new HuffmanTree.TreeNode("morning", 10));

        HuffmanTree.TreeNode<String> node2 =new HuffmanTree.TreeNode("afternoon", 20);
        list.add(node2);
        list.add(new HuffmanTree.TreeNode("hell", 110));
        list.add(new HuffmanTree.TreeNode("hi", 200));

        HuffmanTree tree = new HuffmanTree();
        tree.createHuffmanTree(list);
        tree.showHuffman(tree.root);
        System.out.println("tree.getHuffmanCode(node); = " + tree.getHuffmanCode(node));
        System.out.println("tree.getHuffmanCode(node2) = " + tree.getHuffmanCode(node2));;
    }
}
