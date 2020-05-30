package DP;

/*  279. Perfect Squares
    Given a positive integer n, find the least number of perfect square numbers
    (for example, 1, 4, 9, 16, ...) which sum to n.

    Example 1:
    Input: n = 12
    Output: 3

    Example 2:
    Input: n = 13
    Output: 2
 */

import java.util.Arrays;
/*  DP: Time = O(n) Space = O(1)
    dp[i] = min(dp[i], dp[i - j * j] + 1) (j * j <= i)
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // 初始化
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        // 1 -> n
        for (int i = 2; i <= n; i++) {
            // 1、4、9、16...
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
