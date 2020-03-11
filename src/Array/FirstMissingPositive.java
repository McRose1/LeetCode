package Array;

/*  41. Missing Positive
    Given an unsorted integer array, find the smallest missing positive integer.

    Example 1:
    Input: [1,2,0]
    Output: 3

    Example 2:
    Input: [3,4,-1,1]
    Output: 2

    Example 3:
    Input: [7,8,9,11,12]
    Output: 1

    Note: Your algorithm should run in O(n) time and uses constant extra space.
 */

import java.util.Arrays;
/* bucket sort: 每当 nums[i] != i+1 的时候，将 nums[i] 和 nums[nums[i] - 1]交换，直到无法交换为止
    index: 0 1 2 3 4 5 ...
    num:   1 2 3 4 5 6 ...
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
