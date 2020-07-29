package HashTable;

/*  325. Maximum Size Subarray Sum Equals k
    Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
    If there isn't one, return 0 instead.

    Note:
    The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

    Example 1:
    Input: nums = [1, -1, 5, -2, 3], k = 3
    Output: 4
    Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

    Example 2:
    Input: nums = [-2, -1, 2, 1], k = 1
    Output: 2
    Explanation: The subarray [-1, 2] sums to 1 and is the longest.

    Follow Up: Can you do it in O(n) time?
 */

import java.util.HashMap;

/*  HashMap: Time = O(n) Space = O(n)
    用 HashMap 模拟 preSum[] 数组
 */
public class MaximumSizeSubarraySumEqualsk {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        // <preSum, index>：<preSum[0], 0>, <preSum[1], 1> ... <preSum[n - 1], n - 1>
        HashMap<Integer, Integer> map = new HashMap<>();
        // don't forget <0, -1>: [1,-1,5,-2]=3
        map.put(0, -1);

        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            // compute the preSum
            sum += nums[i];
            // preSum[i] - preSum[target] = k
            if (map.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }
            map.putIfAbsent(sum, i);
        }
        return maxLen;
    }
}

/*  Brute Force: Time = O(n^2) Space = O(1)

        if (nums == null || nums.length == 0) return 0;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == k) {
                max = Math.max(max, 1);
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
 */
