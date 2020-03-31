package DP;

import java.util.HashMap;

/*  416. Partition Equal Subset Sum
    Given a non-empty array containing only positive integers, find if the array can be partitioned into
    two subsets such that the sum of elements in both subsets is equal.

    Note:
    Each of the array element will not exceed 100.
    The array size will not exceed 200.

    Example 1:
    Input: [1, 5, 11, 5]
    Output: true
    Explanation: The array can be partitioned as [1, 5, 5] and [11].

    Example 2:
    Input: [1, 2, 3, 5]
    Output: false
    Explanation: The array cannot be partitioned into equal sum subsets.
 */
/*  DP (Bottom-Up): Time = O(n^2) Space = O(sum)
    这题 DP 其实挺慢的，好在限定了 sum 最大不会超过 100 * 200
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if(sum % 2 == 1){
            return false;
        }
        sum /= 2;
        boolean [] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num : nums) {                      // [1, 5, 11, 5]
            // 剪枝
            for (int j = sum; j >= num; j--) {        // j = 11; 10
                // 剪枝
                if (dp[sum]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - num];   // dp[11] = dp[11] || dp[10]; dp[10]=dp[10]||dp[9] ...
            }
        }
        return dp[sum];
    }
}

/*  Backtracking: Time = O(n^2) Space = O(n)
    因为有剪枝，所以反而比 DP 快

        public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        return dfs(nums, 0, sum / 2);
    }

    private boolean dfs(int[] nums, int index, int target) {
        if (target == 0) return true;
        if (index == nums.length || target < 0) return false;

        // 取当前数加和
        if (dfs(nums, index + 1, target - nums[index])) return true;
        // Skip the same values, in case TLE
        int j = index + 1;
        while (j < nums.length && nums[index] == nums[j]) {
            j++;
        }
        // 不取当前数加和，也就是 backtrack 的感觉
        return dfs(nums, j, target);
    }
 */

/*  Recursion（TLE）: Time = O(2^n) Space = O(n)

    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % 2 != 0) return false;

        return helper(nums, 0, 0, total);
    }
    private boolean helper(int[] nums, int index, int sum, int total) {
        if (sum * 2 == total) return true;
        if (sum > total / 2 || index >= nums.length) return false;

        // take or not take the number to sum
        return helper(nums, index + 1, sum, total) ||
                helper(nums, index + 1, sum + nums[index], total);
    }
 */