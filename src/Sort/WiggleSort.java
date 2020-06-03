package Sort;

/*  280. Wiggle Sort
    Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]...

    Example:
    Input: nums = [3,5,2,1,6,4]
    Output: One possible answer is [3,5,1,6,2,4]
 */

import java.util.Arrays;
/*  Sort
    其实就是 2 个为一对相互交换，除了第一个和最后一个不用动
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
    }
}
