package BFS_DFS;

/*  200. Number of Islands
    Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    You may assume all four edges of the grid are all surrounded by water.

    Example 1:
    Input:
    11110
    11010
    11000
    00000
    Output: 1

    Example 2:
    Input:
    11000
    11000
    00100
    00011
    Output: 3
 */

//  DFS: Time = O(m*n) Space = O(m*n)
public class NumberofIslands {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j, rows, cols);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y, int rows, int cols) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == '0') return;
        grid[x][y] = '0';
        for (int[] dir : dirs) {
            int nX = dir[0] + x;
            int nY = dir[1] + y;
            dfs(grid, nX, nY, rows, cols);
        }
    }
}
