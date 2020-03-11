package Graph;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        List<HashSet<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());       // [{}, {}, {}, {}]
        }
        for (int[] edge : edges) {      // [1, 0]; [1, 2], [1, 3]
            adj.get(edge[0]).add(edge[1]);  // [{}, {0}]; [{1}, {0, 2}]; [{1}, {0, 2, 3}, {1}]
            adj.get(edge[1]).add(edge[0]);  // [{1}, {0}]; [{1}, {0, 2}, {1}]; [{1}, {0, 2, 3}, {1}, {1}]
        }                               // adj = [{1}, {0, 2, 3}, {1}, {1}]
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) {
                res.add(i);             // res = [0, 2, 3]
            }
        }
        while (n > 2) {
            n -= res.size();            // n = 4 - 3 = 1
            List<Integer> leaves = new ArrayList<>();   // leaves = []
            for (int i : res) {             // i = 0; 2
                for (int j : adj.get(i)) {  // j = 1; j = 1
                    adj.get(j).remove(i);   // {2, 3}; {3}
                    if (adj.get(j).size() == 1) {   // size = 2; 1
                        leaves.add(j);              // leaves = [1]
                    }
                }
            }
            res = leaves;
        }
        return res;
    }
}
