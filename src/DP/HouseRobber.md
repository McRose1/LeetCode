# 198. House Robber

DP 题

限制条件：不能抢劫相邻的房子

[1, 2, 3, 1]

[1, 2, 4, 4]

dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])

[2, 7,  9,  3,  1]

[2, 7, 11, 11, 12]

特殊处理下 1 个元素和 2 个元素的情况

初始化：
- dp[0] = nums[0];
- dp[1] = Math.max(nums[0], nums[1]);

秒了，**注意 Corner case：nums.length == 1**
