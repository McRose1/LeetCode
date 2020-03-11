package Matrix;

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

//  DFS + Memoization: Time = O(M x N) Space = O(M x N)
public class LongestIncreasingPathinaMatrix {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int max = dfs(matrix, i, j, m, n, memo);
                res = Math.max(max, res);
            }
        }
        return res;
    }
    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] memo) {
        if (memo[i][j] != 0) return memo[i][j];
        int max = 1;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int len = 1 + dfs(matrix, x, y, m, n, memo);
            max = Math.max(len, max);
        }
        memo[i][j] = max;
        return max;
    }
}
