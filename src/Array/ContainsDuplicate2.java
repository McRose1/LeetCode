package Array;

/*  219. Contains Duplicate 2
    Given an array of integers and an integer k,
    find out whether there are two distinct indices i and j in the array
    such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

    Example 1:
    Input: nums = [1,2,3,1], k = 3
    Output: true

    Example 2:
    Input: nums = [1,0,1,1], k = 1
    Output: true

    Example 3:
    Input: nums = [1,2,3,1,2,3], k = 2
    Output: false
 */

/*
    Sliding Window: Time = O(n) Space = O(k)
    To reduce the space complexity, keep sliding window with k length.
    Using a Set to check if any duplicate number in the sliding window.
    It iterates over the array using a sliding window. The front of the window is at i, the rear of the window is k steps back.
    The elements within that window are maintained using a Set.
    While adding new element to the set, if add() returns false, it means the element already exists in the set.
    At that point, we return true.
    If the control reaches out of for loop, it means that inner return true never executed, meaning no such duplicate element was found.
 */
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate2 {
    public boolean containsNearByDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                // 维持大小为 K 的移动窗口
                set.remove(nums[i - k]);
            }
            if (!set.add(nums[i])) {    // set.add() return false if duplicate
                return true;
            }
        }
        return false;
    }
}

/*
    HashMap: Time = O(n) Space = O(n)
    keep track of every unique number index, check if it appeared before and the difference between current and previous index

import java.util.HashMap;

        if (nums == null || nums.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {     // Integer j = map.get(nums[i]);
                if (i - map.get(nums[i]) <= k) {    // if (j != null && i - j <= k)
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
 */

/*
    Naive Solution: Time = O(n*k) Space = O(1)
    enumerate and try every possible pair

        if (nums == null || nums.length == 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j - i <= k && j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
 */