package TwoPointers;

/*  125. Valid Palindrome
    Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
    Note: For the purpose of his problem, we define empty string as valid palindrome.

    Example 1:
    Input: "A man, a plan, a canal: Panama"
    Output: true

    Example 2:
    Input: "race a car"
    Output: false
 */

/*  Two Pointers: Time = O(n) Space = O(1)

 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            // 数字不能跳过！！！
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (i < j && Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) {
                return false;
            }
        }
        return true;
    }
}

/*  my version (sb)

        if (s == null || s.length() == 0) return true;

        if (s.length() == 1 && Character.isLetter(s.charAt(0))) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        String ss = sb.toString();

        int i = 0;
        int j = ss.length() - 1;
        while (i < j) {

            if (ss.charAt(i) != ss.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
 */

/*  my version

        if (s == null || s.length() == 0) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (left < right && Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
                left++;
                right--;
            } else {
                break;
            }
        }
        return (left >= right);
 */
