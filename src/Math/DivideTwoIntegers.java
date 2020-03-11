package Math;

/*  29. Divide Two Integers
    Given two integers, dividend and divisor,
    divide two integers without using multiplication, division and mod operator.
    Return the quotient after dividing dividend by divisor.
    The integer division should truncate toward zero.

    Example 1:
    Input: dividend = 10, divisor = 3
    Output: 3

    Example 2:
    Input: dividend = 7, divisor = -3
    Output: -2

    Note:
    Both dividend and divisor will be 32-bit signed integers.
    The divisor will never be 0.
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
    [−2^31,  2^31 − 1].
    For the purpose of this problem, assume that your function returns 2^31 − 1 when the division result overflows.
 */

/*  移位倍增法 + Iteration: Time = O(logn) Space = O(1)
    Corner Case:
    1. +, - -> boolean
    2. overflow -> 两个整数相除，不可能出现除出来的数比原来的数大，只有一种情况: MIN_VALUE/-1
    3. = 0: 3/5
    4. 正常
 */
//  先转换成long，然后都变成正数（因为-2147483648）负数比正数多一个
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // 除数为 0
        if (divisor == 0) {
            return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        // 被除数为 0
        if (dividend == 0) {
            return 0;
        }
        // 越界: -2147483648 / -1
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 不想正负情况一起判断，所以都先变成正数再做减法
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int result = 0;
        while (a >= b) {
            int shift = 0;
            // a最大是 2147483648，但是由于a是long，我们的b<<shift可以超过，所以没关系
            while (a >= (b << shift)) {
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative ? -result : result;
    }
}
/*  不用转换成 long，然后用负数

        int a = dividend < 0 ? dividend : -dividend;
        int a = divisor < 0 ? divisor : -divisor;
        // 由于不使用 long，有两个地方可能导致越界
        // 1. (b << shift)可能会越界：-3<<30等于正数，相当于越过了0 这个界限
        // 2. shift 最多只可能移位 31：如果a=-2147483647永远是最小值，b=-1<<31 = -2147483647 进入死循环
        while (a <= b) {
            int shift = 0;
            while (a <= (b << shift) && (b <<shift) < 0 & shift <31) {
                shift++;
            }
            a -= b << (shift - 1);
            result -= 1 << (shift - 1);
        }
 */

/*  Tail Recursion (Binary Search): Time = O(logn) Space = O(logn)

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = -1;
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        if (ldividend < ldivisor || ldividend == 0) return 0;
        long lres = divide(ldividend, ldivisor);
        int res = 0;
        if (lres > Integer.MAX_VALUE) {
            res = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else res = (int)(sign * lres);
        return res;
    }
    public long divide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) return 0;
        long sum = ldivisor;
        long multiple = 1;
        while ((sum + sum) <= ldividend) {  // 加上=可以避免很多递归
            sum += sum;
            multiple += multiple;
        }
        return multiple + divide(ldividend - sum, ldivisor);
    }
}
 */