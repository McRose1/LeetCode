package DP;

/*  115. Distinct Subsequences
    Given a string S and a string T, count the number of distinct subsequences of S which equals T.
    A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
    of the characters without disturbing the relative positions of the remaining characters.
    (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

    Example 1:
    Input: S = "rabbbit", T = "rabbit"
    Output: 3
    Explanation:
    As shown below, there are 3 ways you can generate "rabbit" from S.
    (The caret symbol ^ means the chosen letters)
    rabbbit
    ^^^^ ^^
    rabbbit
    ^^ ^^^^
    rabbbit
    ^^^ ^^^

    Example 2:
    Input: S = "babgbag", T = "bag"
    Output: 5
    Explanation:
    As shown below, there are 5 ways you can generate "bag" from S.
    (The caret symbol ^ means the chosen letters)
    babgbag
    ^^ ^
    babgbag
    ^^    ^
    babgbag
    ^    ^^
    babgbag
      ^  ^^
    babgbag
        ^^^
 */
/*  DP: Time = O(n^2) Space = O(n^2) -> O(n)滚动数组
    if t[i] == s[j]:
      dp[i][j] = dp[i - 1]dp[j - 1] # match s[j], t[i]
    + dp[i][j - 1]                  # skip s[j]
    else:
      dp[i][] = dp[i][j - 1]        # skip s[j]
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        int[][] dp = new int[tLen + 1][sLen + 1];
        // t 为空时
        for (int j = 0; j <= sLen; j++) {
            dp[0][j] = 1;
        }
        // 切记要 <= 而不是 =
        for (int i = 1; i <= tLen; i++) {
            for (int j = 1; j <= sLen; j++) {
                // 记得是 dp[] index 减去 1 才是 string index
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[tLen][sLen];
    }
}
