package DP;

/*  375. Guess Number Higher or Lower 2
    We are playing the Guess Game. The game is as follows:
    I pick a number from 1 to n. You have to guess which number I picked.
    Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
    However, when you guess a particular number x, and you guess wrong, you pay $x.
    You win the game when you guess the number I picked.

    Example:
    n = 10, I pick 8.

    First round:  You guess 5, I tell you that it's higher. You pay $5.
    Second round: You guess 7, I tell you that it's higher. You pay $7.
    Third round:  You guess 9, I tell you that it's lower. You pay $9.

    Game over. 8 is the number I picked.

    You end up paying $5 + $7 + $9 = $21.

    Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

    Hint:
    o The best strategy to play the game is to minimize the maximum loss you could possibly face.
      Another strategy is to minimize the expected loss. Here, we are interested in the first scenario.
    o Take a small example (n = 3). What do you end up paying in the worst case?
    o The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dp.
    o As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of the worst-case loss?
 */
/*  DP (Optimized)
    Minimax: i -> j中，任意猜一个数 x，获胜所花的钱为 x + max(helper(i, x-1), helper(x+1, j))
    dp[i][j]: i -> j 肯定赢需要多少钱
 */
public class GuessNumberHigherorLower2 {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        // 从区间长度 2 开始遍历
        for (int len = 2; len <= n; len++) {
            // 每次选取区间的起点，start + len - 1 是区间终点
            for (int start = 1; start + len - 1 <= n; start++) {
                int j = start + len - 1;
                int min = Integer.MAX_VALUE;
                // Optimized: start -> start + (len - 1) / 2
                for (int guess = start + (len - 1) / 2; guess < j; guess++) {
                    int res = guess + Math.max(dp[start][guess - 1], dp[guess + 1][j]);
                    min = Math.min(min, res);
                }
                dp[start][j] = min;
            }
        }
        return dp[1][n];
    }
}

/*  DP (bottom-up)

        int[][] dp = new int[n + 1][n + 1];
        // 按列从左往右
        for (int j = 2; j <= n; j++) {
            // 按行从下往上
            for (int i = j - 1; i > 0; i--) {
                dp[i][j] = Integer.MAX_VALUE;
                // 遍历区间 [i, j] 中的每一个分割点（猜的数）
                for (int x = i; x < j; x++) {
                    dp[i][j] = Math.min(dp[i][j], x + Math.max(dp[i][x - 1], dp[x + 1][j]));
                }
            }
        }
        return dp[1][n];
 */

/*  Recursion with Memoization (Top-down)
    Minimax: i -> j中，任意猜一个数 x，获胜所花的钱为 x + max(helper(i, x-1), helper(x+1, j))
    dp[i][j]: i -> j 肯定赢需要多少钱

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return helper(dp, 1, n);
    }
    private int helper(int[][] dp, int i, int j) {
        if (i >= j) return 0;
        if (dp[i][j] != 0) return dp[i][j];
        int res = Integer.MAX_VALUE;
        for (int x = i; x <= j; x++) {
            res = Math.min(res, Math.max(helper(dp, i, x - 1), helper(dp, x + 1, j)) + x);
        }
        dp[i][j] = res;
        return res;
    }
 */

/*  Brute Force (Recursion): Time = O(n!) Space = O(n) -> TLE

    public int getMoneyAmount(int n) {
        return calculate(1, n);
    }

    private int calculate(int low, int high) {
        if (low >= high) return 0;
        int min = Integer.MAX_VALUE;
        // Optimized
     // for (int i = (low + high) / 2)
        for (int i = low; i <= high; i++) {
            int res = i + Math.max(calculate(low, i - 1), calculate(i + 1, high));
            min = Math.min(min, res);
        }
        return min;
    }
 */