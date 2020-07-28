package Sort;

/*  280. Wiggle Sort
    Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]...

    Example:
    Input: nums = [3,5,2,1,6,4]
    Output: One possible answer is [3,5,1,6,2,4]
 */

/*  Sort: Time = 0(n) Space = O(1)
    找规律，奇数位大于等于偶数位
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 如果是奇数位并且却小于前一位 或 如果是偶数位并且却大于前一位
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                int temp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = temp;
            }
        }
    }
}

/*  Sort: Time = 0(nlogn) Space = O(1)
    其实就是 2 个为一对相互交换，除了第一个不动

        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
 */
