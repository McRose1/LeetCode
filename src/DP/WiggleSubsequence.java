package DP;

/*  376. Wiggle Subsequence
    A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative.
    The first difference (if one exists) may be either positive or negative.
    A sequence with fewer than two elements is trivially a wiggle sequence.

    For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative.
    In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences,
    the first because its first two differences are positive and the second because its last difference is zero.

    Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
    A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
    leaving the remaining elements in their original order.

    Example 1:
    Input: [1,7,4,9,2,5]
    Output: 6
    Explanation: The entire sequence is a wiggle sequence.

    Example 2:
    Input: [1,17,5,10,13,15,10,5,16,8]
    Output: 7
    Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

    Example 3:
    Input: [1,2,3,4,5,6,7,8,9]
    Output: 2

    Follow up: Can you do it in O(n) time?
 */

/*  Optimized DP: Time: O(n) Space = O(1)
    DP 过程中更新 up[i] 和 down[i]，其实只需要 up[i - 1] 和 down[i - 1]，因此我们可以通过只记录最后一个元素的值而不使用数组来节省空间
 */
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(down, up);
    }
}

/*  Greedy: Time = O(n) Space = O(1)
    维护一个遍历 prevdiff，用来表示当前数字的序列是上升还是下降

        if (nums.length < 2) return nums.length;
        int prevdiff = nums[1] - nums[0];
        int count = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                count++;
                prevdiff = diff;
            }
        }
        return count;
 */

/*  Linear DP: Time: O(n) Space = O(n)

        if (nums.length < 2) return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
 */

/*  DP: Time: O(n^2) Space = O(n)
    up[i] 记录目前为止最长的，以第 i 个元素摆动上升结尾的，摆动序列的长度，
    down[] 记录目前为止最长的，以第 i 个元素摆动下降结尾的，摆动序列的长度

        if (nums.length < 2) return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
 */

/*  Brute Force: Time = O(n!) Space = O(n) -> TLE

    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        return 1 + Math.max(calculate(nums, 0, true), calculate(nums, 0, false));
    }

    private int calculate(int[] nums, int index, boolean isUp) {
        int max = 0;
        for (int i = index + 1; i < nums.length; i++) {
            if ((isUp && nums[i] > nums[index]) || (!isUp && nums[i] < nums[index])) {
                max = Math.max(max, 1 + calculate(nums, i, !isUp));
            }
        }
        return max;
    }
 */