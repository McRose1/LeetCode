package BFS_DFS;

/*  286. Walls and Gates
    You are given a m x n 2D grid initialized with there three possible values.
    1. -1 - A wall or an obstacle.
    2. 0 - A gate.
    3. INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF
    as you may assume that the distance to a gate is less than 2147483647.

    Fill each empty room with the distance to its nearest gate.
    If it is impossible to reach a gate, it should be filled with INF.

    Example:
    Given the 2D grid:

    INF  -1  0  INF
    INF INF INF  -1
    INF  -1 INF  -1
      0  -1 INF INF
    After running your function, the 2D grid should be:

      3  -1   0   1
      2   2   1  -1
      1  -1   2  -1
      0  -1   3   4
 */

/*  DFS
    逆向思维，题目问的是从 INF 出发到 0 的最小距离
    我们就把它转换成从 0 出发进行 DFS 遍历，只要碰到 INF，直接将目前得到的距离把 INF 覆盖掉
    还需要考虑的是最近 0 问题，即第一个 0 出发覆盖了 INF，如果第二个 0 出发到达该位置的距离更小，需要更新最小值
    什么时候不覆盖且退出递归呢？即发现当前距离已经大于 rooms[i][j] 此时的值，说明当前 DFS 得到的一定不是最小值
 */
public class WallsandGates {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int i, int j, int dist) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || dist > rooms[i][j]) return;
        // dist <= rooms[i][j]，切记不要省略 =，因为这是初始状态，初始时 dist = 0; rooms[i][j] = 0，必须执行下去而不是 return
        rooms[i][j] = dist;
        dfs(rooms, i + 1, j, dist + 1);
        dfs(rooms, i - 1, j, dist + 1);
        dfs(rooms, i, j + 1, dist + 1);
        dfs(rooms, i, j - 1, dist + 1);
    }
}

/*  BFS

        int INF = Integer.MAX_VALUE;
        int row = rooms.length;
        int col = rooms[0].length;

        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<Integer> queue = new LinkedList<>();

        // 记录每个 0 的坐标
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(i * col + j);
                }
            }
        }

        // 相当于从多个 0 同时出发进行 BFS 遍历，每次只移动一格，竞争最小距离
        // 哪个 0 出发先到达某 INF，那么这个距离就是它说了算，也一定就是全局的最小距离
        while (!queue.isEmpty()) {
            int index = queue.poll();
            int r = index / col;
            int c = index % col;

            for (int[] dir : dirs) {
                int new_r = r + dir[0];
                int new_c = c + dir[1];

                if (new_r >= 0 && new_r < row && new_c >= 0 && new_c < col && rooms[new_r][new_c] == INF) {
                    queue.offer(new_r * col + new_c);
                    rooms[new_r][new_c] = rooms[r][c] + 1;
                }
            }
        }
 */