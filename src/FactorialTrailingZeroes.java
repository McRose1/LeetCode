/*
    Given an integer n, return the number of trailing zeros in n!.

    Example 1:
    Input: 3
    Output: 0

    Example 2:
    Input: 5
    Output: 1

    Note: Your solution should be in logarithmic time complexity
 */

public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        //return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
        int res = 0;
        while (n != 0) {
            res += n / 5;
            n = n / 5;
        }
        return res;
    }
}

/*
    2 * 5 = 10
 */