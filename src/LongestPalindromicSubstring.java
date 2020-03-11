/*
    Given a string s, find the longest palindromic substring in s.
    You may assume that the maximum length of s is 1000.

    Example 1:
    Input: "babad"
    Output: "bab"

    Example 2:
    Input: "cbbd"
    Output: "bb"
 */
// Expand Around Center
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = helper(s, i, i);               // "bab"
            int len2 = helper(s, i, i + 1);     // "bb"
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);

    }

    public int helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
/*
    DP

        int n = s.length();
        String res = "";
        int max = 0;

        boolean[][] dp = new boolean[n][n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
 */