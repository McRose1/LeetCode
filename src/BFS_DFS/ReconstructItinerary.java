package BFS_DFS;

/*  332. Reconstruct Itinerary
    Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
    reconstruct the itinerary in order.
    All of the tickets belong to a man who departs from JFK. Thus the itinerary must begin with JFK.

    Note:
    If there are multiple valid itineraries,
    you should return the itinerary that has the smallest lexical order when read as a single string.
    For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].

    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.

    Example 1:
    Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
    Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

    Example 2:
    Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
                 But it is larger in lexical order.
 */

import java.util.*;
/*  Eulerian Path（欧拉路径）：Hierholzer 算法 + PQ: Time = O(nlogn) Space = O(n)
    一个有向图中存在欧拉路径，iff:
    - 图是连通的，即图上任意 2 个点总有路径连接
    - 满足下面 2 个条件中的 1 个：
        - 1. 有且只有 1 个点的入度比出度少 1（作为欧拉路径的起点），有且只有 1 个点的入度比出度多 1（作为终点），且其余点 入度=出度
        - 2. 所有点 入度=出度
    已知图中存在欧拉路径，找到 1 个欧拉路径：
    1. Fleury（佛罗莱算法）
    2. Hierholzer 算法
 */
public class ReconstructItinerary {
    List<String> findItinerary(List<List<String>> tickets) {
        // pq 帮助实现 lexical order
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        LinkedList<String> res = new LinkedList<>();

        for (List<String> ticket : tickets) {
            if (!map.containsKey(ticket.get(0))) {
                // 初始化终点集合
                map.put(ticket.get(0), new PriorityQueue<>());
            }
            map.get(ticket.get(0)).offer(ticket.get(1));
        }

        dfs("JFK", res, map);
        return res;
    }

    private void dfs(String start, LinkedList<String> res, Map<String, PriorityQueue<String>> map) {
        PriorityQueue<String> queue = map.get(start);

        while (queue != null && !queue.isEmpty()) {
            dfs(queue.poll(), res, map);
        }
        // 逆序从后往前加，所以每次加在头部实现倒序
        res.addFirst(start);
    }
}

/*  DFS (Backtracking): Time = O(n!) Space = O(n)
    从 idea 开始尝试全部可能的路径，返回第一个用完全部 ticket 的路径
    1、把 input 转换成 graph 的结构：给定一个点，能快速地知道它所连接的邻居点们，并且以 lexical order 的顺序访问它们
    2、在构造的 graph 上从 JFK 开始做 DFS，remove 掉访问过的边，backtrack 的时候再加回来

    List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        if (tickets == null || tickets.size() == 0) return res;

        // n 条 edge 会有 n+1 个 node
        int count = tickets.size() + 1;

        // 构建 graph，key 代表起点，value 代表终点
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            // 图中还没有该起点，初始化 value
            if (!map.containsKey(ticket.get(0))) {
                map.put(ticket.get(0), new ArrayList<>());
            }
            map.get(ticket.get(0)).add(ticket.get(1));
        }
        // {["JFK", ["MUC"]], ["MUC", ["LHR"]], ["LHR", ["SFO"]], ["SFO", ["SJC"]]}

        // Lexically sort
        for (List<String> list : map.values()) {
            Collections.sort(list);
        }

        String start = "JFK";
        res.add(start);
        if (dfs(start, res, map, count)) {
            return res;
        }
        return null;
    }

    private boolean dfs(String start, List<String> path, Map<String, List<String>> map, int count) {
        // 递归的出口
        if (path.size() == count) return true;
        if (!map.containsKey(start)) return false;

        // 当前起点能到达的终点的集合
        List<String> dest = map.get(start);     // ["MUC"]

        for (int i = 0; i < dest.size(); i++) {
            String destination = dest.get(i);
            dest.remove(i);
            path.add(destination);                 // ["JFK", "MUC"]
            // 终点成为新的起点，继续 DFS
            if (dfs(destination, path, map, count)) {
                return true;
            }
            // backtracking
            path.remove(path.size() - 1);
            dest.add(i, destination);
        }
        return false;
    }
 */