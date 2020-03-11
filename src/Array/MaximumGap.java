package Array;

/*  164. Maximum Gap
    Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
    Return 0 if the array contains less than 2 elements.

    Example 1:
    Input: [3,6,9,1]
    Output: 3
    Explanation:
    The sorted form of the array is [1,3,6,9], either
    (3,6) or (6,9) has the maximum difference 3.

    Example 2:
    Input: [10]
    Output: 0

    Note:
    You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
    Try to solve it in linear time/space.
 */

import java.util.Arrays;

/*  Bucket Sort and The Pigeonhole Principle: Time = O(n) Space = O(n)
    avgGap = (max - min) / (n - 1): the smallest (Maximum Gap) 平均值，如果某一 gap 小了，必然有一 gap 变大，max gap 也会随之变大
    只要使得 bucket size <= avgGap, max gap 必然只存在于 bucket 之间而不会 within bucket
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int max = nums[0];
        int min = nums[0];
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        // the minimum possible gap, ceiling of the integer division
        int avgGap = (int)Math.ceil((double)(max - min) / (len - 1));
        int[] bucketMin = new int[len - 1];     // store the min value in that bucket
        int[] bucketMax = new int[len - 1];     // store the max value in that bucket
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        // put numbers into buckets
        for (int i : nums) {
            if (i == min || i == max) {
                continue;
            }
            int idx = (i - min) / avgGap;   // index of the right position in the buckets
            bucketMin[idx] = Math.min(i, bucketMin[idx]);
            bucketMax[idx] = Math.max(i, bucketMax[idx]);
        }
        // scan the buckets for the max gap
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < nums.length - 1; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE && bucketMax[i] == Integer.MIN_VALUE) {
                // empty bucket
                continue;
            }
            // min value minus the previous value is the current gap
            maxGap = Math.max(maxGap, bucketMin[i] - previous);
            // update previous bucket value
            previous = bucketMax[i];
        }
        maxGap = Math.max(maxGap, max - previous);  // update the final max value gap
        return maxGap;
    }
}

/*  Comparison Sorting: Time = O(nlogn) Space = O(1)

        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int gap = nums[i + 1] - nums[i];
            max = Math.max(max, gap);
        }
        return max;
 */