package DP;

/*  312. Burst Balloons
    Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
    You are asked to burst all the balloons. If you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
    Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

    Find the maximum coins you can collect by bursting the balloons wisely.

    Note:
    You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
    0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

    Example:
    Input: [3,1,5,8]
    Output: 167
    Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
                 coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
/*  DP (Bottom-up): Time = O(n^3) Space = O(n^2)
    dp[i][j] = maxCoins(nums[i]~nums[j])
    dp[i][j] = max{arr[i] x arr[k] x arr[j] + dp[i][k] + dp[k][j] | i < k < j}
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        // [3,1,5,8] -> [1,3,1,5,8,1] You may imagine nums[-1] = nums[n] = 1.
        int[] arr = new int[n + 2];
        System.arraycopy(nums, 0, arr, 1, n);
        arr[0] = arr[n + 1] = 1;

        int[][] dp = new int[n + 2][n + 2];
        // i 从大到小，是这道题自底向上的关键，非常重要！！！
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = arr[i] * arr[k] * arr[j];
                    sum += (dp[i][k] + dp[k][j]);
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n + 1];
    }
}

/*  DP (Recursion with Memoization): Time = O(n^3) Space = O(n^2)
    Top-down, Divide & Conquer

    private int[] arr;
    private int[][] memo;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        arr = new int[n + 2];
        System.arraycopy(nums, 0, arr, 1, n);
        arr[0] = arr[n + 1] = 1;

        memo = new int[n + 2][n + 2];
        return helper(0, n + 1);
    }
    private int helper(int left, int right) {
        // 递归的出口
        if (left >= right - 1) return 0;
        // 记忆化递归
        if (memo[left][right] > 0) return memo[left][right];

        for (int i = left + 1; i < right; i++) {
            int sum = arr[left] * arr[i] * arr[right];
            sum += helper(left, i) + helper(i, right);
            memo[left][right] = Math.max(memo[left][right], sum);
        }
        return memo[left][right];
    }
 */