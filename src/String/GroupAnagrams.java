package String;

/*  49. Group Anagrams
    Given an array of strings, group anagrams together.

    Example:
    Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Output:
    [
      ["ate","eat","tea"],
      ["nat","tan"],
      ["bat"]
    ]

    Note:
    All inputs will be in lowercase.
    The order of your output does not matter.
 */
import java.util.*;
/*  Categorize by Count: Time = O(NK) Space = O(NK)
    Two strings are anagrams if and only if their character counts (respective number of occurrences of each character) are the same.
    Transform each string s into a character count, consisting of 26 non-negative integers representing the number of
    a's, b's, c's, etc.
    In Java, the hashtable representation of our count will be a string delimited with '#' characters. 用做分割
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            // 初始化 count 数组
            Arrays.fill(count, 0);
            // 构建 count 数组
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            // 创建 HashMap 的 key
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            // 将 key 转换成 String
            String key = sb.toString();
            // 下面的过程就是从 HashMap 里创建查询
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<>());
            }
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }
}

/*  Categorize by Sorted String:
    Time = O(NKlogK), where N is the length of strs, and K is the maximum length of a string in strs.
    Space = O(NK), the total information content stored in ans.
    Two strings are anagrams if and only if their sorted strings are equal.
    Maintain a map ans: {String -> List} where each key K is a sorted string,
    and each value is the list of strings from the initial input that when sorted, are equal to K

        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        for (String s : strs) {
            // Arrays.sort() 不能直接 sort String，所以需要把 String 先转换为 Char array
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);    // 也可以 new String(ca);
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<>());
            }
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
 */