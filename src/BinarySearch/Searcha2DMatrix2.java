package BinarySearch;

/*  240. Search a 2D Matrix 2
    Write an efficient algorithm that searches for a value in an m x n matrix.
    This matrix has the following properties:
    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

    Consider the following matrix:
    [
      [1,   4,  7, 11, 15],
      [2,   5,  8, 12, 19],
      [3,   6,  9, 16, 22],
      [10, 13, 14, 17, 24],
      [18, 21, 23, 26, 30]
    ]
    Given target = 5, return true.
    Given target = 20, return false.
 */

/*  From Bottom-left or Top-right: Time = O(m + n) Space = O(1)
    因为我们知道从左到右是递增，从上到下也是递增
    所以我们先找一个起点，这个起点非常重要：
    选左上角，往右走和往下走都增大，不能选；选右下角，往上走和往左走都减小，不能选
    选左下角，往右走增大，往上走减小，可选；选右上角，往下走增大，往左走减小，可选
 */
public class Searcha2DMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0;
        int col = matrix[0].length - 1;

        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) return true;
            else if (target < matrix[row][col]) col--;
            else row++;
        }
        return false;
    }
}

/*  my version (Binary Search)

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int n = matrix[0].length;

        for (int[] row : matrix) {
            if (row[0] <= target && row[n - 1] >= target) {
                int left = 0;
                int right = n - 1;
                while (left + 1 < right) {
                    int mid = left + (right - left) / 2;
                    if (row[mid] == target) return true;
                    else if (row[mid] < target) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                if (row[left] == target || row[right] == target) return true;
            }
        }

        return false;
 */

