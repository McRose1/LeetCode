package DP;

/*  120. Triangle
    Given an triangle, find the minimum path sum from top to bottom.
    Each step you may move to adjacent numbers on the row below.

    For example, given the following triangle
    [
         [2],
        [3,4],
       [6,5,7],
      [4,1,8,3]
    ]
    The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

    Note:
    Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */

import java.util.List;
/*  DP（top-down）: Time = O(n^2) Space = (n^2)
    dp[row][col] = Min(dp[row - 1][col - 1], dp[row - 1][col]) + triangle[row][col]
    Corner Case: 左边界和右边界
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int cols = triangle.get(rows - 1).size();
        int[][] dp = new int[rows][cols];
        dp[0][0] = triangle.get(0).get(0);
        for (int row = 1; row < rows; row++) {
            List<Integer> curRow = triangle.get(row);
            int col = 0;
            // 计算左边界
            dp[row][col] = dp[row - 1][col] + curRow.get(col);
            col++;
            // 计算中间
            for (; col < curRow.size() - 1; col++) {
                dp[row][col] = Math.min(dp[row - 1][col - 1], dp[row - 1][col]) + curRow.get(col);
            }
            // 计算右边界
            dp[row][col] = dp[row - 1][col - 1] + curRow.get(col);
        }
        int min = Integer.MAX_VALUE;
        for (int col = 0; col < cols; col++) {
            min = Math.min(min, dp[rows - 1][col]);
        }
        return min;
    }
}

/*  DP（bottom-up）: Time = O(n^2) Space = O(n)
    因为我们是一层一层的更新，更新当前层只需要上一层的信息，所以我们只需要一维数组就可以了

        int rows = triangle.size();
        int cols = triangle.get(rows - 1).size();
        int[] dp = new int[cols];
        dp[0] = triangle.get(0).get(0);
        for (int row = 1; row < rows; row++) {
            List<Integer> curRow = triangle.get(row);
            int col = curRow.size() - 1;
            // 计算右边界
            dp[col] = dp[col - 1] + curRow.get(col);
            col--;
            // 计算中间
            for (; col > 0; col--) {
                dp[col] = Math.min(dp[col - 1], dp[col]) + curRow.get(col);
            }
            // 计算左边界
            dp[col] = dp[col] + curRow.get(col);
        }
        int min = Integer.MAX_VALUE;
        for (int col = 0; col < cols; col++) {
            min = Math.min(min, dp[col]);
        }
        return min;
 */
