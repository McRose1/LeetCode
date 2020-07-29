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

import java.util.Arrays;

/*  找规律
    排序，找中位数
    大于中位数，左 -> 右，奇数位
    小于中位数，右 -> 左，偶数位
    中位数最后放
 */
public class WiggleSort2 {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int mid = (n - 1) / 2;
        int index = 0;
        int[] temp = new int[n];
        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid - i];
            if (index + 1 < n) {
                temp[index + 1] = nums[n - 1 - i];
            }
            index += 2;
        }
        System.arraycopy(temp, 0, nums, 0, n);
    }
}
