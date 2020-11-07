package com.zhangdy.test.algorithm.leetcode;

import com.alibaba.dubbo.common.utils.Stack;

public class P129Test {


    public static void main(String[] args) {

    }
    int res = 0;
    public int sumNumbers(TreeNode root) {
         sumNumbers(root, 0);
         return res;
    }

    private void sumNumbers(TreeNode root, int num){
        if (root == null) {
            return ;
        }

        num = num * 10 + root.val;
        if(root.left == null && root.right == null) {
            res += num;
        }
        sumNumbers(root.left, num);
        sumNumbers(root.right, num);
    }




    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
