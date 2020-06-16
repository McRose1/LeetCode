package DP;

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

/*  Expand Around Center (DP optimized): Time = O(n^2) Space = O(1)
    1. if s[i~j] is a palindrome, s[i+1~j-1] us also a one
    2. if s[i~j] is not a palindrome, then s[i-1~j+1] can not be a palindrome
    e.g. abcea, bce is not a palindrome, abcea can't be one
    Instead of starting from two sides, we can start from the center.
    For each index i, or pair (i, i+1) we take it as the middle elements and expand toward two ends to find the longest palindrome
    if dp[l + 1][r - 1] and s[l] == s[r]: dp[l][r] = True
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0;
        int len = 0;
        // 每次循环选择一个中心，进行左右扩展，判断左右字符是否相等
        for (int i = 0; i < s.length(); i++) {
            // 字符串长度为奇数，中心元素只有 1 个："eab c bad"
            int len1 = getLen(s, i, i);
            // 字符串长度为偶数，中心元素有 2 个："eab cc bad"
            int len2 = getLen(s, i, i + 1);
            // palindrome 最大长度
            int cur = Math.max(len1, len2);
            // 更新最大长度
            if (cur > len) {
                // 中心扩散，算出头和尾
                len = cur;
                start = i - (cur - 1) / 2;
            }
        }
        return s.substring(start, start + len);
    }

    private int getLen(String s, int left, int right) {
        // left 和 right 指针均在界内并且所指的字符相等，便可以继续向两边扩散
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

        for (int i = n - 1; i >= 0; i--) {      for (int j = 0; i < n; i++)
            for (int j = i; j < n; j++) {           for (int i = 0; i <= j; i++)
                // j - i <= 2: aba, aa, a
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                // 以 i 为 start，j 为 end 的字符串是回文字符串并且长度大于 res，就更新 res
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
 */

/*  Manacher's Algorithm（马拉车算法）

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