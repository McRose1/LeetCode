package String;

/*  58. Length of Last Word
    Given a string s consists of upper/lower-case alphabets and empty space characters ' '.
    Return the length of last word in the string.
    If the last word does not exit, return 0.

    Note: A word is defined as a character sequence consists of non-space characters only.

    Example:
        Input: "Hello World"
        Output: 5
 */

/*
    String.trim(s):
    s = "   Hello World    " -> "Hello World"
    s.lastIndexOf(int ch): returns the index of the last occurrence of the character in the character sequence
    s = "Hello world";
    -> s.lastIndexOf(" ") = 5;
 */
public class LengthofLastWord {
    public int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }
}

/*
        int length = 0;
        char[] chars = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (length == 0) {
                if (chars[i] == ' ') {
                    continue;
                } else {
                    length++;
                }
            } else {
                if (chars[i] == ' ') {
                    break;
                } else {
                    length++;
                }
            }
        }
        return length;
 */