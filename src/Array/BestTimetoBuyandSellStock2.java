package Array;

/*  122. Best Time to Buy and Sell Stock 2
    Say you have an array for which the ith elements is the price of a given stock on day i.

    Design an algorithm to find the maximum profit. You may complete as many transactions as you like
    (i.e., buy one and sell on share of stock multiple times)
    Note: You may not engage in multiple transactions at the same time
    (i.e., you must sell the stock before you buy again).

    Example 1:
    Input: [7,1,5,3,6,4]
    Output: 7

    Example 2:
    Input: [1,2,3,4,5]
    Output: 4

    Example 3:
    Input: [7,6,4,3,1]
    Output: 0
 */

/*  Simple One Pass: Time = O(n) Space = O(1)
    Instead of looking for every peak following a valley, we can simply go on crawling over the slope and
    keep on adding the profit obtained from every consecutive transaction.
 */
public class BestTimetoBuyandSellStock2 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;
    }
}

/*  Brute Force: Time = O(n^n) Space = O(n)

    public int maxProfit(int[] prices) {
        return calculate(prices, 0);
    }
    public int calculate(int prices[], int s) {
        if (s >= prices.length) {
            return 0;
        }
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxprofit) {
                        maxprofit = profit;
                    }
                }
            }
            if (maxprofit > max) {
                max = maxprofit;
            }
        }
        return max;
    }
 */

/*  Peak Valley Approach: Time = O(n) Space = O(1)
    The key point is we need to consider every peak immediately following a valley to maximize the profit.
    In case we skip one of the peaks (trying to obtain more profit), we will end up losing the profit over
    one of the transactions leading to an overall lesser profit.

        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++:
            }
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;

 */