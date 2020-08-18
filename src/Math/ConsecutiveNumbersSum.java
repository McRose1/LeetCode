package Math;

/*  829. Consecutive Numbers Sum
    Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

    Example 1:
    Input: 5
    Output: 2
    Explanation: 5 = 5 = 2 + 3

    Example 2:
    Input: 9
    Output: 3
    Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4

    Example 3:
    Input: 15
    Output: 4
    Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5

    Note: 1 <= N <= 10 ^ 9.
 */

/*  Math: Time = O(sqrt(n)) Space = O(1)
    N = k + k+1 + k+2 +⋯+ k+(i-1) -> N = k*i + i(i-1)/2 -> N - i(i-1)/2 = k * i
    N > i(i-1)/2; (N - i(i-1)/2) 是 i 的 k 倍
 */
public class ConsecutiveNumbersSum {
    public int consecutiveNumbersSum(int N) {
        int res = 0;
        for (int i = 1; i * (i - 1) / 2 < N; i++) {
            if ((N - i * (i - 1) / 2) % i == 0) {
                res++;
            }
        }
        return res;
    }
}

/*  LC

        while ((N & 1) == 0) {
            N >>= 1;
        }
        int res = 1, d = 3;

        while (d * d <= N) {
            int e = 0;
            while (N % d == 0) {
                N /= d;
                e++;
            }
            res *= e + 1;
            d += 2;
        }
        if (N > 1) {
            res <<= 1;
        }
        return res;
 */
