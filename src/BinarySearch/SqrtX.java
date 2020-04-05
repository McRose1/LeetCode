package BinarySearch;

/*  69. Sqrt(x)
    Implement int sqrt(int x).
    Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
    Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

    Example 1:
    Input: 4
    Output: 2

    Example 2:
    Input: 8
    Output: 2

    Hint: Use the sorted property of integers to reduced the search space.
 */
// Binary Search: Time = O(logx) Space = O(1)
public class SqrtX {
    public int mySqrt(int x) {
        if (x <= 1) return x;
        int low = 2, high = x;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid > x / mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
}
/*  my version

        if (x <= 1) return x;
        int left = 1;
        int right = x;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) return mid;
            else if (mid > x / mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (right > x / right) return left;
        else return right;
 */

/*  Newton's method: Time = O(logx)
    Find better approximations to the roots of a real-valued function.
    Guess x0, then update x using the following function:
    xn+1 = xn - f(xn)/f'(xn)
    xn+1 = xn - (xn^2 - a) / 2xn
         = (xn + a/xn) / 2

        long a = x;
        while (a * a > x) {
            a = (a + x / a) / 2;
        }
        return (int)a;
 */

/*  Brute Force: Time = O(sqrt(x)) Space = O(1)

        if (x <= 1) return x;
        for (long s = 1; s <= x; ++s) {
            if (s * s > x) return (int)s - 1;
        }
        return -1;
 */