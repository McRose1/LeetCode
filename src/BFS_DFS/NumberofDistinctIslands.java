package BFS_DFS;

/*  Number of Distinct Islands
    Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical).
    You may assume all four edges of the grid are surrounded by water.

    Count the number of distinct islands.
    An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

    Example 1:
    11000
    11000
    00011
    00011
    Given the above grid map, return 1.

    Example 2:
    11011
    10000
    00001
    11011
    Given the above grid map, return 3.

    Notice that :
    11
    1
    and
    1
    11
    are considered different island shapes, because we do not consider reflection / rotation.

    Note: The length of each dimension in the given grid does not exceed 50.
 */

import java.util.HashSet;

/*  DFS: Time = O(mn) Space = O(mn)

 */
public class NumberofDistinctIslands {

    private int m;
    private int n;

    public int numberOfDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        HashSet<String> set = new HashSet<>();
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    String path = computePath(grid, i, j, "X");
                    set.add(path);
                }
            }
        }
        return set.size();
    }

    private String computePath(int[][] grid, int i, int j, String direction) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return "O";
        }

        grid[i][j] = 0;
        String left = computePath(grid, i, j - 1, "L");
        String right = computePath(grid, i, j + 1, "R");
        String up = computePath(grid, i - 1, j, "U");
        String down = computePath(grid, i + 1, j, "D");

        return direction + left + right + up + down;
    }
}
