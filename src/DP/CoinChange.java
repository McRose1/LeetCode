package DP;

import java.util.Arrays;

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

/*  DP(bottom-up) - Iteration: Time = O(S*n) Space = O(S)
    Before calculating F(i), we have to compute all minimum counts for amounts up to i.
    On each iteration i of the algorithm F(i) is computed as min(j=0->n-1) F(i - Cj) + 1
    这个方法的关键就是 DP 数组是枚举所有总额的可能：0-amount，而不是根据 coin 的种类来创建
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        // Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        // 初始化 DP 数组
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                } // else {     剪枝
                // break;
                //}
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

/*  DP(top-down) - Recursion with Memoization: Time = O(S*n) Space = O(S)
    F(S) - minimum number of coins needed to make change for amount S using coin denominations [C0 ... Cn-1]
    F(S) = F(S - C) + 1, compute F(S - Ci) for each possible denomination and choose the minimum among them.
    Uses backtracking and cut the partial solutions in the recursive tree, which doesn't lead to a viable solution.

    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }
    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;     // not valid
        if (rem == 0) return 0;     // completed
        if (count[rem - 1] != 0) return count[rem - 1];     // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
 */
