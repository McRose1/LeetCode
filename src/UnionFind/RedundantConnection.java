package UnionFind;

/*  684. Redundant Connection
    In this problem, a tree is an undirected graph that is connected and has no cycles.
    The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added.
    The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
    The resulting graph is given as a 2D-array of edges.
    Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
    Return an edge that can be removed so that the resulting graph is a tree of N nodes.
    If there are multiple answers, return the answer that occurs last in the given 2D-array.
    The answer edge [u, v] should be in the same format, with u < v.

    Example 1:
    Input: [[1,2], [1,3], [2,3]]
    Output: [2,3]
    Explanation: The given undirected graph will be like this:
      1
     / \
    2 - 3

    Example 2:
    Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
    Output: [1,4]
    Explanation: The given undirected graph will be like this:
    5 - 1 - 2
        |   |
        4 - 3

    Note:
    The size of the input 2D-array will be between 3 and 1000.
    Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */

/*  Union Find: Time = O(n) Space = O(n)
    dsu.find(node x)，找到元素 x 所在的集合的代表，该操作也可以用于判断 2 个元素是否位于同一个集合。
    dsu.union(node x, node y)，把元素 x 和元素 y 所在的集合合并，要求 x 和 y 所在的集合不相交，如果相较则不合并。
    实现这一点，我们跟踪父结点，它会记录同一连接节点中较小节点的所在的集合。如果结点是它自己的父结点，我们将其称为为连接结点的领导者。
    我们使用 2 种技术来提高运行时的复杂性：路径压缩和按秩合并：
        o 路径压缩涉及将 find 函数中的 x = parent[x] 更改为 parent[x] = find(parent[x])
        o 按秩合并涉及到将发现的工作量平均分配给领导者。
          每当 dsu.union(x, y) 时，我们都有 2 个领导者 xr, yr，并且我们要选择是要 parent[x] = yr 还是 parent[y] = xr。
          我们选择有更多子节点的领导者作为领导者。
        o 具体地说，rank 的含义是 x 的跟随者少于 2 ^ rank[x]，这个策略可以作为 dsu.find 中的递归循环终止的界限

 */
public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length + 1);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[0];
    }
}

/*  DFS: Time = O(N^2) Space = O(N)
    对于每个边（u, v），用 DFS 遍历 graph，以查看是否可以将 u 连接到 v。如果可以，则它是重复边

    HashSet<Integer> seen = new HashSet<>();

    public int[] findRedundantConnection(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[1001];
        for (int i = 0; i <= 1000; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() && dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return new int[0];
    }

    private boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) {
                return true;
            }
            for (int nei : graph[source]) {
                if (dfs(graph, nei, target)) {
                    return true;
                }
            }
        }
        return false;
    }
 */