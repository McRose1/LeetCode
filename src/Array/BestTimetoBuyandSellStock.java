package Array;

/*  121. Best Time to Buy and Sell Stock
    Say you have an array for which the ith elements is the price of a given stock on day i.
    If you were only permitted to complete at most one transaction
    (i.e., buy one and sell one share of the stock),
    design an algorithm to find the maximum profit.
    Note that you cannot sell a stock before you buy one.

    Example 1:
    Input: [7,1,5,3,6,4]
    Output: 5

    Example 2:
    Input: [7,6,4,3,1]
    Output: 0
 */

/*
    Definition:
    max_profit = max{price[j] - price[i]},
    0 <= i < j <= n-1

    Observation:
    Buy: prices[i]: min{prices[k]}, k <= i
    Sell: prices[j]: max{prices[k]}, k >= j
 */
//  One pass: Time = O(n) Space = O(1)
public class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max){
                max = prices[i] - min;
            }
        }
        return max;
    }
}

/*  Brute Force: Time = O(n^2) Space = O(1)

        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;

 */

/*
    DP: keep tracking the min price so far
    L[i]: lowest price up to i-th day
    P[i]: max profit up to ith day
    P[i] = max(P[i - 1], price{i} - L[i])
    max_profit = P[n - 1]
 */