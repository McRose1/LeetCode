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
    dp[2] = 1(dp[0]*dp[1]) + 1(dp[1]*dp[0]) = 2
    dp[3] = 2(dp[0]*dp[2]) + 1 + 2 = 5
    dp[4] = 5(dp[0]*dp[3]) + 2(dp[1]*dp[2]) + 2 + 5 = 14
    ...
    dp[i] = sum(dp[j] * dp[i - j - 1]) j(0->i)
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        if (n < 1) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += (dp[i - j - 1] * dp[j]);
            }
        }
        return dp[n];
    }
}
