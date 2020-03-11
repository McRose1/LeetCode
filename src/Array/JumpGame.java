package Array;

/*  55. Jump Game
    Given an array of non-negative integers, you are initially positioned at first index of the array.
    Each element in the array represents your maximum jump length at that position.
    Determine if you are able to reach the last index.

    Example 1:
    Input: [2,3,1,1,4]
    Output: true
    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

    Example 2:
    Input: [3,2,1,0,4]
    Output: false
    Explanation: You will always arrive at index 3 no matter what.
    Its maximum jump length is 0, which makes it impossible to reach the last index.
 */

/*
    Greedy: Time = O(n) Space = O(1)
    Iterating right-to-left, for each position we check if there is a potential jump that reaches a GOOD index:
    currPosition + nums[currPosition] >= leftmostGoodIndex.
    If we can reach a GOOD index, then our position is itself GOOD.
    Also, this new GOOD position will be the new leftmost GOOD index.
    Iterations continues until the beginning of the array.
    If first position is a GOOD index then we can reach the last index from the first position.
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}

/*  Backtracking (recursive): Time = O(2^n) Space = O(n)
    This is the inefficient solution where we try every single jump pattern that takes us from the first position to the last.
    We start from the first position and jump to every index that is reachable.
    We repeat the process until last index is reached. When stuck, backtrack.

    public boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nexPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
        // Optimized: check the nextPosition from right to left
        for (int nextPosition = furthestJump; nextPosition > position; nextPosition--)
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }
    public boolean canJump(int[] nums) {
        return canJumpFromPosition(0, nums);
    }
 */

/*  DP Top-down: Time = O(n^2) Space = O(2n) -> O(n) First n originates from recursion, second n comes from the usage of the memo table
    can be thought of as optimized backtracking. it relies on the observation that once we determine that a certain
    index is good/bad, this result will never change, we can store the result and not need to recompute it every time.
    using memoization, make an array memo and let its values be either one of: GOOD, BAD, UNKNOWN.

    enum Index {
        GOOD, BAD, UNKNOWN;
    }
        Index[] memo;
        public boolean canJumpFromPosition(int position, int[] nums) {
            if (memo[position] != Index.UNKNOWN) {
                return memo[position] == Index.GOOD ? true : false;
            }
            int furthestJump = Math.min(position + nums[position], nums.length - 1);
            for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
                if (canJumpFromPosition(nextPosition, nums)) {
                    memo[position] = Index.GOOD;
                    return true;
                }
            }
            memo[position] = Index.BAD;
            return false;
        }
        public boolean canJump(int[] nums) {
            memo = new Index[nums.length];
            for (int i = 0; i < memo.length; i++) {
                memo[i] = Index.UNKNOWN;
            }
            memo[memo.length - 1] = Index.GOOD;
            return canJumpFromPosition(0, nums);
        }
 */

/*  DP Bottom-up: Time = O(n^2) Space = O(n)
    Top-down to bottom-up conversion is done by eliminating recursion.
    In practice, this achieves better performance as we no longer have the method stack overhead and might even benefit from some caching.
    The recursion is usually eliminated by trying to reverse the order of the steps from the top-down approach.

    enum Index {
        GOOD, BAD, UNKNOWN;
    }
        public boolean canJump(int[] nums) {
            Index[] memo = new Index(nums.length);
            for (int i = 0; i < memo.length; i++) {
                memo[i]= Index.UNKNOWN;
            }
            memo[memo.length - 1] == Index.GOOD;

            for (int i = num.length - 2; i >= 0; i--) {
                int furthestJump = Math.min(i + nums[i], nums.length - 1);
                for (int j = i + 1; j <= furthestJump; j++) {
                    if (memo[j] == Index.GOOD) {
                        memo[i] = Index.GOOD;
                        break;
                    }
                }
            }
            return memo[0] == Index.GOOD;
        }
 */