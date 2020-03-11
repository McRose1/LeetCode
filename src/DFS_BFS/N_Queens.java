package DFS_BFS;

/*  51. N-Queens
    The n-queens puzzle is the problem of placing n queens on an nxn chessboard such that no two queens attack each other.
    Given an integer n, return all distinct solutions to the n-queens puzzle.
    Each solution contains a distinct board configuration of the n-queens' placement,
    where 'Q' and '.' both indicate a queen and an empty space respectively.

    Example:
    Input: 4
    Output: [
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

import java.util.*;
//  DFS + Backtracking
public class N_Queens {
    Set<Integer> col = new HashSet<>();
    Set<Integer> diff = new HashSet<>();
    Set<Integer> sum = new HashSet<>();
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(n, res, board, 0);
        return res;
    }

    private void dfs(int n, List<List<String>> res, char[][] board, int level) {
        if (level == n) {
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                tmp.add(String.valueOf(board[i]));
            }
            res.add(tmp);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!col.contains(j) && !diff.contains(level - j) && !sum.contains(level + j)) {
                col.add(j);
                diff.add(level - j);
                sum.add(level + j);
                board[level][j] = 'Q';
                dfs(n, res, board, level + 1);
                board[level][j] = '.';
                col.remove(j);
                diff.remove(level - j);
                sum.remove(level + j);
            }
        }
    }
}
