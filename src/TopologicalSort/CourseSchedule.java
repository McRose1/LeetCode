package TopologicalSort;

/*  207 Course Schedule
    There are a total of n courses you have to take, labeled from 0 to n-1.
    Some courses may have prerequisites, for example to take course 0 you have first take course 1,
    which is expressed as a pair: [0, 1]
    Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

    Example 1:
    Input: 2, [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0. So it is possible.

    Example 2:
    Input: 2, [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0, and to take course 0 you should
                 also have finished course 1. So it is impossible.

    Note:
    1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
    Read more about how a graph is represented.
    2. You may assume that there are no duplicate edges in the input prerequisites.

    Hint 1:
    This problem is equivalent to finding if a cycle exists in a directed graph.
    If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
 */

import java.util.LinkedList;
import java.util.Queue;

/*  BFS（Topological Sort）
    只将入度为 0 的点添加至 queue。当完成一个顶点的搜索（从 queue 中取出），它指向的所有顶点的入度都减 1
    若此时某顶点入度为 0 则添加至 queue，若完成 BFS 后，所有的点入度都为 0，则 graph 没有 cycle
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        int count = 0;
        for (int[] pair : prerequisites) {
            inDegree[pair[0]]++;
        }
        // add to queue if element's indegree is 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            // no prerequisite course
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        // BFS
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            count++;
            for (int[] pair : prerequisites) {
                if (pair[1] == pre) {
                    inDegree[pair[0]]--;
                    if (inDegree[pair[0]] == 0) {
                        queue.offer(pair[0]);
                    }
                }
            }
        }
        return count == numCourses;
    }
}

/*  DFS
    如果正在搜索某一顶点（还未退出该顶点的 DFS），又回到了该顶点，即证明 graph 有 cycle

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            graph.get(course).add(prerequisite);
        }

        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, graph, visited)) return false;
        }
        return true;
    }

    private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited) {
        if (visited[curr] == 1) return true;
        if (visited[curr] == 2) return false;

        visited[curr] = 1;

        for (int next : graph.get(curr)) {
            if (dfs(next, graph, visited)) return true;
        }

        visited[curr] = 2;
        return false;
    }
 */
