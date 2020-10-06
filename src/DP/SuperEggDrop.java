package DP;

/*  887. Super Egg Drop
    You are given K eggs, and you have access to a building with N floors from 1 to N.
    Each egg is identical in function, and if an egg breaks, you cannot drop it again.
    You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break,
    and any egg dropped at or blow floor F will not break.
    Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
    Your goal is to know with certainty what value of F is.
    What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?

    Example 1:
    Input: K = 1, N = 2
    Output: 2
    Explanation:
    Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
    Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
    If it didn't break, then we know with certainty F = 2.
    Hence, we needed 2 moves in the worst case to know what F is with certainty.

    Example 2:
    Input: K = 2, N = 6
    Output: 3

    Example 3:
    Input: K = 3, N = 14
    Output: 4

    Note:
        1. 1 <= K <= 100
        2. 1 <= N <= 10000
 */

import java.util.Arrays;

/*  Recursion with Memo + BinarySearch: Time = O(knlogn) Space = O(kn)
    D(K, N) := 1 + min{max(D(K-1, i), D(K, N-i))} (1 <= i <= N)
    Let f(i) = D(K-1, i-1), f(i) is monotonically increasing with i
    Let g(i) = D(K, N-i), g(i) is monotonically decreasing with i
    We can use binary search to find i that minimizes max(f(i), g(i))
 */
public class SuperEggDrop {
    public int superEggDrop(int K, int N) {
        dp = new int[K + 1][N + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return helper(K, N);
    }

    private int[][] dp;

    private int helper(int k, int n) {
        // corner case
        // 没有鸡蛋，返回 0
        if (k == 0) return 0;
        // 1 个鸡蛋，需要移动 n 次
        if (k == 1) return n;
        // 楼层小于 1，移动 n 次
        if (n <= 1) return n;

        // memo
        if (dp[k][n] != Integer.MAX_VALUE) {
            return dp[k][n];
        }

        // binary search
        int l = 1;
        int r = n + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 比较 broken 和 unbroken 的情况
            if (helper(k - 1, mid - 1) >= helper(k, n - mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        // transition
        dp[k][n] = 1 + Math.max(helper(k - 1, l - 1), helper(k, n - l));
        return dp[k][n];
    }
}

/*  DP
    For the problem of D(K, N), N choices: drop an egg from the i-th floor (1 <= i <= N)
    And there are 2 outcomes:
    1. Broken: We have K-1 eggs left, and we know 0<=F<=i, i-1 (1~i-1) floors left
    2. Unbroken: We still have K eggs, and we know i<F<=N, N-1 (i+1~N) floors left
    We need to know the worst case => max(D(K-1, i), D(K, N-i))
    And among all the choices, we can pick the best one:
    D(K, N) := 1 + min{max(D(K-1, i), D(K, N-i))} (1 <= i <= N)
 */