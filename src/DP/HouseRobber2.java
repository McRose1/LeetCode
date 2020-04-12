package DP;

/*  213. House Robber 2
    You are a professional robber planning to rob houses along a street.
    Each house has a certain amount of money stashed.
    All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
    Meanwhile, adjacent houses have security system connected and it will automatically contact the police
    if two adjacent houses were broken into on the same night.

    Given a list of non-negative integers representing the amount of money of each house,
    determine the maximum amount of money you can rob tonight without alerting the police.

    Example 1:
    Input: [2,3,2]
    Output: 3
    Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
                 because they are adjacent houses.

    Example 2:
    Input: [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
                 Total amount you can rob = 1 + 3 = 4.

    Hint: Since House[1] and House[n] are adjacent, they cannot be robbed together.
    Therefore, the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n], depending one which choice offers more money.
    Now the problem has degenerated to the House Robber, which is already been solved.
 */

//  DP: Time = O(n) Space = O(n)
public class HouseRobber2 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp1 = new int[n + 1];
        dp1[0] = 0;
        dp1[1] = nums[0];

        int[] dp2 = new int[n + 1];
        dp2[0] = 0;
        dp2[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i - 1]);
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i - 1]);
        }

        return Math.max(dp1[n - 1], dp2[n]);
    }
}

/*  Optimized DP: Time = O(n) Space = O(1)

    public int rob(int[] nums) {
        int n = nums.length;
        // 考虑第一家
        int max1 = robHelper(nums, 0, n - 1);
        // 不考虑第一家
        int max2 = robHelper(nums, 1, n);
        return Math.max(max1, max2);
    }

    private int robHelper(int[] nums, int start, int end) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int pre = 0;
        int cur = nums[start];
        for (int i = start + 2; i <= end; i++) {
            int temp = cur;
            cur = Math.max(pre + nums[i - 1], cur);
            pre = temp;
        }
        return cur;
    }
 */

/*  my version

        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        int[] dp1 = new int[n - 1];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }

        int[] dp2 = new int[n - 1];
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1], nums[2]);

        for (int i = 2; i < n - 1; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i + 1]);
        }

        return Math.max(dp1[n - 2], dp2[n - 2]);
 */