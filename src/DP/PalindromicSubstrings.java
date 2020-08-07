package DP;

/*  647. Palindromic Substrings
    Given a string, your task is to count how many palindromic substrings in this string.
    The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

    Example 1:
    Input: "abc"
    Output: 3
    Explanation: Three palindromic strings: "a", "b", "c".

    Example 2:
    Input: "aaa"
    Output: 6
    Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

    Note: The input string length won't exceed 1000.

    Hint:
    o How can we reuse a previously computed palindrome to compute a larger palindrome?
    o If "aba" is a palindrome, is "xabax" a palindrome? Similarly is "xabay" a palindrome?
    o Complexity based hint:
      If we use brute-force and check whether for every start and end position a substring is a palindrome
      we have O(n^2) start - end pairs and O(n) palindromic checks.
      Can we reduce the time for palindromic checks to O(1) by reusing some previous computation?
 */

//  二维 DP: Time = O(n^2) Space = O(n^2)
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // j - i <= 2 这一判断非常重要，因为 aa (j - i == 1) 和 aba (j - i == 2) 只要首尾相等，一定回文
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (dp[i][j]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

/*  中心扩散法

        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            // 奇数
            int left = i;
            int right = i;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
            // 偶数
            left = i;
            right = i + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }
        return count;
 */

/*  Expand Around Center (LC): Time = O(n^2) Space = O(1)
    在长度为 n 的字符串中，可能的回文串中心位置有 2n-1 个：字母，或两个字母中间。
    对于每个可能的回文串中心位置，尽可能扩大它的回文区间 [left, right]。

        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int ans = 0;
        for (int center = 0; center <= 2 * n - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
 */

/*  Brute Force: Time = O(n^3) Space = O(1)

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s.substring(i, j + 1))) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindrome(String sub) {
        int left = 0;
        int right = sub.length() - 1;
        while (left <= right) {
            if (sub.charAt(left) != sub.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
 */