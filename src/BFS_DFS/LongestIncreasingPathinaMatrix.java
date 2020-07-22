package BFS_DFS;

/*  329. Longest Increasing Path in a Matrix
    Given an integer matrix, find the length of the longest increasing path.

    From each cell, you can either move to four directions: left, right, up or down.
    You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

    Example 1:
    Input: nums =
    [
      [9,9,4],
      [6,6,8],
      [2,1,1]
    ]
    Output: 4
    Explanation: The longest increasing path is [1, 2, 6, 9].

    Example 2:
    Input: nums =
    [
      [3,4,5],
      [3,2,6],
      [2,2,1]
    ]
    Output: 4
    Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

/*  DFS + Memoization (Top-down): Time = O(mn) Space = O(mn)
    用一个集合来避免一次 DFS 中的重复访问。
    在计算中，记忆化是一种优化技术，它通过存储“昂贵”的函数调用的结果，在相同的输入再次出现时返回缓存的结果，依次加快程序的速度。
    在本问题中，我们多次调用 dfs(x, y)。但是，如果我们已经知道 4 个相邻单元格的结果，就只需要常数时间。
    在搜索过程中，如果为计算过单元格的结果，我们会计算并将其缓存；否则，直接从缓存中获取之。
 */
public class LongestIncreasingPathinaMatrix {

    private int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m;
    private int n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, i, j, cache));
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                int len = 1 + dfs(matrix, x, y, cache);
                max = Math.max(max, len);
            }
        }
        cache[i][j] = max;
        return max;
    }

    /* Another version of DFS
    private int dfs(int[][] matrix, int i, int j, int[][] cache, int min) {
        if (i < 0 || i >= m || j < 0 || j >= n || matrix[i][j] <= min) return 0;

        if (cache[i][j] != 0) return cache[i][j];

        min = matrix[i][j];
        int a = dfs(matrix, i + 1, j, cache, min) + 1;
        int b = dfs(matrix, i - 1, j, cache, min) + 1;
        int c = dfs(matrix, i, j + 1, cache, min) + 1;
        int d = dfs(matrix, i, j - 1, cache, min) + 1;
        int max = Math.max(a, Math.max(b, Math.max(c, d)));

        cache[i][j] = max;
        return max;
    }
     */
}

/*  DP + Topological Sort: Time = O(mn) Space = O(mn)
    动态规划要求按照拓扑顺序解决子问题。对于很多问题，拓扑顺序与自然秩序一致。而对于那些并非如此的问题，需要首先执行拓扑排序。
    因此，对于复杂拓扑问题（如本题），使用记忆化搜索通常是更容易更好的选择。

    private int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m;
    private int n;
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;

        // padding the matrix with zero as boundaries
        // assuming all positive integer, otherwise use INT_MIN as boundaries
        int[][] grid = new int[m + 2][n + 2];
        for (int i = 0; i < m; i++) {
            System.arraycopy(matrix[i], 0, grid[i + 1], 1, n);
        }

        // calculate outdegrees
        int[][] outDegree = new int[m + 2][n + 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int[] d : dirs) {
                    if (grid[i][j] < grid[i + d[0]][j + d[1]]) {
                        outDegree[i][j]++;
                    }
                }
            }
        }

        // find leaves who have 0 outdegree as the initial level
        n += 2;
        m += 2;
        List<int[]> leaves = new ArrayList<>();
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (outDegree[i][j] == 0) {
                    leaves.add(new int[]{i, j});
                }
            }
        }

        // remove leaves level by level in topological order
        int height = 0;
        while (!leaves.isEmpty()) {
            height++;
            List<int[]> newLeaves = new ArrayList<>();
            for (int[] node : leaves) {
                for (int[] d : dirs) {
                    int x = node[0] + d[0], y = node[1] + d[1];
                    if (grid[node[0]][node[1]] > grid[x][y]) {
                        if (--outDegree[x][y] == 0) {
                            newLeaves.add(new int[]{x, y});
                        }
                    }
                }
            }
            leaves = newLeaves;
        }
        return height;
    }
 */

/*  DFS (TLE): Time = O(2^(m+n)) Space = O(mn)

    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(matrix, i, j));
            }
        }
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j) {
        int ans = 0;
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                ans = Math.max(ans, dfs(matrix, x, y));
            }
        }
        return ++ans;
    }
 */