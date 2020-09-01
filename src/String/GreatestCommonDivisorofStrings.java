package String;

/*  1071. Greatest Common Divisor of Strings
    For two strings s and t, we say "t divides s" if and only if s = t + ... + t (t concatenated with itself 1 or more times).
    Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

    Example 1:
    Input: str1 = "ABCABC", str2 = "ABC"
    Output: "ABC"

    Example 2:
    Input: str1 = "ABABAB", str2 = "ABAB"
    Output: "AB"

    Example 3:
    Input: str1 = "LEET", str2 = "CODE"
    Output: ""

    Example 4:
    Input: str1 = "ABCDEF", str2 = "ABC"
    Output: ""

    Constraints:
        o 1 <= str1.length <= 1000
        o 1 <= str2.length <= 1000
        o str1 and str2 consist of English uppercase letters.

    Hint:
    The greatest common divisor must be a prefix of each string, so we can try all prefixes.
 */

/*  Imitate gcd algorithm (Recursive)
    1. If longer string starts with shorter string, cut off the common prefix part of the longer string; repeat till one is empty, then the other is gcd string;
    2. If the longer string does NOT start with the shorter one, there is no gcd string.
 */
public class GreatestCommonDivisorofStrings {
    public String gcdOfStrings(String str1, String str2) {
        // make sure str1 is not shorter than str2
        if (str1.length() < str2.length()) {
            return gcdOfStrings(str2, str1);
        }
        // shorter string is not common prefix
        else if (!str1.startsWith(str2)) {
            return "";
        }
        // gcd string found
        else if (str2.isEmpty()) {
            return str1;
        }
        // cut off the common prefix part of str1
        else {
            return gcdOfStrings(str1.substring(str2.length()), str2);
        }
    }
}

/*  Compare substring (Iterative)
    the length of gcd substring must be the gcd of the lengths of both string.
    check if the substring(0, i) of str1 is the divisor string of
    str1;
    str2.
    Increase i to find its maximum value for gcd string.

        int i = 1, max = 0, len1 = str1.length(), len2 = str2.length();
        while (i <= len1 && i <= len2 && str1.charAt(i - 1) == str2.charAt(i - 1)) {
            boolean cd = true;
            // substring length i must be the common divisor of lengths of str1 and str2
            if (len1 % i == 0 && len2 % i == 0) {
                String commonDivisorStr = str1.substring(0, i);
                for (int j = i * 2; cd && j <= len1; j += i) {
                    cd = commonDivisorStr.equals(str1.substring(j - i, j));
                }
                for (int j = i * 2; cd && j <= len2; j += i) {
                    cd = commonDivisorStr.equals(str2.substring(j - i, j));
                }
                if (cd) {
                    max = i;
                }
            }
            i++;
        }
        return str1.substring(0, max);
 */