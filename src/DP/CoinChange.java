package DP;

/*  322. Coin Change
    You are given coins of different denominations and a total amount of money amount.
    Write a function to compute the fewest number of coins that you need to make up that amount.
    If that amount of money cannot be made up by any combination of the coins, return -1.

    Example 1:
    Input: coins = [1, 2, 5], amount = 11
    Output: 3
    Explanation: 11 = 5 + 5 + 1

    Example 2:
    Input: coins = [2], amount = 3
    Output: -1

    Note: You may assume that you have an infinite number of each kind of coin.
 */
import java.util.Arrays;
/*  DP (Bottom-up) - Iteration: Time = O(S*n) Space = O(S) 比自顶下先要快
    Before calculating F(i), we have to compute all minimum counts for amounts up to i.
    On each iteration i of the algorithm F(i) is computed as min(j=0->n-1) F(i - Cj) + 1
    这个方法的关键就是 DP 数组是枚举所有总额的可能：0-amount，而不是根据 coin 的种类来创建
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 初始化 DP 数组
        // 这里全部初始赋值 amount + 1 是很有讲究的，因为 amount + 1 > 最大的硬币数（即使都为 1），这样设置确保了后面会被更新
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // 零钱数额不能大于 target
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
                // 如果 coins 是升序排序的，那么这里可以剪枝
                // 但是本题并不是，有 [474,83,404,3] 这样的输入，除非一开始 Arrays.sort(coins)
                // else {
                //    break;
                // }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

/*  DP (Top-down) - Recursion with Memoization: Time = O(S*n) Space = O(S)
    F(S) - minimum number of coins needed to make change for amount S using coin denominations [C0 ... Cn-1]
    F(S) = F(S - C) + 1, compute F(S - Ci) for each possible denomination and choose the minimum among them.
    Uses backtracking and cut the partial solutions in the recursive tree, which doesn't lead to a viable solution.

    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return helper(coins, amount, new int[amount]);
    }
    private int helper(int[] coins, int rem, int[] dp) {
        // not valid
        if (rem < 0) return -1;

        // completed
        if (rem == 0) return 0;

        // already computed, so reuse（记忆化递归）
        if (dp[rem - 1] != 0) return dp[rem - 1];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = helper(coins, rem - coin, dp);
            if (count >= 0 && count < min) {
                min = count + 1;
            }
        }
        dp[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[rem - 1];
    }
 */
