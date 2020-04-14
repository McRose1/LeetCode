package DP;

/*  221. Maximum Square
    Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

    Example:
    Input:

    1 0 1 0 0
    1 0 1 1 1
    1 1 1 1 1
    1 0 0 1 0

    Output: 4
 */

/*  DP: Time = O(n^2) Space = O(n^2)
    if dp[i][j] == '1':
    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;
        int max = 0;
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}

/*  Optimized DP: Time = O(n^2) Space = O(n)
    In the previous approach for calculating dp of ith row we are using only the previous element and the (i-1)th row.
    Thus, we don't need 2D dp matrix as 1D dp array will be sufficient for this.
    dp[j] = min(dp[j - 1], dp[j], prev), prev refers to the old dp[j - 1]

        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;
        int max = 0, prev = 0;
        int[] dp = new int[cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                // 这一步是关键，保存了 dp[i - 1][j - 1] 的值
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    // dp[j - 1] 代表 dp[i][j - 1]，dp[j] 代表 dp[i - 1][j]，prev 代表 dp[i - 1][j - 1]
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    max = Math.max(max, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return max * max;
 */

/*  Brute Force: Time = O(n^4) Space = O(1)

        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    int sqlen = 1;
                    boolean flag = true;
                    while (sqlen + i < rows && sqlen + j < cols && flag) {
                        // 横向
                        for (int k = j; k <= sqlen + j; k++) {
                            if (matrix[i + sqlen][k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        // 纵向
                        for (int k = i; k <= sqlen + i; k++) {
                            if (matrix[k][j + sqlen] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            sqlen++;
                        }
                    }
                    if (max < sqlen) {
                        max = sqlen;
                    }
                }
            }
        }
        return max * max;
 */