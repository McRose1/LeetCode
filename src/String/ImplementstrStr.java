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

/*  Substring: Time = O() Space = O()

 */
public class ImplementstrStr {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) return i;
        }
        return -1;
    }
}

/*  KMP: Time = O(m+n) Space = O(n)

        int[] pi = kmpPreprocess(needle);
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == needle.length() ? i - j : -1;

    private int[] kmpPreprocess(String pattern) {
        int i = 1, j = 0;
        int[] res = new int[pattern.length()];
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                res[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = res[j - 1];
            } else {
                res[i] = 0;
                i++;
            }
        }
        return res;
    }
 */

/*  Brute Force: Time = O(mn) Space = O(1)

        for (int i = 0; i < haystack.length() - needle.length(); i++) {
            for (int j = 0; j < needle.length() && haystack.charAt(i + j) == needle.charAt(j); j++) {
                if (j == needle.length() - 1) {
                    return i;
                }
            }
        }
        return -1;
 */