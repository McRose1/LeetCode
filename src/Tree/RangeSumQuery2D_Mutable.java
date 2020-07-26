package Tree;

/*  308. Range Sum Query 2D - Mutable
    Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1)
    and lower right corner (row2, col2).

    You have to implement 3 functions:
        o NumMatrix(matrix) The constructor.
        o sumRegion(row1, col1, row2, col2) Return the sum of the elements inside the rectangle defined by its upper left corner(row1, col1)
          and lower right corner (low2, col2).
        o update(row, col, val) Update the element at (row, col) to val.

    Example:
    Given matrix = [
      [3, 0, 1, 4, 2],
      [5, 6, 3, 2, 1],
      [1, 2, 0, 1, 5],
      [4, 1, 0, 1, 7],
      [1, 0, 3, 0, 5]
    ]

    sumRegion(2, 1, 4, 3) -> 8
    update(3, 2, 2)
    sumRegion(2, 1, 4, 3) -> 10

    Note:
    The matrix is only modifiable by the update function.
    You may assume the number of calls to update and sumRegion function is distributed evenly.
    You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

/*  Binary Index Tree (Fenwick Tree)
    root.val = sum(children.val) + num[root.id]
    Update Tree: i += lowbit(i)
    Query Tree: i -= lowbit(i)
    lowbit(x) = x & (-x)
 */
public class RangeSumQuery2D_Mutable {
    private int[][] nums;
    private int[][] tree;
    private int m;
    private int n;

    public RangeSumQuery2D_Mutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        nums = new int[m][n];
        tree = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int diff = val - nums[row][col];
        nums[row][col] = val;

        for (int i = row + 1; i <= m; i += (i & (-i))) {
            for (int j = col + 1; j <= n; j += (j & (-j))) {
                tree[i][j] += diff;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return query(row2 + 1, col2 + 1) - query(row1, col2 + 1) - query(row2 + 1, col1) + query(row1, col1);
    }

    private int query(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= (i & (-i))) {
            for (int j = col; j > 0; j -= (j & (-j))) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}
