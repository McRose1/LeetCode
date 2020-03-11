package PriorityQueue;

/*  313. Super Ugly Number
    Write a program to find the nth super ugly number.
    Super ugly number are positive numbers whose all prime factors are in the given prime list primes of size k.

    Example:
    Input: n = 12, primes = [2,7,13,19]
    Output: 32
    Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
                 super ugly numbers given primes = [2,7,13,19] of size 4.

    Note:
    1 is a super ugly number for any given primes.
    The given numbers in primes are in ascending order.
    0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
    The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//  minHeap: Time = O(nklogn) Space = O(k)
public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        set.add(1l);
        minHeap.offer(1l);
        long ans = 1l;
        for (int i = 1; i <= n; i++) {  // 1; 2
            long cur = minHeap.poll();  // 1; 2
            if (i == n) {
                ans = cur;
            }
            for (int prime : primes) {  // 2; 7; 13; 19
                long newVal = cur * prime;  // 2; 7; 13; 19/ 4; 14; 26; 38
                if (newVal > Integer.MAX_VALUE) {
                    continue;
                }
                if (!set.contains(newVal)) {
                    minHeap.offer(newVal);  // 2,7,13,19; 4,13,14,19,26,38
                    set.add(newVal);
                }
            }
        }
        return (int) ans;
    }
}

/*  Time = O(nk)  Space = O(n)
    依次求出前 n 个超级丑数
    定义 times[i] 表示当前的超级丑数中的质因数中，列表中第 i 个素数的次数
    uglys[i] 表示第 i+1 个素数
    1. 枚举，求出 uglys[i+1] 即为 primes[j] * uglys[times[j]] 的最小值
    2. 更新对应的 times[i]

        int[] times = new int[primes.length];
        int[] uglys = new int[n];
        uglys[0] = 1;

        for (int i = 1; i < n; i++) {
            uglys[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                uglys[i] = Math.min(uglys[i], primes[j] * uglys[times[j]]);
            }
            for (int j = 0; j < times.length; j++) {
                if (uglys[times[j]] * primes[j] == uglys[i]) {
                    times[j]++;
                }
            }
        }
        return uglys[n - 1];
 */
