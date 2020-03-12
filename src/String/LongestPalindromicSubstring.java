package String;

/*  5. Longest Palindromic String
    Given a string s, find the longest palindromic substring in s.
    You may assume that the maximum length of s is 1000.

    Example 1:
    Input: "babad"
    Output: "bab"

    Example 2:
    Input: "cbbd"
    Output: "bb"
 */
/* Expand Around Center (DP optimized): Time = O(n^2) Space = O(1)

 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        // 记录 substring 的头和尾
        int start = 0, end = 0;
        // 中心扩散，奇数有 n 个中心，偶数有 2*n -1 个中心
        for (int i = 0; i < s.length(); i++) {
            // 奇数的中心
            int len1 = helper(s, i, i);               // "bab"
            // 偶数的中心
            int len2 = helper(s, i, i + 1);     // "bb"
            // palindrome 最大长度
            int cur = Math.max(len1, len2);
            // 更新
            if (cur > end - start) {
                // 中心扩散，算出头和尾
                start = i - (cur - 1) / 2;
                end = i + cur / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // left 和 right 已经越界: right - left + 1 - 2
        return right - left - 1;
    }
}
/*  DP（二维）: Time = O(n^2) Space = O(n^2)

        int n = s.length();
        String res = "";
        int max = 0;

        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // j - i <= 2: aba, aa, a
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
 */

/*  Brute Force: Time = O(n^3) Space = O(1)
    判断是否是 palindrome
    compare head and tail

    # entire string
    def isPalindrome:
        n = len(s)
        for i in range (n//2 + 1)
            if s[i] != s[n - i - 1]: return false
        return true

    # substring s[l~r]
    def isPalindrome(s, l, r):
        while l < r:
            if s[l] != s[r]: return false
            l += 1
            r -= 1
        return true

    public String longestPalindrome(String s) {
        String res = "";
        int max = 0;
        // 遍历所有可能的 substring
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (isPalindrome(s, i, j) && j - i + 1 > max) {
                    res = s.substring(i, j + 1);
                    max = j - i + 1;
                    break;
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

 */

/*  Manacher's Algorithm

 */
