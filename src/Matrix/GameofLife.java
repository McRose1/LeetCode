package Matrix;

/*  289. Game of Life
    According to the WiKi: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
    Given a board with m by n cells, each cell has an initial state live(1) or dead(0).
    Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from te above WiKi):
    1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    2. Any live cell with two or three live neighbors lives on to the next generation.
    3. Any live cell with more than three live neighbors dies, as if by over-population.
    4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

    Write a function to compute the next state (after one update) of the board given its current state.
    The next state is created by applying te above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

    Example:
    Input:
    [
      [0,1,0],
      [0,0,1],
      [1,1,1],
      [0,0,0]
    ]
    Output:
    [
      [0,0,0],
      [1,0,1],
      [0,1,1],
      [0,1,0]
    ]

    Follow up:
    1. Could you solve it in-place? Remember that the board needs to be updated at the same time:
        You cannot update some cells first and then use their updated values to update other cells.
    2. In this question, we represent the board using a 2D array.
       In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array.
       How would you address these problems?

 */
/*  In-place: Time = O(mn) Space = O(1)
    We only have two states live(1) or dead(0) for a cell. We can use some dummy cell value to signify previous state of the cell along with the new changed value.
    1. If the value of the cell was 1 originally but it has now become 0 after applying the rule, then we can change the value to -1.
    The negative sign signifies the cell is now dead(0) but the magnitude signifies the cell was a live(1) originally.
    2. If the value of the cell was 0 originally but it has now become 1 after applying the rule, then we can change the value to 2.
    The positive sign signifies the cell is now live(1) but the magnitude of 2 signifies the cell was a dead(0) cell originally.
 */
public class GameofLife {
    public void gameOfLife(int[][] board) {
        // Neighbors array to find 8 neighboring cells for a given cell
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // Iterate through board cell by cell
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // For each cell count the number of live neighbors
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        // 8 个方向（除了原位置）
                        if (neighbors[i] == 0 && neighbors[j] == 0) continue;
                        int r = row + neighbors[i];
                        int c = col + neighbors[j];

                        // Check the validity of the neighboring cell and whether it was originally a live cell.
                        // The evaluation is done against the copy, since that is never updated.
                        if (r >= 0 && r < rows && c >= 0 && c < cols && Math.abs(board[r][c]) == 1) {
                            liveNeighbors += 1;
                        }
                    }
                }
                // Rule 1 or Rule 3
                if (board[row][col] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    // -1 signifies the cell is now dead but originally was live
                    board[row][col] = -1;
                }
                // Rule 4
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    // 2 signifies the cell is now live but was originally dead
                    board[row][col] = 2;
                }
            }
        }
        // Get the final representation for the newly updated board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }
}

/*  Make a copy of the original board: Time = O(mn) Space = O(mn)
    copyBoard which will remain unchanged throughout the process
    Iterate the cells of the Board one by one.
    While computing the results of the rules, use the copyBoard and apply the result in the original board.

        // Neighbors array to find 8 neighboring cells for a given cell
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // Create a copy of the original board
        int[][] copyBoard = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            System.arraycopy(board[row], 0, copyBoard[row], 0, cols);
        }
        // Iterate through board cell by cell
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // For each cell count the number of live neighbors
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        // 8 个方向（除了原位置）
                        if (neighbors[i] == 0 && neighbors[j] == 0) continue;
                        int r = row + neighbors[i];
                        int c = col + neighbors[j];

                        // Check the validity of the neighboring cell and whether it was originally a live cell.
                        // The evaluation is done against the copy, since that is never updated.
                        if (r >= 0 && r < rows && c >= 0 && c < cols && copyBoard[r][c] == 1) {
                            liveNeighbors += 1;
                        }
                    }
                }
                // Rule 1 or Rule 3
                if (copyBoard[row][col] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                // Rule 4
                if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
 */