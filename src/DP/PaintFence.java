package DP;

/*  276. Paint Fence
    There is a fence with n posts, each post can be painted with one of the k colors.
    You have to paint all the posts such that no more than two adjacent fence posts have the same color.
    Return the total number of ways you can paint the fence.

    Note:
    n and k and non-negative integers.

    Example:
    Input: n = 3, k = 2
    Output: 6
    Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

                post1  post2  post3
     -----      -----  -----  -----
       1         c1     c1     c2
       2         c1     c2     c1
       3         c1     c2     c2
       4         c2     c1     c1
       5         c2     c1     c2
       6         c2     c2     c1
 */

/*  DP: Time = O(n) Space = O(1)
    一次需要考虑 3 个相邻的 fence，第 3 个 fence 在前 2 个 fence 可能涂法的基础上有 2 种涂法，即和前一个相同和不同
    而前面 2 个 fence 又有 2 种涂法：
    1. 前 2 个 fence 涂不同的颜色：第 3 个 fence 可以和第 2 个 fence 颜色相同
    2. 前 2 个 fence 涂相同的颜色：第 3 个 fence 需要和第 2 个 fence 颜色不同
 */
public class PaintFence {
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;

        // 前 2 个 fence：
        // 涂不同的颜色
        int diff = k * (k - 1);
        // 涂相同的颜色
        int same = k;

        // 后面的 fence
        for (int i = 2; i < n; i++) {
            int temp = diff;
            // 第 3 个 fence 和第 2 个 fence 颜色不同
            // 前 2 个相同 * (k - 1) + 前 2 个不同 * (k - 1)
            diff = diff * (k - 1) + same * (k - 1);
            // 第 3 个 fence 和第 2 个 fence 颜色相同
            // 前 2 个必须不同，diff * 1
            same = temp;
        }
        return same + diff;
    }
}
