package BFS_DFS;

/*  547. Friend Circles
    There are N students in a class. Some of them are friends, while some are not.
    Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C.
    And we define a friend circle is a group of students who are direct or indirect friends.

    Given a N*N matrix M representing the friend relationship between students in the class.
    If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
    And you have to output the total number of friend circles among all the students.

    Example 1:
    Input:
    [[1,1,0],
     [1,1,0],
     [0,0,1]]
    Output: 2
    Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
    The 2nd student himself is in a friend circle. So return 2.

    Example 2:
    Input:
    [[1,1,0],
     [1,1,1],
     [0,1,1]]
    Output: 1
    Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
    so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

    Note:
    1. N is in range [1,200].
    2. M[i][i] = 1 for all students.
    3. If M[i][j] = 1, then M[j][i] = 1.
 */

/*  DFS: Time = O(n^2) Space = O(n)
    给定的矩阵可以看成图的邻接矩阵，把问题转换成求无向图连通块的个数。
 */
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M[0].length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
}

/*  BFS: Time = O(n^2) Space = O(n)

        int[] visited = new int[M.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    visited[cur] = 1;
                    for (int j = 0; j < M[0].length; j++) {
                        if (M[cur][j] == 1 && visited[j] == 0) {
                            queue.offer(j);
                        }
                    }
                }
                count++;
            }
        }
        return count;
 */

/*  Union Find: Time = O(n^2) Space = O(n)
    另一种统计图中连通块数量的方法是使用并查集

    public int findCircleNum(int[][] M) {
        int[] parent = new int[M.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }

    private int find(int[] parent, int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    private void union(int[] parent, int x, int y) {
        int x_root = find(parent, x);
        int y_root = find(parent, y);
        if (x_root != y_root) {
            parent[x_root] = y_root;
        }
    }
 */