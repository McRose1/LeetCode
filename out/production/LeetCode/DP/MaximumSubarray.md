# 53. Maxium Subarray

DP 的简单题

[-2,1,-3,4,-1,2,1,-5,4]

找出和最大的连续序列

dp[i]: the max sum of subarray in 0->i

dp[i] = nums[i] + Math.max(dp[i - 1], 0);

说一下这题每次做都会写的 bug，那就是 max 的初始化

一开始想着 max 应该是 Integer.MIN_VALUE 吧，但是发现长度为 1 的数组直接返回 Int_MIN，长度为 2 的数组直接返回第 2 个数字

所以应该让 max = nums[0]
