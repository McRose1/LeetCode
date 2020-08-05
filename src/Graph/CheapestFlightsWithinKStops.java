package Graph;

/*  787. Cheapest Flights Within K Stops
    There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
    Now given all the cities and flights, together with starting city src and the destination dst,
    your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

    Example 1:
    Input:
    n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
    src = 0, dst = 2, k = 1
    Output: 200
    Explanation:
    The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

    Example 2:
    Input:
    n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
    src = 0, dst = 2, k = 0
    Output: 500
    Explanation:
    The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.

    Constraints:
        o The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
        o The size of flights will be in range [0, n * (n - 1) / 2].
        o The format of each flight will be (src, dst, price).
        o The price of each flight will be in the range [1, 10000].
        o k is in the range of [0, n - 1].
        o There will not be any duplicated flights or self cycles.
 */

import java.util.PriorityQueue;

/*  Dijkstra + Heap: Time = O(E + nlogn) Space = O(n)
    按照 cost 从小到大的顺序扩展所有可能的飞行路线，当城市被添加到 dst 时，dst 中对应的值就是到达该城市的最低花费。
 */
public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // 邻接矩阵
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.offer(new int[] {0, 0, src});

        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int cost = cur[0];
            int k = cur[1];
            int place = cur[2];

            if (place == dst) {
                return cost;
            }

            if (k <= K) {
                for (int i = 0; i < n; i++) {
                    if (graph[place][i] > 0) {
                        minHeap.offer(new int[] {cost + graph[place][i], k + 1, i});
                    }
                }
            }
        }
        return -1;
    }
}

/*  Bellman-Ford (DP): Time = O(k * n) Space = O(k * n)

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] cost = new int[n];
        Arrays.fill(cost, 1000000);
        cost[src] = 0;

        for (int i = 0; i <= K; i++) {
            int[] temp = cost.clone();
            for (int[] flight : flights) {
                temp[flight[1]] = Math.min(temp[flight[1]], cost[flight[0]] + flight[2]);
            }
            cost = temp;
        }
        return cost[dst] >= 1000000 ? -1 : cost[dst];
    }
 */