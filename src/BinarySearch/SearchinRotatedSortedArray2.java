package BinarySearch;

/*  81. Search in Rotated Sorted Array 2
    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
    (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
    You are given a target value to search. If found in the array return true, otherwise return false.

    Example 1:
    Input: nums = [2,5,6,0,0,1,2], target = 0
    Output: true

    Example 2:
    Input: nums = [2,5,6,0,0,1,2], target = 3
    Output: false

    Note:
    This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
    Would this affect the run-time complexity? How and why?
 */

/*  Binary Search: Time = best(O(logn)) worst(O(n)) Space = O(1)
    [1, 3, 1, 1, 1]
    当 nums[start] == nums[mid] 的时候需要单独考虑，can't directly check which half the target may be in.
 */
public class SearchinRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[start]) {
                if (nums[mid] >= target && nums[start] <= target) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[start]) {
                if (nums[mid] <= target && nums[end] >= target) {
                    start = mid;
                } else {
                    end = mid;
                }
            // IMPORTANT: Handle the worst case: 1 1 1 1 0 1 1
            // If nums[start] == nums[mid], just move start to the next index.
            } else {
                // 因为我们知道 nums[mid] 不可能等于 target，所以可以舍弃
                start++;            // [1, 3, 1, 1, 1] -> [3, 1, 1, 1]
            }
        }
        if (nums[start] == target || nums[end] == target) return true;
        return false;
    }
}
