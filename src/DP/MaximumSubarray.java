package DP;

/*  53. Maximum Subarray
    Given an integer array nums, find the contiguous subarray (containing at least one number)
    which has the largest sum and return its sum.

    Example:
    Input: [-2,1,-3,4,-1,2,1,-5,4],
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.

    Follow up:
    If you have figured out the O(n) solution,
    try coding another solution using the divide and conquer approach, which is more subtle.
 */

//  DP: Time = O(n) Space = O(n)
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + Math.max(dp[i - 1], 0);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

/*  Divide and Conquer: Time = O(nlogn) Space = O(n)
    折半，定义函数 getSubMax 输出 num[start, end) 中子数组最大值
    对于我们要找的和最大子数组有两种情况：
    1. mid 不在我们要找的子数组中 ->
    子数组的最大值要么是 mid 左半部分数组的子数组产生，要么是右半部分产生
    2. mid 在我们要找的子数组中 ->
    从 mid 左边扩展和右边扩展，找出两边和最大的时候，加起来
    如果左边或者右边最大的小于 0，就不加了

    public int maxSubArray(int[] nums) {
        return getSubMax(0, nums.length - 1, nums);
    }

    private int getSubMax(int start, int end, int[] nums) {
        // 递归出口
        if (start == end) {
            return nums[start];
        }
        int mid = (start + end) / 2;
        // 要找的数组不包含 mid, 然后得到左边和右边的最大值
        int leftMax = getSubMax(start, mid, nums);
        int rightMax = getSubMax(mid + 1, end, nums);
        // 要找的数组包含 mid
        int containsMidMax = getContainMidMax(start, end, mid, nums);
        // 返回它们 3 个中最大的
        return Math.max(containsMidMax, Math.max(leftMax, rightMax));
    }

    private int getContainMidMax(int start, int end, int mid, int[] nums) {
        int containsMidLeftMax = 0;     // 初始化为 0，防止最大的值也小于 0
        // 找左边最大
        if (mid > 0) {
            int sum = 0;
            for (int i = mid - 1; i >= 0; i--) {
                sum += nums[i];
                if (sum > containsMidLeftMax) {
                    containsMidLeftMax = sum;
                }
            }
        }
        int containsMidRightMax = 0;
        // 找右边最大
        if (mid < end) {
            int sum = 0;
            for (int i = mid + 1; i <= end; i++) {
                sum += nums[i];
                if (sum > containsMidRightMax) {
                    containsMidRightMax = sum;
                }
            }
        }
        return containsMidLeftMax + nums[mid] + containsMidRightMax;
    }
 */