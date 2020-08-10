package Tree;

/*  742. Closest Leaf in a Binary Tree
    Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target  k in the tree.
    Here, nearest to the leaf means the least number of edges travelled on the binary tree to reach any leaf to the tree.
    Also, a node is called a leaf if it has no children.
    In the following examples, the input tree is represented in flattened form row by row.
    The actual root tree given will be a TreeNode object.

    Example 1:
    Input:
    root = [1, 3, 2], k = 1
    Diagram of binary tree:
              1
             / \
            3   2

    Output: 2 (or 3)

    Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.

    Example 2:
    Input:
    root = [1], k = 1
    Output: 1

    Explanation: The nearest leaf node is the root node itself.

    Example 3:
    Input:
    root = [1,2,3,4,null,null,null,5,null,6], k = 2
    Diagram of binary tree:
                 1
                / \
               2   3
              /
             4
            /
           5
          /
         6

    Output: 3
    Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.

    Note:
    1. root represents a binary tree with at least 1 node and at most 1000 nodes.
    2. Every node has a unique node.val in range [1, 1000].
    3. There exists some node in the given binary tree for which node.val == k.
 */

import java.util.*;

/*  DFS + BFS: Time = O(n) Space = O(n)
    转换成 graph problem: find the shortest path to any of the leaf node from the target node.
    Use DFS to construct the undirected graph, and find the start node
    Use BFS to search the graph from start node to any leaf node
 */
public class ClosestLeafinaBinaryTree {

    private HashMap<TreeNode, List<TreeNode>> graph;

    public int findClosestLeaf(TreeNode root, int k) {
        if (root.left == null && root.right == null) {
            return k;
        }
        graph = new HashMap<>();
        buildGraph(root, null);

        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode> seen = new HashSet<>();
        for (TreeNode node : graph.keySet()) {
            if (node != null && node.val == k) {
                queue.offer(node);
                seen.add(node);
            }
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                // 如果是 leaf node
                if (graph.get(cur).size() <= 1) {
                    return cur.val;
                }
                for (TreeNode nei : graph.get(cur)) {
                    if (!seen.contains(nei)) {
                        seen.add(nei);
                        queue.offer(nei);
                    }
                }
            }
        }
        return -1;
    }

    private void buildGraph(TreeNode node, TreeNode parent) {
        if (node != null) {
            if (!graph.containsKey(node)) {
                graph.put(node, new ArrayList<>());
            }
            if (!graph.containsKey(parent)) {
                graph.put(parent, new ArrayList<>());
            }

            graph.get(node).add(parent);
            graph.get(parent).add(node);

            buildGraph(node.left, node);
            buildGraph(node.right, node);
        }
    }
}
