package DFS_BFS;

/*  130. Surrounded Regions
    Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
    A region is captured by flipping all 'O's into 'X's in that surrounded region.

    Example:
    X X X X
    X O O X
    X X O X
    X O X X
    After running your function, the board should be:
    X X X X
    X X X X
    X X X X
    X O X X
    Explanation:
    Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
    Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
    Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */

//  DFS，逆向思维
public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length - 1;
        int n = board[0].length - 1;
        for (int i = 0; i <= m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][n] == 'O') {
                dfs(board, i, n);
            }
        }
        for (int j = 0; j <= n; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            if (board[m][j] == 'O') {
                dfs(board, m, j);
            }
        }
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != 'O') return;
        board[i][j] = '1';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }
}
