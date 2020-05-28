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
//  Two Pointers: Time = O(n) Space = O(1)
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
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
