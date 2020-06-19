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
/*  优化版 DP: Time = O(n^2) Space = O(n)
    在选取不同 index 作为起点的循环内直接遍历 wordDict
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            // dp[i] 表示 [0~i-1] 的字符串可以被 break
            if (dp[i]) {
                for (String word : wordDict) {
                    int end = i + word.length();
                    if (end <= len && s.substring(i, end).equals(word)) {
                        dp[end] = true;
                    }
                }
            }
        }
        return dp[len];
    }
}

/*  DP(LC): Time = O(n^2) Space = O(n)
    dp[i] represents if substring (0, i) is breakable.
    dp[i] = true if [0, j](dp[0] - dp[j]) is true and [j, i] in dict

        boolean[] dp = new boolean[s.length() + 1];
     // HashSet<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        // 枚举所有的 substring
        // 选取 substring 的尾
        for (int i = 1; i <= s.length(); i++) {
            // 选取 substring 的头
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {    // set.contains() 比 wordDict.contains() 用空间换时间
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
 */

/*  BFS: Time = O(n^2) Space = O(n)
    将字符串可视化成一棵树，每一个节点是用 end 为结尾的前缀字符串。
    当两个节点之间的所有节点都对应了字典中一个有效字符串时，两个节点可以被连接。
    选第一个字符作为 root，开始找所有可行的以该字符为首字符的可行子串，
    将每一个字符串的结束字符的下标放在 queue 的尾部供 BFS 后续使用。

        Set<String> set = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (!visited[start]) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (set.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = true;
            }
        }
        return false;
 */

/*  Recursion with Memoization: Time = O(n^2) Space = O(n^2)

    public boolean wordBreak(String s, List<String> wordDict) { // s = "leetcode", wordDict = ["leet", "code"]
        // list to HashSet
        Set<String> dict = new HashSet<>(wordDict);                 // ["leet", "code"]
        // 记忆化递归的精髓，计算过的就存起来，以后再遇到就直接到 mem 里取
        Map<String, Boolean> mem = new HashMap<>();
        return dfs(s, mem, dict);
    }
    private boolean dfs(String s, Map<String, Boolean> mem, Set<String> dict) {
        // In memory, directly return
        if (mem.containsKey(s)) return mem.get(s);

        // Whole string is a word, memorize and return
        if (dict.contains(s)) {
            mem.put(s, true);
            return true;
        }

        // Try every break point
        for (int i = 1; i < s.length(); i++) {
            // Find the solution for s
            if (dfs(s.substring(0, i), mem, dict) && dict.contains(s.substring(i)) {   // dfs(l); dfs(le); dfs(lee); dfs(leet) ...
                mem.put(s, true);
                return true;
            }
        }
        // No solution for s, memorize and return
        mem.put(s, false);
        return false;
    }
 */