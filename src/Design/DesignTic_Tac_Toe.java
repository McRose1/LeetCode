package Design;

/*  348. Design Tic-Tac-Toe
    Design a Tic-tac-toe game that is played between two players on a n x n grid.
    You may assume the following rules:
        1. A move is guaranteed to be valid and is placed on an empty block.
        2. Once a winning condition is reached, no more moves is allowed.
        3. A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.

    Example:
    Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

    TicTacToe toe = new TicTacToe(3);

    toe.move(0, 0, 1); -> Returns 0 (no one wins)
    |X| | |
    | | | |    // Player 1 makes a move at (0, 0).
    | | | |

    toe.move(0, 2, 2); -> Returns 0 (no one wins)
    |X| |O|
    | | | |    // Player 2 makes a move at (0, 2).
    | | | |

    toe.move(2, 2, 1); -> Returns 0 (no one wins)
    |X| |O|
    | | | |    // Player 1 makes a move at (2, 2).
    | | |X|

    toe.move(1, 1, 2); -> Returns 0 (no one wins)
    |X| |O|
    | |O| |    // Player 2 makes a move at (1, 1).
    | | |X|

    toe.move(2, 0, 1); -> Returns 0 (no one wins)
    |X| |O|
    | |O| |    // Player 1 makes a move at (2, 0).
    |X| |X|

    toe.move(1, 0, 2); -> Returns 0 (no one wins)
    |X| |O|
    |O|O| |    // Player 2 makes a move at (1, 0).
    |X| |X|

    toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
    |X| |O|
    |O|O| |    // Player 1 makes a move at (2, 1).
    |X|X|X|

    Follow up: Could you do better than O(n^2) per move() operation?
 */

public class DesignTic_Tac_Toe {
    int[] rowCounter;
    int[] colCounter;
    int diagLeft;
    int diagRight;
    int n;
    /** Initialize your data structure here. */
    public DesignTic_Tac_Toe(int n) {
        rowCounter = new int[n];
        colCounter = new int[n];
        diagLeft = 0;
        diagRight = 0;
        this.n = n;
    }

    public int move(int row, int col, int player) {
        int move = player == 1 ? 1 : -1;
        rowCounter[row] += move;
        colCounter[col] += move;
        if (row == col) {
            diagLeft += move;
        }
        if (row == n - col) {
            diagRight += move;
        }
        if (Math.abs(rowCounter[row]) == n || Math.abs(colCounter[col]) == n || Math.abs(diagLeft) == n || Math.abs(diagRight) == n) {
            return player;
        }
        return 0;
    }
}
