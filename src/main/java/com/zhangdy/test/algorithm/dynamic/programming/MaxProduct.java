package com.zhangdy.test.algorithm.dynamic.programming;

public class MaxProduct {


    public static void main(String[] args) {




    }


    public static int maxProduct(int[] nums){
        int n = nums.length, ans = nums[0];
        int[] maxNums = new int[n];
        int[] minNums = new int[n];
        maxNums[0] = minNums[0] = nums[0];

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                maxNums[i] = Integer.max(nums[i], maxNums[i - 1] * nums[i]);
                minNums[i] = Integer.min(nums[i], minNums[i - 1] * nums[i]);
            }
            if (nums[i] < 0) {
                maxNums[i] = Integer.max(nums[i], minNums[i - 1] * nums[i]);
                minNums[i] = Integer.min(nums[i], maxNums[i - 1] * nums[i]);
            }
            ans = Integer.max(ans, maxNums[i]);
        }
        return ans;
    }

}
