package Backtracking;

/*  473. Matchsticks to Square
    Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has,
    please find out a way you can make one square by using up all those matchsticks.
    You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
    Your input will be several matchsticks the girl has, represented with their stick length.
    Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

    Example 1:
    Input: [1,1,2,2,2]
    Output: true
    Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.

    Example 2:
    Input: [3,3,3,3,4]
    Output: false
    Explanation: You cannot find a way to form a square with all the matchsticks.

    Note:
    1. The length sum of the given matchsticks is in the range of 0 to 10^9.
    2. The length of the given matchstick array will not exceed 15.
 */

import java.util.Arrays;
/*  Backtracking
    This problem boils down to splitting an array of integers into 4 subsets where all of these subsets are:
    1. mutually exclusive i.e. no specific element of the array is shared by any two of these subsets;
    2. have the same sum which is equal to the side of square.
 */
public class MatchstickstoSquare {
    private int target;

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        target = sum / 4;
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(nums);
        return dfs(nums, 0, 0, 1, new boolean[nums.length]);
    }

    private boolean dfs(int[] nums, int index, int tmp, int group, boolean[] visited) {
        // 已经遍历完 3 个边，都满足说明第 4 个也会满足
        if (group == 4) return true;
        // 当前累加和已经超过边长
        if (tmp > target) return false;
        // 1 条边已经成功拼出，换下一条
        if (tmp == target) return dfs(nums, 0, 0, group + 1, visited);

        for (int i = index; i < nums.length; i++) {
            // 跳过遍历过的火柴
            if (visited[i]) continue;
            // 如果存在相同长度的火柴，首次出现的没成功，说明相同长度都是不成功的，直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            visited[i] = true;
            if (dfs(nums, i + 1, tmp + nums[i], group, visited)) {
                return true;
            }
            // backtracking
            visited[i] = false;
        }
        return false;
    }
}

/*  DP

 */
