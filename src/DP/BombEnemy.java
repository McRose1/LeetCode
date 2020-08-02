package DP;

/*  361. Bomb Enemy
    Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
    return the maximum enemies you can kill using one bomb.
    The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.

    Note: You can only put the bomb at an empty cell.

    Example:
    Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
    Output: 3
    Explanation: For the given grid,

    0 E 0 0
    E 0 W E
    0 E 0 0

    Placing a bomb at (1,1) kills 3 enemies.
 */

/*  DP

 */
public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        int[] cols = new int[n];

        for (int i = 0; i < m; i++) {
            int rows = 0;
            for (int j = 0; j < n; j++) {
                // 碰到空地就可以开始结算
                if (grid[i][j] == '0') {
                    // 加上之前 cache 的敌人总数，也就是按行从右往左和按列从下往上
                    int total = rows + cols[j];
                    // 按列从左往右
                    for (int k = j + 1; k < n && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') {
                            total++;
                        }
                    }
                    // 按行从上往下
                    for (int k = i + 1; k < m && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') {
                            total++;
                        }
                    }
                    max = Math.max(max, total);
                }
                // 碰到墙要重新开始结算
                else if (grid[i][j] == 'W') {
                    rows = 0;
                    cols[j] = 0;
                }
                // 碰到敌人先把数量 cache 起来
                else {
                    rows++;
                    cols[j]++;
                }
            }
        }
        return max;
    }
}
