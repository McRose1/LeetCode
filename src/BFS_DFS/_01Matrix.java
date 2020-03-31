package BFS_DFS;

/*  542. 01 Matrix
    Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
    The distance between two adjacent cells is 1.

    Example 1:
    Input:
    [[0,0,0],
     [0,1,0],
     [0,0,0]]
    Output:
    [[0,0,0],
     [0,1,0],
     [0,0,0]]

    Example 2:
    Input:
    [[0,0,0],
     [0,1,0],
     [1,1,1]]
    Output:
    [[0,0,0],
     [0,1,0],
     [1,2,1]]

    Note:
    The number of elements of the given matrix will not exceed 10,000.
    There are at least one 0 in the given matrix.
    The cells are adjacent in only four directions: up, down, left and right.
 */

import java.util.*;
/*  BFS + Queue: Time = O(rc) Space = O(rc)
    首先遍历一次矩阵，将值为 0 的点都存入 queue，将值为 1 的点改为 INT_MAX
    所有的 0 都是起点，从 queue 中取出一个位置，遍历其周围四个点
 */
public class _01Matrix {
    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    // mark unreached cells
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int new_r = cell[0] + d[0];
                int new_c = cell[1] + d[1];
                // 如果越界，或者周围点的值小于等于当前值加 1
                // 因为周围点的距离更小的话，就没有更新的必要
                if (new_r < 0 || new_c < 0 || new_r >= rows || new_c >= cols ||
                    matrix[new_r][new_c] < matrix[cell[0]][cell[1]] + 1) {
                    continue;
                }
                queue.offer(new int[]{new_r, new_c});
                matrix[new_r][new_c] = matrix[cell[0]][cell[1]] + 1;
            }
        }
        return matrix;
    }
}

/*  Brute Force(TLE): Time = O((rc)^2) Space = O(rc)

 */