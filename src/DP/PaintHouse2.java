package DP;

/*  257. Paint House 2
    There are a row of n houses, each house can be painted with one of the k colors.
    The cost of painting each house with a certain color is different.
    You have to paint all the houses such that no two adjacent houses have the same color.

    The cost of painting each house with a certain color is represented by a n x k cost matrix.
    For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color 2, and so on...
    Find the minimum cost to paint all houses.

    Note: All costs are positive integers.

    Example:
    Input: [[1,5,3],[2,9,4]]
    Output: 5
    Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
                 Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.

    Follow up: Could you solve it in O(nk) runtime?
 */

//  DP
public class PaintHouse2 {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int m = costs.length;
        int n = costs[0].length;

        int[][] dp = new int[m][n];

        // Initialize the 1st house's min cost of each color
        for (int i = 0; i < n; i++) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < m; i++) {
            int min = -1;
            int secondMin = -1;
            int minIndex = 0;

            // 初始化 min 和 second min
            if (dp[i - 1][0] < dp[i - 1][1]) {
                min = dp[i - 1][0];
                secondMin = dp[i - 1][1];
            } else {
                min = dp[i - 1][1];
                secondMin = dp[i - 1][0];
                minIndex = 1;
            }
            // Find the min and second min of dp[i - 1]
            for (int j = 2; j < n; j++) {
                if (dp[i - 1][j] < min) {
                    secondMin = min;
                    min = dp[i - 1][j];
                    minIndex = j;
                } else if (dp[i - 1][j] < secondMin) {
                    secondMin = dp[i - 1][j];
                }
            }
            // Construct the dp[i]
            for (int j = 0; j < n; j++) {
                if (j != minIndex) {
                    dp[i][j] = min + costs[i][j];
                } else {
                    dp[i][j] = secondMin + costs[i][j];
                }
            }
        }
        // Find the min cost of dp[last]
        int res = dp[m - 1][0];
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[m - 1][i]);
        }
        return res;
    }
}

/*  优化 DP

        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;

        int min1 = -1, min2 = -1;
        for (int i = 0; i < n; i++) {
            int last1 = min1, last2 = min2;
            min1 = -1; min2 = -1;
            for (int j = 0; j < k; j++) {
                if (j != last1) {
                    costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
                } else {
                    costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
                }

                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n - 1][min1];
 */
