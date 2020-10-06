package DP;

/*  931. Minimum Falling Path Sum
    Given a square array of integers A, we want the minimum sum of a falling path through A.
    A falling path starts at any element in the first row, and chooses one element from each row.
    The next row's choice must be in a column that is different from the previous row's column by at most one.

    Example 1:
    Input: [[1,2,3],[4,5,6],[7,8,9]]
    Output: 12
    Explanation:
    The possible falling paths are:
    [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
    [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
    [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
    The falling path with the smallest sum is [1,4,7], so the answer is 12.

    Constraints:
        o 1 <= A.length == A[0].length <= 100
        o -100 <= A[i][j] <= 100
 */

/*  二维 DP: Time = O(n^2) Space = O(n^2)
    从上往下
 */
public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        int[][] dp = new int[n][n];

        System.arraycopy(A[0], 0, dp[0], 0, n);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + A[i][j];
                } else if (j == n - 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + A[i][j];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]), dp[i - 1][j]) + A[i][j];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, dp[n - 1][j]);
        }
        return min;
    }
}

/*  一维 DP: Time = O(n^2) Space = O(n)
    从下往上

        int n = A.length;
        for (int r = n - 2; r >= 0; r--) {
            for (int c = 0; c < n; c++) {
                int best = A[r + 1][c];
                if (c > 0) {
                    best = Math.min(best, A[r + 1][c - 1]);
                }
                if (c + 1 < n) {
                    best = Math.min(best, A[r + 1][c + 1]);
                }
                A[r][c] += best;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int x : A[0]) {
            min = Math.min(min, x);
        }
        return min;
 */