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
/*  DP（bottom-up）: Time = O(n^2) Space = O(n)
    因为我们是一层一层的更新，更新当前层只需要上一层的信息，所以我们只需要一维数组就可以了
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
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
    }
}

/*  my version: Space = O(n)；时间复杂度比标准答案要高一些

        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int m = triangle.get(n - 1).size();

        if (n == 1) {
            return triangle.get(0).get(0);
        }

        int[] dp = new int[m];

        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    dp[j] = dp[i - 1] + triangle.get(i).get(j);
                } else if (j == 0) {
                    dp[j] = dp[0] + triangle.get(i).get(j);
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int num : dp) {
            min = Math.min(min, num);
        }
        return min;
 */

/*  my version: Space = O(n^2)

        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int m = triangle.get(n - 1).size();

        if (n == 1) {
            return triangle.get(0).get(0);
        }

        int[][] dp = new int[n][m];

        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][0] + triangle.get(i).get(j);
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][i - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int num : dp[n - 1]) {
            min = Math.min(min, num);
        }
        return min;
 */

/*  DP（top-down）: Time = O(n^2) Space = (n^2)
    dp[row][col] = Min(dp[row - 1][col - 1], dp[row - 1][col]) + triangle[row][col]
    Corner Case: 左边界和右边界

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
 */