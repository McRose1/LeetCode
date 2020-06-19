package Backtracking;

/*  140. Word Break 2
    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
    add spaces in s to construct a sentence where each word is a valid dictionary word.
    Return all such possible sentences.

    Note:
    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

    Example 1:
    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output:
    [
      "cats and dog",
      "cat sand dog"
    ]

    Example 2:
    Input:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    Output:
    [
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    ]
    Explanation: Note that you are allowed to reuse a dictionary word.

    Example 3:
    Input:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output:
    []
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//  Recursion with Memoization: Time = O(n^3) Space = O(n)
public class WordBreak2 {
    private HashSet<String> set;
    private HashMap<Integer, List<String>> mem = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict); // ["cat", "cats", "and", "sand", "dog"]
        return dfs(s, 0);
    }

    private List<String> dfs(String s, int start) {  // s = "catsanddog"
        // 记忆化递归的核心
        if (mem.containsKey(start)) {
            return mem.get(start);
        }
        List<String> res = new ArrayList<>();
        // 处理遍历到字符串结尾的情况
        if (start == s.length()) {
            // 这一步非常重要！如果不加""，那么 res 里就没有任何数据，也就进不去 for (String temp : list) 的遍历，res 也就没法更新，永远为空
            res.add("");
        }
        // 遍历所有 substring 的情况，从长度为 1 开始
        for (int end = start + 1; end <= s.length(); end++) {   // 1; 2; 3 -> 4; 5; 6; 7 -> 8; 9; 10
            if (set.contains(s.substring(start, end))) {        // "c"; "ca"; "cat" -> "s"; "sa"; "san"; "sand" -> "d"; "do"; "dog"
                // wordDict 存在该 substring，开始 DFS 遍历，end 作为新的一轮的起点
                List<String> list = dfs(s, end);  // list = dfs(3); dfs(7); dfs(10)
                for (String temp : list) {
                    // 把结果从后往前拼接起来，如果是最后一个单词就不用加空格
                    res.add(s.substring(start, end) + (temp.equals("") ? "" : " ") + temp);
                }
            }
        }
        mem.put(start, res);
        return res;
    }
}
