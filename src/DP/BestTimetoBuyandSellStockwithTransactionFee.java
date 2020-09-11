package DP;

/*  Best Time to Buy and Sell Stock with Transaction Fee
    You are given an array of integers prices, for which the i-th element is the price of a given stock on day i;
    and a non-negative integer fee representing a transaction fee.

    You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
    You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

    Return the maximum profit you can make.

    Example 1:
    Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
    Output: 8
    Explanation: The maximum profit can be achieved by:
    Buying at prices[0] = 1
    Selling at prices[3] = 8
    Buying at prices[4] = 4
    Selling at prices[5] = 9
    The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

    Note:
        o 0 < prices.length <= 50000.
        o 0 < prices[i] < 50000.
        o 0 <= fee < 50000.

    Hint: Consider the first K stock prices.
          At the end, the only legal states are the you don't own a share of stock, or that you do.
          Calculate the most profit you could have under each of these two cases.
 */

/*  DP: Time = O(n) Space = O(1)
    At the end of the i-th day, we maintain cash, the maximum profit we would have if we did not have a share of stock,
    and hold, the maximum profit we could have if we owned a share of stock.
    To transition from the i-th day to the i+1-th day, we either sell our stock: cash = max(cash, hold + prices[i] - fee)
    or buy a stock: hold = max(hold, cash - prices[i]).
    At the end, we want to return cash.
    在计算这个 2 个状态转移方程时，我们可以不使用临时变量来存储第 i-1 天 cash 和 hold 的值，
    而是可以先计算 cash 再计算 hold，原因是同一天卖出再买入（亏了一笔手续费）一定不会比不进行任何操作好。
 */
public class BestTimetoBuyandSellStockwithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int cash = 0;
        int hold = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}
