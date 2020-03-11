package BitManipulation;

/*  260. Single Number 3
    Given an array of numbers nums,
    in which exactly two elements appear only once and all the other elements appear exactly twice.
    Find the two elements that appear only once.

    Example:
    Input:  [1,2,1,3,2,5]
    Output: [3,5]

    Note:
    The order of the result is not important. So in the above example, [5, 3] is also correct.
    Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */

/*  Bit Manipulation: Time = O(n) space = O(1)
    假定 a 和 b 分别是我们最终要找的那两个数
    我们可以把所有数分成与 a 相关的和与 b 相关的两组
    与 a 相关的组 A 中，所有数异或起来的结果就是 a
    与 b 相关的组 B 中，所有数异或起来的结果就是 b
    x = a XOR b
    制造分离判断因子：找出 a 和 b 二进制表示法中不相同的其中一位
    x = x & (~(x-1)) -> 这会找到 x 从右往左数第一个为 1 （即 a 和 b 在这个位上不相同）的位
    将所有数分为 &x == 0 和 &x != 0 两组，每组内部进行 XOR 运算
 */
public class SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            // diff = a XOR b
            diff ^= num;        // 3 ^ 5 = 110
        }
        // 负数相当于取反+1
        // int lastBit = diff & ~(diff - 1)
        int lastBit = diff & (-diff);
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & lastBit) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}