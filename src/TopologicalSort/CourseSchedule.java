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
        // 统计每个 node 入度的数组
        int[] inDegree = new int[numCourses];
        int count = 0;
        // 初始化入度数组，需要几个先修，入度就为几
        for (int[] pair : prerequisites) {
            inDegree[pair[0]]++;
        }
        // add to queue if element's inDegree is 0
        Queue<Integer> queue = new LinkedList<>();
        // 直接在 inDegree 数组里遍历，不用通过 HashMap 构建 graph
        for (int i = 0; i < inDegree.length; i++) {
            // no prerequisite course
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        // BFS
        while (!queue.isEmpty()) {
            // queue 里存的都是入度为 0 的课程
            int pre = queue.poll();
            count++;
            for (int[] pair : prerequisites) {
                // 遍历先修课程，也就是上完先修课程，依赖它的后面的课程入度就会减 1
                if (pair[1] == pre) {
                    inDegree[pair[0]]--;
                    // 如果后面这个课程的先修课程都已经上完了，就可以上这门课，入度为 0 就 offer 到 queue 中
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
        // 构建邻接图
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            graph.get(course).add(prerequisite);
        }

        // 已被其他 node 为起点的 DFS 访问设为 -1，已被当前 node 为起点的 DFS 访问设为 1，初始化 unvisited 为 0
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            // 判断 graph 是否有 cycle
            if (dfs(i, graph, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int curr, List<List<Integer>> graph, int[] visited) {
        // 说明在本轮 DFS 中该节点被第 2 次访问，即有 cycle
        if (visited[curr] == 1) return true;
        // 说明当前 node 已被其他 node 启动的 DFS 访问，无需再重复搜索
        // 因为我们已经知道从这个 node 出发是不会有 cycle 的
        if (visited[curr] == -1) return false;

        // 标记该 node 在本轮 DFS 被访问过，但本轮 DFS 还没有结束
        // 如果在本轮 DFS 还能碰到这个 node 并且标记是 1，说明形成了 cycle
        visited[curr] = 1;

        for (int next : graph.get(curr)) {
            // 如果发现 cycle 就返回 true 退出 function
            if (dfs(next, graph, visited)) {
                return true;
            }
        }

        // 当前 node 的所有 neighbors 已被遍历，没有 cycle，置为 -1，该轮 DFS 结束表明当前
        visited[curr] = -1;
        return false;
    }
 */

/*  BFS + HashMap

        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;
            if (graph.containsKey(prerequisites[i][1])) {
                graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(prerequisites[i][0]);
                graph.put(prerequisites[i][1], list);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            List<Integer> toTake = graph.get(cur);
            for (int i = 0; toTake != null && i < toTake.size(); i++) {
                inDegree[toTake.get(i)]--;
                if (inDegree[toTake.get(i)] == 0) {
                    queue.offer(toTake.get(i));
                }
            }
        }
        // 检查最后入度数组是否各 node 都为 0
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] != 0) {
                return false;
            }
        }
        return true;
 */