package Math;

/*  50. Pow(x, n)
    Implement pow(x, n), which calculates x raised to the power n (x^n).

    Example 1:
    Input: 2.00000, 10
    Output: 1024.00000

    Example 2:
    Input: 2.10000, 3
    Output: 9.26100

    Example 3:
    Input: 2.00000, -2
    Output: 0.25000

    Note:
    -100.0 < x < 100.0
    n is a 32-bit signed integer, within the range [-2^31, 2^31 - 1]
 */

/*  Recursion: Time = O(logn) Space = O(n)
    e.g. 2^2 = 2^1 * 2^1 = (2^0 * 2^0 * 2)
    e.g. 2^3 = 2^1 * 2^1 * 2= (2^0 * 2^0 * 2) * 2
    不论 pow 为正数还是负数，都可以用一个 helper 函数来执行
 */
public class Pow_x_n {
    public double myPow(double x, int n) {
        if (n > 0) {
            return pow(x, n);
        } else {
            return 1.0 / pow(x, n);
        }
    }
    public static double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        // 相当于二叉树，层数减为 logn
        double y = pow(x, n / 2);
        if (n % 2 == 0) {
            return y * y;
        } else {
            return y * y * x;
        }
    }
}

/*  Iteration: Time = O(logn) Space = O(1)
    将 pow 都取绝对值变为正数
    2^4 = 4^2 = 16

        if (n == 0) return 1;
        double res = 1;
        // 解决 Math.abs(-2147483648) 这一异常情况
        long abs = Math.abs((long)n);       // abs = 4
        while (abs > 0) {
            // abs 最终肯定为折半变为 1，所以这里 res 为出口
            if (abs % 2 != 0) {
                res *= x;                   // res = 16
            }
            x *= x;                         // x = 4; 16
            abs /= 2;                       // abs = 2; 1
        }
        if (n < 0) {
            return 1.0 / res;
        }
        return res;
 */
