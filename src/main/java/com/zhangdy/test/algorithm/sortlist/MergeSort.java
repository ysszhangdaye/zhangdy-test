package com.zhangdy.test.algorithm.sortlist;

import java.util.Arrays;

public class MergeSort {


    public static void main(String[] args) {
        int[] array = {2, 1, 3, 34, 2, 2, 41, 1, 23, 56, 32, 788, 23, 2, 2, 1000, 10001, 10002};
        sort(array, 0, array.length - 1);
        System.out.printf(Arrays.toString(array));
    }

    public static void sort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            sort(a, low, mid);
            sort(a, mid + 1, high);
            //左右归并
            merge(a, low, mid, high);
        }
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        // 复制一份原来的数组
        int[] copy = nums.clone();

        // 定义一个 k 指针表示从什么位置开始修改原来的数组，i 指针表示左半边的起始位置，j 表示右半边的起始位置
        int k = low, i = low, j = mid + 1;

        while (k <= high) {
            if (i > mid) {
                nums[k++] = copy[j++];
            } else if (j > high) {
                nums[k++] = copy[i++];
            } else if (copy[j] < copy[i]) {
                nums[k++] = copy[j++];
            } else {
                nums[k++] = copy[i++];
            }
        }
    }
}
