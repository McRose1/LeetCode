package DP;

/*  63. Unique Paths 2
    A robot is located at the top-left corner of a (m x n) grid (marked 'Start' in the diagram below).
    The robot can only move either down or right at any point in time.
    The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

    Now consider if some obstacles are added to the grids. How many unique paths would there be?
    An obstacle and empty space is marked as 1 and 0 respectively in the grid.

    Note: m and n will be at most 100.

    Example 1:
    [
      [0,0,0],
      [0,1,0],
      [0,0,0]
    ]
    Output: 2
 */

/*  DP: Time = O(m*n) Space = O(1)
    We will be using the obstacleGrid array as the DP array thus not utilizing any additional space.
    cell with an obstacle has a value 1, we would use this value to make sure if a cell needs to be included in the path or not.
    After that we can use the same cell to store the number of ways to reach that cell.
    1. If the first cell contains 1, this means there is an obstacle in the first cell.
    Hence the robot won't be able to make any move and we would return the number of ways as 0.
    2. Otherwise, if obstacleGrid[0,0] has a 0 originally we set it to 1 and move ahead.
    3. Iterate the first row.
    4. Iterate the first column.
    5. Iterate through the array starting from cell obstacleGrid[1,1].
    6. If a cell contains an obstacle set it to 0 and continue.
 */
public class UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be no paths to the destination
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        // Number of ways of reaching the starting cell = 1
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }
        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }
        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }
}

/*  my version

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            } else {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
 */