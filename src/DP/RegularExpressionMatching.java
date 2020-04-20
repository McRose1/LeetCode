package DP;

/*  10. Regular Expression Matching
    Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.
    The matching should cover the entire input string (not partial)

    Note:
    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.

    Example 1:
    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".

    Example 2:
    Input:
    s = "aa"
    p = "a*"
    Output: true
    Explanation: '*' means zero or more of the preceding element, 'a'.
    Therefore, by repeating 'a' once, it becomes "aa".

    Example 3:
    Input:
    s = "ab"
    p = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".

    Example 4:
    Input:
    s = "aab"
    p = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

    Example 5:
    Input:
    s = "mississippi"
    p = "mis*is*p*."
    Output: false
 */
//  DP: Time = O(n^2) Space = O(n^2)
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int sLen = s.length();
        int pLen = p.length();

        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;

        // 初始化第一列
        for (int pIdx =  1; pIdx <= pLen; pIdx++) {
            if (p.charAt(pIdx - 1) == '*') {
                // ab* - #
                dp[pIdx][0] = dp[pIdx - 2][0];
            }
        }

        for (int pIdx = 1; pIdx <= pLen; pIdx++) {
            for (int sIdx = 1; sIdx <= sLen; sIdx++) {
                if (p.charAt(pIdx - 1) == '.' || p.charAt(pIdx - 1) == s.charAt(sIdx - 1)) {
                    dp[pIdx][sIdx] = dp[pIdx - 1][sIdx - 1];
                } else if (p.charAt(pIdx - 1) == '*') {
                    // 如果 * 前面一个字符等于当前需要匹配的 s 的字符，那么会有两种情况：
                    if (p.charAt(pIdx - 2) == s.charAt(sIdx - 1) || p.charAt(pIdx - 2) == '.') {
                        // 1. * 匹配 0 次，如：a - aa* -> dp[pIdx - 2][sIdx]
                        // 2. * 匹配 1 次或多次：aaa - aa* -> dp[pIdx][sIdx - 1]
                        dp[pIdx][sIdx] = dp[pIdx - 2][sIdx] || dp[pIdx][sIdx - 1];
                    // 如果 * 前面一个字符不等于当前需要匹配的 s 的字符，那么 * 的功能一定是让前面这个字符出现 0 次
                    } else {
                        // * 匹配前一个字符 0 次：a - ac*
                        dp[pIdx][sIdx] = dp[pIdx - 2][sIdx];
                    }
                }
            }
        }
        return dp[pLen][sLen];
    }
}

/*  DP(Bottom-Up):

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;

        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean first_match = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
 */

/*  Recursion

        // 递归出口
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty() && p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        // 只有 p 长度大于等于 2 的时候，才考虑 *
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // p 直接跳过两个字符 -> * 前边的字符出现 0 次, s = aa, p = **
            // p 不变，s = aa, p = a*，第一个 a 匹配，s 的第二个 a 接着和 p 的第一个 a 匹配
            return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
        } else {
            // s = aa, p = *a
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
 */
