package DP;

/*  309. Best Time to Buy and Sell Stock with Cooldown
    Say you have an array for which the ith elements is the price of a given stock on day i.
    Design an algorithm to find the maximum profit. You may complete as many transactions as you like.
    (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
    1. You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    2. After you sell your stock, you cannot buy stock on next day (ie. cooldown 1 day)

    Example:
    Input: [1,2,3,0,2]
    Output: 3
    Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */

/*  DP: Time = O(n) Space = O(n) -> O(1)滚动记录
    hold[i] = max(hold[i - 1], rest[i - 1] - prices[i])
    sold[i] = hold[i - 1] + prices[i]
    rest[i] = max(rest[i - 1], sold[i - 1])
 */
public class BestTimetoBuyandSellStockwithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int sold = 0;
        int rest = 0;
        int hold = Integer.MIN_VALUE;
        for (int price : prices) {
            int prev_sold = sold;
            sold = hold + price;
            hold = Math.max(hold, rest - price);
            rest = Math.max(rest, prev_sold);
        }
        return Math.max(rest, sold);
    }
}

/*  DP: Time = O(n) Space = O(n)

        if (prices == null || prices.length <= 1) return 0;

        int n = prices.length;
        int[] hold = new int[n];
        int[] unhold = new int[n];

        // base case
        hold[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            if (i == 1) {
                // base case
                hold[i] = Math.max(hold[i - 1], -prices[1]);
            } else {
                hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i]);
            }
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
        }
        return unhold[n - 1];
 */