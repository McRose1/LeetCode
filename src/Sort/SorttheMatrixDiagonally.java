package Sort;

/*  1329. Sort the Matrix Diagonally
    Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right
    then return the sorted array.

    Example:
    Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
    Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]

    Constraints:
        o m == mat.length
        o n == mat[i].length
        o 1 <= m, n <= 100
        o 1 <= mat[i][j] <= 100

    Hint:
    1. Use a data structure to store all values of each diagonal.
    2. How to index the data structure with the id of the diagonal?
    3. All cells in the same diagonal (i,j) have the same difference so we can get the diagonal of a cell using the difference i-j.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*  HashMap + PriorityQueue: Time = O(mnlogn) Space = O(mn)
    mat[i][j] on the same diagonal have same value of i - j
    For each diagonal, put its elements together, sort and set them back.
 */
public class SorttheMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.putIfAbsent(i - j, new PriorityQueue<>());
                map.get(i - j).add(mat[i][j]);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = map.get(i - j).poll();
            }
        }
        return mat;
    }
}

/*  Brute Force

    public int[][] diagonalSort(int[][] mat) {
        m = mat.length;
        n = mat[0].length;

        // right half diagonal part
        for (int j = 0; j < n; j++) {
            int[] count = diagonalCount(mat, 0, j);
            sort(mat, count, 0, j);
        }

        // left half diagonal part
        for (int i = 1; i < m; i++) {
            int[] count = diagonalCount(mat, i, 0);
            sort(mat, count, i, 0);
        }
        return mat;
    }

    private int m;
    private int n;

    private int[] diagonalCount(int[][] mat, int i, int j) {
        int[] count = new int[101];
        while (i < m && j < n) {
            count[mat[i][j]]++;
            i++;
            j++;
        }
        return count;
    }

    private void sort(int[][] mat, int[] count, int i, int j) {
        int start = 1;
        while (i < m && j < n && start < 101) {
            while (count[start] == 0) {
                start++;
            }
            mat[i][j] = start;
            i++;
            j++;
            count[start]--;
        }
    }
 */