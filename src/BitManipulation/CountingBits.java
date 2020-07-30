package BitManipulation;

/*  338. Counting Bits
    Given a non negative integer number num.
    For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

    Example 1:
    Input: 2
    Output: [0,1,1]

    Example 2:
    Input: 5
    Output: [0,1,1,2,1,2]

    Follow up:
        o It is very easy to come up with a solution with run time O(n*sizeof(integer)).
          But can you do it in linear time O(n) / possibly in a single pass?
        o Space complexity should be O(n).
        o Can you do it like a boss?
          Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

    Hint:
    1. You should make use of what you have produced already.
    2. Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
    3. Or does the odd/even status of the number help you in calculating the number of 1s?
 */

/*  DP: Time = O(n) Space = O(n)
    看 Hint 3 找规律：
    偶数 1 的个数和该偶数除以 2 得到数字的 1 的个数相同，奇数的个数等于该奇数除以 2 得到数字的 1 的个数再加 1
 */
public class CountingBits {
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i / 2] + 1;
            }
        }
        return dp;
    }
}

/*  DP + 最后设置位: Time = O(n) Space = O(n)
    i & (i - 1) 将从右到左第一个为 1 的位置为 0
    dp[i] = dp[i & (i - 1)] + 1
i    bit   i          (i-1)  i&(i-1)
     0    0000    0           0
     -----------------------
     1    0001    1    0000   0+1
     -----------------------
     2    0010    1    0000   0+1
     3    0011    2    0010   1+1
     -----------------------
     4    0100    1    0000
     5    0101    2    0100
     6    0110    2    0100
     7    0111    3    0110
     -----------------------
     8    1000    1    0000
     9    1001    2    1000
     10   1010    2    1000
     11   1011    3    1010
     12   1100    2    1000
     13   1101    3    1100
     14   1110    3    1100
     15   1111    4    1110

    public int[] countBits(int num) {
        int[] count = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            count[i] = count[i & (i - 1)] + 1;
        }
        return count;
    }
 */

/*  DP + 最高有效位: Time = O(n) Space = O(n)
    Hint 1, 2
    0 -> 0; 1 -> 01; 2 -> 10; 3 -> 11
    我们可以使用 [0,3] 作为蓝本来得到 [4,7]，以此类推...[8,15]...
    P(x + b) = P(x) + 1, b = 2^m > x

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        int i = 0, b = 1;
        // [0, b) is calculated
        while (b <= num) {
            // generate [b, 2b) or [b, num) from [0, b)
            while (i < b && i + b <= num) {
                res[i + b] = res[i] + 1;
                i++;
            }
            // reset i
            i = 0;
            // b = 2b
            b <<= 1;
        }
        return res;
    }
 */

/*  DP + 最低有效位（就是高亮答案）: Time = O(n) Space = O(n)
    观察 x 和 x/2 的关系：只有最低有效为不同
    P(x) = P(x/2) + (x mod 2)

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            // x / 2 is x >> 1 and x % 2 is x & 1
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }
 */

/*  Integer.bitCount()

        int[] count = new int[num + 1];
            for (int i = 0; i <= num; i++) {
                count[i] = Integer.bitCount(i);
            }
            return count;
 */

/*  Bit Manipulation: Time = O(nk) Space = O(n)

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = popcount(i);
        }
        return res;
    }

    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; count++) {
            // zeroing out the least significant nonzero bit
            x &= x - 1;
        }
        return count;
    }
 */