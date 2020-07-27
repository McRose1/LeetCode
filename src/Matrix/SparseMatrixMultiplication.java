package Matrix;

/*  311. Sparse Matrix Multiplication
    Given two sparse matrices A and B, return the result of AB.
    You may assume that A's column number is equal to B's row number.

    Example:
    Input:
    A = [
          [ 1, 0, 0],
          [-1, 0, 3]
        ]

    B = [
          [ 7, 0, 0 ],
          [ 0, 0, 0 ],
          [ 0, 0, 1 ]
        ]

    Output:

         |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
    AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                      | 0 0 1 |
 */

/*  Math
                                                     C00                           C01                             C02
    | a00 a01 a02 |   | b00 b01 b02 |   | (a00*b00 + a01*b10 + a02*b20) (a00*b01 + a01*b11 + a02*b21) (a00*b02 + a01*b12 + a02*b22) |
    | a10 a11 a12 | x | b10 b11 b12 | = | (a10*b00 + a11*b10 + a12*b20) (a10*b01 + a11*b11 + a12*b21) (a10*b02 + a11*b12 + a12*b22) |
                      | b20 b21 b22 |                C10                           C11                             C22
 */
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0) {
                    for (int k = 0; k < B[0].length; k++) {
                        if (B[j][k] != 0) {
                            res[i][k] += A[i][j] * B[j][k];
                        }
                    }
                }
            }
        }
        return res;
    }
}
