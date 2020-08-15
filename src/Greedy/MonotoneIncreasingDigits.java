package Greedy;

/*  738. Monotone Increasing Digits
    Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
    (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

    Example 1:
    Input: N = 10
    Output: 9:

    Example 2:
    Input: N = 1234
    Output: 1234

    Example 3:
    Input: N = 332
    Output: 299

    Note: N is an integer in the range [0, 10^9].

    Hint:
    Build the answer digit by digit, adding the largest possible one that would make the number still less than or equal to N.
 */

/*  Greedy: Time = O(n) Space = O(n)
    While the cliff exists, decrement the digits and move i back
    Finally make rest of the digits 9 and return
 */
public class MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int N) {
        char[] chars = ("" + N).toCharArray();

        // Find the cliff
        int i = 1;
        while (i < chars.length && chars[i] >= chars[i - 1]) {          // 332 -> i = 2
            i++;
        }

        // Decrement while cliff exists
        while (i > 0 && i < chars.length && chars[i - 1] > chars[i]) {
            chars[--i]--;                                               // 332 -> 322 -> 222; i = 0
        }

        // Make rest of the digits 9
        for (int j = i + 1; j < chars.length; j++) {
            chars[j] = '9';                                             // 222 -> 299
        }

        return Integer.parseInt(String.valueOf(chars));
    }
}
