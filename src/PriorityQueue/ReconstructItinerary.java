package PriorityQueue;

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
/*  DFS
    从 idea 开始尝试全部可能的路径，返回第一个用完全部 ticket 的路径
    1、把 input 转换成 graph 的结构：给定一个点，能快速地知道它所连接的邻居点们，并且以 lexical order 的顺序访问它们
    2、在构造的 graph 上从 JFK 开始做 DFS，remove 掉访问过的边，backtrack 的时候再加回来
 */
public class ReconstructItinerary {
    List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        if (tickets == null || tickets.size() == 0) return res;
        int len = tickets.size() + 1;
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
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
        if (dfs(start, res, map, len)) {
            return res;
        }
        return null;
    }
    private boolean dfs(String start, List<String> path, Map<String, List<String>> map, int len) {
        if (path.size() == len) return true;
        if (!map.containsKey(start)) return false;

        List<String> destinations = map.get(start);     // ["MUC"]

        for (int i = 0; i < destinations.size(); i++) {
            String dest = destinations.get(i);
            destinations.remove(i);
            path.add(dest);                 // ["JFK", "MUC"]
            if (dfs(dest, path, map, len)) {
                return true;
            }
            // backtracking 还原
            path.remove(path.size() - 1);
            destinations.add(i, dest);
        }
        return false;
    }
}

/*  Eulerian Path（欧拉路径）：Hierholzer 算法 + PQ

    List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        LinkedList<String> res = new LinkedList<>();

        for (List<String> ticket : tickets) {
            if (!map.containsKey(ticket.get(0))) {
                PriorityQueue<String> minHeap = new PriorityQueue<>();
                map.put(ticket.get(0), minHeap);
            }
            map.get(ticket.get(0)).add(ticket.get(1));
        }

        dfs("JFK", res, map);
        return res;
    }
    private void dfs(String s, LinkedList<String> res, Map<String, PriorityQueue<String>> map) {
        PriorityQueue<String> q = map.get(s);

        while (q != null && !q.isEmpty()) {
            dfs(q.poll(), res, map);
        }
        res.addFirst(s);
    }

 */