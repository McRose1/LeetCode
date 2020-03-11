package String;

/*  242. Valid Anagram（同字母异序词）
    Given two strings s and t, write a function to determine if t is an anagram of s.

    Example 1:
    Input: s = "anagram", t = "nagaram"
    Output: true

    Example 2:
    Input: s = "rat", t = "car"
    Output: false

    Note: You may assume the string contains only lowercase alphabets.

    Follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?
    Answer: Use a hash table instead of a fixed size counter. Imagine allocating a large size array to fit the
    entire range of unicode characters, which could go up to more than 1 million.
    A hash table is a more generic solution and could adapt to any range of characters.
 */

/*  Hash Table: Time = O(n) Space = O(1)
    count occurrences of each letter in the two strings and compare them.
    we don't need tow counter table for comparison because we could increment the counter for each letter in s and
    decrement the counter for each letter in t, then check if the counter reaches back to 0.
 */
//  Online: first increment the counter for s, then decrement the counter for t. If at any point the counter drops below 0,
//  we know that t contains an extra letter not in s and return false immediately.
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
/*  Offline: initialize the whole hash table and then check for every element

        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        for (int i : counts) {
            if (i != 0) {
                return false;
            }
        }
        return true;
 */

/*  Sorting:
    Time = O(nlogn): sorting cost O(nlogn), comparing two strings costs O(n). Sorting time dominates the overall time complexity
    Space = O(1): costs O(1) auxiliary space if heapsort is used
    sorting both strings will result in two identical strings.

        if (s.length() != t.length()) {
           return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
 */
