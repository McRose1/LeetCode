package DP;

/*  363. Max Sum of Rectangle No Larger Than K
    Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

    Example:
    Input: matrix = [[1,0,1],[0,-2,3]], k = 2
    Output: 2
    Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
                 and 2 is the max number no larger than k (k = 2).

    Note:
    1. The rectangle inside the matrix must have an area > 0.
    2. What if the number of rows is much larger than the number of columns?
 */

import java.util.TreeSet;

/*
    1. 求 matrix 中和最大的那个矩形，返回最大值
    2. 一维 array，找出其中连续的一段，其和最大，但是不大于 k
 */
public class MaxSumofRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int row = matrix.length;
        int col = matrix[0].length;
        int res = Integer.MIN_VALUE;

        for (int left = 0; left < col; left++) {
            int[] sums = new int[row];
            for (int right = left; right < col; right++) {
                for (int i = 0; i < row; i++) {
                    sums[i] += matrix[i][right];
                }
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int cur = 0;
                for (int sum : sums) {
                    cur += sum;
                    Integer num = set.ceiling(cur - k);
                    if (num != null) {
                        res = Math.max(res, cur - num);
                    }
                    set.add(cur);
                }
            }
        }
        return res;
    }
}
