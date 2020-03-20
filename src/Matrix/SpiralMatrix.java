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

    Hint 1: Well for some problems, the best way really is to come up with some algorithms for simulation.
            Basically, you need to simulate what the problem asks us to do.

    Hint 2: We go boundary by boundary and move inwards. That is the essential operation.
            First row, last column, last row, first column and then we move inwards by 1 and then repeat.
            That's all, that is all the simulation that we need.

    Hint 3: Think about when you want to switch the progress on one of the indexes.
            If you progress on i out of [i, j], you'd be shifting in the same column.
            Similarly, by changing values for j, you'd be shifting in the same row.
            Also, keep track of the end of a boundary so that you can move inwards and then keep repeating.
            It's always best to run the simulation on edge cases like a single column or a single row to see if anything breaks or not.
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
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        // 方向分别对应向右、向下，向左，向上
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        // 注意这里的 i 并不代表行数或列数，而是代表遍历元素的个数，所以 i < R * C，这个 for 循环里会遍历矩阵里的所有元素
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            // 判断下一步是否 valid 并且没有走到过，如果这两个条件都满足，则更新当前坐标
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
                r = cr;
                c = cc;
            // 否则，说明我们此刻已经到达边界或者重复遍历了，所以需要更换方向
            } else {
                // 切换遍历方向，因为有四个方向，所以设置为 (di + 1) % 4
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

        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) return ans;
        int r1 = 0;                     // 上边界
        int r2 = matrix.length - 1;     // 下边界
        int c1 = 0;                     // 左边界
        int c2 = matrix[0].length - 1;  // 右边界
        // 包含了边界条件
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);      // 1,2,3
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);  // 6,9
            // 如果 r1 == r2 || c1 == c2，存在只有一行或者只有一列的情况，只存在一个方向的遍历，没必要继续下面的另外两个方向
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
