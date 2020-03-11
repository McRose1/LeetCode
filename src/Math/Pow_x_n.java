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
        double y = pow(x, n / 2);
        if (n % 2 == 0) {
            return y * y;
        } else {
            return y * y * x;
        }
    }
}

/*  Iteration: Time = O(logn) Space = O(1)

        if (n == 0) return 1;
        double res = 1;
        long abs = Math.abs((long)n);
        while (abs > 0) {
            if (abs % 2 != 0) {
                res *= x;
            }
            x *= x;
            abs /= 2;
        }
        if (n < 0) {
            return 1.0 / res;
        }
        return res;
 */
