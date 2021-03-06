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

/*  DFS: Time = O(m*n) Space = O(m*n)
    遇到 1 开始 DFS，从这个 1 开始向四个方向 DFS
    访问过的 1 置为 0
 */
public class NumberofIslands {

    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        m = grid.length;
        n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        // 这一步注意 grid[x][y] == '0' 的位置，必须先判断坐标是否越界，grid[x][y] 才不会 out of bound
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') return;

        // 这一步是这题的关键，相当于起到 visited 数组的作用，保证 DFS 不会回退
        grid[i][j] = '0';

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}

/*  BFS: Time = O(m*n) Space = O(min(m,n))

        if (grid == null || grid.length == 0) return 0;

        int row = grid.length;
        int col = grid[0].length;
        int count = 0;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    // mark as visited
                    grid[r][c] = '0';
                    Queue<Integer> nei = new LinkedList<>();
                    // 相当于是矩阵的遍历器：0 -> m*n-1
                    nei.offer(r * col + c);

                    while (!nei.isEmpty()) {
                        int idx = nei.poll();
                        // 这里我们可以保证新的坐标在矩阵的边界内
                        int new_r = idx / col;
                        int new_c = idx % col;
                        // 往四个方向 BFS
                        // 往上遍历，只需检查上边界
                        if (new_r - 1 >= 0 && grid[new_r - 1][new_c] == '1') {
                            nei.offer((new_r - 1) * col + new_c);
                            grid[new_r - 1][new_c] = '0';
                        }
                        // 往下遍历，只需检查下边界
                        if (new_r + 1 < row && grid[new_r + 1][new_c] == '1') {
                            nei.offer((new_r + 1) * col + new_c);
                            grid[new_r + 1][new_c] = '0';
                        }
                        // 往左遍历，只需检查左边界
                        if (new_c - 1 >= 0 && grid[new_r][new_c - 1] == '1') {
                            nei.offer((new_r) * col + new_c - 1);
                            grid[new_r][new_c - 1] = '0';
                        }
                        // 往右遍历，只需检查右边界
                        if (new_c + 1 < col && grid[new_r][new_c + 1] == '1') {
                            nei.offer((new_r) * col + new_c + 1);
                            grid[new_r][new_c + 1] = '0';
                        }
                    }
                }
            }
        }
        return count;
 */

/*  Union Find: Time = O(m*n) Space = O(m*n)

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int row = grid.length;
        int col = grid[0].length;

        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * col + c, (r - 1) * col + c);
                    }
                    if (r + 1 < row && grid[r + 1][c] == '1') {
                        uf.union(r * col + c, (r + 1) * col + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * col + c, r * col + c - 1);
                    }
                    if (c + 1 < col && grid[r][c + 1] == '1') {
                        uf.union(r * col + c, r * col + c + 1);
                    }
                }
            }
        }
        return uf.getCount();
    }

    class UnionFind {
        int count;      // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;

            parent = new int[m * n];
            rank = new int[m * n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public void union(int x, int y) {   // union with rank
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                count--;
            }
        }

        public int find(int i) {    // path compression
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public int getCount() {
            return count;
        }
    }
 */
