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
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String>  map = new HashMap<>();
        return dfs(pattern, str, map);
    }

    private boolean dfs(String pattern, String str, HashMap<Character, String> map) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }

        char c = pattern.charAt(0);
        // map 里有 c
        if (map.containsKey(c)) {
            if (!str.startsWith(map.get(c))) {
                return false;
            } else {
                return dfs(pattern.substring(1), str.substring(map.get(c).length()), map);
            }
        }

        // map 里没有 c
        for (int i = 0; i < str.length(); i++) {
            String word = str.substring(0, i + 1);
            if (map.containsValue(word)) continue;
            map.put(c, word);
            if (dfs(pattern.substring(1), str.substring(i + 1), map)) {
                return true;
            }
            map.remove(c);  // backtrack
        }
        return false;

    }
}
