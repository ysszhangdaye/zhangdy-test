package com.zhangdy.test.algorithm.sortlist;

import java.util.Arrays;

public class MergeSort1 {

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
            if (i > mid) {
                // 左边已经全部排好序 左半边的数都处理完毕，只剩下右半边的数，只需要将右半边的数逐个拷贝过去
                array[k++] = copy[j++];
            } else if (j > high) {
                // 右半边的数都处理完毕，只剩下左半边的数，只需要将左半边的数逐个拷贝过去就好
                array[k++] = copy[i++];
            } else if (copy[j] < copy[i]) {
                // 右边的大于左边的 ---> 将右边的复制到左边 右边的数小于左边的数，将右边的数拷贝到合适的位置，j 指针往前移动一位。
                array[k++] = copy[j++];
            } else {
                // 左边的数小于右边的数，将左边的数拷贝到合适的位置，i 指针往前移动一位
                array[k++] = copy[i++];
            }



        }


    }


}
