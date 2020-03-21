package DP;

/*  64. Minimum Path Sum
    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right
    which minimizes the sum of all numbers along its path.

    Note: You can only move either down or right at any point in time.

    Example:
    Input:
    [
      [1,3,1],
      [1,5,1],
      [4,2,1]
    ]
    Output: 7
    Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
//  DP: Time = O(m*n) Space = O(m*n)
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int r = 1; r < m; r++) {
            dp[r][0] = dp[r - 1][0] + grid[r][0];
        }

        for (int c = 1; c < n; c++) {
            dp[0][c] = dp[0][c - 1] + grid[0][c];
        }

        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[r][c] = Math.min(dp[r - 1][c], dp[r][c - 1]) + grid[r][c];
            }
        }
        return dp[m - 1][n - 1];
    }
}
