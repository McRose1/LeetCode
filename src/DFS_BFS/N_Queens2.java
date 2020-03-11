package DFS_BFS;

/*  52. N-Queens 2
    The n-queens puzzle is the problem of placing n queens on an nxn chessboard such that no two queens attack each other.
    Given an integer n, return the number of distinct solutions to the n-queens puzzle.
    Each solution contains a distinct board configuration of the n-queens' placement,
    where 'Q' and '.' both indicate a queen and an empty space respectively.

    Example:
    Input: 4
    Output: 2
    [
     [".Q..",  // Solution 1
      "...Q",
      "Q...",
      "..Q."],

     ["..Q.",  // Solution 2
      "Q...",
      "...Q",
      ".Q.."]
    ]
 */

import java.util.HashSet;
import java.util.Set;
//  DFS + backtracking: 行列、对角线
public class N_Queens2 {
    public int res = 0;
    Set<Integer> col = new HashSet<>();
    // 左上到右下这条对角线的点横纵坐标之差相等
    Set<Integer> diff = new HashSet<>();
    // 左下到右上这条对角线的点横纵坐标之和相等
    Set<Integer> sum = new HashSet<>();
    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        dfs(0, n);
        return res;
    }

    private void dfs(int level, int n) {
        if (level == n) {
            res++;
            return;
        }
        // row 不相等
        for (int j = 0; j < n; j++) {
            if (!col.contains(j) && !diff.contains(level - j) && !sum.contains(level + j)) {
                col.add(j);
                diff.add(level - j);
                sum.add(level + j);
                dfs(level + 1, n);
                // backtracking
                col.remove(j);
                diff.remove(level - j);
                sum.remove(level + j);
            }
        }
    }
}
