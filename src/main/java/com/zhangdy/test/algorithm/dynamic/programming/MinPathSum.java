package com.zhangdy.test.algorithm.dynamic.programming;

public class MinPathSum {

    public static void main(String[] args) {
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
        int[][] grid = new int[3][3];
        grid[0][0] = 1;
        grid[0][1] = 3;
        grid[0][2] = 1;

        grid[1][0] = 1;
        grid[1][1] = 5;
        grid[1][2] = 1;

        grid[2][0] = 4;
        grid[2][1] = 2;
        grid[2][2] = 1;


        int i = minPathSum(grid);
        System.out.println(i);
    }


    public static int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i ==0 && j ==0) {
                    continue;
                }
                int tp = 1000000000;
                if (i > 0) {
                    tp = Integer.min(tp, grid[i-1][j]);
                }
                if (j>0) {
                    tp = Integer.min(tp, grid[i][j-1]);
                }
                grid[i][j] += tp;


            }
        }

        return grid[grid.length-1][grid[0].length-1];

    }
}
