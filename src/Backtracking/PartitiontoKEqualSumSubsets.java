package Backtracking;

/*  698. Partition to K Equal Sum Subsets
    Given an array of integers nums and a positive integer k,
    find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

    Example:
    Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
    Output: True
    Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

    Note:
        o 1 <= k <= len(nums) <= 16.
        o 0 < nums[i] < 10000.

    Hint:
    We can figure out what target each subset must sum to.
    Then, let's recursively search, where at each call to our function, we choose which of k subsets the next value will join.
 */

/*  Backtracking

 */
public class PartitiontoKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        // corner case
        if (sum % k != 0 || max > sum / k) {
            return false;
        }
        int target = sum / k;
        return backtrack(nums, k, target, 0, 0, new boolean[nums.length]);
    }

    private boolean backtrack(int[] nums, int k, int target, int curSum, int nextIdx, boolean[] visited) {
        // 递归出口
        if (k == 0) {
            return true;
        }
        // 得到解
        if (curSum == target) {
            return backtrack(nums, k - 1, target, 0, 0, visited);
        }

        for (int i = nextIdx; i < nums.length; i++) {
            if (!visited[i] && curSum + nums[i] <= target) {
                visited[i] = true;
                if (backtrack(nums, k, target, curSum + nums[i], i + 1, visited)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
