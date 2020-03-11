package Matrix;

/*  Spiral Matrix 2
    Given a positive integer n, generate a square matrix filled with elements fro 1 to n^2 in spiral order.

    Example:
    Input: 3
    Output:
    [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
    ]
 */

//  Layer-by-Layer: Time = O(n) Space = O(1)
public class SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int r1 = 0;                     // 上边界
        int r2 = n - 1;                 // 下边界
        int c1 = 0;                     // 左边界
        int c2 = n - 1;                 // 右边界
        int num = 0;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) res[r1][c] = ++num;      // 1,2,3
            for (int r = r1 + 1; r <= r2; r++) res[r][c2] = ++num;  // 4,5
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) res[r2][c] = ++num; // 6
                for (int r = r2; r > r1; r--) res[r][c1] = ++num;     // 7,8
            }
            // 进入里面一层
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }
}

/*  Simulation: Time = O(n) Space = O(n)

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 0;
        boolean[][] seen = new boolean[n][n];
        // 方向分别对应向右、向下，向左，向上
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < n * n; i++) {
            res[r][c] = ++num;
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < n && 0 <= cc && cc < n && !seen[cr][cc]) {
                r = cr;
                c = cc;
            } else {
                // 切换遍历方向
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return res;
    }
 */
