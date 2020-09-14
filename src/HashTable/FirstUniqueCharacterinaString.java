package HashTable;

/*  387. First Unique Character in a String
    Given a string, find the first non-repeating character in it and return its index.
    If it doesn't exist, return -1.

    Examples:
    s = "leetcode"
    return 0.

    s = "loveleetcode",
    return 2.

    Note: You may assume the string contain only lowercase letters.
 */

//  Array: Time = O(N) Space = O(1)
public class FirstUniqueCharacterinaString {
    public int firstUniqueChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}

/*  Linear time solution - HashMap: Time = O(N) Space = O(N)

        HashMap<Character, Integer> map = new HashMap<>();
        int n = s.length();
        // build hash map: character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // find the index
        for (int i = 0; i < n; i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
 */