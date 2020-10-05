package BinarySearch;

/*  793. Preimage Size of Factorial Zeros Function
    Let f(x) be the number of zeroes at the end of x1.
    (Recall that x! = 1 * 2 * 3 * ... * x, and by conversion, 0! = 1.)
    For example, f(3) = 0 because 3! = 6 has no zeros at the end, while f(11) = 2 because 11! = 39916800 has 2 zeros at the end.
    Given K, find how many non-negative integers x have the property that f(x) = K.

    Example 1:
    Input: K = 0
    Output: 5
    Explanation: 0!, 1!, 2!, 3!, and 4! end with K = 0 zeroes.

    Example 2:
    Input: K = 5
    Output: 0
    Explanation: There is no x such that x! ends in K = 5 zeroes.

    Note:
        K will be an integer in the range [0, 10^9].
 */

/*  Binary Search: Time = O(logK) Space = O(logK)
    阶乘末尾零的个数由因子 2 和 5 个数的最小值决定，其实就是除以 5 的次数之和
 */
public class PreimageSizeofFactorialZeroesFunction {
    public int preimageSizeFZF(int K) {
        long lo = K;
        long hi = K * 10L + 1;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            long zmi = zeta(mid);
            if (zmi == K) {
                return 5;
            } else if (zmi < K) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return 0;
    }

    private long zeta(long x) {
        if (x == 0) return 0;
        return x / 5 + zeta(x / 5);
    }
}

/*  Version 2

    public int preimageSizeFZF(int K) {
        if (K == 0) return 5;
        return (int) (binarySearch(K) - binarySearch(K - 1));
    }

    private long binarySearch(int K) {
        long l = 0;
        long r = K * 10L + 1;

        while (l <= r) {
            long mid = l + (r - l) / 2;
            long k = numOfTrailingZeros(mid);

            if (k <= K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private long numOfTrailingZeros(long x) {
        long res = 0;
        for ( ; x > 0; x /= 5) {
            res += x / 5;
        }
        return res;
    }
 */
