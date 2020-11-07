package com.zhangdy.test.algorithm.leetcode;

public class P31NextPermutation {

    public static void main(String[] args) {


    }

    public void nextPermutation(int[] nums) {
        if (nums == null) {
            return;
        }
        if (nums.length == 1) {
            return;
        }
        if (nums.length == 2) {
            int num = nums[1];
            nums[1] = nums[0];
            nums[0] = num;
            return;
        }

    


    }


}
