package Array;

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

/*  Simplified DP: Time = O(n) Space = O(1)
    buy1 = max(buy1, -prices[i])
    sell1 = max(sell1, buy1 + prices[i])
    buy2 = max(buy2, sell1 - prices[i])
    sell2 = max(sell2, buy2 + prices[i])

 */
public class BestTimetoBuyandSellStock3 {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int price : prices) {
            sell2 = Math.max(sell2, buy2 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell1 = Math.max(sell1, buy1 + price);
            buy1 = Math.max(buy1, -price);
        }
        return sell2;
    }
}

/*  DP

 */