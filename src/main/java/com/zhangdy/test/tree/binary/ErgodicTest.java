package com.zhangdy.test.tree.binary;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;

public class ErgodicTest {

    /**
     * 构建二叉树
     * @param list
     * @return
     */
    public static TreeNode buildBinaryTree(LinkedList<Integer> list){
        TreeNode node = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        Integer data = list.removeFirst();
        if(data != null){
            node            = new TreeNode(data);
            node.leftChild  = buildBinaryTree(list);
            node.rightChild = buildBinaryTree(list);
        }
        return node;
    }

    /**
     * 谦虚遍历
     * @param node
     */
    public static void preOrderTraverse(TreeNode node){
        if (node == null) {
            return;
        }
        System.out.print(node.data + ",");
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    /**
     * 中序遍历
     * @param node
     */
    public static void midOrderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        midOrderTraverse(node.leftChild);
        System.out.print(node.data + ",");
        midOrderTraverse(node.rightChild);
    }

    /**
     * 后序遍历
     * @param node
     */
    public static void postOrderTraverse(TreeNode node){
        if (node == null) {
            return;
        }
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.print(node.data + ",");
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,   null,8,null,4}));
        TreeNode treeNode = buildBinaryTree(list);

        System.out.println("前序遍历");
        preOrderTraverse(treeNode);
        System.out.println();


        System.out.println("中序遍历");
        midOrderTraverse(treeNode);
        System.out.println();

        System.out.println("后序遍历");
        postOrderTraverse(treeNode);
        System.out.println();

    }


    @Getter
    public static class TreeNode {
        int data;
        TreeNode leftChild, rightChild;
        public TreeNode(int data){
            this.data = data;
        }

    }

}
