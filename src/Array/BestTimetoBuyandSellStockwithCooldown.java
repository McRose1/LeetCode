package Array;

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

/*
    DP: Time = O(n) Space = O(n) -> O(1)滚动记录
    buy[i] = max(buy[i-1], rest[i-1] - prices[i])
    sell[i] = max(buy[i-1] + prices[i], sell[i-1])
    rest[i] = max(rest[i-1], sell[i-1]); rest[i] = sell[i-1] ->
    buy[i] = max(sell[i-2] - prices[i], buy[i - 1])
    sell[i] = max(buy[i-1] + prices[i], sell[i-1])
    init: rest[0] = sold[0], hold[0] = -∞
    ans: max(rest[i], sold[i])
 */
public class BestTimetoBuyandSellStockwithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int sell = 0, prevSell = 0, buy = Integer.MIN_VALUE, prevBuy = 0;
        for (int price : prices) {
            prevBuy = buy;
            buy = Math.max(prevSell - price, prevBuy);
            prevSell = sell;
            sell = Math.max(prevBuy + price, prevSell);
        }
        return sell;
    }
}
