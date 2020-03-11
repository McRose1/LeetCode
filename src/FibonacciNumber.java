/*
    The Fibonacci numbers, commonly denoted F(n) from a sequence, called the Fibonacci sequence,
    such that each number is the sum of the two preceding ones, starting from 0 and 1.
    That is:
    F(0) = 0,   F(1) = 1
    F(N) = F(N - 1) + F(N - 2), for N > 1.
    Given N, calculate F(N).

    Example 1:
    Input: 2
    Output: 1

    Example 2:
    Input: 3
    Output: 2

    Example 3:
    Input: 4
    Output: 3

    Note: 0 ≤ N ≤ 30.
 */
// Bottom-Up Approach using Memorization (O(N), O(N))
public class FibonacciNumber {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        return memoize(N);
    }

    public int memoize(int N) {
        int[] cache = new int[N + 1];
        cache[1] = 1;

        for (int i = 2; i <= N; i ++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[N];
    }
}

/*
    // Top-Down Approach using Memorization (O(N), O(N))
public class FibonacciNumber {

    private Integer[] cache = new Integer[31];

    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        cache[0] = 0;
        cache[1] = 1;
        return memoize(N);
    }

    public int memoize(int N) {
        if (cache[N] != null) {
            return cache[N];
        }
        cache[N] = memoize(N - 1) + memoize(N - 2);
        return memoize[N];
    }
}
 */
/*
// Iterative Top-Down Approach (O(N), O(1))
public class FibonacciNumber {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        if (N == 2) {
            return 1;
        }
        int current = 0;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 3; i <= N; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
}
 */

/*
    Recursive is bad here -> Time = O(2^n)
public class FibonacciNumber {
    public int fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fib(N - 1) + fib(N - 2);
    }
}
 */