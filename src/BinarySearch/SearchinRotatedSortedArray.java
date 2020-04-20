package BinarySearch;

/*  33. Search in Rotated Sorted Array
    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
    (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
    You are given a target value to search. If found in the array return its index, otherwise return -1.
    You may assume no duplicate exists in the array.
    Your algorithm's runtime complexity must be in the order of O(logn)

    Example 1:
    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4

    Example 2:
    Input: nums = [4,5,6,7,0,1,2], target = 3
    Output: -1
 */

/*  Binary Search: Time = O(logn) Space = O(1)
    4 5 6 7 | 0 1 2
    l             r
 */
public class SearchinRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // 先确定 mid 在左边
            if (nums[mid] > nums[left]) {
                // 再确定 target 在左边的 mid 左边
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid;
                // 再确定 target 在左边的 mid 右边
                } else left = mid;
            // 先确定 mid 在右边
            } else {
                // 再确定 target 在右边的 mid 右边
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid;
                // 再确定 target 在右边的 mid 左边
                } else right = mid;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}
