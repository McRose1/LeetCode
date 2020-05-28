package UnionFind;

/*  261. Graph Valid Tree
    Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
    write a function to check whether these edges make up a valid tree.

    Example 1:
    Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
    Output: true

    Example 2:
    Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
    Output: false

    Note: you can assume that no duplicate edges will appear in edges.
    Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */

import java.util.*;

/*  Union Find: Time = O(edges * nodes) Space = O(n)
    其实就是检查是否存在环，因为树就是不存在 cycle 的 graph
    遍历节点，如果两个节点相连，我们将其 roots 值连上，初始化 roots 数组为 -1
    对一个 pair 的两个节点分别调用 find 函数，得到的值如果相同的话，则说明环存在，不同的话，将其 root 值 union 上
 */
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {    // [[0,1], [1,2], [2,3], [1,3], [1,4]]
        // no connection
        if (edges.length != n - 1) return false;

        if (n == 1 && edges.length == 0) return true;

        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = -1;
        }

        for (int[] pair : edges) {        // [0, 1]; [1, 2]; [2, 3]; [1, 3]
            int x = find(roots, pair[0]); // x = find(roots, 0) -> 0; ... x = find(roots, 1) -> 3
            int y = find(roots, pair[1]); // y = find(roots, 1) -> 1; ... y = find(roots, 3) -> 3
            if (x == y) return false;                                  // x == y = 3 return false
            roots[x] = y;               // root[0] = 1; root[1] = 2; root[2] = 3
        }
        return true;
    }

    private int find(int[] roots, int i) {  // 1; 3
        while (roots[i] != -1) {    // roots[1] = 2, root[2] = 3
            i = roots[i];           // i = 2, i = 3
        }
        return i;       // 3
    }
}

/*  DFS

    public boolean validTree(int n, int[][] edges) {
        // no connection
        if (edges.length != n - 1) return false;

        if (n == 1 && edges.length == 0) return true;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);

        boolean res = dfs(graph, visited, 0, -1);
        if (!res) return false;
        return visited.size() == n;
    }

    private boolean dfs(List<List<Integer>> graph, HashSet<Integer> visited, int node, int parent) {
        List<Integer> neighbors = graph.get(node);
        for (int nei : neighbors) {
            // we don't want to go back
            if (nei == parent) continue;
            // cycle
            if (visited.contains(nei)) return false;
            visited.add(nei);
            boolean res = dfs(graph, visited, nei, node);
            if (!res) return false;
        }
        return true;
    }
 */

/*  BFS

        // no connection
        if (edges.length != n - 1) return false;

        if (n == 1 && edges.length == 0) return true;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(edges[0][0]);
        HashSet<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (visited.contains(cur)) return false;
            visited.add(cur);
            List<Integer> neighbors = graph.get(cur);
            for (int nei : neighbors) {
                if (!visited.contains(nei)) {
                    queue.offer(nei);
                }
            }
        }
        return visited.size() == n;
 */
