/*
    Given n and m which are the dimensions of a matrix initialized by zeros
    and given an array indices where indices[i] = [ri, ci].
    For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.
    Return the number of cells with odd values in the matrix after applying the increment to all indices.

    Exampe 1:
    Input: n = 2, m = 3, indices = [[0,1],[1,1]]
    Output: 6
 */

public class CellswithOddValuesinaMatrix {
    public int oddCells(int n, int m, int[][] indices) {
        int[][] a = new int[n][m];
        for (int[] idx : indices) {
            for (int r = 0; r < n; r++) {
                a[r][idx[1]]++;
            }
            for (int c = 0; c < m; c++) {
                a[idx[0]][c]++;
            }
        }
        int ans = 0;
        for (int r = 0; r < n; r++){
            for (int c = 0; c < m; c++) {
                ans += a[r][c] & 1;
            }
        }
        return ans;
    }
}
