package DP;

/*  96. Unique Binary Search Trees
    Given n, how many structurally unique BST's (binary search trees) that stores values 1 ... n?

    Example:
    Input: 3
    Output: 5
    Explanation:
    Given n = 3, there are a total of 5 unique BST's:

       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
 */
/*  DP: Time = O(n^2) Space = O(n)
    dp[0] = 1
    dp[1] = 1
    dp[2] = 1(dp[0] * dp[1]) + 1(dp[1] * dp[0]) = 2
    dp[3] = 2(dp[0] * dp[2]) + 1 + 2 = 5
    dp[4] = 5(dp[0] * dp[3]) + 2(dp[1] * dp[2]) + 2 + 5 = 14
    ...
    dp[i] = sum(dp[j] * dp[i - j - 1]) j(0 -> i)
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        // 空树也是 1 种可能
        dp[0] = 1;
        // 只有 1 个节点，1 种可能
        dp[1] = 1;

        // 子问题，节点的数量从 2 -> n
        for (int i = 2; i <= n; i++) {
            // j 是左子树的节点数量，相当于遍历 root 从 1 -> i 的所有可能
            for (int j = 0; j < i; j++) {
                // i-j-1 是右子树的节点数量，加和为 i-1（去掉 root）
                dp[i] += (dp[j] * dp[i - j - 1]);
            }
        }
        return dp[n];
    }
}

/*  Math: Time = O(n) Space = O(1)
    卡特兰数
    Cn+1 = (2(2n + 1) / (n + 2)) * Cn (C0 = 1)

        // 提示：我们在这里需要用 long 类型防止计算过程中的溢出
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
 */