package Math;

/*  1201. Ugly Number 3
    Write a program to find the n-th ugly number.

    Ugly numbers are positive numbers which are divisible by a or b or c.

    Example:
    Input: n = 3, a = 2, b = 3, c = 5
    Output: 4
    Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.

    Example 2:
    Input: n = 4, a = 2, b = 3, c = 4
    Output: 6
    Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.

    Example 3:
    Input: n = 5, a = 2, b = 11, c = 13
    Output: 10
    Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.

    Example 4:
    Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
    Output: 1999999984

    Constraints:
        o 1 <= n, a, b, c <= 10^9
        o 1 <= a * b * c <= 10^18
        o It's guaranteed that the result will be in range [1, 2 * 10^9]

    Hint:
    1. Write a function f(k) to determine how many ugly numbers smaller than k.
       As f(k) is non-decreasing, try binary search.
    2. Find all ugly numbers in [1, LCM(a, b, c)] (LCM is Least Common Multiple).
       Use inclusion-exclusion principle to expand the result.
 */

/*  Math + Binary Search: Time = O(log(2^32)) = O(1) Space = O(1)
    Guess x, compute how many ugly numbers are less or equal to x.
    How many ugly numbers are less or equal to:
    2: 2, 4, 6, 8, 10, 12, ..., 30, ...   c = x/2
    3: 3, 6, 9, 12, 15, ..., 30, ...      c = x/3
    5: 5, 10, 15, ..., 30 ...             c = x/5
    c = x/2 + x/3 + x/5 - x/(2*3) - x/(2*5) - x/(3*5) + x/(2*3*5)
      = x/a + x/b + x/c - x/lcm(a,b) - x/lcm(b,c) - x/lcm(a,c) + x/lcm(a,b,c)
 */
public class UglyNumber3 {
    private int max = (int) 2e9;
    public int nthUglyNumber(int n, int a, int b, int c) {
        int left = 0;
        int right = max;
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (count(mid, a, b, c) >= n) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private int count(long num, long a, long b, long c) {
        return (int) (num / a + num / b + num / c - num / lcm(a, b) - num / lcm(b, c) - num / lcm(a, c) + num / (lcm(a, lcm(b, c))));
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
