package BFS_DFS;

import java.util.LinkedList;
import java.util.Queue;

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
//  BFS
public class ShortestDistancefromAllBuildings {
    public int shortestDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] distance = new int[rows][cols];
        int[][] reachable = new int[rows][cols];
        int buildingNumber = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    buildingNumber++;
                    Queue<int[]> queue = new LinkedList<>();
                    // 从每个 1 开始 BFS
                    queue.offer(new int[] {i, j});
                    int step = 1;
                    boolean[][] visited = new boolean[rows][cols];

                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int[] cur = queue.poll();

                            for (int[] d : dirs) {
                                int new_r = cur[0] + d[0];
                                int new_c = cur[1] + d[1];
                                // 检查新的 index 是否在边界以内，并且是否为空地以及没有被 visit 过
                                if (new_r >= 0 && new_c >= 0 && new_r < rows && new_c < cols &&
                                        grid[new_r][new_c] == 0 && !visited[new_r][new_c]) {
                                    distance[new_r][new_c] += step;
                                    reachable[new_r][new_c]++;
                                    visited[new_r][new_c] = true;
                                    queue.offer(new int[] {new_r, new_c});
                                }
                            }
                        }
                        step++;
                    }
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0 && reachable[i][j] == buildingNumber) {
                    result = Math.min(result, distance[i][j]);
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
