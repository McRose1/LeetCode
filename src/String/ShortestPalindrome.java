package String;

/*  214. Shortest Palindrome
    Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it.
    Find and return the shortest palindrome you can find by performing this transformation.

    Example 1:
    Input: "aacecaaa"
    Output: "aaacecaaa"

    Example 2:
    Input: "abcd"
    Output: "dcbabcd"
 */

/*  KMP: Time = O(n) Space = O(n)
    问题可以归结为从开头寻找最长的 palindrome
    根据倒置的方法，aacecaaa -> aaacecaa，可以进一步把问题转换为寻找 aacecaaa#aaacecaa 的前后缀相等的情况
    Find suffix(rev) which is longest prefix(s)
    Remove the longest suffix from rev_s
    [rev - Lsuffix(rev)] + s
    因为我们构造的字符串后缀就是原字符串的倒置，前缀后缀相等时，也就意味着当前前缀是一个 palindrome
    而 next 数组是寻求最长的前缀，我们就找到了开头开始的最长 palindrome -> aacecaa
           a a c e c a a a # a a a c e c a a
    next  -1 0 1 0 0 0 1 2 2 0 1 2 2 3 4 5 6 7（需要额外加 1）
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        String s_new = s + '#' + rev;
        int end = getLastNext(s_new);
        return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
    }

    // 返回 next 数组的最后一个值
    private int getLastNext(String s) {
        int n = s.length();
        int[] next = new int[n];
        next[0] = -1;
        // k 表示最长的相同前后缀的长度
        int k = -1;
        int i = 0;
        while (i < n - 1) {
            if (k == -1 || s.charAt(k) == s.charAt(i)) {
                k++;
                i++;
                next[i] = k;
            } else {
                k = next[k];
            }
        }
        return next[n - 1];
    }
}

/*  Brute Force (version 2)
    反转原字符串并存储为 rev：aacecaaa -> aaacecaa，比较对应的子串即可判断是否是 palindrome
    aacecaaa != aaacecaa
    aacecaa == aacecaa => 这就是从开头开始的最长 palindrome
    将末尾不是 palindrome 的部分 reverse 加到原字符串开头即可

        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int i = 0;
        for (; i < n; i++) {
            if (s.substring(0, n - i).equals(rev.substring(i))) {
                break;
            }
        }
        return new StringBuilder(s.substring(n - i)).reverse() + s;
 */

/*  Brute Force (Two Pointers): Time = O(n^2) Space = O(n)
    从字符串的开头找到最长的 palindrome，然后 reverse 剩余的子串并附加到开头
    aacecaaa -> aaceceaa + a -> a + aaceceaa + a

        int i = 0;
        int j = s.length() - 1;
        int end = s.length() - 1;
        // 从开头开始寻找最长 palindrome
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                i = 0;
                end--;
                j = end;
            }
        }
        return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
 */
