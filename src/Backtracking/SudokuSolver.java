package Backtracking;

/*  37. Sudoku Solver
    Write a program to solve a Sudoku puzzle by filling the empty cells.

    A Sudoku solution must satisfy all of the following rules:
    1. Each of the digits 1-9 must occur exactly once in each row.
    2. Each of the digits 1-9 must occur exactly once in each column.
    3. Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
    Empty cells are indicated by the character '.'.

    Note:
    The given board contain only digits 1-9 and the character '.'.
    You may assume that the given Sudoku puzzle will have a single unique solution.
    The given board size is always 9x9.
 */
/*  Backtracking
    Choice: 1-9
    Constraints: board should be valid
    Goal: fill the whole board
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        backtrack(board);
    }

    public boolean backtrack(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;

                            if (backtrack(board)) {
                                return true;
                            } else {
                                // backtrack
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        int cubeRow = 3 * (row / 3);
        int cubeCol = 3 * (col / 3);
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false;
            if (board[row][i] == c) return false;
            if (board[cubeRow + i / 3][cubeCol + i % 3] == c) return  false;
        }
        return true;
    }
}
