package Backtracking;

/*  291. Word Pattern 2
    Given a pattern and a string str, find if str follows the same pattern.
    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

    Example 1:
    Input: pattern = "abab", str = "redblueredblue"
    Output: true

    Example 2:
    Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
    Output: true

    Example 3:
    Input: pattern = "aabb", str = "xyzabcxzyabc"
    Output: false

    Note:
    You may assume both pattern and str contains only lowercase letters.
 */

import java.util.HashMap;

/*  Backtracking
    没有空格隔开，不知道对应的单词是什么，需要自行分开，通过回溯法来生成每一种情况来判断
 */
public class WordPattern2 {
    HashMap<Character, String> map = new HashMap<>();
    public boolean wordPatternMatch(String pattern, String str) {
        return dfs(pattern, str);
    }

    private boolean dfs(String pattern, String str) {
        // 递归的出口
        if (pattern.length() == 0) {
            return str.length() == 0;
        }
        char c = pattern.charAt(0);
        if (map.containsKey(c)) {
            // str 里找不到 key 为 c 时所对应的 value
            if (!str.startsWith(map.get(c))) {
                return false;
            } else {
                // 成功找到一对 <key,value> pair，继续找下一对
                return dfs(pattern.substring(1), str.substring(map.get(c).length()));
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                // 在 key 为 c 确定的情况下，尝试每一种 value 的可能，也就是 str 的各个子字符串的可能性
                String word = str.substring(0, i + 1);
                if (!map.containsValue(word)) {
                    map.put(c, word);
                    if (dfs(pattern.substring(1), str.substring(i + 1))) {
                        return true;
                    }
                    // backtrack
                    map.remove(c);
                }
            }
        }
        return false;
    }
}
