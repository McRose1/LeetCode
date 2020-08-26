package Math;

/*  233. Number of Digit One
    Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

    Example:
    Input: 13
    Output: 6
    Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

    Hint: Beware of overflow.
 */

/*  Math: Time = O(log10(n)) Space = O(1)
    10: 1     个位
    100: 10   十位
    1000: 100 百位
 */
public class NumberofDigitOne {
    public int countDigitOne(int n) {
        int count = 0;
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            count += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0l), i);
        }
        return count;
    }
}
