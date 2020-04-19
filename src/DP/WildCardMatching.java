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

/*  DP (Bottom-Up): Time = O(n^2) Space = O(n^2)
    Case 1:
    if 字符相同 or p.charAt(i) == '?':
        dp[p][s] = dp[p - 1][s - 1]
    Case 2:
    if p.charAt(i) == '*' :
        1. *不做任何字符匹配，作为空字符
        2. *匹配多个
        dp[p][s] = dp[p - 1][s] (不匹配) || dp[p][s - 1]（匹配）
 */
public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        // base cases
        if (s == null || p == null) return false;
        if (s.equals(p) || p.equals("*")) return true;

        int sLen = s.length(), pLen = p.length();

        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;

        // 初始化遇到 '*' 匹配空字符的情况
        for (int pIdx = 1; pIdx <= pLen; pIdx++) {
            if (p.charAt(pIdx - 1) == '*') {
                dp[pIdx][0] = dp[pIdx - 1][0];
            }
        }

        for (int pIdx = 1; pIdx <= pLen; pIdx++) {
            for (int sIdx = 1; sIdx <= sLen; sIdx++) {
                if (p.charAt(pIdx - 1) == s.charAt(sIdx - 1) || p.charAt(pIdx - 1) == '?') {
                    dp[pIdx][sIdx] = dp[pIdx - 1][sIdx - 1];
                } else if (p.charAt(pIdx - 1) == '*') {
                    // '*' 选择不匹配: ab, ab*; 或者匹配多个 abcd, ab*
                    // dp[pIdx][sIdx - 1] 是整个问题的难点！需要往左匹配
                    dp[pIdx][sIdx] = dp[pIdx - 1][sIdx] || dp[pIdx][sIdx - 1];
                }
            }
        }
        return dp[pLen][sLen];
    }
}

/*  Backtracking (Two Pointers): Time = O(min(s, p)) Space = O(1)
    不需要计算整个表格，而是检查每个 '*' 的所有可能性：0 - n
    让我们从匹配 0 个字符开始，如果这个假设导致不匹配，则回溯：回到前一个 '*'，假设它匹配 1 个字符

        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        // starIdx 表示前一个 '*' 的坐标
        int starIdx = -1;
        // sTmpIdx string 里第一个未配对的字符的 index
        int sTmpIdx = 0;

        // 遍历整个字符串
        while (sIdx < sLen) {
            // 一对一匹配，两指针同时后移
            if (pIdx < pLen && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?')) {
                pIdx++;
                sIdx++;
            // 碰到 *，假设它匹配 0 个字符，并且用 starIdx 记录 * 的位置，记录当前字符串的位置，pIdx 后移
            } else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                sTmpIdx = sIdx;
                // 匹配 0 个字符
                pIdx++;
            // 当前字符不匹配，并且前面用过 *，回溯; pIdx 回到 * 的下一个位置
            } else if (starIdx != -1) {
                // 这步代表用 * 匹配了一个字符
                pIdx = starIdx + 1;
                sTmpIdx++;
                sIdx = sTmpIdx;
            // 字符不匹配，也没有 *，返回 false
            } else {
                return false;
            }
        }
        // 能出 while 循环说明 s 里的字符都已经被匹配完毕，如果此时 p 里还有字符，那么肯定是 false
        // The remaining characters in the pattern should all be '*'
        for (int i = pIdx; i < pLen; i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
 */

/*  DP (Bottom-up)  LC 官方（感觉有点写复杂了）
    可以将 '*' 不匹配和匹配这两种情况分开初始化，不用一定放在一个循环里面
    Case 2:
    if p.charAt(i) == '*' && dp[i - 1][j - 1] == true:
        1. *不做任何字符匹配，作为空字符
        2. *匹配一个或多个的字符
        dp[p - 1][i] = true (i >= s - 1)

        // base cases
        if (s == null || p == null) return false;
        if (s.equals(p) || p.equals("*")) return true;

        int sLen = s.length(), pLen = p.length();

        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;

        for (int pIdx = 1; pIdx <= pLen; pIdx++) {
            // the current character in the pattern is '*'
            if (p.charAt(pIdx - 1) == '*') {
                int sIdx = 1;
                // 从 s 里找到第一个还未匹配的字符的 index
                while(!dp[pIdx - 1][sIdx - 1] && (sIdx <= sLen)) {
                    sIdx++;
                }
                // '*' 不匹配字符：a* - a
                dp[pIdx][sIdx - 1] = dp[pIdx - 1][sIdx - 1];
                // '*' 匹配一个或多个字符
                while (sIdx <= sLen) {
                    dp[pIdx][sIdx++] = true;
                }
            } else {
                for (int sIdx = 1; sIdx <= sLen; sIdx++) {
                    // the current character in the pattern is '?' or 当前 index 字符 match
                    if (p.charAt(pIdx - 1) == '?' || p.charAt(pIdx - 1) == s.charAt(sIdx - 1)) {
                        dp[pIdx][sIdx] = dp[pIdx - 1][sIdx - 1];
                    }
                }
            }
        }
        return dp[pLen][sLen];
 */

/*  DP (Top-down)


 */

