package com.zhangdy.test.algorithm.leetcode;

import org.apache.flink.table.expressions.E;

public class P8StringToIntegerAtoi {
    public static void main(String[] args){
        Solution solution = new P8StringToIntegerAtoi().new Solution();

        int asdsd = solution.myAtoi("  -42");
        System.out.println(asdsd);

    }
    class Solution {
        public int myAtoi(String str) {

            if (str == null || str.trim().length() == 0) {
                return 0;
            }

            str = str.trim();
            StringBuilder s = new StringBuilder();
            boolean first = true;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '-' || c == '+') {
                    if (first) {
                        first = false;
                        s.append(c);
                    }
                } else if ( (c>47&&c<58)) {
                    s.append(c);
                }else  {
                    break;
                }
            }
            if (s.toString().equals("-") ){
                return 0;
            }
            if (s.length() == 0 ) {
                return 0;
            }
            try {
                return Integer.parseInt(s.toString());
            }catch (Exception e) {
                return s.toString().charAt(0)=='-'?Integer.MIN_VALUE:Integer.MAX_VALUE;
            }

        }
    }

}
