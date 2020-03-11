/*
    Given an array nums of n integers and an integer target,
    find three integers in nums such that the sum is closet to target.
    Return the sum of the three integers.
    You may assume that each input would have exactly one solution.

    Example:
    Given array nums = [-1, 2, 1, -4], and target = 1.
    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

import java.util.Arrays;

public class ThreeSumCloset {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum > target) {
                    high--;
                } else {
                    low++;
                }
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
            }
        }
        return res;
    }
}
