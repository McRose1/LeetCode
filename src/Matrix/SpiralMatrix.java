package Matrix;

/*  54. Spiral Matrix
    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

    Example 1:
    Input:
    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
    Output: [1,2,3,6,9,8,7,4,5]

    Example 2:
    Input:
    [
      [1, 2, 3, 4],
      [5, 6, 7, 8],
      [9,10,11,12]
    ]
    Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */

import java.util.ArrayList;
import java.util.List;

/*  Simulation: Time = O(n) Space = O(n)
    Our current position is (r, c), facing direction di, our candidate next position is (cr, cc).
    If the candidate is in the bounds of the matrix and unseen, then it becomes our next position;
    otherwise, our next position is one after performing a clockwise turn.
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        // 方向分别对应向右、向下，向左，向上
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
                r = cr;
                c = cc;
            } else {
                // 切换遍历方向
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }
}
/*  Layer-by-Layer: Time = O(n) Space = O(1)
    Start from the top left corner to top-right to bottom-right to bottom-left

        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int r1 = 0;                     // 上边界
        int r2 = matrix.length - 1;     // 下边界
        int c1 = 0;                     // 左边界
        int c2 = matrix[0].length - 1;  // 右边界
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);      // 1,2,3
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);  // 6,9
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]); // 8
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);     // 7,4
            }
            // 进入里面一层
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
 */
