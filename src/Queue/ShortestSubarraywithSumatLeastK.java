package Queue;

/*  862. Shortest Subarray with Sum at Least K
    Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
    If there is no non-empty subarray with sum at least K, return -1.

    Example 1:
    Input: A = [1], K = 1
    Output: 1

    Example 2:
    Input: A = [1,2], K = 4
    Output: -1

    Example 3:
    Input: A = [2,-1,2], K = 3
    Output: 3

    Note:
        1. 1 <= A.length <= 50000
        2. -10 ^ 5 <= A[i] <= 10 ^ 5
        3. 1 <= K <= 10 ^ 9
 */

import java.util.Deque;
import java.util.LinkedList;

/*  Sliding Window + Deque: Time = O(n) Space = O(n)
    我们用 opt(y) 表示对于固定的 y，最大的满足 preSum[x] <= preSum[y] - K 的 x，这样所有 y - opt(y) 中的最小值即为答案
    我们发现有 2 条性质：
    1. 如果 x1 < x2 且 preSum[x2] <= preSum[x1]，那么 opt(y) 的值不可能为 x1
    2. 如果 opt(y1) 的值为 x，那么我们以后就不用再考虑 x 了
    我们维护一个关于前缀和数组 preSum 的单调队列，它是一个双端队列，其中存放了下标 x：x0, x1, ... 满足 preSum[x0], preSum[x1] 单调递增，为了满足性质一
    当我们遇到了一个新的下标 y 时，我们会在队尾移除若干元素，直到 preSum[x0], preSum[x1] ... 单调递增，同样为了满足性质一
    同时，我们会在队首也移除若干元素，如果 preSum[y] >= preSum[x0] + K，则将队首元素移除，直到该不等式不满足，为了满足性质二
 */
public class ShortestSubarraywithSumatLeastK {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        // 前缀和数组
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + (long) A[i];
        }

        // Want smallest y-x with P[y]-P[x] >= K
        int min = n + 1;

        // opt(y) candidates, as indices of preSum
        // 存放的是 0~i 之间可能的起始位置，i 为结束为止
        Deque<Integer> monoQueue = new LinkedList<>();

        for (int y = 0; y < preSum.length; y++) {
            // Want opt(y) = largest x with P[x] <= P[y] - k
            while (!monoQueue.isEmpty() && preSum[y] <= preSum[monoQueue.peekLast()]) {
                monoQueue.pollLast();
            }
            while (!monoQueue.isEmpty() && preSum[y] >= preSum[monoQueue.peekFirst()] + K) {
                min = Math.min(min, y - monoQueue.pollFirst());
            }
            monoQueue.offerLast(y);
        }
        return min < n + 1 ? min : -1;
    }
}
