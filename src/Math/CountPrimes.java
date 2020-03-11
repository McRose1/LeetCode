package Math;

/*  204. Count Primes
    Count the number of prime numbers less than a non-negative number, n.

    Example:
    Input: 10
    Output: 4
 */

//  空间换时间：Sieve of Eratosthenes Time = O(nlog(logn)) Space = O(n)
public class CountPrimes {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];    // 初始化为 false
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                res++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return res;
    }
}
/*  Optimization:
    In fact, we can mark off multiples of 5 starting at 5x5=25 because 5x2=10 was already marked off by multiple of 2,
    similarly 5x3=15 was already marked off by multiple of 3.
    Therefore, if the current number is p, we can always mark off multiples of p starting at p^2, then in increments of p: p^2+p, p^2=2p,...

    public int countPrimes(int n) {
    boolean[] isPrime = new boolean[n];
    for (int i = 2; i < n; i++) {
       isPrime[i] = true;
    }
    // Loop's ending condition is i * i < n instead of i < sqrt(n)
    // to avoid repeatedly calling an expensive function sqrt().
    for (int i = 2; i * i < n; i++) {
       if (!isPrime[i]) continue;
       for (int j = i * i; j < n; j += i) {
          isPrime[j] = false;
       }
    }
    int count = 0;
    for (int i = 2; i < n; i++) {
       if (isPrime[i]) count++;
    }
    return count;
    }
 */

/*  Brute Force: Time = O(n^1.5) Space = O(1)

    public class CountPrimes {
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        // Loop's ending condition is i*i <= num instead of i <= sqrt(num) to avoid repeatedly calling an expensive function sqrt()
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
 */