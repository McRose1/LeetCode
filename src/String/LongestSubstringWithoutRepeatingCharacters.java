package String;

/*  3. Longest Substring Without Repeating Characters
    Given a string, find the length of the longest substring without repeating characters.

    Example 1:
    Input: "abcabcbb"
    Output: 3

    Example 2:
    Input: "bbbbb"
    Output: 1

    Example 3:
    Input: "pwwkew"
    Output: 3
 */

import java.util.HashMap;
import java.util.Map;

/*  Sliding Window: Time = O(n) Space = O(min(m, n))
    The solution with HashSet requires at most 2n steps. In fact, it could be optimized to require only n steps.
    We could define a mapping of the characters to its index. Then we can skip the characters immediately when we found a repeated character.
    The reason is that if s[j] have a duplicate in the range [i,j) with index j', we don't need to increase i little by little.
    We can skip all the elements in the range [i,j'] and let i to be j'+1 directly.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [slow, fast]
        for (int slow = 0, fast = 0; fast < n; fast++) {
            if (map.containsKey(s.charAt(fast))) {
                slow = Math.max(map.get(s.charAt(fast)), slow);
            }
            ans = Math.max(ans, fast - slow + 1);
            // already fast' + 1
            map.put(s.charAt(fast), fast + 1);
        }
        return ans;
    }
}

/*  Sliding Window + HashSet: Time = O(n) Space = O(min(m, n))
    If a substring Sij from index i to j-1 is already checked to have no duplicate characters.
    We only need to check if s[j] is already in the substring sij.
    using HashSet as a sliding window

        int n = s.length();
        HashSet<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                // right boundary
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                // left boundary
                set.remove(s.charAt(i++));
            }
        }
        return ans;
 */

/*  Brute Force + HashSet: Time = O(n^3) Space = O(min(n, m))

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }
    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
 */
