/*
    In a 2 dimensional array grid, each value grid[i][j] represents the height of a building located there.
    We are allowed to increase the height of any number of buildings,
    by any amount (the amounts can be different for different buildings).
    Height 0 is  considered to be a building as well.

    At the end, the "skyline" when viewed from all four directions of the grid,
    i.e. top, bottom, left, right, must be the same as the skyline of the original grid.
    A city's skyline is the outer contour of the rectangles formed by all the buildings when viewed from a distance.
    See the following example.

    What is the maximum total sum that the height of the buildings can be increased?
    Example:
    Input: grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
    Output: 35

    Note:
    1 < grid.length = grid[0].length <= 50.
    All heights grid[i][j] are in the range [0, 100].
    All buildings in grid[i][j] occupy the entire grid cell: that is, they are a 1 x 1 x grid[i][j] rectangular prism.
 */

public class MaxIncreasetoKeepCitySkyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int N = grid.length;
        int[] rowMax = new int[N];
        int[] colMax = new int[N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                rowMax[r] = Math.max(rowMax[r], grid[r][c]);
                colMax[c] = Math.max(colMax[c], grid[r][c]);
            }
        }
        int ans = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                ans += Math.min(rowMax[r], colMax[c]) - grid[r][c];
            }
        }
        return ans;
    }
}
