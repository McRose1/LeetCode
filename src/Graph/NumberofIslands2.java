package Graph;

/*  305. Number of Islands 2
    A 2d grid map of m rows and n columns is initially filled with water.
    We may perform an addLand operation which turns the water at position (row, col) into a land.
    Given a list of positions to operate, count the number of islands after each addLand operation.
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    You may assume all four edges of the grid are all surrounded by water.

    Example:
    Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
    Output: [1,1,2,3]

    Explanation:
    Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
    0 0 0
    0 0 0
    0 0 0
    Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
    1 0 0
    0 0 0   Number of islands = 1
    0 0 0
    Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
    1 1 0
    0 0 0   Number of islands = 1
    0 0 0
    Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
    1 1 0
    0 0 1   Number of islands = 2
    0 0 0
    Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
    1 1 0
    0 0 1   Number of islands = 3
    0 1 0

    Follow up:
    Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//  Union Find
public class NumberofIslands2 {
    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public List<Integer> numIslands(int m, int n, int[][] positions) {  // [[0,0], [0,1], [1,2], [2,1]]
        List<Integer> res = new ArrayList<>();
        if (m <= 0 || n <= 0) return res;

        int count = 0;
        int[] roots = new int[m * n];
        Arrays.fill(roots, -1);

        // 转换成一维
        for (int[] pair : positions) {               // [0, 0]; [0, 1]
            int position = n * pair[0] + pair[1];   // 3*0+0=0; 3*0+1=1
            roots[position] = position;             // roots[0]=0; roots[1]=1
            count++;                                // 1; 2

            for (int[] dir : dirs) {
                // 往四个方向遍历
                int x = pair[0] + dir[0];
                int y = pair[1] + dir[1];
                int curPos = n * x + y;         // 3*0+1=1; curPos=0（回去）
                // 排查边界和不是岛屿的情况
                if (x < 0 || x >= m || y < 0 || y >= n || roots[curPos] == -1) {
                    continue;
                }
                int anoIsland = find(roots, curPos);    // find(roots, 0) -> 0
                if (position != anoIsland) {            // 1 != 0
                    roots[position] = anoIsland;        // roots[1] = 0
                    position = anoIsland;               // position = 0
                    count--;                            // count = 2 - 1 = 1
                }
            }
            res.add(count);
        }
        return res;
    }

    private int find(int[] roots, int i) {
        while (i != roots[i]) {
            i = roots[i];
        }
        return i;       // 0
    }
}
