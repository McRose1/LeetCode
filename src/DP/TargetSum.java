package DP;

/*  494. Target Sum
    You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
    Now you have 2 symbols + and -.
    For each integer, you should choose one from + and - as its new symbol.
    Find out how many ways to assign symbols to make sum of integers equal to target S.

    Example:
    Input: nums is [1, 1, 1, 1, 1], S is 3.
    Output: 5
    Explanation:

    -1+1+1+1+1 = 3
    +1-1+1+1+1 = 3
    +1+1-1+1+1 = 3
    +1+1+1-1+1 = 3
    +1+1+1+1-1 = 3

    There are 5 ways to assign symbols to make the sum of nums be target 3.

    Constraints:
        o The length of the given array is positive and will not exceed 20.
        o The sum of elements in the given array will not exceed 1000.
        o Your output answer is guaranteed to be fitted in a 32-bit integer.
 */

/*  二维 DP: Time = O(l*n) Space = O(l*n)

 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }
}

/*  一维 DP: Time = O(l*n) Space = O(n)

        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
 */

/*  Recursion with Memo: Time = O(l*n) Space = O(l*n)

    public int findTargetSumWays(int[] nums, int S) {
        // [-1000 ~ 1000] -> 0 ~ 2001
        memo = new int[nums.length][2001];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return helper(nums, 0, 0, S);
    }

    private int count = 0;
    private int[][] memo;

    private int helper(int[] nums, int idx, int sum, int S) {
        if (idx == nums.length) {
            if (sum == S) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (memo[idx][sum + 1000] != Integer.MIN_VALUE) {
                return memo[idx][sum + 1000];
            }
            int add = helper(nums, idx + 1, sum + nums[idx], S);
            int subtract = helper(nums, idx + 1, sum - nums[idx], S);
            memo[idx][sum + 1000] = add + subtract;
            return memo[idx][sum + 1000];
        }
    }
 */

/*  DFS: Time = O(2^n) Space = O(n)

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        dfs(nums, S, 0);
        return count;
    }

    private int count = 0;

    private void dfs(int[] nums, int target, int idx) {
        if (idx == nums.length) {
            if (target == 0) {
                count++;
            }
        } else {
            dfs(nums, target - nums[idx], idx + 1);
            dfs(nums, target + nums[idx], idx + 1);
        }
    }
 */