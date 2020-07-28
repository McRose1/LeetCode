package Sort;

/*  324. Wiggle Sort 2
    Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3] ...

    Example 1:
    Input: nums = [1, 5, 1, 1, 6, 4]
    Output: One possible answer is [1, 4, 1, 5, 1, 6].

    Example 2:
    Input: nums = [1, 3, 2, 2, 3, 1]
    Output: One possible answer is [2, 3, 1, 3, 1, 2].

    Note: You may assume all input has valid answer.

    Follow up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 */

public class WiggleSort2 {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && nums[i] <= nums[i - 1]) || (i % 2 == 0 && nums[i] >= nums[i - 1])) {
                int temp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = temp;
            }
        }
    }
}
