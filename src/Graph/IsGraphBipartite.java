package Graph;

/*  785. Is Graph Bipartite?
    Given an undirected graph, return true if and only if it is bipartite.
    Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B such that
    every edge in the graph has one node in A and another node in B.

    The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
    Each node is an integer between 0 and graph.length - 1.
    There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

    Example 1:
    Input: [[1,3], [0,2], [1,3], [0,2]]
    Output: true
    Explanation:
    The graph looks like this:
    0----1
    |    |
    |    |
    3----2
    We can divide the vertices into two groups: {0, 2} and {1, 3}.

    Example 2:
    Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
    Output: false
    Explanation:
    The graph looks like this:
    0----1
    | \  |
    |  \ |
    3----2
    We cannot find a way to divide the set of nodes into two independent subsets.

    Note:
        o graph will have length in range [1, 100].
        o graph[i] will contain integers in range [0, graph.length - 1].
        o graph[i] will not contain i or duplicate values.
        o The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 */

import java.util.LinkedList;
import java.util.Queue;

/*  BFS: Time = O(N + M) Space = O(N)
    转化问题：给定一个无向图，判断是否能找到一个使用两种颜色的着色方案，使每条边连接的两点颜色均不同。
 */

public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        // label 0: unvisited
        // label 1: red
        // label 2: green
        int[] visited = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] != 0) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            // mark as red
            visited[i] = 1;

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int curLabel = visited[cur];
                int neiLabel = curLabel == 1 ? 2 : 1;
                for (int nei : graph[cur]) {
                    if (visited[nei] == 0) {
                        visited[nei] = neiLabel;
                        queue.offer(nei);
                    } else if (visited[nei] != neiLabel) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

/*  DFS: Time = O(N + M) Space = O(N)

    private int[] visited;
    private boolean valid;
    public boolean isBipartite(int[][] graph) {
        visited = new int[graph.length];
        valid = true;
        for (int i = 0; i < graph.length && valid; i++) {
            if (visited[i] == 0) {
                dfs(i, 1, graph);
            }
        }
        return valid;
    }

    private void dfs(int node, int label, int[][] graph) {
        visited[node] = label;
        int neiLabel = label == 1 ? 2 : 1;
        for (int nei : graph[node]) {
            if (visited[nei] == 0) {
                dfs(nei, neiLabel, graph);
                if (!valid) {
                    return;
                }
            } else if (visited[nei] != neiLabel) {
                valid = false;
                return;
            }
        }
    }
 */