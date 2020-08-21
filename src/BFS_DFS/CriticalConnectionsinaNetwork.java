package BFS_DFS;

/*  1192. Critical Connections in a Network
    There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections
    forming a network where connections[i] = [a, b] represents a connection between servers a and b.
    Any server can reach any other server directly or indirectly through the network.
    A critical connection is a connection that, if removed, will make some server unable to reach some other server.
    Return all critical connections in the network in any order.

    Example 1:
    Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
    Output: [[1,3]]
    Explanation: [[3,1]] is also accepted.

    Constraints:
        o 1 <= n <= 10^5
        o n-1 <= connections.length <= 10^5
        o connections[i][0] != connections[i][1]
        o There are no repeated connections.

    Hint: Use Tarjan's algorithm
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*  DFS: Time = O(n) Space = O(n)
    1. 找 cycle，一个 cycle 上面的任何一条边都不是 critical connections
    2. 如何 O(n) 找到所有的环？
        (1). DFS 向前探索：每个节点最多走一遍，每走一步记录从原点走到当前节点的步数
        (2). DFS 探索返回：返回当前节点所（间接）接触到非父节点的最小步数的节点
 */
public class CriticalConnectionsinaNetwork {

    private int time = -1;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        // build graph
        for (List<Integer> connection : connections) {
            int a = connection.get(0);
            int b = connection.get(1);
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] discover = new int[n];
        int[] low = new int[n];
        Arrays.fill(discover, -1);

        for (int i = 0; i < n; i++) {
            if (discover[i] == -1) {
                dfs(i, low, discover, graph, res, i);
            }
        }

        return res;
    }

    private void dfs(int u, int[] low, int[] discover, List<Integer>[] graph, List<List<Integer>> res, int pre) {
        low[u] = discover[u] = time++;

        for (int j = 0; j < graph[u].size(); j++) {
            int v = graph[u].get(j);
            if (v == pre) {
                continue;
            }
            if (discover[v] == -1) {
                dfs(v, low, discover, graph, res, u);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] > discover[u]) {
                    // u - v is critical, there is no path for v to reach back to u or previous vertices of u
                    res.add(Arrays.asList(u, v));
                }
            } else { // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
                low[u] = Math.min(low[u], discover[v]);
            }
        }
    }
}
