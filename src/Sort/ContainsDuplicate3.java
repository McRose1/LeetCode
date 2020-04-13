package Sort;

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
import java.util.HashMap;

/*  Bucket: Time = O(n) Space = O(bucket_size) worst case O((max_number - min_number) / t) 优化：O(k)
    Put every number in a corresponding bucket (bucket_index = (number - min_num) / (t + 1)).
    Return true if we see two numbers in a same bucket or the adjacent bucket has a number with diff less than t.
 */
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

        // in case of t = 0
        // 这题的关键
        long diff = (long) t + 1;

        for (int i = 0; i < nums.length; i++) {
            // 通过区间大小得到桶号
            long index = ((long) nums[i] - (long) min) / diff;

            // check if current bucket is empty, each bucket may contain at most one element
            if (bucket.containsKey(index)) {
                return true;
            }

            // Check left adjacent bucket
            if (bucket.containsKey(index - 1) && Math.abs((long) nums[i] - (long) bucket.get(index - 1)) < diff) {
                return true;
            }
            // Check right adjacent bucket
            if (bucket.containsKey(index + 1) && Math.abs((long) nums[i] - (long) bucket.get(index + 1)) < diff) {
                return true;
            }
            // now current bucket is empty and no almost duplicate in neighbor buckets
            bucket.put(index, nums[i]);
            if (i >= k) {
                bucket.remove(((long) nums[i - k] - (long) min) / diff);
            }
        }
        return false;
    }
}

/*  TreeSet: Time = O(n * log(min(n, k))) Space = O(min(n, k))
    Record numbers in a Binary Search Tree (TreeSet in Java).
    check if the diff between the new number came in and its closest number in the BST is less than t.
    BST 可以实现 O(logk) 的 insert，search，delete
    查找大于等于 x 的最小的数
    查找小于等于 x 的最大的数

        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        TreeSet<Long> set = new TreeSet();
        for (int i = 0; i < nums.length; i++) {
            // the smallest number larger than nums[i]
            Long ceil = set.ceiling((long) nums[i]);
            if (ceil != null && ceil - nums[i] <= t) {
                return true;
            }
            // the largest number smaller than nums[i]
            Long floor = set.floor((long) nums[i]);
            if (floor != null && nums[i] - floor <= t) {
                return true;
            }
            set.add((long) nums[i]);
            // 类似 sliding window
            if (set.size() > k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
 */

/*  Naive solution (TLE): Time = O(n * min(k, n) Space = O(1)
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