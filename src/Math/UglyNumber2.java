package Math;

/*  264. Ugly Number 2
    Write a program to find the n-th ugly number.

    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

    Example:
    Input: n = 10
    Output: 12
    Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

    Note:
    1 is typically treated as an ugly number.
    Input is within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 */

/*  Math + DP
    乘 2: 1×2, 2×2, 3×2, 4×2, 5×2, 6×2, 8×2, 9×2,…
    乘 3: 1×3, 2×3, 3×3, 4×3, 5×3, 6×3, 8×3, 9×3,…
    乘 5: 1×5, 2×5, 3×5, 4×5, 5×5, 6×5, 8×5, 9×5,…
    我们需要做的就是把上边三组按照顺序合并起来
 */
public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        int[] nums = new int[n];
        // 相当于维护了 3 个指针
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        nums[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int next2 = nums[index2] * 2;
            int next3 = nums[index3] * 3;
            int next5 = nums[index5] * 5;
            nums[i] = Math.min(next2, Math.min(next3, next5));
            if (nums[i] == nums[index2] * 2) {
                index2++;
            }
            if (nums[i] == nums[index3] * 3) {
                index3++;
            }
            if (nums[i] == nums[index5] * 5) {
                index5++;
            }
        }
        return nums[n - 1];
    }
}
