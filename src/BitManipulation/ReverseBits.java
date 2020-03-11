package BitManipulation;

/*  190. Reverse Bits
    Reverse bits of a given 32 bits unsigned integer.

    Example 1：
    Input: 00000010100101000001111010011100
    Output: 00111001011110000010100101000000

    Example 2:
    Input: 11111111111111111111111111111101
    Output: 10111111111111111111111111111111

    Note:
    Note that in some languages such as Java, there is no unsigned integer type.
    In this case, both input and output will be given as signed integer type and should not affect your implementation,
    as the internal binary representation of the integer is the same whether it is signed or unsigned.

    In Java, the compiler represents the signed integers using 2's compliment notation.
    Therefore, in Example 2 above the input represents the signed integer -3
    and the output represents the signed integer -1073741825.
 */

/*  Bit Manipulation: Time = O(logn) Space = O(1)
    Base-10 time case:
    ans = ans * 10 + n % 10
    n /= 10
    Base-2 is exact the same if n is positive
    ans = ans * 2 + n % 2
    n /= 2
    or use bit operators:
    ans = (ans << 1) | (n & 1)
    n >>= 1
    But need to handle leading zeros.
    e.g. 00110 => 0100 bit 011
    When n is negative (only for Java), can not use arithmetic operators
 */
public class ReverseBits {
    public int reverseBits(int n) {
        if (n == 0) return 0;

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // 取到 least-significant bit, 从右往左
            ans = (ans << 1) | (n & 1);
            // 去掉 least-significant bit
            n >>= 1;
        }
        return ans;
    }
}
