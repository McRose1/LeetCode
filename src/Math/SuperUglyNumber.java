package Math;

/*  313. Super Ugly Number
    Write a program to find the nth super ugly number.
    Super ugly number are positive numbers whose all prime factors are in the given prime list primes of size k.

    Example:
    Input: n = 12, primes = [2,7,13,19]
    Output: 32
    Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
                 super ugly numbers given primes = [2,7,13,19] of size 4.

    Note:
        o 1 is a super ugly number for any given primes.
        o The given numbers in primes are in ascending order.
        o 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
        o The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */

/*  DP: Time = O(nk)  Space = O(n)
    依次求出前 n 个超级丑数
    定义 times[j] 表示当前的超级丑数中的质因数中，列表中第 i 个素数的次数（prime index 指针），而 dp[times[j]] 就是 prime 当前要乘的丑数
    uglys[i] 表示第 i+1 个素数
    1. 枚举，求出 uglys[i+1] 即为 primes[j] * uglys[times[j]] 的最小值
    2. 更新对应的 times[i]
 */
public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        // 相当于指针数组，初始化为 0
        int[] times = new int[len];
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;

            // 将丑数从小到大依次排序
            for (int j = 0; j < len; j++) {
                dp[i] = Math.min(dp[i], primes[j] * dp[times[j]]);
            }

            // 更新各个 prime 的 index，这一步是为了避免 duplicate
            for (int j = 0; j < len; j++) {
                // 说明这一轮循环选择的 prime 是 j，即最小数，可能会有多个 j 满足最小数
                if (dp[times[j]] * primes[j] == dp[i]) {
                    times[j]++;
                }
            }
        }
        return dp[n - 1];
    }
}

/*  minHeap: Time = O(nklogn) Space = O(k)

        // avoid duplicates
        HashSet<Long> set = new HashSet<>();

        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        set.add(1l);
        minHeap.offer(1l);
        long res = 1l;
        for (int i = 1; i < n; i++) {  // 1; 2
            long cur = minHeap.poll();  // 1; 2
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
        return minHeap.poll().intValue();
 */
