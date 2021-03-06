package HashTable;

/*  205. Isomorphic Strings
    Given two strings s and t, determine if they are isomorphic.

    Two strings are isomorphic if the characters in s can be replaced to get t.

    All occurrences of a character must be replaced with another character while preserving the order of characters.
    No two characters may map to the same character but a character may map to itself.

    Example 1:
    Input: s = "egg", t = "add"
    Output: true

    Example 2:

    Input: s = "foo", t = "bar"
    Output: false

    Example 3:

    Input: s = "paper", t = "title"
    Output: true

    Note: You may assume both s and t have the same length.
 */

// HashMap: Time = O(n) Space = O(n)
import java.util.HashMap;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return true;
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.containsKey(a)) {
                if (!map.get(a).equals(b)) {
                    return false;
                }
            } else {
                // 这题的关键
                if (!map.containsValue(b)) {
                    map.put(a, b);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
/*  Array: Time = O(n) Space = O(1)
    用数组代替 map，节省空间
    ASCII码共有256个字符

        int[] sChars = new int[256];
        int[] tChars = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (sChars[s.charAt(i)] != tChars[t.charAt(i)]) {
                return false;
            } else {
                sChars[s.charAt(i)] = i + 1;
                tChars[t.charAt(i)] = i + 1;
            }
        }
        return true;
 */