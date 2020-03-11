package String;

/*  290. Word Pattern
    Given a pattern and a string str, find if str follows the same pattern.
    Here follow means a full match, such that there is a bijection between a letter
    in pattern and a non-empty word in str.

    Example 1:
    Input: pattern = "abba", str = "dog cat cat dog"
    Output: true

    Example 2:
    Input: pattern = "aaaa", str = "dog cat cat fish"
    Output: false

    Example 3:
    Input: pattern = "aaaa", str = "dog cat cat dog"
    Output: false

    Example 4:
    Input: pattern = "abba", str = "dog dog dog dog"
    Output: false

    Notes:
    You may assume pattern contains only lowercase letters,
    and str contains lowercase letters that may be separated by a single space.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/*  HashMap: Time = O(n) Space = O(n)
    用 HashMap 联立 str 中的单词和 pattern 中的字母，如果后面出现 str 中的单词与 pattern 中的字母对不上，则输出 false，否则 true
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");
        if (arr.length != pattern.length()) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(arr[i])) {
                    return false;
                }
            } else {
                if (!set.add(arr[i])) {
              //if (map.containsValue(arr[i])) { containsValue: Time = O(N) huge time overhead
                    return false;
                }
                map.put(c, arr[i]);
            }
        }
        return true;
    }
}
