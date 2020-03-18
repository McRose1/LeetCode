package DP;

/*  44. Wildcard Matching
    Given an input string (s) and a pattern (p), implement wildcard pattern matching with support '?' and '*'.
    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
    The matching should cover the entire input string (not partial).

    Note:
    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like ? or *.

    Example 1:
    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".

    Example 2:
    Input:
    s = "aa"
    p = "*"
    Output: true
    Explanation: '*' matches any sequence.

    Example 3:
    Input:
    s = "cb"
    p = "?a"
    Output: false
    Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

    Example 4:
    Input:
    s = "adceb"
    p = "*a*b"
    Output: true
    Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

    Example 5:
    Input:
    s = "acdcb"
    p = "a*c?b"
    Output: false
 */

//  Two Pointers: Time = O(n) Space = O(1)
public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int prevStar = -1, matchStart = 0;
        // 遍历整个字符串
        while (i < s.length()) {
            // 一对一匹配，两指针同时后移
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            // 碰到 *，假设它匹配空串，并且用 prevStar 记录 * 的位置，记录当前字符串的位置，j 后移
            } else if (j < p.length() && p.charAt(j) == '*') {
                prevStar = j;
                matchStart = i;
                j++;
            // 当前字符不匹配，并且也没有 *，回退
            // j 回到 * 的下一个位置
            // match 更新到下一个位置
            // i 回到更新后的 match
            // 这步代表用 * 匹配了一个字符
            } else if (prevStar != -1) {
                j = prevStar + 1;
                matchStart++;
                i = matchStart;
            //字符不匹配，也没有 *，返回 false
            } else {
                return false;
            }
        }
        // 将末尾多余的 * 直接匹配空串 例如 text = ab, pattern = a*******
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }
}

/*  DP (Bottom-Up)

        if (s == null || p == null) return false;
        // 多一维的空间，因为求 dp[len - 1][j] 的时候需要知道 dp[len][j] 的情况
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;
        for (int pi = 1; pi <= p.length(); pi++) {
            if (p.charAt(pi - 1) == '*') {
                match[0][pi] = match[0][pi - 1];
            }
        }

        for (int si = 1; si <= s.length(); si++) {
            for (int pi = 1; pi <= p.length(); pi++) {
                if (s.charAt(si - 1) == p.charAt(pi - 1) || p.charAt(pi - 1) == '?') {
                    match[si][pi] = match[si - 1][pi - 1];
                } else if (p.charAt(pi - 1) == '*') {
                    match[si][pi] = match[si - 1][pi] || match[si][pi - 1];
                }
            }
        }
        return match[s.length()][p.length()];
 */
