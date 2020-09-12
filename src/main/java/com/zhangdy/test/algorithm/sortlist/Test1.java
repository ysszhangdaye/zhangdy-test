package com.zhangdy.test.algorithm.sortlist;

public class Test1 {

    public static void main(String[] args) {

        StringBuffer result = new StringBuffer();
        String[] strs = {"flower","flow","flight"};
        char tmp = 0;

        int size = strs[0].length();
        a : for (int i = 0; i < size; i++) {

            for (int i1 = 0; i1 < strs.length; i1++) {
                if (strs[i1].length() < size) {
                    size = strs[i1].length();
                }
                strs[i1].charAt(i);
                if (tmp == 0) {
                    tmp = strs[i1].charAt(i);
                    continue;
                }
                if (tmp != strs[i1].charAt(i)) {
                    break a;
                }
            }
            result.append(tmp);
            tmp = 0;
        }

        System.out.println(result.toString());
    }

}
