package String;

/*  151. Reverse Words in a String
    Given an input string, reverse the string word by word.

    Example 1:
    Input: "the sky is blue"
    Output: "blue is sky the"

    Example 2:
    Input: "  hello world!  "
    Output: "world! hello"
    Explanation: Your reversed string should not contain leading or trailing spaces.

    Example 3:
    Input: "a good   example"
    Output: "example good a"
    Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

    Note:
    A word is defined as a sequence of non-space characters.

    Input string may contain leading or trailing spaces.
    However, your reversed string should not contain leading or trailing spaces.

    You need to reduce multiple spaces between two words to a single space in the reversed string.
 */

//  StringBuilder: Time = O(n) Space = O(n)
public class ReverseWordsinaString {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        String[] words = s.trim().split("\\s+");
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i] + " ");
        }
        return sb.toString().trim();
    }
}

/*  Helper function: Time = O(n) Space = O(n)

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        char[] a = s.toCharArray();
        int n = a.length;

        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWord(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }
    private void reverseWord(char[] a, int n) {
        int i = 0, j = 0;
        while (i < n) {
            // skip spaces
            while (i < j || j < n && a[i] == ' ') {
                i++;
            }
            // skip non-spaces
            while (j < i || j < n && a[j] != ' ') {
                j++;
            }
            // reverse the word
            reverse(a, i, j - 1);
        }
    }
    private String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;
        while (j < n) {
            // skip leading spaces
            while (j < n && a[j] == ' ') {
                j++;
            }
            // keep non-spaces
            while (j < n && a[j] != ' ') {
                a[i++] = a[j++];
            }
            // skip trailing spaces
            while (j < n && a[j] == ' ') {
                j++;
            }
            // keep only one space
            if (j < n) {
                a[i++] == ' ';
            }
        }
        return new String(a).substring(0, i);
    }
 */
