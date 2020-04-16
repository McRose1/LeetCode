package BitManipulation;

/*  231. Power of Two
    Given an integer, write a function to determine if it is a power of two.

    Example 1:
    Input: 1
    Output: true

    Example 2:
    Input: 16
    Output: true

    Example 3:
    Input: 218
    Output: false
 */
/*  去除二进制中最右边的 1: Time = O(1)
    因为 x & (x - 1) 可以将最右边的 1 设置为 0
    而 2 的幂只有 1 个 1，所以通过这个操作如果得到的是 0 就一定能证明是 2 的幂
 */
public class PowerofTwo {
    public boolean isPowerOfTwo(int n) {
        if (n < 1) return false;
        return (n & (n - 1)) == 0;
    }
}

/*  获取二进制中最右边的 1: Time = O(1)
    x & (-x) 可以获取二进制中最右边的 1，且其他位均为 0
    In Two's complement: -x = ~x + 1
    因此，x 和 -x 只有一个共同点：最右边的 1
    而 2 的幂只有 1 个 1，所以最右边的 1 也就是这个唯一的 1 被保留

        if (n < 1) return false;
        return (n & (-n)) == n;
 */

/*  my version: Time = O(logn)

        if (n < 1) {
            return false;
        }
        if (n == 1) {
            return true;
        }

        while (n != 1) {
            if ((n & 1) == 1) {
                return false;
            }
            n = n >> 1;
        }
        return true;
 */
