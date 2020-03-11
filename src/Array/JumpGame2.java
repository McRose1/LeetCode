package Array;

/*  45. Jump Game 2
    Given an array of non-negative integers, you are initially positioned at first index of the array.
    Each element in the array represents your maximum jump length at that position.
    Your goal is to reach the last index in the minimum number of jumps.

    Example 1:
    Input: [2,3,1,1,4]
    Output: 2
    Explanation:
    The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.

    Note: You can assume that you can always reach the last index.
 */

/*
    Greedy: Time = O(n) Space = O(1)
    furthest 记录当前位置 i 能走的最远距离
    curEnd 记录 jump 步数时，能走的最远距离
    当位置 i 超越 jump 能走的 curEnd 距离时，jump++，更新 curEnd = furthest
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int jump = 0, curEnd = 0, furthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            furthest = Math.max(furthest, i + nums[i]);
            if (i == curEnd) {
                jump++;
                curEnd = furthest;
            }
        }
        return jump;
    }
}

/*  BFS: Time = O(n) Space = O(1)

        if (nums == null || nums.length < 2) {
            return 0;
        }
        int level = 0, curEnd = 0, furthest = 0;
        int i = 0;
        while (curEnd - i + 1 > 0) {
            level++;
            for(; i <= curEnd; i++) {
                furthest = Math.max(furthest, nums[i] + 1);
                if (furthest > nums.length - 1) {
                    return level;
                }
            }
            curEnd = furthest;
        }


 */