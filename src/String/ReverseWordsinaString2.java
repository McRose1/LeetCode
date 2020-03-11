package String;

/*  186. Reverse Words in a String 2
    Given an input string, reverse the string word by word.

    Example:
    Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
    Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]

    Note:
    A word is defined as a sequence of non-space characters.
    The input string does not contain leading or trailing spaces.
    The words are always separated by a single space.

    Follow up: Could you do it in-place without allocating extra space?
 */

/*  Reverse the Whole String and Then Reverse Each Word: Time = O(N) Space = O(1)

 */
public class ReverseWordsinaString2 {
    public void reverseWords(char[] s) {
        // reverse the whole string
        reverse(s, 0, s.length - 1);

        // reverse each word
        reverseEachWord(s);
    }
    public void reverse(char[] s, int left, int right) {
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
    public void reverseEachWord(char[] s) {
        int n = s.length;
        int start = 0, end = 0;

        while (start < n) {
            // go to the end of the word
            while (end < n && s[end] != ' ') {
                ++end;
            }
            // reverse the word
            reverse(s, start, end - 1);
            // move to the next word
            start = end + 1;
            ++end;
        }
    }
}
