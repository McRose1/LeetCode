package Array;

/*  628. Maximum Product of Three Numbers
    Given an integer array, find three numbers whose product is maximum and output the maximum product.

    Example 1:
    Input: [1,2,3]
    Output: 6

    Example 2:
    Input: [1,2,3,4]
    Output: 24

    Note:
    1. The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
    2. Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */

/*  Single Scan: Time = O(n) Space = O(1)
    实际上我们只用找出数组中最大的 3 个数以及最小的 2 个数即可。
 */
public class MaximumProductofThreeNumbers {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min1) {
                min2 = min1;
                min1 = num;
            } else if (num <= min2) {
                min2 = num;
            }
            if (num >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                max3 = max2;
                max2 = num;
            } else if (num >= max3) {
                max3 = num;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}

/*  Sorting: Time = O(nlogn) Space = O(1)
    我们将数组进行升序排序，如果数组中所有的元素都是非负数，那么答案即为最后 3 个元素的乘积。
    如果数组中出现了负数，那么我们还需要考虑乘积中包含负数的情况，显然选择最小的 2 个负数和最大的 1 个正数是最优的。

        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[0] * nums[1] * nums[n - 1]);
 */
