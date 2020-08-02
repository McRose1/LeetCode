package BinarySearch;

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
        if (num == 1) return true;
        long left = 2;
        long right = num / 2;
        // num = 16 会漏掉 mid = 4 如果是 left < right
        while (left <= right) {
            long mid = left + (right - left) / 2;
            // pay attention to the possible overflow problem
         // if (mid == num / mid && num % mid == 0)
            if (mid * mid == (long)num) {
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

/*  Newton Method: Time = O(logn) Space = O(1)

        if (num == 1) return true;
        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
 */


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

/*  Brute Force: Time = O(n) Space = O(1) -> TLE (n = 2147483647)

        if (num < 0) return false;
        if (num == 1) return true;
        for (int i = 1; i <= num; i++) {
            if (i * i == num) return true;
        }
        return false;
 */