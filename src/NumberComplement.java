/*
    Given a positive integer, output its complement number.
    The complement strategy is to flip the bits of it binary representation.

    Note:
        The given integer is guaranteed to fit within the range of a 32-bit signed integer.
        You could assume no leading zero bit in the integer’s binary representation.

    Example 1:
    Input: 5
    Output: 2

    Example 2:
    Input: 1
    Output: 0
 */
// 1001 + 0110 = 1111
public class NumberComplement {
    public int findComplement(int num) {
        int n = 0;
        while (n < num) {
            n = (n << 1) | 1;
        }
        return n - num;
    }
}
