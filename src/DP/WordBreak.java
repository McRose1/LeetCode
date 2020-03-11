package DP;

/*  139. Word Break
    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
    determine if s can be segmented into a space-separated sequence of one or more dictionary words.

    Note:
    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

    Example 1:
    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true

    Example 2:
    Input: s = "applepenapple", wordDict = ["apple", "pen"]
    Output: true

    Example 3:
    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output: false
 */

import java.util.List;
/*  DP: Time = O(n^2) Space = O(n)
    dp[i] represents if substring (0, i) is breakable.
    dp[i] = true if [0, j] is true and [j, i] in dict
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        // 枚举所有的 substring
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

/*  Recursion with Memoization: Time = O(n^2) Space = O(n^2)

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<String, Boolean> mem = new HashMap<>();
        return helper(s, mem, dict);
    }
    private boolean helper(String s, Map<String, Boolean> mem, Set<String> dict) {
        // In memory, directly return
        if (mem.containsKey(s)) return mem.get(s);
        // Whole string is a word, memorize and return
        if (dict.contains(s)) {
            mem.put(s, true);
            return true;
        }
        // Try every break point
        for (int i = 1; i < s.length(); ++i) {
            // Find the solution for s
            if (dict.contains(s.substring(i)) && helper(s.substring(0, i), mem, dict)) {
                mem.put(s, true);
                return true;
            }
        }
        // No solution for s, memorize and return
        mem.put(s, false);
        return false;
    }

 */
