package SlidingWindow;

/*  209. Minimum Size Subarray Sum
    Given an array of n positive integers and a positive integer s,
    find the minimal length of a contiguous subarray of which the sum >= s.
    If there isn't one, return 0 instead.

    Example:
    Input: s = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: the subarray [4,3] has the minimal length under the problem constraint.

    Follow up: If you have figured out the O(n) solution,
               try coding another solution of which the time complexity is O(n log n).
 */

/*  Sliding Window: Time = O(n) Space = O(1)
    1. right 向右移增大窗口,直到窗口内的数字之和大于等于s.进行第 2 步
    2. 记录此时的长度,left 向右移动,开始减少长度,每减少一次,就更新最小长度.直到当前窗口内的数字和小于s,回到第一步
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        while (right < nums.length) {
            sum += nums[right];
            right++;
            while (sum >= s) {
                min = Math.min(min, right - left);
                sum -= nums[left];
                left++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

/*  Binary Search(根据长度): Time = O(nlogn) Space = O(1)
    使用二分法的关键在于寻找递增序列
    这个方法有点巧妙,将递增的概念用在长度上,对长度二分,并且在特定长度上在通过该长度的滑动窗口计算最大和

    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int minLen = 0;
        int maxLen = n;
        int min = Integer.MAX_VALUE;

        while (minLen <= maxLen) {
            int midLen = minLen + (maxLen - minLen) / 2;
            // 判断当前长度的最大和是否大于等于 s
            if (getMaxSum(midLen, nums) >= s) {
                maxLen = midLen - 1;
                min = midLen;
            } else {
                minLen = midLen + 1;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private int getMaxSum(int len, int[] nums) {
        int n = nums.length;
        int sum = 0;
        int maxSum = 0;
        // 初始化 maxSum
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        maxSum = sum;

        for (int i = len; i < n; i++) {
            // 加一个数字,减一个数字,保持长度不变
            sum += nums[i];
            sum = sum - nums[i - len];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
 */

/*  Binary Search: Time = O(nlogn) Space = O(1)
    sum[i]代表从 0 到 i 的累加和
    求从 i 开始的数字,总和大于等于 s 时的长度,只需找从 i+1 到 j 的和大于等于 s - nums[i] 即可
    求 i+1 到 j 的所有数字的和也可直接通过 sums[j] - sums[i] 得到
    让 s2 = s - nums[i], 得到 sum[j] - sums[i] >= s2 => sum[j] >= s2 + sums[i]
    变成了寻找一个 sums[j],使其刚好大于等于 s2 + sums[i]

    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = nums[i] + sums[i - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int s2 = s - nums[i];
            // 二分查找,目标值是 s2 + sums[i]
            int k = binarySearch(i, n - 1, sums, s2 + sums[i]);
            if (k != -1) {
                min = Math.min(min, k - i + 1);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // 寻找刚好大于 target 的 sums 下标,也就是大于等于 target 所有 sums 中最小的那个
    private int binarySearch(int start, int end, int[] sums, int target) {
        int mid = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (sums[mid] == target) {
                return mid;
            } else if (sums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return sums[mid] > target ? mid : -1;
    }
 */

/*  Brute Force: Time = O(n^2) Space = O(1)

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int start = i;
            int sum = 0;
            while (start < n) {
                sum += nums[start];
                start++;
                // Found the smallest subarray with sum>=s starting with index i, hence move to next index
                if (sum >= s) {
                    min = Math.min(min, start - i);
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
 */