# 377. Combination Sum 4
给定一个没有重复且全是正数的数组，求组合加和为 target 的个数

**Note that different sequences are counted as different combinations.**

本来这系列的题目的标签都是 backtracking（这题会 TLE），而这题却是 DP

因为一般如果是带回溯的 DFS，要求的输出通常是输出所有组合，而如果只是要求输出个数的话，那么一般会想到用 DP

其实这题就是属于背包问题的 DP 问题，对于每个数字，都有选和不选 2 种选择，相当于找硬币问题、爬楼梯问题

## DP (Bottom-up)
[1, 2, 3] -> 4

类似爬楼梯问题：每次能走 1、2、3 步，问到第 4 层有多少种不同走法

1 2 3 4

1 2 4 7

dp[i] += dp[i - num] 

## Recursion with Memoization (Top-down)
dp(nums, target): all combinations that uses nums to sum up to target 

dp([1,2,3], 4) = sum:

{1} x dp([1,2,3], 4-1)  -> dp([1,2,3], 3) -> 4  -> dp[4] - 1 -> dp[3]

{2} x dp([1,2,3], 4-2)  -> dp([1,2,3], 2) -> 2  -> dp[4] - 2 -> dp[2]

{3} x dp([1,2,3], 4-3)  -> dp([1,2,3], 1) -> 1  -> dp[4] - 3 -> dp[1]
