package BFS_DFS;

/*  317. Shortest Distance from All Buildings
    You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
    You can only move up, down, left and right.
    You are given a 2D grid of values 0, 1 or 2, where:
    Each 0 marks an empty land which you can pass by freely.
    Each 1 marks a building which you cannot pass through.
    Each 2 marks an obstacle which you cannot pass through.

    Example:
    Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
    1 - 0 - 2 - 0 - 1
    |   |   |   |   |
    0 - 0 - 0 - 0 - 0
    |   |   |   |   |
    0 - 0 - 1 - 0 - 0
    Output: 7
    Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
                 the point (1,2) is an ideal empty land to build a house, as the total
                 travel distance of 3+3+1=7 is minimal. So return 7.

    Note:
    There will be at least one building.
    If it is not possible to build such house according to the above rules, return -1.
 */
import java.util.LinkedList;
import java.util.Queue;
/*  BFS
    记录每个 building 到每个空地的距离，相加找到最小值
 */
public class ShortestDistancefromAllBuildings {
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 记录每一个空地到达所有 building 的最短距离之和（也有可能到达不了所有 building）
        int[][] distance = new int[m][n];

        // 记录每一个空地能够到达 building 的数量
        int[][] reachable = new int[m][n];
        // 记录矩阵中所有 building 的总和
        int building = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    building++;
                    Queue<int[]> queue = new LinkedList<>();
                    // 从每个 1 开始 BFS，queue 里存 building 的坐标
                    queue.offer(new int[] {i, j});
                    int step = 1;
                    boolean[][] visited = new boolean[m][n];

                    while (!queue.isEmpty()) {
                        // 记录这一轮 BFS candidate 的数量
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int[] cur = queue.poll();

                            for (int[] d : dirs) {
                                int new_x = cur[0] + d[0];
                                int new_y = cur[1] + d[1];
                                // 检查新的 index 是否在边界以内，并且是否为空地以及没有被 visit 过
                                if (new_x >= 0 && new_y >= 0 && new_x < m && new_y < n && grid[new_x][new_y] == 0 && !visited[new_x][new_y]) {
                                    distance[new_x][new_y] += step;
                                    reachable[new_x][new_y]++;
                                    visited[new_x][new_y] = true;
                                    queue.offer(new int[] {new_x, new_y});
                                }
                            }
                        }
                        // 每一轮 BFS 结束后要更新 step
                        step++;
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reachable[i][j] == building) {
                    res = Math.min(res, distance[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
