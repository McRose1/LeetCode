package String;

/*  557. Reverse Words in a String 3
    Given a string, you need to reverse the order of characters in each word within a sentence while still
    preserving whitespace and initial word order.

    Example:
    Input: "Let's take LeetCode contest"
    Output: "s'teL ekat edoCteeL tsetnoc"

    Note: In a string, each word is separated by single space and there will not be any extra spae in the string.
 */

public class ReverseWordsinaString3 {
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            String temp = reverse(strs[i]);
            if (i == strs.length - 1) {
                sb.append(temp);
            } else {
                sb.append(temp);
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
