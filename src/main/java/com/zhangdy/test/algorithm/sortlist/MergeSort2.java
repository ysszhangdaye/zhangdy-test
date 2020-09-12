package com.zhangdy.test.algorithm.sortlist;

import java.util.Arrays;

public class MergeSort2 {

    public static void main(String[] args) {
        int[] array = {2, 1, 3, 34, 2, 2, 41, 1, 23, 56, 32, 788, 23, 2, 2, 1000, 10001, 10002};
        sort(array, 0, array.length - 1);
        System.out.printf(Arrays.toString(array));
    }


    public static void sort(int[] array, int low, int high) {

        int mid = (low + high) / 2;
        if (low < high) {
            sort(array, low, mid);
            sort(array, mid + 1, high);
            merge(array, low, mid, high);
        }


    }

    public static void merge(int[] array, int low, int mid, int high) {

        int[] copy = array.clone();

        int k = low, i = low, j = mid + 1;

        while (k <= high) {
            if (i > mid ) {
                // 左边的全部排好序 将右边的复制到左边
                array[k++] = copy[j++];
            } else if (j > high) {
                // 右边的全部排好序，将左边的复制到右边
                array[k++] = copy[i++];
            } else if (copy[j] < copy[i]) {
                array[k++] = copy[j++];
            } else {
                array[k++] = copy[i++];
            }


        }


    }


}
