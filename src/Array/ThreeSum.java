package Array;

/*  15. 3Sum
    Given an array nums of n integers, are there elements a, b, c in nums
    such that a + b + c = 0?
    Find all unique triplets in the array which gives the sum of zero.

    Note:
    The solution set must not contain duplicate triplets.

    Example:
    Given array nums = [-1, 0, 1, 2, -1, -4],

    A solution set is:
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//  Two Pointers
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 通过排序可以在第一个循环中避免 duplicate 的情况，还是后面双指针移动的前提
        Arrays.sort(nums);      // 这一步非常关键
        for (int i = 0; i < nums.length - 2; i++) {
            // avoid duplicate for first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i + 1;
            int high = nums.length - 1;
            int target = -nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == target) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    // 排除 duplicate
                    while (low < high && nums[low] == nums[low + 1]) {
                        low++;
                    }
                    // 排除 duplicate
                    while (low < high && nums[high] == nums[high - 1]) {
                        high--;
                    }
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < target) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return res;
    }
}
