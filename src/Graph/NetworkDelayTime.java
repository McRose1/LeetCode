package Graph;

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

/*  Dijkstra + Heap: Time = O(ElogE) Space = O(N + E)
    Single Source All Destinations Shortest Path
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        // <source, {[dest1, dist to dest1], [dest2, dist to dest2] ...}>
        // graph 记录每个 node 到各个可达节点的距离
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
        }
        // 根据距离从小到大排序
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((info1, info2) -> info1[1] - info2[1]);
        // 先 offer 进 source 和距离 0
        minHeap.offer(new int[] {K, 0});

        // <dest, dist from source to dest>
        // dist 记录的是起点到各个终点的最短路径，其中的最大值便是题目的答案
        Map<Integer, Integer> dist = new HashMap<>();

        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int node = cur[0];
            int d = cur[1];
            if (dist.containsKey(node)) {
                continue;
            }
            dist.put(node, d);
            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int nei = edge[0];
                    int d2 = edge[1];
                    if (!dist.containsKey(nei)) {
                        minHeap.offer(new int[] {nei, d + d2});
                    }
                }
            }
        }
        if (dist.size() != N) {
            return -1;
        }
        int ans = 0;
        for (int cand : dist.values()) {
            ans = Math.max(ans, cand);
        }
        return ans;
    }
}

/*  Dijkstra: Time = O(N^2 + E) Space = O(N + E)

    Map<Integer, Integer> dist;
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
        }
        dist = new HashMap<>();
        for (int node = 1; node <= N; node++) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(K, 0);
        boolean[] visited = new boolean[N + 1];

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && dist.get(i) < candDist) {
                    candDist = dist.get(i);
                    candNode = i;
                }
            }

            if (candNode < 0) break;
            visited[candNode] = true;
            if (graph.containsKey(candNode)) {
                for (int[] info : graph.get(candNode)) {
                    dist.put(info[0], Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));
                }
            }
        }
        int ans = 0;
        for (int cand : dist.values()) {
            if (cand == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, cand);
        }
        return ans;
    }
 */

/*  Bellman-Ford: Time = O(V*E) Space = O(N)

    public int networkDelayTime(int[][] times, int N, int k) {
        int[] dist = new int[N];
        for (int u = 0; u < N; u++) {
            dist[u] = -1;
        }
        dist[K - 1] = 0;
        for (int k = 0; k < N - 1; k++) {
            for (int[] edge : times) {
                int u = edge[0] - 1;
                int v = edge[1] - 1;
                int w = edge[2];
                if (dist[u] == -1) {
                    continue;
                }
                if (dist[v] == -1 || dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }
        int max = 0;
        for (int u = 0; u < N; u++) {
            if (dist[u] == -1) {
                return -1;
            }
            max = Math.max(max, dist[u]);
        }
        return max;
    }
 */

/*  DFS

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
 */