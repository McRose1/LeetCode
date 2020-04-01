package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  133. Clone Graph
    Given a reference of a node in a connected undirected graph.
    Return a deep copy (clone) of the graph.
    Each node in the graph contains a val(int) and a list(List[Node]) of its neighbors.

    Test case format:
    For simplicity sake, each node's value is the same as the node's index(1-indexed).
    For example, the first node with val = 1, the second node with val = 2, and so on.
    The graph is represented in the test case using an adjacency list.

    Adjacency list is a collection of unordered lists used to represent a finite graph.
    Each list describes the set of neighbors of a node in the graph.

    The given node will always be the first node with val = 1.
    You must return the copy of the given node as a reference to the cloned graph.

    Example 1:
    Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
    Output: [[2,4],[1,3],[2,4],[1,3]]
    Explanation: There are 4 nodes in the graph.
    1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
    2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
    3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
    4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

    Example 2:
    Input: adjList = [[]]
    Output: [[]]
    Explanation: Note that the input contains one empty list.
    The graph consists of only one node with val = 1 and it does not have any neighbors.

    Example 3:
    Input: adjList = []
    Output: []
    Explanation: This an empty graph, it does not have any nodes.

    Example 4:
    Input: adjList = [[2],[1]]
    Output: [[2],[1]]

    Constraints:
    1 <= Node.val <= 100
    Node.val is unique for each node.
    Number of Nodes will not exceed 100.
    There is no repeated edges and no self-loops in the graph.
    The Graph is connected and all nodes can be visited starting from the given node.
 */
/*  DFS: Time = O(n) Space = O(n): HashMap + O(H)，H 是 graph 的 depth
    用 HashMap 建立 original graph node 和 current graph node 之间的映射
    <Key: original graph node, Value: clone graph node>
 */
public class CloneGraph {
    Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList<>());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node nei : node.neighbors) {
            // dfs is here, recursively explore current node's neighbors' neighbors
            cloneNode.neighbors.add(cloneGraph(nei));
        }
        return cloneNode;
    }
}

/*  BFS: Time = O(n) Space = O(n): HashMap + O(W)，W 是 graph 的 breadth
    Use queue to simulate the process of BFS

        if (node == null) return null;

        // Hash map to save the visited node and it's clone as key and value respectively.
        // This helps to avoid cycles.
        Map<Node, Node> visited = new HashMap<>();

        // Put the first node in the queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        // Clone the node and put it in the visited dictionary.
        visited.put(node, new Node(node.val, new ArrayList()));

        // Start BFS traversal
        while (!queue.isEmpty()) {
            // Pop a node from the top of the queue
            Node cur = queue.remove();
            // Iterate through all the neighbors of the node
            for (Node nei : cur.neighbors) {
                if (!visited.containsKey(nei)) {
                    // Clone the neighbors and put in the visited, if not present already
                    visited.put(nei, new Node(nei.val, new ArrayList()));
                    // Add the newly encountered node to the queue.
                    queue.add(nei);
                }
                // Add the clone of the neighbors to the neighbors of the clone node.
                visited.get(cur).neighbors.add(visited.get(nei));
            }
        }
        // Return the clone of the node from visited.
        return visited.get(node);
 */
