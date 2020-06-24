package Greedy;

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

/*  Greedy: Time = O(n) Space = O(1)
    furthest 记录当前位置 i 能走的最远距离
    curEnd 记录从当前 index 出发，能走的最远距离（边界）
    当位置 i 到达 jump 能走的 curEnd 边界时，jump++，更新 curEnd = furthest
 */
public class JumpGame2 {
    public int jump(int[] nums) {                       // [2,3,1,1,4]
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int jump = 0, curEnd = 0, furthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {     // 0; 1; 2
            furthest = Math.max(furthest, i + nums[i]); // max(0, 0+2)=2; max(2, 1+3)=4; max(4, 2+1)=4
            // 遇到边界，就更新边界，并且步数加 1
            if (i == curEnd) {                          // 0 = 0; 2 = 2
                jump++;                                 // 1; 2
                curEnd = furthest;                      // 2; 4
                if (curEnd == nums.length - 1) break;
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