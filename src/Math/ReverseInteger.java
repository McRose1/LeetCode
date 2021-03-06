package Math;

/*  7. Reverse Integer
    Given a 32-bit signed integer, reverse digits of an integer.

    Example 1:
    Input: 123
    Output: 321

    Example 2:
    Input: -123
    Output: -321

    Example 3:
    Input: 120
    Output: 21

    Note:
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31, 2^31−1].
    For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */

/*  Pop and Push Digits & Check before Overflow: Time = O(logx), there are roughly log10(x) digits in x; Space = O(1)
    pop operation:
    pop = x % 10;
    x /= 10;

    push operation:
    temp = rev * 10 + pop; (overflow)
    rev = temp;

    (rev ≥ INTMAX/10 will cause overflow)
    when rev is positive:
    1. If rev > INTMAX/10 -> guarantee to overflow
    2. If rev = INTMAX/10 -> overflow if and only if pop > 7 Integer.MAX_VALUE = 2147483647(end with 7)

    when rev is negative:
    1. If rev > INTMIN/10 -> guarantee to overflow
    2. If rev = INTMIN/10 -> overflow if and only if pop < -8 Integer.MIN_VALUE = -2147483648(end with 8)

    Reversing an integer can be done similarly to reversing a string.
    repeatedly "pop" the last digit off x and "push" it to the back of the rev.
    To "pop" and "push" digits without the help of some auxiliary stack/array, we can use math.
 */
public class ReverseInteger {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {            // 123; 12; 1
            // pop operation
            int pop = x % 10;       // pop = 3; 2; 1
            x /= 10;                // x = 12; 1; 0
            // positive
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            // negative
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            // push operation
            rev = rev * 10 + pop;       // rev = 0*10+3=3; 3*10+2=32; 32*10+1=321
        }
        return rev;
    }
}
