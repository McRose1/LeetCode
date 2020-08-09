package BFS_DFS;

/*  1391. Check if There is a Valid Path in a Grid
    Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
        o 1 which means a street connecting the left cell and the right cell.
        o 2 which means a street connecting the upper cell and the lower cell.
        o 3 which means a street connecting the left cell and the lower cell.
        o 4 which means a street connecting the right cell and the lower cell.
        o 5 which means a street connecting the left cell and the upper cell.
        o 6 which means a street connecting the right cell and the upper cell.
    You will initially start at the street of the upper-left cell (0, 0).
    A valid path in the grid is a path which starts from the upper left cel (0, 0) and ends at the bottom-right cell (m - 1, n - 1).
    The path should only follow the streets.
    Notice that you are not allowed to change any street.
    Return true if there is a valid path in the grid or false otherwise.

    Example 1:
    Input: grid = [[2,4,3],[6,5,2]]
    Output: true
    Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).

    Example 2:
    Input: grid = [[1,2,1],[1,2,1]]
    Output: false
    Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)

    Example 3:
    Input: grid = [[1,1,2]]
    Output: false
    Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).

    Example 4:
    Input: grid = [[1,1,1,1,1,1,3]]
    Output: true

    Example 5:
    Input: grid = [[2],[2],[2],[2],[2],[2],[6]]
    Output: true

    Constraints:
        o m == grid.length
        o n == grid[i].length
        o 1 <= m, n <= 300
        o 1 <= grid[i][j] <= 6

    Hint:
    1. Start DFS from the node (0, 0) and follow the path till you stop.
    2. When you reach a cell and cannot move anymore check that this cell is (m - 1, n - 1) or not.
 */

import java.util.LinkedList;
import java.util.Queue;

/*  BFS

 */
public class CheckifThereisaValidPathinaGrid {
    public boolean hasValidPath(int[][] grid) {
       int m = grid.length;
       int n = grid[0].length;
       boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;
        int[][][] dirs = {{{0, -1}, {0, 1}}, {{1, 0}, {-1, 0}}, {{0, -1}, {1, 0}}, {{1, 0}, {0, 1}}, {{0, -1}, {-1, 0}}, {{-1, 0}, {0, 1}}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            if (x == m - 1 && y == n - 1) {
                return true;
            }
            int type = grid[x][y] - 1;
            for (int[] dir : dirs[type]) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    int[] dir1 = dirs[grid[nx][ny] - 1][0];
                    int[] dir2 = dirs[grid[nx][ny] - 1][1];
                    if (dir[0] == -dir1[0] && dir[1] == -dir1[1] || dir[0] == -dir2[0] && dir[1] == -dir2[1]) {
                        queue.offer(new int[] {nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return false;
    }
}

/*  DFS

    private int m;
    private int n;
    private boolean[][] visited;
    private int[][][] dirs = {{{0, -1}, {0, 1}}, {{1, 0}, {-1, 0}}, {{0, -1}, {1, 0}}, {{1, 0}, {0, 1}}, {{0, -1}, {-1, 0}}, {{-1, 0}, {0, 1}}};

    public boolean hasValidPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        return dfs(grid, 0, 0);
    }

    private boolean dfs(int[][] grid, int x, int y) {
        // 递归出口
        if ( x == m - 1 && y == n - 1) return true;

        visited[x][y] = true;
        for (int[] dir : dirs[grid[x][y] - 1]) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                // check next cell can go back to current cell (意味着连通)
                for (int[] ndir : dirs[grid[nx][ny] - 1]) {
                    if (nx + ndir[0] == x && ny + ndir[1] == y) {
                        if (dfs(grid, nx, ny)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
 */