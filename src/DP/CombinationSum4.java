package DP;

/*  377. Combination Sum 4
    Give an integer array with all positive numbers and no duplicates,
    find the number of possible combinations that add up to a positive integer target.

    Example:
    nums = [1, 2, 3]
    target = 4

    The possible combination ways are:
    (1, 1, 1, 1)
    (1, 1, 2)
    (1, 2, 1)
    (1, 3)
    (2, 1, 1)
    (2, 2)
    (3, 1)

    Note that different sequences are counted as different combinations.
    Therefore the output is 7.

    Follow up:
    What if negative numbers are allowed in the given array?
    How does it change the problem?
    What limitation we need to add to the question to allow negative numbers?
 */

/*  DP (Bottom-up): Time = O(n*k) Space = O(k)
    简化成爬楼梯，想象成一共 4 级楼梯，每次只能爬 1、2、3 级，一共有多少种爬法
    状态：最后一步从（target-num）开始走，子问题：target-1, target-2...target=0
    转移方程：dp[i] += dp[i - num]
 */
public class CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}

/*  Recursion with Memoization (Top-down): Time = O(n*k) Space = O(n)

    private int[] memo;
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) return 0;
        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        // 找到一种可能，加 1
        if (target == 0) return 1;
        // 没有找到
        if (target < 0) return 0;
        // 记忆化递归
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int num : nums) {
            res += helper(nums, target - num);
        }
        memo[target] = res;
        return res;
    }
 */
