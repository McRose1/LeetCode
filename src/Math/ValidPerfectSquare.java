package Math;

/*  367. Valid Perfect Square
    Given a positive integer num, write a function which returns True if num is a perfect square else False.
    Note: Do not use any built-in library function such as sqrt.

    Example 1:
    Input: 16
    Output: true

    Example 2:
    Input: 14
    Output: false
 */
//  Binary Search: Time = O(logn) Space = O(1)
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        //if (num == 1) return true;
        long left = 1;
        long right = num;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (mid * mid == (long)num) {    // pay attention to the possible overflow problem
                return true;
            } else if (mid * mid > (long)num) {
                right = mid + 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}

/*  Math: Time = O(n) Space = O(1)
    1 = 1
    2 = 1 + 1
    4 = 1 + 3
    9 = 1 + 3 + 5
    16 = 1 + 3 + 5 + 7
    ...
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
 */

/*  Newton Method:

        long x = num;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
 */

/*  Brute Force: Time = O(n) Space = O(1) -> TLE (n = 2147483647)

        if (num < 0) return false;
        if (num == 1) return true;
        for (int i = 1; i <= num; i++) {
            if (i * i == num) return true;
        }
        return false;
 */