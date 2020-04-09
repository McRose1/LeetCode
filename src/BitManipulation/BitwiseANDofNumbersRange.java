package BitManipulation;

/*  201. Bitwise AND of Numbers Range
    Given a range [m, n] where 0 <= m <= n <= 2147483647,
    return the bitwise AND of all numbers in this range, inclusive.

    Example 1:
    Input: [5,7]
    Output: 4

    Example 2:
    Input: [0,1]
    Output: 0
 */

/*  Bit Manipulation
    m = 5  1 0 1          2  1 0             1 1
        6  1 1 0          3  1 1             1 1
    n = 7  1 1 1          3  1 1             1 1
               0               0               1
   答案只可能是 1111...000000
   不可能出现 111...000...111
 */
public class BitwiseANDofNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        // 结果中从右到左 0 的个数
        int zeros = 0;
        while (m != n) {
            zeros++;
            m >>>= 1;
            n >>>= 1;
        }
        return m << zeros;
    }
}

/*  利用 n & (n-1) 来消去 n 的最低位的 1

        while (n > m) {
            n &= (n - 1);
        }
        return n;
 */
