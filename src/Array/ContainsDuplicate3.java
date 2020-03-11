package Array;

/*  220. Contains Duplicate 3
    Given an array of integers, find out whether there are two distinct indices i and j in the array
    such that the absolute difference between nums[i] and nums[j] is at most t
    and the absolute difference between i and j is at most k.

    Example 1:
    Input: nums = [1,2,3,1], k = 3, t = 0
    Output: true

    Example 2:
    Input: nums = [1,0,1,1], k = 1, t = 2
    Output: true

    Example 3:
    Input: nums = [1,5,9,1,5,9], k = 2, t = 3
    Output: false
 */

/*
    Bucket: Time = O(n) Space = O(bucket_size) worst case O((max_number - min_number) / t) 优化：O(k)
    Put every number in a corresponding bucket (bucket_index = (number - min_num) / (t + 1)).
    Return true if we see two numbers in a same bucket or the adjacent bucket has a number with diff less than t.
 */

import java.util.HashMap;

public class ContainsDuplicate3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
        }
        HashMap<Long, Integer> bucket = new HashMap<>();
        long diff = Long.valueOf(t) + 1;    // in case of t = 0
        for (int i = 0; i < nums.length; i++) {
            long index = (Long.valueOf(nums[i]) - Long.valueOf(min)) / diff;
            // Check left adjacent bucket
            Integer left_bucket = bucket.get(index - 1);
            if (left_bucket != null && Math.abs(Long.valueOf(nums[i]) - Long.valueOf(left_bucket)) < diff) {
                return true;
            }
            // Check right adjacent bucket
            Integer right_bucket = bucket.get(index + 1);
            if (right_bucket != null && Math.abs(Long.valueOf(nums[i]) - Long.valueOf(right_bucket)) < diff) {
                return true;
            }
            // Check current bucket
            Integer current_bucket = bucket.get(index);
            if (current_bucket != null && Math.abs(Long.valueOf(nums[i]) - Long.valueOf(current_bucket)) < diff) {
                return true;
            }
            bucket.put(index, nums[i]);
            if (i >= k) {
                bucket.remove((Long.valueOf(nums[i - k]) - Long.valueOf(min)) / diff);
            }
        }
        return false;
    }
}

/*
    TreeSet: Time = O(n * logk) Space = O(k)
    Record numbers in a Binary Search Tree (TreeSet in Java).
    check if the diff between the new number came in and its closest number in the BST is less than t.

        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet();
        for (int i = 0; i < nums.length; i++) {
            // the smallest number larger than nums[i]
            Integer ceil = set.ceiling(nums[i]);
            if (ceil != null && Long.valueOf(ceil) - Long.valueOf(nums[i]) <= t) {
                return true;
            }
            // the largest number smaller than nums[i]
            Integer floor = set.floor(nums[i]);
            if (floor != null && Long.valueOf(nums[i]) - Long.valueOf(floor) <= t) {
                return true;
            }
            set.add(nums[i]);
            if (i >= k) {   // 类似 sliding window
                set.remove(nums[i - k]);
            }
        }
        return false;
 */

/*
    Naive solution: Time = O(n * k) Space = O(1)
    try every number compare with its next k numbers

        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j - i <= k && j < nums.length; j++) {
                if (Math.abs(Long.valueOf(nums[j]) - Long.valueOf(nums[i])) <= t) {
                    return true;
                }
            }
        }
        return false;
 */