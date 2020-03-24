package Backtracking;

/*  79. Word Search
    Given a 2D board and a word, find if the word exists in the grid.
    The word can be constructed from letters of sequentially adjacent cell,
    where "adjacent" cells are those horizontally or vertically neighboring.
    The same letter cell may not be used more than once.

    Example:
    board =
    [
      ['A','B','C','E'],
      ['S','F','C','S'],
      ['A','D','E','E']
    ]
    Given word = "ABCCED", return true.
    Given word = "SEE", return true.
    Given word = "ABCB", return false.
 */

//  Backtracking: Time = O(m * n^4) Space = O(m*n)
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && backtrack(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, int i, int j, int count, String word) {
        if (count == word.length()) return true;
        // 越界或者不配对就返回 false
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != word.charAt(count)) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = ' ';      // 标记已经用过的 cell
        // 向四个方向遍历
        boolean found = backtrack(board, i + 1, j, count + 1, word) ||
                        backtrack(board, i - 1, j, count + 1, word) ||
                        backtrack(board, i, j + 1, count + 1, word) ||
                        backtrack(board, i, j - 1, count + 1, word);
        // backtrack
        board[i][j] = temp;
        return found;
    }
}
