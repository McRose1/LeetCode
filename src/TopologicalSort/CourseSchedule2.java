package TopologicalSort;

/*  210 Course Schedule 2
    There are a total of n courses you have to take, labeled from 0 to n-1.
    Some courses may have prerequisites, for example to take course 0 you have first take course 1,
    which is expressed as a pair: [0, 1]
    Given the total number of courses and a list of prerequisite pairs,
    return the ordering of courses you should take to finish all courses.
    There may be multiple correct orders, you just need to return one of them.
    If it is impossible to finish all courses, return an empty array.

    Example 1:
    Input: 2, [[1,0]]
    Output: [0, 1]]
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0. So the correct course order is [0,1] .

    Example 2:
    Input: 4, [[1,0],[2,0],[3,1],[3,2]]
    Output: [0,1,2,3] or [0,2,1,3]
    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

    Note:
    1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
    Read more about how a graph is represented.
    2. You may assume that there are no duplicate edges in the input prerequisites.

    Hint 1:
    This problem is equivalent to finding if a cycle exists in a directed graph.
    If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  DFS: Time = O(n) Space = O(n)
public class CourseSchedule2 {
    static int WHITE = 1;
    static int GRAY = 2;
    static int BLACK = 3;

    boolean isPossible;
    Map<Integer, Integer> color;
    Map<Integer, List<Integer>> adjList;
    List<Integer> topologicalOrder;

    private void init(int numCourses) {
        this.isPossible = true;
        this.color = new HashMap<>();
        this.adjList = new HashMap<>();
        this.topologicalOrder = new ArrayList<>();

        // By default all vertices are WHITE
        for (int i = 0; i < numCourses; i++) {
            this.color.put(i, WHITE);
        }
    }

    private void dfs(int node) {

        // Don't recurse further if we found a cycle already
        if (!this.isPossible) {
            return;
        }

        // Start the recursion
        this.color.put(node, GRAY);

        // Traverse on neighboring vertices
        for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<>())) {
            if (this.color.get(neighbor) == WHITE) {
                this.dfs(neighbor);
            } else if (this.color.get(neighbor) == GRAY) {
                // An edge to a GRAY vertex represents a cycle
                this.isPossible = false;
            }
        }

        // Recursion ends. We mark it as black
        this.color.put(node, BLACK);
        this.topologicalOrder.add(node);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        this.init(numCourses);

        // Create the adjacency list representation of the graph
        for (int[] prerequisite : prerequisites) {
            int dest = prerequisite[0];
            int src = prerequisite[1];
            List<Integer> list = adjList.getOrDefault(src, new ArrayList<>());
            list.add(dest);
            adjList.put(src, list);     // 一个 src 可能有多个 dest
        }

        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) {
            if (this.color.get(i) == WHITE) {
                this.dfs(i);
            }
        }

        int[] order;
        if (this.isPossible) {
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                order[i] = this.topologicalOrder.get(numCourses - i - 1);
            }
        } else {
            order = new int[0];
        }
        return order;
    }
}

/*  Using Node Indegree: Time = O(n) Space = O(n)

        boolean isPossible = true;
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> list = adjList.getOrDefault(src, new ArrayList<>());
            list.add(dest);
            adjList.put(src, list);

            // Record in-degree of each vertex
            indegree[dest] += 1;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.remove();
            topologicalOrder[i++] = node;

            // Reduce the in-degree of each neighbor by 1
            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    indegree[neighbor]--;

                    // If in-degree of a neighbor becomes 0, add it to the Q
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }
        // Check to see if topological sort is possible or not.
        if (i == numCourses) {
            return topologicalOrder;
        }
        return new int[0];
 */
