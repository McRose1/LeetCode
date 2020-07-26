# 309 Best Time to Buy and Sell Stock with Cooldown
限制条件：每卖完一次需要一天的 Cooldown

## DP
1. 定义状态：
- hold[i]：第 i 天 hold 股票的最大 profit
- unhold[i]：第 i 天不 hold 股票的最大 profit
2. Target：unhold[n - 1]
3. Base Case：
- hold[0] = -prices[0]
- hold[1] = max(-prices[1], -prices[0])
- unhold[0] = 0
4. 状态转移：
- hold[i] 取以下情况最大值
    1. 第 i 天买入：unhold[i - 2] - prices[i]
    2. 第 i 天没有买入：hold[i - 1]
- unhold[i] 取以下情况最大值
    1. 第 i 天卖出：hold[i - 1] + prices[i]
    2. 第 i 天没有卖出：unhold[i - 1]

### 花花酱
![状态转移图](/src/images/%23309.png)

- hold[i] = max(hold[i - 1], rest[i - 1] - prices[i])
- sold[i] = hold[i - 1] + prices[i]
- rest[i] = max(rest[i - 1], sold[i - 1])

init: rest[0] = sold[0] = 0, hold[0] = -∞

ans: max(res[i], sold[i])
    
### Space = O(n) -> O(1)
在上面的过程我们可以发现，在 DP 过程中我们只需要 unhold[i - 2]、unhold[i - 1]、hold[i - 1] 这 3 个值，而不需要整个 DP 数组，所以我们可以进一步优化空间

