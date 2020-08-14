package Stack;

/*  901. Online Stock Span
    Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.
    The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards)
    for which the price of the stock was less than or equal to today's price.
    For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85],
    then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

    Example:
    Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
    Output: [null,1,1,1,2,1,4,6]
    Explanation:
    First, S = StockSpanner() is initialized.  Then:
    S.next(100) is called and returns 1,
    S.next(80) is called and returns 1,
    S.next(60) is called and returns 1,
    S.next(70) is called and returns 2,
    S.next(60) is called and returns 1,
    S.next(75) is called and returns 4,
    S.next(85) is called and returns 6.

    Note that (for example) S.next(75) returned 4, because the last 4 prices
    (including today's price of 75) were less than or equal to today's price.

    Note:
    1. Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
    2. There will be at most 10000 calls to StockSpanner.next per test case.
    3. There will be at most 150000 calls to StockSpanner.next across all test cases.
    4. The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.
 */

import java.util.Stack;
/*  Monotonic Stack: Time = O(1) amortized Space = O(n)
    DP 的状态压缩版
    Stack <price, span> ordered by DESC
    1. if price[i] < stack.peek().price: stack.push(price, 1)
    2. while price[i] >= stack.peek().price, pop top and accumulate spans until price[i] < stack.peek() or stack becomes empty
 */
public class OnlineStockSpan {

    Stack<int[]> stack;

    public OnlineStockSpan() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int res = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            res += stack.pop()[1];
        }
        stack.push(new int[] {price, res});

        return res;
    }
}

/*  2 Stacks (LC)

    Stack<Integer> prices;
    Stack<Integer> weights;
    public OnlineStockSpan() {
        prices = new Stack<>();
        weights = new Stack<>();
    }

    public int next(int price) {
        int w = 1;
        while (!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            w += weights.pop();
        }

        prices.push(price);
        weights.push(w);
        return w;
    }
 */

/*  DP: Time = O(1) amortized Space = O(n)
    dp[i] := span of price[i]
    j = i - 1
    while price[i] > price[j]:
        dp[i] += dp[j]
        j -= dp[j]

    private int[] prices;
    private int[] dp;
    private int idx = -1;

    public OnlineStockSpan() {
        prices = new int[10000];
        dp = new int[10000];
    }

    public int next(int price) {
        int res = 1;

        int i = idx;
        while (i >= 0 && price >= prices[i]) {
            res += dp[i];
            i -= dp[i];
        }
        prices[++idx] = price;
        dp[idx] = res;

        return res;
    }
 */
