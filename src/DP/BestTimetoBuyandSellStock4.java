package DP;

/*  188. Best Time to Buy and Sell Stock 4
    Say you have an array for which the ith elements is the price of a given stock on day i.
    Design an algorithm to find the maximum profit. You may complete at most k transactions.
    Note: You may not engage in multiple transactions at the same time
    (i.e., you must sell the stock before you buy again).

    Example 1:
    Input: [2,4,1], k = 2
    Output: 2

    Example 2:
    Input: [3,2,6,5,0,3], k = 2
    Output: 7
 */

/*  DP: Time = O(nk) Space = O(n)
    if k > N/2 -> Best Time to Buy and Sell Stock 2
    else:
    dp[i][j] = maximum profit from at most i transactions using prices[0...j]
    Now on day j, we have two options:
    1. Do nothing (or buy) which doesn't change the acquired profit: dp[i][j] = dp[i][j - 1]
    2. Sell the stock: In order to sell the stock, you must've bought it on a day t = [0... j-1].
    假如我们在第 t 天买入：
    Maximum profit that can be attained is t: 0 -> j-1 max(prices[j] - prices[t] + dp[i - 1][t - 1])
    In order to reduce time complexity from O(n^2*k) to O(nk):
    max(prices[j] - prices[t] + dp[i - 1][t - 1]) == prices[j] + max(dp[i - 1][t - 1] - prices[t])
 */

public class BestTimetoBuyandSellStock4 {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        // if k >= len/2, then you can make maximum number of transactions
        // 直接使用第二题的代码即可
        if (k >= len / 2) return helper(prices);

        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            // tmpMax 表示从 0 - j-1 天买入的最低支出
            int tmpMax = -prices[0];
            for (int j = 1; j < len; j++) {
                // do nothing or sell the stock
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + tmpMax);
                tmpMax = Math.max(tmpMax, dp[i - 1][j - 1] - prices[j]);
            }
        }
        return dp[k][len - 1];
    }
    private int helper(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++) {
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
