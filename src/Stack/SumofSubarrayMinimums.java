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

/*  前驱/后继数组: Time = O(n) Space = O(n)
    prev[] 代表 Previous Less Element（PLE）
    next[] 代表 Next Less Element（NLE）
    A:    [3, 1, 2, 4]
    idx:   0  1  2  3
    prev: -1 -1  1  2
    next:  1  4  4  4
    所以 (i - prev[i]) * (next[i] - i) 就是最小值为 i 的 subarray 的个数，再乘上 A[i] 就是 i 所贡献的值
 */
public class SumofSubarrayMinimums {
    public int sumSubarrayMins(int[] A) {
        int MOD = 1000000007;
        int n = A.length;

        Stack<Integer> stack = new Stack<>();

        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && A[i] <= A[stack.peek()]) {
                stack.pop();
            }
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack = new Stack<>();
        int[] next = new int[n];
        for (int j = n - 1; j >= 0; j--) {
            while (!stack.isEmpty() && A[j] < A[stack.peek()]) {
                stack.pop();
            }
            next[j] = stack.isEmpty() ? n : stack.peek();
            stack.push(j);
        }

        // Use prev/next array to count answer
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (i - prev[i]) * (next[i] - i) * A[i];
            ans %= MOD;
        }

        return (int) ans;
    }
}

/*  Monotonic Stack: Time = O(n) Space = O(n)
    <val, count>：val 是 A[i] 的值，count 表示在 A[i] 前面，排在栈顶下的第 2 个元素后面，有多少个大于等于 A[i]（包括 A[i] 本身）

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
 */