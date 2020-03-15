package String;

/*  28. Implement strStr()
    Implement strStr().
    Return the index of the first occurrence of needle in haystack,
    or -1 if needle is not part of haystack.

    Example 1:
    Input: haystack = "hello", needle = "ll"
    Output: 2

    Example 2:
    Input: haystack = "aaaaa", needle = "bba"
    Output: -1

    Clarification:
    What should we return when needle is an empty string? This is a great question to ask during an interview.
    For the purpose of this problem, we will return 0 when needle is an empty string.
    This is consistent to C's strstr() and Java's indexOf().
 */

/*  KMP: Time = O(m+n) Space = O(n)
    Java indexOf() 就是用 KMP 实现的
    前缀后缀，可以省去前面已经匹配的重复匹配
    a a a a a a a b
    a a a b             pi = [-1, 0, 1, 2]

    a a a a a a a b
      a a a b
    和暴力解相比虽然也是一次移动一位，但是省去了前面 2 个 a 的重新匹配 -> t[3] == p[2] rather than (t[1]=p[0], t[2]=p[1])
 */
public class ImplementstrStr {
    public int strStr(String haystack, String needle) { // haystack = "aaaaab", needle = "aab"
        int m = needle.length();            // m = 3
        int n = haystack.length();          // n = 6
        if (m == 0) return 0;
        int[] pi = kmpPreprocess(needle);   // [-1, 0, 1]

        int i = 0, k = 0;
        while (i < n && k < m) {
            if (k == -1 || haystack.charAt(i) == needle.charAt(k)) {
                i++;        // i = 1; i = 2 -> i = 3 -> i = 4 -> i = 5; i = 6
                k++;        // k = 1; k = 2 -> k = 2 -> k = 2 -> k = 2; k = 3
            } else {
                k = pi[k];  // k = pi[2] = 1; k = pi[2] = 1; k = pi[2] = 1
            }
        }
        if (k == needle.length()) { // 3 = 3
            return i - k;           // 6 - 3 = 3
        }
        return -1;
    }

    // 构造前后缀数组的实质就是使用双指针遍历 pattern 的过程
    private int[] kmpPreprocess(String pattern) {   // aab
        int m = pattern.length();
        int[] pi = new int[m];
        pi[0] = -1; // pi[0] is always -1
        int k = -1;
        int i = 0;

        while (i < m - 1) {
            if (k == -1 || pattern.charAt(k) == pattern.charAt(i)) {
                k++;        // k = 0; k = 1
                i++;        // i = 1; i = 2
                pi[i] = k;  // pi[1] = 0; pi[2] = 1
            } else {
                k = pi[k];  // k = pi[1] = 0
            }
        }
        return pi;
    }
}

/*  Substring: Time = O(n) Space = O(1)

        if (needle.length() == 0) return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) return i;
        }
        return -1;
 */

/*  Brute Force: Time = O(mn) Space = O(1)
    每次只把 needle 往后移动一位

        for (int i = 0; i < haystack.length() - needle.length(); i++) {
            for (int j = 0; j < needle.length() && haystack.charAt(i + j) == needle.charAt(j); j++) {
                if (j == needle.length() - 1) {
                    return i;
                }
            }
        }
        return -1;
 */