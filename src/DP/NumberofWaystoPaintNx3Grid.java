package DP;

/*  1411. Number of Ways to Paint N x 3 Grid
    You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors:
    Red, Yellow or Green while making sure that no two adjacent cells have the same color
    (i.e no two cells that share vertical or horizontal sides have the same color).

    You are given n the number of rows of the grid.

    Return the number of ways you can paint this grid.
    As the answer may grow large, the answer must be computed modulo 10^9 + 7.

    Example 1:
    Input: n = 1
    Output: 12

    Example 2:
    Input: n = 2
    Output: 54

    Example 3:
    Input: n = 3
    Output: 246

    Example 4:
    Input: n = 7
    Output: 106494

    Example 5:
    Input: n = 5000
    Output: 30228214

    Constraints:
        o n == grid.length
        o grid[i].length == 3
        o 1 <= n <= 5000

    Hint:
    1. We will use DP approach. We will try all possible configuration.
    2. Let dp[idx][prev1col][prev2col][prev3col] be the number of ways to color the rows of the grid from idx to n-1
       keeping in mind that the previous row (idx-1) has colors prev1col, prev2col and prev3col.
       Build the dp array to get the answer.
 */

/*  Recursion with Memo

 */
public class NumberofWaystoPaintNx3Grid {

    private int[][][][] memo;

    public int numOfWays(int n) {
        memo = new int[n + 1][4][4][4];
        // Bottom-up
        return dfs(n, 0, 0, 0);
    }

    private int dfs(int n, int prev1col, int prev2col, int prev3col) {
        if (n == 0) {
            return 1;
        }
        if (memo[n][prev1col][prev2col][prev3col] != 0) {
            return memo[n][prev1col][prev2col][prev3col];
        }
        int res = 0;
        int[] colors = new int[] {1, 2, 3};
        for (int a : colors) {
            if (a == prev1col) {
                continue;
            }
            for (int b : colors) {
                if (b == prev2col || b == a) {
                    continue;
                }
                for (int c : colors) {
                    if (c == prev3col || c == b) {
                        continue;
                    }
                    res += dfs(n - 1, a, b, c);
                    res %= (1e9 + 7);
                }
            }
        }
        memo[n][prev1col][prev2col][prev3col] = res;
        return res;
    }
}

/*  DP

        long a121 = 6, a123 = 6, b121, b123, mod = (long)1e9 + 7;
        for (int i = 1; i < n; ++i) {
            b121 = a121 * 3 + a123 * 2;
            b123 = a121 * 2 + a123 * 2;
            a121 = b121 % mod;
            a123 = b123 % mod;
        }
        return (int)((a121 + a123) % mod);
 */