package BFS_DFS;

/*  743. Network Delay Time
    There are N network nodes, labelled 1 to N.

    Given times, a list of travel times as directed edges times[i] = (u, v, w),
    where u is the source code, v is the target node, and w is the time it takes for a signal to travel from source to target.

    Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? It it is impossible, return -1.

    Example:
    Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
    Output: 2

    Note:
    1. N will be in the range [1, 100].
    2. K will be in the range [1, N].
    3. The length of times will be in the range [1, 6000].
    4. All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.

    Hint: We visit each node at some time, and if that time is better than the fastest time we've reached this node,
          we travel along outgoing edges in sorted order.
          Alternatively, we could use Dijkstra's algorithm.
 */

import java.util.*;

/*  DFS

 */
public class NetworkDelayTime {
    Map<Integer, Integer> dist;

    public int networkDelayTime(int[][] times, int N, int K) {
        // <source, {[weight1, target1], [weight2, target2] ...}>
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[] {edge[2], edge[1]});
        }

        for (int node : graph.keySet()) {
            // 按 weight 从小到大排序
            graph.get(node).sort((a, b) -> a[0] - b[0]);
        }

        dist = new HashMap<>();
        for (int node = 1; node <= N; node++) {
            dist.put(node, Integer.MAX_VALUE);
        }

        dfs(graph, K, 0);
        int ans = 0;
        for (int candidate : dist.values()) {
            if (candidate == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, candidate);
        }
        return ans;
    }

    private void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
        if (elapsed >= dist.get(node)) {
            return;
        }

        dist.put(node, elapsed);
        if (graph.containsKey(node)) {
            for (int[] info : graph.get(node)) {
                dfs(graph, info[1], elapsed + info[0]);
            }
        }
    }
}
