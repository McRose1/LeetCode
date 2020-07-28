package DP;

/*  1130. Minimum Cost Tree From Leaf Values
    Given an array arr of positive integers, consider all binary trees such that:
        o Each node has either 0 or 2 children;
        o The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
          (Recall that a node is a leaf if and only if it has 0 children.)
        o The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.

    Among all positive binary trees considered, return the smallest possible sum of the values of each non-leaf node.
    It is guaranteed this sum fits into a 32-bit integer.

    Example:
    Input: arr = [6,2,4]
    Output: 32
    Explanation:
    There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

        24            24
       /  \          /  \
      12   4        6    8
     /  \               / \
    6    2             2   4

    Constraints:
    2 <= arr.length <= 40
    1 <= arr[i] <= 15
    It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).

    Hint:
    Do a DP, where dp(i, j) is the answer for the subarray arr[i]..arr[j].
    For each possible way to partition the subarray i <= k < j,
    the answer is max(arr[i]..arr[k]) * max(arr[k+1]..arr[j]) + dp(i, k) + dp(k+1, j).
 */

/*  区间DP: Time = O(n^3) Space = O(n^2)
    See Hint!
 */
public class MinimumCostTreeFromLeafValues {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;

        // max[i][j] 表示在[i,j]这段区间内的最大值
        int[][] max = new int[n][n];
        for (int i = 0; i < n; i++) {
            int localMax = 0;
            for (int j = i; j < n; j++) {
                if (arr[j] > localMax) {
                    localMax = arr[j];
                }
                max[i][j] = localMax;
            }
        }

        int[][] dp = new int[n][n];
        // 从最小的区间开始遍历
        for (int len = 1; len < n; len++) {
            // 左边界
            for (int i = 0; i + len < n; i++) {
                // 右边界
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                // 当前区间只有 2 个叶子节点
                if (len == 1) {
                    dp[i][j] = arr[i] * arr[j];
                } else {
                    // k 作为 root 将二叉树分为左右子树
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], max[i][k] * max[k + 1][j] + dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}

/*  Stack: Time = O(n) Space = O(n)

        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int min = 0;
        stack.push(Integer.MAX_VALUE);
        for (int cur : arr) {
            while (stack.peek() <= cur) {
                // drop value is the value smaller than its both side
                int drop = stack.pop();
                min += drop * Math.min(stack.peek(), cur);
            }
            stack.push(cur);
        }

        while (stack.size() > 2) {
            // make the bigger number be used as less as possible
            min += stack.pop() * stack.peek();
        }
        return min;
 */