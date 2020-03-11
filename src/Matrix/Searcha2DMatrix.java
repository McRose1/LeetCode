package Matrix;

/*  74. Search a 2D Matrix
    Write an efficient algorithm that searches for a value in an m x n matrix.
    This matrix has the following properties:
    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

    Example 1:
    Input:
    matrix = [
      [1,   3,  5,  7],
      [10, 11, 16, 20],
      [23, 30, 34, 50]
    ]
    target = 3
    Output: true

    Example 2:
    Input:
    matrix = [
      [1,   3,  5,  7],
      [10, 11, 16, 20],
      [23, 30, 34, 50]
    ]
    target = 13
    Output: false
 */

/*  Binary Search: Time = O(logmn) Space = O(1)
    先对列做二分法处理，找到 target 所处的行，再对这一行进行二分法处理
 */
public class Searcha2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int startRow = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;
        int row = -1;
        while (startRow + 1 < endRow) {
            int mid = startRow + (endRow - startRow) / 2;
            if (matrix[mid][endCol] < target) startRow = mid;
            else endRow = mid;
        }
        if (matrix[startRow][endCol] >= target) row = startRow;
        else if (matrix[endRow][endCol] >= target) row = endRow;
        else return false;

        int start = 0;
        int end = endCol;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] < target) start = mid;
            else end = mid;
        }
        if (matrix[row][start] == target || matrix[row][end] == target) return true;
        else return false;
    }
}
