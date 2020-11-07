package com.zhangdy.test.algorithm.dynamic.programming;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

public class LengthOfLIS {

    public static void main(String[] args) {
        int[] array = {10, 9, 2, 5, 3, 7, 101, 18};
        int i = lengthOfLIS(array);
        System.out.println(i);
    }


    public static int lengthOfLIS(int[] array) {
        int length = array.length, ans = 0;
        int[] f = new int[length];
        for (int i = 0; i < length; i++) {
            int tmp = 1;
            for (int j = length - 1; j >= 0; j--) {
                if (array[i] > array[j]) {
                    tmp = Integer.max(tmp, f[j] + 1);
                }
            }
            f[i] = tmp;
            ans = Integer.max(ans, tmp);
            System.out.println(JSON.toJSONString(f));
        }

        return ans;
    }

}
