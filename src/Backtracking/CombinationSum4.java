package Backtracking;

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

/*  DP: Time = O(n*k) Space = O(k)
    简化成爬楼梯，想象成一共 4 级楼梯，每次只能爬 1、2、3 级，一共有多少种爬法
    状态：最后一步从（target-num）开始走，子问题：target-1, target-2...target=0
    转移方程：res[i] += res[i - num]

 */
public class CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        int[] sum = new int[target + 1];
        sum[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    sum[i] += sum[i - num];
                }
            }
        }
        return sum[target];
    }
}

/*  DFS + Memoization(HashMap): Time = O(2^n) Space = O(n)

    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        return helper(nums, target, map);
    }

    private int helper(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        if (map.containsKey(target)) {
            return map.get(target);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += helper(nums, target - nums[i], map);
        }
        map.put(target, res);
        return res;
    }
 */
