package DFS_BFS;

/*  286. Walls and Gates
    You are given a m x n 2D grid initialized with there three possible values.
    1. -1 - A wall or an obstacle.
    2. 0 - A gate.
    3. INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF
    as you may assume that the distance to a gate is less than 2147483647.

    Fill each empty room with the distance to its nearest gate.
    If it is impossible to reach a gate, it should be filled with INF.

    Example:
    Given the 2D grid:

    INF  -1  0  INF
    INF INF INF  -1
    INF  -1 INF  -1
      0  -1 INF INF
    After running your function, the 2D grid should be:

      3  -1   0   1
      2   2   1  -1
      1  -1   2  -1
      0  -1   3   4
 */

public class WallsandGates {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(i, j, 0, rooms);
                }
            }
        }
    }

    private void dfs(int i, int j, int count, int[][] rooms) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[i].length || rooms[i][j] < count) return;
        rooms[i][j] = count;
        dfs(i + 1, j, count + 1, rooms);
        dfs(i - 1, j, count + 1, rooms);
        dfs(i, j + 1, count + 1, rooms);
        dfs(i, j - 1, count + 1, rooms);
    }
}