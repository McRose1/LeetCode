package BFS_DFS;

/*  310. Minimum Height Trees
    For an undirected graph with tree characteristics, we can choose any node as the root.
    The result graph is then a rooted tree.
    Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
    Given such a graph, write a function to find all the MHTs and return a list of their root labels.

    Format
    The graph contains n nodes which are labeled from 0 to n-1.
    You will be given the number n and a list of undirected edges (each edge is a pair of labels).
    You can assume that no duplicate edges will appear in edges.
    Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

    Example 1:
    Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
            0
            |
            1
           / \
          2   3
    Output: [1]

    Example 2:
    Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
         0  1  2
          \ | /
            3
            |
            4
            |
            5
    Output: [3, 4]

    Note:
    According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path.
    In other words, any connected graph without simple cycles is a tree.”
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */

import java.util.*;
/*  BFS
    将从无向图中找 root 的问题转换为从图中找出 degree 最大的 node 们
    计算每个 node 的 degree（关联该点的边数），先遍历 degree 为 1 的 node，遍历过程中将相连的其他 node 的 degree 减 1
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }

        int[] degree = new int[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] pair : edges) {
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
            degree[pair[0]]++;
            degree[pair[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            // 每一次 BFS 遍历的是 degree 相同的 node，BFS 越深说明该 node 的 degree 越大
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                list.add(cur);
                for (int nei : map.get(cur)) {
                    degree[nei]--;
                    if (degree[nei] == 1) {
                        queue.offer(nei);
                    }
                }
            }
            res = list;
        }
        return res;
    }
}
