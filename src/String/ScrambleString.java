package String;

/*  87. Scramble String
    Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
    Below is one possible representation of s1 = "great":
            great
            /   \
           gr   eat
          / \   /  \
         g   r  e   at
                   /  \
                  a    t
    To scramble the string, we may choose any non-leaf node and swap its two children.
    For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
            rgeat
           /    \
          rg    eat
         / \    /  \
        r   g  e   at
                   / \
                  a   t
    We say that "rgtae" is a scrambled string of "great".
    Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

    Example 1:
    Input: s1 = "great", s2 = "rgeat"
    Output: true

    Example 2:
    Input: s1 = "abcde", s2 = "caebd"
    Output: false
 */

/*  Recursion: Time = O(n!) Space = O(n)
    两个互为 scrambled 的字符串，它们的子字符串也一定是 scrambled
    遍历所有的切割可能，每一种切割里做递归
 */
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.equals(s2)) return true;

        // 判断两个字符串每个字母出现的次数是否一致
        int[] letters = new int[26];
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        // 如果两个字符串的字母出现不一致直接返回 false
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) return false;
        }
        // 遍历每个切割位置
        for (int i = 1; i < len; i++) {
            // Case1: s1 切割为两部分，不交换，然后进行若干步切割交换，最后判断两个子树分别是否能变成 s2 的两部分
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            // Case2: s1 切割为两部分，并且交换，然后进行若干步切割交换，最后判断两个子树分别是否能变成 s2 的两部分
            if (isScramble(s1.substring(0, i), s2.substring(len - i)) && isScramble(s1.substring(i), s2.substring(0, len - i))) {
                return true;
            }
        }
        return false;
    }
}
