package DP;

/*  516. Longest Palindromic Subsequence
    Given a string s, find the longest palindromic subsequence's length in s.
    You may assume that the maximum length of s is 1000.

    Example 1:
    Input:
    "bbbab"
    Output:
    4
    One possible longest palindromic subsequence is "bbbb".

    Example 2:
    Input:
    "cbbd"
    Output:
    2
    One possible longest palindromic subsequence is "bb".

    Constraints:
        o 1 <= s.length <= 1000
        o s consists only of lowercase English letters.
 */

/*  DP: Time = O(n^2) Space = O(n^2)

 */
class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) dp[i][j] = 1;
                else if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}

/*  Space Optimized DP: Time = O(n^2) Space = O(n)

        int n = s.length();
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            int sub = 0;
            for (int j = i + 1; j < n; j++) {
                int mIJ;
                if (s.charAt(i) == s.charAt(j)) {
                    mIJ = sub + 2;
                } else {
                    mIJ = Math.max(dp[j - 1], dp[j]);
                }
                sub = dp[j];
                dp[j] = mIJ;
            }
        }
        return dp[n - 1];
 */