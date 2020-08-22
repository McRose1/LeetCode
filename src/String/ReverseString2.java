package String;

/*  541. Reverse String 2
    Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string.
    If there are less than k characters left, reverse all of them.
    If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.

    Example:
    Input: s = "abcdefg", k = 2
    Output: "bacdfeg"

    Restrictions:
        1. The string consists of lower English letters only.
        2. Length of the given string and k will in the range [1, 10000]
 */

public class ReverseString2 {
    public String reverseStr(String s, int k) {
        int n = s.length();
        if (n <= k) {
            return reverse(s);
        } else if (n < 2 * k) {
            return reverse(s.substring(0, k)) + s.substring(k);
        } else {
            StringBuilder sb = new StringBuilder();
            int start = 0;
            while (start < n) {
                // 最后一层循环
                if (n - start <= 2 * k) {
                    if (n - start <= k) {
                        sb.append(reverse(s.substring(start)));
                    } else {
                        sb.append(reverse(s.substring(start, start + k)));
                        sb.append(s.substring(start + k));
                    }
                    break;
                } else {
                    sb.append(reverse(s.substring(start, start + k)));
                    sb.append(s, start + k, start + 2 * k);
                    start += 2 * k;
                }
            }
            return sb.toString();
        }
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

/*  LC (Two Pointers)

        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            int i = start;
            int j = Math.min(start + k - 1, a.length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
                j--;
            }
        }
        return new String(a);
 */
