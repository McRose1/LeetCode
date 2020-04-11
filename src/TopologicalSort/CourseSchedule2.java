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
    // 未被 visit 过
    int WHITE = 1;
    // 这本轮 DFS 中被 visit 过，标为 visiting
    int GRAY = 2;
    // 被别的 DFS visit 过，标为 visited
    int BLACK = 3;

    boolean isPossible;
    Map<Integer, Integer> color;
    Map<Integer, List<Integer>> graph;
    List<Integer> topoReverseOrder;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 这个答案特有的初始化，也可以不这么写
        init(numCourses);

        // Create the adjacency graph representation of the graph
        for (int[] pre : prerequisites) {
            int dest = pre[0];
            int src = pre[1];
            List<Integer> temp = graph.getOrDefault(src, new ArrayList<>());
            temp.add(dest);
            graph.put(src, temp);     // 一个 src 可能有多个 dest
        }

        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) {
            if (color.get(i) == WHITE) {
                dfs(i);
            }
        }

        int[] order;
        if (isPossible) {
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                // 顺序相反从右往左读入
                order[i] = topoReverseOrder.get(numCourses - i - 1);
            }
        } else {
            order = new int[0];
        }
        return order;
    }

    private void init(int numCourses) {
        isPossible = true;
        color = new HashMap<>();
        graph = new HashMap<>();
        topoReverseOrder = new ArrayList<>();

        // By default all vertices are WHITE
        for (int i = 0; i < numCourses; i++) {
            color.put(i, WHITE);
        }
    }

    private void dfs(int node) {

        // Don't recurse further if we found a cycle already
        if (!isPossible) {
            return;
        }

        // Start the recursion
        color.put(node, GRAY);

        // Traverse on neighboring vertices
        for (Integer nei : graph.getOrDefault(node, new ArrayList<>())) {
            if (color.get(nei) == WHITE) {
                dfs(nei);
            } else if (color.get(nei) == GRAY) {
                // An edge to a GRAY vertex represents a cycle
                isPossible = false;
            }
        }

        // Recursion ends. We mark it as black
        color.put(node, BLACK);
        topoReverseOrder.add(node);
    }
}

/*  BFS (Using Node InDegree): Time = O(n) Space = O(n)

        boolean isPossible = true;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // Create the adjacency graph representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> temp = graph.getOrDefault(src, new ArrayList<>());
            list.add(dest);
            graph.put(src, temp);

            // Record in-degree of each vertex
            inDegree[dest] += 1;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.remove();
            topologicalOrder[i++] = node;

            // Reduce the in-degree of each neighbor by 1
            if (graph.containsKey(node)) {
                for (Integer nei : graph.get(node)) {
                    inDegree[nei]--;

                    // If in-degree of a neighbor becomes 0, add it to the Q
                    if (inDegree[nei] == 0) {
                        q.add(nei);
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

/*  my version (BFS)
    没用 HashMap 构建 graph，相当于用时间换空间

        int[] inDegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            inDegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] res = new int[numCourses];
        int count = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[count++] = cur;

            for (int[] pre : prerequisites) {
                if (pre[1] == cur) {
                    inDegree[pre[0]]--;
                    if (inDegree[pre[0]] == 0) {
                        queue.offer(pre[0]);
                    }
                }
            }
        }
        if (count == numCourses) {
            return res;
        } else {
            return new int[0];
        }
 */
