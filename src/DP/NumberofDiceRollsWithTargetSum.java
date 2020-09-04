package DP;

/*  1155. Number of Dice Rolls With Target Sum
    You have a dice, and each die has f faces numbered 1, 2, ..., f.
    Return the number of possible ways (out of f^d total ways) modulo 10^9 + 7 to roll the dice
    so the sum of the face up numbers equals target.

    Example 1:
    Input: d = 1, f = 6, target = 3
    Output: 1
    Explanation:
    You throw one die with 6 faces.  There is only one way to get a sum of 3.

    Example 2:
    Input: d = 2, f = 6, target = 7
    Output: 6
    Explanation:
    You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
    1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

    Example 3:
    Input: d = 2, f = 5, target = 10
    Output: 1
    Explanation:
    You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.

    Example 4:
    Input: d = 1, f = 2, target = 3
    Output: 0
    Explanation:
    You throw one die with 2 faces.  There is no way to get a sum of 3.

    Example 5:
    Input: d = 30, f = 30, target = 500
    Output: 222616187
    Explanation:
    The answer must be returned modulo 10^9 + 7.

    Constraints:
        o 1 <= d, f <= 30
        o 1 <= target <= 1000

    Hint:
    Use DP. The states are how may dice are remaining, and what sum total you have rolled so far.
 */

/*  DP: Time = O(d*f*target) Space = O(d*target)
    sumproblem/state: (i, t) 用前 i 个色子掷出总和为 t 的所有可能性
    dp[i][j]: ways to have target sum using first i dices (all must be used)
    Init: dp[0][0] = 1
    Transition:
    dp[i][t] = sum(dp[i-1][t-j]), i <= j <= min(f, t)
 */
public class NumberofDiceRollsWithTargetSum {
    public int numRollsToTarget(int d, int f, int target) {
        int mod = 1000000007;
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;

        // 用前 i 个色子 [1~d]
        for (int i = 1; i <= d; i++) {
            // 遍历所有面可能的数值 [1~f]
            for (int j = 1; j <= f; j++) {
                // 遍历所有可能的总和 [1~target]
                for (int k = j; k <= target; k++) {
                    dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % mod;
                }
            }
        }
        return dp[d][target];
    }
}
