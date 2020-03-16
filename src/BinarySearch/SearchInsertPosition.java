package BinarySearch;

/*  35. Search Insert Position
    Given a sorted array and a target value, return the index if the target is found.
    If not, return the index where it would be if it were inserted in order.

    You may assume no duplicates in the array.

    Example 1:
    Input: [1,3,5,6], 5
    Output: 2

    Example 2:
    Input: [1,3,5,6], 2
    Output: 1

    Example 3:
    Input: [1,3,5,6], 7
    Output: 4

    Example 4:
    Input: [1,3,5,6], 0
    Output: 0
 */

/*  Binary Search: Time = O(logn) Space = O(1)
    三种情况：target____target_____target
 */
public class SearchInsertPosition {
   public int searchInsert(int[] nums, int target) {
       int start = 0;
       int end = nums.length - 1;
       while (start + 1 < end) {
           int mid = (end - start) / 2 + start;
           if (target == nums[mid]) return mid;     // return the index if the target is found
           else if (target < nums[mid]) end = mid;
           else start = mid;
       }
       // the target is not found, return the index where it would be if it were inserted in order
       if (target <= nums[start]) {
           return start;
       } else if (target <= nums[end]) {
           return end;
       } else {
           return end + 1;
       }
   }
}

/*  my version

        if (nums[0] > target) return 0;
        if (nums[nums.length - 1] < target) return nums.length;

        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        else return right;
 */
