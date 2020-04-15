package BinarySearch;

/*  Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
    Write an algorithm to minimize the largest sum among these m subarrays.

    Note: If n is the length of array, assume the following constraints are satisfied:
    1 ≤ n ≤ 1000
    1 ≤ m ≤ min(50, n)

    Example:
    Input:
    nums = [7,2,5,10,8]
    m = 2

    Output:
    18

    Explanation:
    There are four ways to split nums into two subarrays.
    The best way is to split it into [7,2,5] and [10,8],
    where the largest sum among the two subarrays is only 18.
 */

import java.util.Arrays;

/*  Binary Search + Greedy: Time = O(log(sum(nums))*n) Space = O(1)
    The answer must be in the range:
    [l=max(nums), r=sum(nums)]
    Given a candidate C, compute the number groups k needed
    if k > m:       // C to small
        l = C + 1
    else:
        r = C
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        int max = 0;
        int sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        int left = max;
        int right = sum;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int numOfSubarrays = split(nums, mid);
            // candidate is too small
            if (numOfSubarrays > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    // Using greedy to compute the count of subarray
    private int split(int[] nums, int validMaxSum) {
        int sum = 0;
        // count of subarray
        int count = 1;
        for (int num : nums) {
            // we need a new subarray
            if (sum + num > validMaxSum) {
                sum = num;
                count++;
            } else {
                sum += num;
            }
        }
        return count;
    }
}

/*  二维 DP: Time = O(mn^2) Space = O(mn)
    Sub-problem: shorter, fewer groups
    dp[i][j] = ans of sub-problem, splitting nums[0]~nums[i] into j groups
    初始化：dp[1][j] = sum(0, j)
    每次需要一个分割点 k：
    dp[i][j] = min(max(dp[i - 1][k], sums(k + 1, j)))  0<=k<j

        int n = nums.length;

        // 计算 preSum 数组
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // 初始化 dp 数组
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], preSum[i] - preSum[k]));
                }
            }
        }
        return dp[n][m];
 */
