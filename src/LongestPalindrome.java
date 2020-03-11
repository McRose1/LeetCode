/*
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

public class LongestPalindrome {
    public int longestPalindrome(String s) {
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
    }
}
/*
    HashSet
 */