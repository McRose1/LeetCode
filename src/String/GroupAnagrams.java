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

/*  Categorize by Count: Time = O(NK) Space = O(NK)
    Transform each string s into a character count, consisting of 26 non-negative integers representing the number of
    a's, b's, c's, etc.
    In Java, the hashtable representation of our count will be a string delimited with '#' characters. 用做分割
 */
import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List> ans = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}

/*  Categorize by Sorted String:
    Time = O(NKlogK), where N is the length of strs, and K is the maximum length of a string in strs.
    Space = O(NK), the total information content stored in ans.
    Maintain a map ans: {String -> List} where each key K is a sorted string,
    and each value is the list of strings from the initial input that when sorted, are equal to K

        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
 */