package BFS_DFS;

/*  695. Max Area of Island
    Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
    You may assume all four edges of the grid are surrounded by water.

    Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

    Example 1:
    [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,1,1,0,1,0,0,0,0,0,0,0,0],
     [0,1,0,0,1,1,0,0,1,0,1,0,0],
     [0,1,0,0,1,1,0,0,1,1,1,0,0],
     [0,0,0,0,0,0,0,0,0,0,1,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.

    Example 2:
    [[0,0,0,0,0,0,0,0]]
    Given the above grid, return 0.

    Note: The length of each dimension in the given grid does not exceed 50.
 */

/*  DFS Recursive

 */
public class MaxAreaofIsland {

    private int m;
    private int n;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        m = grid.length;
        n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int local = dfs(grid, i, j);
                    max = Math.max(max, local);
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = 0;
        return dfs(grid, i + 1, j) + dfs(grid, i - 1, j) + dfs(grid, i, j + 1) + dfs(grid, i, j - 1) + 1;
    }
}

/*  DFS Iterative (Stack)

        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int count = 0;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[] {i, j});
                    grid[i][j] = 0;
                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        int r = cur[0];
                        int c = cur[1];
                        count++;
                        for (int[] dir : dirs) {
                            int nr = r + dir[0];
                            int nc = c + dir[1];
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                                stack.push(new int[] {nr, nc});
                                grid[nr][nc] = 0;
                            }
                        }
                    }
                    ans = Math.max(ans, count);
                }
            }
        }
        return ans;
 */

/*  BFS (Queue)

        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int count = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[] {i, j});
                    grid[i][j] = 0;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int r = cur[0];
                        int c = cur[1];
                        count++;
                        for (int[] dir : dirs) {
                            int nr = r + dir[0];
                            int nc = c + dir[1];
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                                queue.offer(new int[] {nr, nc});
                                grid[nr][nc] = 0;
                            }
                        }
                    }
                    ans = Math.max(ans, count);
                }
            }
        }
        return ans;
 */