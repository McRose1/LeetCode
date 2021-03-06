package BinarySearch;

/*  300. Longest Increasing Subsequence
    Given an unsorted array of integers, find the length of longest increasing subsequence.

    Example:
    Input: [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

    Note:
    There may be more than one LIS combination, it is only necessary for you to return the length.
    Your algorithm should run in O(n^2) complexity.

    Follow up:
    Could you improve it to O(nlogn) time complexity?
 */

/*  Greedy + Binary Search: Time = O(nlogn) Space = O(n)
    Note: d array does not result in longest increasing subsequence, but length of dp array will give you length of LIS.
    The insertion point is the point at which the key would be inserted into the array.
    [0, 8, 4, 12, 2]
    d: [0]
    d: [0, 8]
    d: [0, 4]
    d: [0, 4, 12]
    d: [0, 2, 12]
    观察可知，只有当新元素插入到原数组的末尾时，LIS 的长度才会更新，插入到头部和中间长度都是没有改变的
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] d = new int[n + 1];
        int len = 1;
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int left = 1;
                int right = len;
                // 如果找不到说明所有的数都比 nums[i] 大，此时要把 nums[i] 放在 d 的头部，也就是需要更新 d[1]，所以这里将 pos 设为 0
                int pos = 0;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}

/*  Arrays.binarySearch

        int[] d = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            // 通过二分搜索找到当前元素插入数组时应该放置的位置
            int i = Arrays.binarySearch(d, 0, len, num);
            // not found, index = (-(insertion point) - 1)
            if (i < 0) {
                i = -(i + 1);
            }
            d[i] = num;
            // 如果能插入最后一个元素后面，说明新元素比前面的都要大，长度++
            if (i == len) {
                len++;
            }
        }
        return len;
 */

/*  DP: Time = O(n^2) Space = O(n)
    dp[i] represents the length of the longest increasing subsequence possible considering the array elements upto the ith index only,
    by necessarily including the ith element.
    dp[i] = max(dp[j]) + 1, ∀0 ≤ j < i

        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            // 局部最大长度
            int len = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    len = Math.max(len, dp[j] + 1);
                }
            }
            dp[i] = len;
            // 全局最大长度
            max = Math.max(max, dp[i]);
        }
        return max;
 */

/*  Recursion with Memoization: Time = O(n^2) Space = O(n^2)
    In the previous approach, many recursive calls had to made again and again with the same parameters.
    This redundancy can be eliminated by storing the results obtained for a particular call in a 2-d memoization array memo.
    memo[i][j] represents the length of the LIS possible using nums[i] as
    the previous element considered to be included / not included in the LIS,
    with nums[j] as the current element considered to be included / not included in the LIS.

    public int lengthOfLIS(int[] nums) {
        int[][] memo = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS(nums, -1, 0, memo);
    }
    public int lengthofLIS(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo);
        }
        int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo);
        memo[previndex + 1][curpos] = Math.max(taken, nottaken);
        return memo[previndex + 1][curpos];
    }
 */

/*  Brute Force: Time = O(2^n) Space = O(n) TLE
    1. The current element is larger than the previous element included in the LIS.
    In this case, we can include the current element in the LIS.
    Thus, we find out the length of the LIS obtained by including it.
    Further, we also find out the length of LIS possible by not including the current element in the LIS.
    The value returned by the current function is, thus, the maximum out of the two lengths.
    2. The current element is smaller than the previous element included in the LIS.
    In this case, we can't include the current element in the LIS.
    Thus, we find out only the length of the LIS possible by not including the current element in the LIS,
    which is returned by the current function.

    public int lengthOfLIS(int[] nums) {
        return lengthofLIS(nums, Integer.MIN_VALUE, 0);
    }

    public int lengthofLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
        }
        int nottaken = lengthofLIS(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }
 */
