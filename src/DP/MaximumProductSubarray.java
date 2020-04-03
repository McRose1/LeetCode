package DP;

/*  152. Maximum Product Subarray
    Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

    Example 1:
    Input: [2,3,-2,4]
    Output: 6
    Explanation: [2,3] has the largest product 6.

    Example 2:
    Input: [-2,0,-1]
    Output: 0
    Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int curMax = nums[0];
        int curMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int nextMax = curMax * nums[i];
            int nextMin = curMin * nums[i];
            curMax = Math.max(nums[i], Math.max(nextMax, nextMin));
            curMin = Math.min(nums[i], Math.min(nextMax, nextMin));
            max = Math.max(max, curMax);
        }
        return max;
    }
}
