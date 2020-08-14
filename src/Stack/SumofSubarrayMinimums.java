package Stack;

/*  907. Sum of Subarray Minimums
    Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
    Since the answer may be large, return the answer modulo 10^9 + 7.

    Example:
    Input: [3,1,2,4]
    Output: 17
    Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
    Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.

    Note:
    1. 1 <= A.length <= 30000
    2. 1 <= A[i] <= 30000
 */

import java.util.Stack;

/*  Monotonic Stack: Time = O(n) Space = O(n)
    <val, count>：val 是 A[i] 的值，count 表示在 A[i] 前面，排在栈顶下的第 2 个元素后面，有多少个大于等于 A[i]（包括 A[i] 本身）
 */
public class SumofSubarrayMinimums {
    public int sumSubarrayMins(int[] A) {
        int MOD = 1000000007;

        Stack<int[]> stack = new Stack<>();
        int res = 0;
        int dot = 0;

        // Add all answers for subarrays [i, j], i <= j
        for (int j = 0; j < A.length; j++) {
            // 初始化大于等于包含本身，所以是 1
            int count = 1;

            while (!stack.isEmpty() && stack.peek()[0] >= A[j]) {
                int[] temp = stack.pop();
                // 寻找 j 前面所有大于当前值的数量
                count += temp[1];
                // 因为此前栈中元素已经加入到 sum 中，所以出栈的时候也要相应的从 sum 中减去
                dot -= temp[0] * temp[1];
            }
            stack.push(new int[]{A[j], count});
            // 计算 [i, j] 的总和
            dot += A[j] * count;

            res += dot;
            res %= MOD;
        }
        return res;
    }
}
