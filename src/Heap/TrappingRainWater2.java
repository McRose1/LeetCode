package Heap;

/*  407. Trapping Rain Water 2
    Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map,
    compute the volume of water it is able to trap after raining.

    Example:
    Given the following 3x6 height map:
    [
      [1,4,3,1,3,2],
      [3,2,1,3,2,4],
      [2,3,3,2,3,1]
    ]
    Return 4.

    Constraints:
    1 <= m, n <= 110
    0 <= heightMap[i][j] <= 20000
 */

import java.util.PriorityQueue;

/*  PriorityQueue + BFS
    Whether the cell can hold water is determined by the min(itself, min(heights of 4 neighbors))
    最外边一圈的 cell 肯定是无法蓄水的
    1. offer all the cells on the edges to the heap
    2. poll a cell from the heap:
    2.1 check its 4 neighbors:
            if it's valid and unvisited:
                if the new height is < the height:
                    can hold water, accumulated to res
                    offer the updated height to the heap
 */
public class TrappingRainWater2 {
    int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        // int[] 存了 cell 的坐标和 cell 的高度
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];

        // 初始化 visited 数组和 minHeap，由于四边 cell 是不可能蓄水的，所以先标注为 visited 并且 offer 进 minHeap
        // 按行：
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            minHeap.offer(new int[] {i, 0, heightMap[i][0]});
            minHeap.offer(new int[] {i, n - 1, heightMap[i][n - 1]});
        }
        // 按列：
        for (int j = 1; j < n - 1; j++) {
            visited[0][j] = true;
            visited[m - 1][j] = true;
            minHeap.offer(new int[] {0, j, heightMap[0][j]});
            minHeap.offer(new int[] {m - 1, j, heightMap[m - 1][j]});
        }
        int res = 0;
        while (!minHeap.isEmpty()) {
            int[] cell = minHeap.poll();
            for (int[] dir : dirs) {
                int new_r = cell[0] + dir[0];
                int new_c = cell[1] + dir[1];
                if (new_r >= 0 && new_c >= 0 && new_r < m && new_c < n && !visited[new_r][new_c]) {
                    res += Math.max(0, cell[2] - heightMap[new_r][new_c]);
                    minHeap.offer(new int[] {new_r, new_c, Math.max(heightMap[new_r][new_c], cell[2])});
                    visited[new_r][new_c] = true;
                }
            }
        }
        return res;
    }
}
