package TwoPointers;

/*  395. Longest Substring with At Least K Repeating Characters
    Find the length of the longest substring T of a given string (consists of lowercase letters only)
    such that every character in T appears no less than k times.

    Example 1:
    Input:
    s = "aaabb", k = 3

    Output:
    3

    The longest substring is "aaa", as 'a' is repeated 3 times.

    Example 2:
    Input:
    s = "ababbc", k = 2

    Output:
    5

    The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */

/*
    字符串分割: aaa | b | ccccc
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        boolean fullString = true;
        for (int i = 0; i < s.length(); i++) {
            int cur = count[s.charAt(i) - 'a'];
            if (cur > 0 && cur < k) {
                fullString = false;
                break;
            }
        }
        if (fullString) {
            return s.length();
        }
        int start = 0, end = 0;
        int res = 0;
        while (end < s.length()) {
            if (count[s.charAt(end) - 'a'] < k) {
                res = Math.max(res, longestSubstring(s.substring(start, end), k));
                start = end + 1;
            }
            end++;
        }
        return Math.max(res, longestSubstring(s.substring(start), k));
    }
}
