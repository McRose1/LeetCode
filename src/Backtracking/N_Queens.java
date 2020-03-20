package Backtracking;

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
/*  Backtracking using Set
    对角线冲突 -> 左对角线的点，x,y坐标相减=0，右对角线的点，x,y坐标相加为相等的数
 */
public class N_Queens {
    // 列冲突
    Set<Integer> col = new HashSet<>();
    // 左对角线冲突
    Set<Integer> diff = new HashSet<>();
    // 右对角线冲突
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
        // level 代表行数
        backtrack(n, res, board, 0);
        return res;
    }

    private void backtrack(int n, List<List<String>> res, char[][] board, int level) {
        if (level == n) {
            List<String> tmp = new ArrayList<>();
            // 对行进行遍历，避免了行冲突
            for (int i = 0; i < n; i++) {
                tmp.add(String.valueOf(board[i]));
            }
            res.add(tmp);
            return;
        }
        // 列遍历
        for (int j = 0; j < n; j++) {
            if (!col.contains(j) && !diff.contains(level - j) && !sum.contains(level + j)) {
                col.add(j);
                diff.add(level - j);
                sum.add(level + j);
                board[level][j] = 'Q';
                backtrack(n, res, board, level + 1);
                board[level][j] = '.';
                col.remove(j);
                diff.remove(level - j);
                sum.remove(level + j);
            }
        }
    }
}

/*  Backtracking using helper function

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        backtrack(new ArrayList<>(), res, n);
        return res;
    }

    private void backtrack(List<Integer> list, List<List<String>> res, int n) {
        if (list.size() == n) {
            List<String> temp = new ArrayList<>();
            // 一行一行加皇后，所以行里不会产生冲突
            for (int i = 0; i < n; i++) {
                char[] ch = new char[n];
                Arrays.fill(ch, '.');
                ch[list.get(i)] = 'Q';      // "Q..."
                temp.add(new String(ch));
            }
            res.add(temp);
        }

        for (int col = 0; col < n; col++) {
            // 当前列是否冲突
            if (!list.contains(col)) {
                // 判断对角线是否冲突
                if (isDiagnoalAttack(list, col)) {
                    continue;
                }
                list.add(col);
                backtrack(list, res, n);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isDiagnoalAttack(List<Integer> list, int col) {
        int curRow = list.size();
        int curCol = col;
        // 判断每一行的皇后的情况
        for (int row = 0; row < list.size(); row++) {
            // 左上角的对角线和右上角的对角线，差要么相等，要么互为相反数，直接写成绝对值
            if (Math.abs(curRow - row) == Math.abs(curCol - list.get(row))) {
                return true;
            }
        }
        return false;
    }
 */