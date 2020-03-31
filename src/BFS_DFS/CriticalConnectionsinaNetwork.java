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
    1 <= n <= 10^5
    n-1 <= connections.length <= 10^5
    connections[i][0] != connections[i][1]
    There are no repeated connections.

    Hint: Use Tarjan's algorithm
 */

import java.util.List;
/*  DFS: Time = O(n) Space = O(n)
    1. 找 cycle，一个 cycle 上面的任何一条边都不是 critical connections
    2. 如何 O(n) 找到所有的环？
        (1). DFS 向前探索：每个节点最多走一遍，每走一步记录从原点走到当前节点的步数
        (2). DFS 探索返回：返回当前节点所（间接）接触到非父节点的最小步数的节点
 */
public class CriticalConnectionsinaNetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

    }
}
