package HashTable;

/*  409. Longest Palindrome
    Given a string which consists of lowercase or uppercase letters,
    find the length of the longest palindromes that can be build with those letters.

    This is case sensitive, for example "Aa" is not considered a palindrome here.

    Note:
    Assume the length of given string will not exceed 1,010.

    Example:
    Input:
    "abccccdd"
    Output:
    7
 */

import java.util.HashSet;

/*  Greedy + HashTable: Time = O(n) Space = O(n)

 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int ans = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }
}

/*  HashSet

        if (s == null || s.length() == 0) return 0;
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
                count++;
            } else {
                set.add(s.charAt(i));
            }
        }
        if (!set.isEmpty()) {
            return count * 2 + 1;
        } else {
            return count * 2;
        }
 */

/*  My version

        if (s == null || s.length() == 0) return 0;

        // HashMap<Character, Integer> count = new HashMap<>();

        // 'A' = 65; 'z'=122
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        int len = 0;
        int odd = 0;
        for (int value : count) {
            if (value % 2 == 0) {
                len += value;
            } else {
                odd = 1;
                if (value == 1) {
                    continue;
                }
                len += (value - 1);
            }
        }
        return len + odd;
 */
