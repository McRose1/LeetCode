package Math;

/*  264. Ugly Number 2
    Write a program to find the n-th ugly number.

    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

    Example:
    Input: n = 10
    Output: 12
    Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

    Note:
    1 is typically treated as an ugly number.
    Input is within the 32-bit signed integer range: [−2^31,  2^31 − 1].

    Hint:
    1. The naive approach is to call isUgly for every number until you reach the nth one. (TLE)
       Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
    2. An ugly number must be multiplied by either 2, 3 or 5 from a smaller ugly number.
    3. The key is how to maintain the order of the ugly numbers.
       Try a similar approach of merging from three sorted lists: L1, L2, and L3.
    4. Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
 */

/*  Math + DP: Time = O(1) Space = O(1)
    乘 2: 1×2, 2×2, 3×2, 4×2, 5×2, 6×2, 8×2, 9×2,…
    乘 3: 1×3, 2×3, 3×3, 4×3, 5×3, 6×3, 8×3, 9×3,…
    乘 5: 1×5, 2×5, 3×5, 4×5, 5×5, 6×5, 8×5, 9×5,…
    我们需要做的就是把上边三组按照顺序合并起来
 */
public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        // 相当于维护了 3 个指针
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int next2 = dp[index2] * 2;
            int next3 = dp[index3] * 3;
            int next5 = dp[index5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));

            if (dp[i] == dp[index2] * 2) {
                index2++;
            }
            if (dp[i] == dp[index3] * 3) {
                index3++;
            }
            if (dp[i] == dp[index5] * 5) {
                index5++;
            }
        }
        return dp[n - 1];
    }
}

/*  TreeSet

        TreeSet<Long> tset = new TreeSet<>();
        tset.add(1l);
        for (int i = 1; i < n; i++) {
            long first = tset.pollFirst();
            tset.add(first * 2);
            tset.add(first * 3);
            tset.add(first * 5);
        }
        return tset.first().intValue();
 */

/*  Heap

        if (n == 1) return 1;
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(1l);

        for (long i = 1; i < n; i++) {
            long temp = queue.poll();
            // remove duplicates
            while (!queue.isEmpty() && queue.peek() == temp) {
                temp = queue.poll();
            }
            queue.offer(temp * 2);
            queue.offer(temp * 3);
            queue.offer(temp * 5);
        }
        return queue.poll().intValue();
 */
