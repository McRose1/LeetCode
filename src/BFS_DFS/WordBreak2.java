package BFS_DFS;

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
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }
    HashMap<Integer, List<String>> mem = new HashMap<>();

    private List<String> dfs(String s, List<String> wordDict, int start) {  // s = "catsanddog"
        HashSet<String> set = new HashSet<>(wordDict); // ["cat", "cats", "and", "sand", "dog"]
        // 记忆化递归的核心
        if (mem.containsKey(start)) {
            return mem.get(start);
        }
        List<String> res= new ArrayList<>();
        if (start == s.length()) {
            res.add("");
        }

        for (int end = start + 1; end <= s.length(); end++) {   // 1; 2; 3 -> 4; 5; 6; 7 -> 8; 9; 10
            if (set.contains(s.substring(start, end))) {        // "c"; "ca"; "cat" -> "s"; "sa"; "san"; "sand" -> "d"; "do"; "dog"
                List<String> list = dfs(s, wordDict, end);  // list = dfs(3); dfs(7); dfs(10)
                for (String temp : list) {
                    // 把结果连起来
                    res.add(s.substring(start, end) + (temp.equals("") ? "" : " ") + temp);
                }
            }
        }
        mem.put(start, res);
        return res;
    }
}
