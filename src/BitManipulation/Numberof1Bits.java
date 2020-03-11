package BitManipulation;

/*  191. Number of 1 Bits
    Write a function that takes an unsigned integer and return the number of '1' bits it has
    (also known as the Hamming weight).

    Example 1:
    Input: 00000000000000000000000000001011
    Output: 3

    Example 2:
    Input: 00000000000000000000000010000000
    Output: 1

    Example 3:
    Input: 11111111111111111111111111111101
    Output: 31

    Note:
    Note that in some languages such as Java, there is no unsigned integer type.
    In this case, the input will be given as signed integer type and should not affect your implementation,
    as the internal binary representation of the integer is the same whether it is signed or unsigned.

    In Java, the compiler represents the signed integers using 2's complement notation.
    Therefore, in Example 3 above the input represents the signed integer -3.

    Follow up: If this function is called many times, how would you optimize it?
 */

//  Loop and Flip: Time = O(1) Space = O(1)
public class Numberof1Bits {
    public int hammingWeight(int n) {
        int res = 0;
        int mask = 1;
        // traverse every bit
        for (int i = 0; i < 32; i++) {
            if ((n & mask) == 1) {
                res++;
            }
            n >>= 1;
        }
        return res;
    }
}

/*  Bit Manipulation: Time = O(1) Space = O(1)
    Instead of checking every bit of the number, we repeatedly flip the least-significant 1-bit of the number to 0,
    and add 1 to the sum.
    AND-ing n and n-1 flips the least-significant 1-bit to 0.

        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
 */