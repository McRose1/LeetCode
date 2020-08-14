package DP;

/*  198. House Robber
    You are a professional robber planning to rob houses along a street.
    Each house has a certain amount of money stashed,
    the only constraint stopping you from robbing each of them is that
    adjacent houses have security system connected and it will automatically contact the police
    if two adjacent houses were broken into on the same night.

    Given a list of non-negative integers representing the amount of money of each house,
    determine the maximum amount of money you can rob tonight without alerting the police.

    Example 1:
    Input: [1,2,3,1]
    Output: 4

    Example 2:
    Input: [2,7,9,3,1]
    Output: 12
 */

/*  DP: Time = O(n) Space = O(n)
    For a given house i, we have two options:
    1. Take the money if we didn't robber house i - 1
    2. Skip it
    dp[i]: Max money after "visiting" house[i]
    dp[i] = max(dp[i - 2 + money[i], dp[i - 1]])
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        // corner case
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[n - 1];
    }
}

/*  Optimized DP: Time = O(n) Space = O(1)
    更新 dp[i] 的时候只需要 dp[i - 1] 和 dp[i - 2] 这两个信息
    不需要数组，只需要几个变量

        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        // 相当于 dp[i - 2]
        int pre = 0;
        // 相当于 dp[i - 1] 和 dp[i]
        int cur = nums[0];
        for (int i = 2; i <= n; i++) {
            int temp = cur;
            cur = Math.max(pre + nums[i - 1], cur);
            pre = temp
        }
        return cur;
 */


