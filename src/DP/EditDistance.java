package DP;

/*  72. Edit Distance
    Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
    You have the following 3 operations permitted on a word:
    1. Insert a character
    2. Delete a character
    3. Replace a character

    Example 1:
    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation:
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')

    Example 2:
    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation:
    intention -> inention (remove 't')
    inention -> enention (replace 'i' with 'e')
    enention -> exention (replace 'n' with 'x')
    exention -> exection (replace 'n' with 'c')
    exection -> execution (insert 'u')
 */
/*  DP (Bottom-up): Time = O(n^2) Space = O(n^2)
    Replace         Delete
    Insert          Current
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 有一个字符串为空串
        if (m * n == 0) {
            return m + n;
        }

        int[][] dp = new int[m + 1][n + 1];

        // 边界状态初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 存在相同的字母，只用看去掉这个字母后的两个 substring 之间的关系
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[m][n];
    }
}

/*  Recursion with Memoization (Top-down)

    private int[][] memo;

    public int minDistance(String word1, String word2) {
        memo = new int[word1.length() + 1][word2.length() + 1];
        return helper(word1, word2);
    }

    private int helper(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // base case
        if (m == 0 || n == 0) {
            return Math.max(m, n);
        }

        // 记忆化递归
        if (memo[m][n] != 0) {
            return memo[m][n];
        }

        // 末尾字母相同
        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            memo[m][n] = helper(word1.substring(0, m - 1), word2.substring(0, n - 1));
        } else {
            memo[m][n] = 1 + Math.min(helper(word1, word2.substring(0, n - 1)),
                             (Math.min(helper(word1.substring(0, m - 1), word2),
                                      helper(word1.substring(0, m - 1), word2.substring(0, n - 1)))));
        }
        return memo[m][n];
    }
 */
