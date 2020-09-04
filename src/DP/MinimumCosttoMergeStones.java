package DP;

/*  1000. Minimum Cost to Merge Stones
    There are N piles of stones arranged in a row. The i-th pile has stones[i] stones.
    A move consists of merging exactly K consecutive piles into one pile,
    and the cost of this move is equal to the total number of stones in these K piles.
    Find the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.

    Example 1:
    Input: stones = [3,2,4,1], K = 2
    Output: 20
    Explanation:
    We start with [3, 2, 4, 1].
    We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
    We merge [4, 1] for a cost of 5, and we are left with [5, 5].
    We merge [5, 5] for a cost of 10, and we are left with [10].
    The total cost was 20, and this is the minimum possible.

    Example 2:
    Input: stones = [3,2,4,1], K = 3
    Output: -1
    Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.

    Example 3:
    Input: stones = [3,5,1,2,6], K = 3
    Output: 25
    Explanation:
    We start with [3, 5, 1, 2, 6].
    We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
    We merge [3, 8, 6] for a cost of 17, and we are left with [17].
    The total cost was 25, and this is the minimum possible.

    Note:
        o 1 <= stones.length <= 30
        o 2 <= K <= 30
        o 1 <= stones[i] <= 100
 */

import java.util.Arrays;

/*
    Q: When there will be a solution?
    A: (n-1) % (K-1) merges
    There are (n-1)/(K-1) merges
    Q: Total cost?
    A: Sum(A[i] * m[i]), m[i] is the number of times the i-th element got merged.
    Q: Merge cost of range [i, m] ~ [m+1, j]?
    A: Sum(A[i] ~ A[j]), independent of previous merges
 */

/*  三维 DP (Bottom-up): Time = O(n^3) Space = O(k * n^2)
    n <= 30 => Time complexity <= O(n^4)
    Impossible to do search. Greedy doesn't work either. DP is the only approach.
    Similar to LC 312. Burst Ballons.
    Non-overlapping subproblems: min cost of merging subarray A[i]~A[j] into k piles.
    dp[i][j][k]: min cost to merge subarray i~j into k piles
    init: dp[i][i][1] = 0 # no cost to merge one into one
    Transition:
        1. dp[i][j][k] = min{dp[i][m][1] + dp[m+1][j][k-1]}, i <= m < j, 2 <= k <= K
        2. dp[i][j][1] = dp[i][j][K] + sum(A[i]~A[j])
 */
public class MinimumCosttoMergeStones {
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) {
            return -1;
        }

        // prefix sum
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stones[i];
        }

        int[][][] dp = new int[n][n][K + 1];
        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b, Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < n; i++) {
            dp[i][i][1] = 0;
        }

        // sub-problem length
        for (int len = 2; len <= n; len++) {
            // start
            for (int i = 0; i <= n - len; i++) {
                // end
                int j = i + len - 1;
                // piles
                for (int k = 2; k <= K; k++) {
                    // split point
                    // m++ -> m += K - 1 (optimization)
                    for (int m = i; m < j; m += K - 1) {
                        // 左边 1 pile，右边 k - 1 pile
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]);
                    }
                }
                // merge the whole array into one
                dp[i][j][1] = dp[i][j][K] + sum[j + 1] - sum[i];
            }
        }
        return dp[0][n - 1][1];
    }
}

/*  二维 DP: Time = O(n^2) Space = O(n^2)
    The number of piles the right part can be merged into can be determined: (len - 1) % (K - 1) + 1
    And we need to merge them first before the final merge of left and right.
    dp[i][j]: min cost to merge A[i]~A[j] to (j-1)%(K-1)+1 piles
    Init: dp[i][j] = 0
    dp[i][j] = min{dp[i][m] + dp[m+1][j]} # m = i + Z*(K - 1)
    + sum(A[i] ~ A[j]) if (j - i) % (K - 1) == 0

        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) {
            return -1;
        }

        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stones[i];
        }

        int[][] dp = new int[n][n];
        for (int[] a : dp) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                for (int m = i; m < j; m += K - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m + 1][j]);
                }
                if ((len - 1) % (K - 1) == 0) {
                    dp[i][j] += sum[j + 1] - sum[i];
                }
            }
        }
        return dp[0][n - 1];
 */

/*  Recursion with Memo (Top-down)

    private int[][] memo;
    private int[] sum;
    private int K;

    public int mergeStones(int[] stones, int K) {
        this.K = K;
        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) {
            return -1;
        }

        sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stones[i];
        }

        memo = new int[n][n];
        for (int[] a : memo) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }

        return helper(0, n - 1);
    }

    private int helper(int i, int j) {
        int len = j - i + 1;
        if (len < K) {
            return 0;
        }
        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int m = i; m < j; m += K - 1) {
            res = Math.min(res, helper(i, m) + helper(m + 1, j));
        }
        if ((len - 1) % (K - 1) == 0) {
            res += sum[j + 1] - sum[i];
        }
        memo[i][j] = res;
        return res;
    }
 */