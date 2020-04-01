package BFS_DFS;

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

/*  DFS: Time = O(n^2) Space = O(n^2)
    先找 DFS 的入口，在这里边界是特殊位置
    如果边界上有 'O'，先标记为比如 '1'，以此为起点遇到 'O' 就标记
    到最后验收的时候只需要把标记过的 'O' 还原为 'O'，没有标记过的 'O' 统一变成 'X' 就行了
 */
public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int row = board.length - 1;
        int col = board[0].length - 1;

        // 左右边界
        for (int i = 0; i <= row; i++) {
            // 左边界
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            // 右边界
            if (board[i][col] == 'O') {
                dfs(board, i, col);
            }
        }

        // 上下边界
        for (int j = 0; j <= col; j++) {
            // 上边界
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            // 下边界
            if (board[row][j] == 'O') {
                dfs(board, row, j);
            }
        }

        // 最后遍历整个 board
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        // 判断是否出界以及是否为 'O'，因为我们不需要管 'X'
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != 'O') return;
        // 从起点能遍历到的 'O' 就标记
        board[i][j] = '1';
        // 四个方向 DFS
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }
}
