package DP;

/*  123. Best Time to Buy and Sell Stock 3
    Say you have an array for which the ith elements is the price of a given stock on day i.
    Design an algorithm to find the maximum profit. You may complete at most two transactions.
    Note: You may not engage in multiple transactions at the same time
    (i.e., you must sell the stock before you buy again).

    Example 1:
    Input: [3,3,5,0,0,3,1,4]
    Output: 6
    Explanation:
    Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
    Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

    Example 2:
    Input: [1,2,3,4,5]
    Output: 4

    Example 3:
    Input: [7,6,4,3,1]
    Output: 0
 */

/*  一维 DP: Time = O(n) Space = O(n)

 */
public class BestTimetoBuyandSellStock3 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        int K = 2;
        int[] dp = new int[K + 1];
        int[] min = new int[K + 1];
        for (int k = 1; k <= K; k++) {
            min[k] = prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= K; k++) {
                // 找出第 1 天到第 i 天 prices[buy] - dp[buy][k - 1] 的最小值
                min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
                // 比较不操作和选择一天买入的哪个收益更大
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }
        return dp[K];
    }
}

/*  Simplified DP: Time = O(n) Space = O(1)

        int n = prices.length;
        if (prices == null || n < 2) return 0;

        int dp1 = 0;
        int dp2 = 0;
        int min1 = prices[0];
        int min2 = prices[0];

        for (int i = 1; i < n; i++) {
            // 第一次交易
            min1 = Math.min(prices[i] - 0, min1);
            dp1 = Math.max(dp1, prices[i] - min1);
            // 第二次交易
            min2 = Math.min(prices[i] - dp1, min2);
            dp2 = Math.max(dp2, prices[i] - min2);
        }
        return dp2;
 */

/*  2 个一维 DP 数组：Time = O(n) Space = O(n)

        int n = prices.length;
        if (n == 1) return 0;

        int[] first = new int[n];
        first[0] = 0;
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, prices[i]);
            if (prices[i] > prices[i - 1]) {
                first[i] = Math.max(first[i - 1], prices[i] - min);
            } else {
                first[i] = first[i - 1];
            }
        }

        int[] second = new int[n];
        second[n - 1] = 0;
        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            if (prices[i] < prices[i + 1]) {
                second[i] = Math.max(second[i + 1], max - prices[i]);
            } else {
                second[i] = second[i + 1];
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, first[i] + second[i]);
        }
        return res;
 */

/*  State Machine: Time = O(n) Space = O(1)
    buy1 = max(buy1, -prices[i])
    sell1 = max(sell1, buy1 + prices[i])
    buy2 = max(buy2, sell1 - prices[i])
    sell2 = max(sell2, buy2 + prices[i])

        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int price : prices) {
            sell2 = Math.max(sell2, buy2 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell1 = Math.max(sell1, buy1 + price);
            buy1 = Math.max(buy1, -price);
        }
        return sell2;
 */

/*  二维 DP（安卓大宝贝）

        int n = prices.length;
        if (prices == null || n < 2) return 0;

        int[][] dp = new int[n + 1][5 + 1];
        for (int k = 1; k <= 5; k++) {
            dp[0][k] = Integer.MIN_VALUE;
        }

        dp[0][1] = 0;
        for (int i = 1; i <= n; i++) {
            // phase 1,3,5 手头没有股票
            for (int j = 1; j <= 5; j += 2) {
                // 前一天也没有
                dp[i][j] = dp[i - 1][j]; // 因为昨天和今天都不持有股票，所以是在同一阶段；表示到昨天为止的最大获益
                if (j > 1 && i > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    // 前一天持有股票和前一天不持有股票，哪个获益更大
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            // phase 2,4 手头有股票
            for (int j = 2; j <= 5; j += 2) {
                // 前一天没有股票
                dp[i][j] = dp[i - 1][j - 1]; // 因为昨天不持有，今天持有股票，所以是在不同阶段；表示到昨天为止的最大获益
                if (j > 1 && i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) { // 前一天也持有股票
                    // 前一天持有股票和前一天不持有股票，哪个获益更大
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
            }
        }
        int res = 0;
        for (int j = 1; j <= 5; j++) {
            res = Math.max(res, dp[n][j]);
        }
        return res;
 */