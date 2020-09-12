package com.zhangdy.test.algorithm.tree;

import com.alibaba.fastjson.JSON;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.util.*;

public class BinaryTest {


    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root = createTree(root, 0);


//        traverse(root);
        List<Double> doubles = leetcode637Traverse(root);


        System.out.println(doubles);
    }




    public static void traverse(TreeNode root){
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
//        queue.offer(null)
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            System.out.println(poll.val);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }


    }

    public static List<Double> leetcode637Traverse(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        double sum = 0;
        int size = 0;


        List<Double> result  = new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNode poll = queue.pollFirst();
            if (poll == null) {
                result.add(sum / size);
                if(!queue.isEmpty()){
                    queue.add(null);
                }
            } else {
                sum += poll.val;
                size++;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }

        }
        return result;

    }


    public static class TreeValue {
        public static int index = 0;
        public static final int[] TREE_VALUE = new int[]{1,2,3,0,4,5,0,0,6,0,0,7,0,0,8,0,9,10,0,0,0};
    }


    public static TreeNode createTree(TreeNode node, int i) {
        if (0 == TreeValue.TREE_VALUE[i]) {
            return null;
        } else {
            node.val = TreeValue.TREE_VALUE[i];
        }

        TreeNode leftChild = new TreeNode();
        node.left = createTree(leftChild, ++TreeValue.index);
        TreeNode rightChild = new TreeNode();
        node.right = createTree(rightChild, ++TreeValue.index);

        return node;
    }



    @NoArgsConstructor
    @Getter
    public static  class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val =  val;
        }
    }

}
