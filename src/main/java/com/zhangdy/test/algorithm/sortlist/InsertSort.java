package com.zhangdy.test.algorithm.sortlist;

import java.util.Arrays;

public class InsertSort {


    public static void main(String[] args) {
        int[] array = {2,1,3,34,2,2,41,1,23,56,32,788,23,2,2,1000,10001,10002};
        sort(array);
        System.out.printf(Arrays.toString(array));
    }
    public static void sort(int[] array){


        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >0; j--) {
                if (array[j] < array[j-1]) {
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
        
        
        
    }
    
}
