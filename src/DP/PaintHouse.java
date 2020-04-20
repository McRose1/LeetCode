package DP;

/*  256. Paint House
    There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
    The cost of painting each house with a certain color is different.
    You have to paint all the houses such that no two adjacent houses have the same color.

    The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
    For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
    Find the minimum cost to paint all houses.

    Note: All costs are positive intergers.

    Example:
    Input: [[17,2,17],[16,16,5],[14,3,19]]
    Output: 10
    Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
                 Minimum cost: 2 + 5 + 3 = 10.
 */
//  DP: Time = O(3*n) Space = O(3*n)
public class PaintHouse {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int n = costs.length;

        int[][] dp = new int[n][3];

        for (int j = 0; j < 3; j++) {
            dp[0][j] = costs[0][j];
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            min = Math.min(min, dp[n-1][j]);
        }
        return min;
    }
}
