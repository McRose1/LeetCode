package BFS_DFS;

/*  994. Rotting Oranges
    In a given grid, each cell can have one of three values:
        o the value 0 representing an empty cell;
        o the value 1 representing a fresh orange;
        o the value 2 representing a rotten orange.
    Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
    Return the minimum numbers of minutes that must elapse until no cell has a fresh orange.
    If this is impossible, return -1 instead.

    Example 1:
    Input: [[2,1,1],[1,1,0],[0,1,1]]
    Output: 4

    Example 2:
    Input: [[2,1,1],[0,1,1],[1,0,1]]
    Output: -1
    Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

    Example 3:
    Input: [[0,2]]
    Output: 0
    Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.

    Note:
        1. 1 <= grid.length <= 10
        2. 1 <= grid[0].length <= 10
        3. grid[i][j] is only 0, 1, or 2.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*  BFS + HashMap

 */
public class RottingOranges {
    public int orangeRotting(int[][] grid) {
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int r = grid.length;
        int c = grid[0].length;

        Queue<Integer> queue = new LinkedList<>();
        // 记录每个 cell 中的 orange 是第几批 BFS 感染的
        HashMap<Integer, Integer> depth = new HashMap<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 2) {
                    int idx = i * c + j;
                    queue.offer(idx);
                    depth.put(idx, 0);
                }
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int idx = queue.poll();
            int x = idx / c;
            int y = idx % c;
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx >= 0 && nx < r && ny >= 0 && ny < c && grid[nx][ny] == 1) {
                    grid[nx][ny] = 2;
                    int nIdx = nx * c + ny;
                    queue.offer(nIdx);
                    depth.put(nIdx, depth.get(idx) + 1);
                    res = depth.get(nIdx);
                }
            }
        }
        // 确定是否存在好的 orange
        for (int[] row : grid) {
            for (int v : row) {
                if (v == 1) {
                    return -1;
                }
            }
        }
        return res;
    }
}
