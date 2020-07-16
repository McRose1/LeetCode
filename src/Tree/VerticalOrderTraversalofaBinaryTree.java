package Tree;

/*  987. Vertical Order Traversal of a Binary Tree
    Given a binary tree, return the vertical order traversal of its nodes values.
    For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
    Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes,
    we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
    If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
    Return an list of non-empty reports in order of X coordinate. Every report will have a list of values of nodes.

    Example 1:
    Input: [3,9,20,null,null,15,7]
    Output: [[9],[3,15],[20],[7]]
    Explanation:
    Without loss of generality, we can assume the root node is at position (0, 0):
    Then, the node with value 9 occurs at position (-1, -1);
    The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
    The node with value 20 occurs at position (1, -1);
    The node with value 7 occurs at position (2, -2).

    Example 2:
    Input: [1,2,3,4,5,6,7]
    Output: [[4],[2],[1,5,6],[3],[7]]
    Explanation:
    The node with value 5 and the node with value 6 have the same position according to the given scheme.
    However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.

    Note:
    1. The tree will have between 1 and 1000 nodes.
    2. Each node's value will be between 0 and 1000.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

//  DFS
public class VerticalOrderTraversalofaBinaryTree {
    // <x, <y, pq>>
    // 最外层 TreeMap 实现 x 从小到大排序
    // 里层 TreeMap 实现 y 从小到大排序
    // pq 实现相同 y 的情况下，val 从小到大排序
    TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, 0);
        for (TreeMap<Integer, PriorityQueue<Integer>> y : map.values()) {
            res.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : y.values()) {
                while (!nodes.isEmpty()) {
                    res.get(res.size() - 1).add(nodes.poll());
                }
            }
        }
        return res;
    }

    private void dfs(TreeNode root, int x, int y) {
        if (root == null) return;

        if (!map.containsKey(x)) {
            map.put(x, new TreeMap<>());
        }
        if (!map.get(x).containsKey(y)) {
            map.get(x).put(y, new PriorityQueue<>());
        }
        map.get(x).get(y).offer(root.val);

        dfs(root.left, x - 1, y - 1);
        dfs(root.right, x + 1, y - 1);
    }
}

/*  BFS

    class Node {
        TreeNode root;
        int x;
        int y;

        Node (TreeNode root, int x, int y) {
            this.root = root;
            this.x = x;
            this.y = y;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Map<Integer, List<Node>> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0, 0));
        int minx = 0;
        int maxx = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            minx = Math.min(minx, cur.x);
            maxx = Math.max(maxx, cur.x);
            map.putIfAbsent(cur.x, new ArrayList<>());
            map.get(cur.x).add(cur);
            if (cur.root.left != null) {
                queue.offer(new Node(cur.root.left, cur.x - 1, cur.y - 1));
            }
            if (cur.root.right != null) {
                queue.offer(new Node(cur.root.right, cur.x + 1, cur.y - 1));
            }
        }
        int index = 0;
        for (int i = minx; i <= maxx; i++){
            Collections.sort(map.get(i), (a, b) -> {
                if (a.y == b.y) {
                    return a.root.val - b.root.val;
                }
                return b.y - a.y;
            });
            res.add(new ArrayList<>());
            for (Node node : map.get(i)) {
                res.get(index).add(node.root.val);
            }
            index++;
        }
        return res;
    }
 */
