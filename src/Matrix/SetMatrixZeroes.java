package Matrix;

/*  73. Set Matrix Zeroes
    Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

    Example 1:
    Input:
    [
      [1,1,1],
      [1,0,1],
      [1,1,1]
    ]
    Output:
    [
      [1,0,1],
      [0,0,0],
      [1,0,1]
    ]

    Example 2:
    Input:
    [
      [0,1,2,0],
      [3,4,5,2],
      [1,3,1,5]
    ]
    Output:
    [
      [0,0,0,0],
      [0,4,5,0],
      [0,3,1,0]
    ]

    Follow up:
    A straight forward solution using O(mn) space is probably a bad idea.
    A simple improvement uses O(m + n) space, but still not the best solution.
    Could you devise a constant space solution?
 */

/*  O(1) Space, Efficient Solution: Time = O(M x N)
    The inefficiency in the second approach is that we might be repeatedly setting a row or column
    even if it was set to zero already.
    We can avoid this by postponing the step of setting a row or a column to zeroes.
    We can rather use the first cell of every row and column as a flag.
    This flag would determine whether a row or column has been set to zero.
    This means for every cell instead of going to M + N cells and setting it to zero we just set the flag in two cells.
    由于第一行和第一列不一定会被置 0，所以需要用 isCol 来标记第一列是否需要置 0，用 matrix[0][0] 标记第一行是否需要置 0
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        Boolean isCol = false;
        int R = matrix.length;
        int C = matrix[0].length;

        for (int i = 0; i < R; i++) {

            // Since first cell for both first row and first column is the same
            // We can use an additional variable for either the first row/column
            // For this solution we are using an additional variable for the first column
            // and using matrix[0][0] for the first row.
            if (matrix[i][0] == 0) {
                isCol = true;
            }

            for (int j = 1; j < C; j++) {
                // If an element is zero, we set the first element of the corresponding row and column to 0
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // Iterate over the array once again and using the first row and first column, update the elements.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // See if the first row needs to be set to zero as well
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }

        // See if the first column needs to be set to zero as well
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

/*  Additional Memory Approach: Time = O(M x N) Space = O(M + N)

        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        // Essentially, we mark the rows and columns that are to be made zero
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        // Iterate over the array once again and using the rows and cols sets, update the elements.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
 */

/*  Brute O(1) space: Time = O((M x N) x (M + N))
    avoid additional use of space by manipulating the original array instead.

        int MODIFIED = -1000000;    // 假设这个数字不在 matrix 中
        int R = matrix.length;
        int C = matrix[0].length;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (matrix[r][c] == 0) {
                    // We modify the corresponding rows and column elements in place.
                    // Note, we only change the non zeroes to MODIFIED.
                    for (int k = 0; k < C; k++) {
                        if (matrix[r][k] != 0) {
                            matrix[r][k] = MODIFIED;
                        }
                    }
                    for (int k = 0; k < R; k++) {
                        if (matrix[k][c] != 0) {
                            matrix[k][c] = MODIFIED;
                        }
                    }
                }
            }
        }
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // Make a second pass and change all MODIFIED elements to 0
                if (matrix[r][c] == MODIFIED) {
                    matrix[r][c] = 0;
                }
            }
        }
 */