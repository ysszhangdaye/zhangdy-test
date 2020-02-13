package com.zhangdy.test.sort;

import com.alibaba.fastjson.JSON;

public class TestInsertSort {

    public static void main(String[] args) {
        int[] a = {23, 12 , 12, 45, 23, 9};
        int length = a.length;
        int insertNum;//要插入的数
        for(int i=1;i<length;i++){//插入的次数
            System.out.println(JSON.toJSONString(a));
            insertNum=a[i];//要插入的数
            int j = i-1;//已经排序好的序列元素个数
            while (j >= 0 && a[j] > insertNum) {//序列从后到前循环，将大于insertNum的数向后移动一格
                a[j+1]=a[j];//元素移动一格
                j--;
            }
            a[j+1]=insertNum;//将需要插入的数放在要插入的位置。

        }

        System.out.println(JSON.toJSONString(a));


    }

}
