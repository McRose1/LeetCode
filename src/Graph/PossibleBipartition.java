package Graph;

/*  886. Possible Bipartition
    Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
    Each person may dislike some other people, and they should not go into the same group.
    Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
    Return true if and only if it is possible to split everyone into two groups in this way.

    Example 1:
    Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
    Output: true
    Explanation: group1 [1,4], group2 [2,3]

    Example 2:
    Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
    Output: false

    Example 3:
    Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
    Output: false

    Constraints:
        o 1 <= N <= 2000
        o 0 <= dislikes.length <= 10000
        o dislikes[i].length == 2
        o 1 <= dislikes[i][j] <= N
        o dislikes[i][0] < dislikes[i][1]
        o There does not exist i != j for which dislikes[i] == dislikes[j].
 */

import java.util.*;
/*  BFS: Time = O(N + E) Space = O(N + E)
    参考 #785
 */
public class PossibleBipartition {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] color = new int[N + 1];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] dislike : dislikes) {
            graph.get(dislike[0]).add(dislike[1]);
            graph.get(dislike[1]).add(dislike[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int idx = 1; idx <= N; idx++) {
            if (color[idx] == 0) {
                queue.offer(idx);
                color[idx] = 1;
            }
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    int neiColor = color[cur] == 1 ? 2 : 1;
                    List<Integer> dislikeList = graph.get(cur);
                    for (int dis : dislikeList) {
                        if (color[dis] == 0) {
                            color[dis] = neiColor;
                            queue.offer(dis);
                        } else if (color[dis] != neiColor) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}

/*  DFS: Time = O(N + E) Space = O(N + E)

    Map<Integer, List<Integer>> graph;
    int[] color;
    private boolean valid;

    public boolean possibleBipartition(int N, int[][] dislikes) {
        valid = true;
        graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : dislikes) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        color = new int[N + 1];
        for (int node = 1; node <= N && valid; node++) {
            if (color[node] == 0) {
                dfs(node, 1);
            }
        }
        return valid;
    }

    private void dfs(int node, int c) {
        color[node] = c;
        int neiColor = c == 1 ? 2 : 1;

        for (int nei : graph.get(node)) {
            if (color[nei] == 0) {
                dfs(nei, neiColor);
                if (!valid) {
                    return;
                }
            } else if (color[nei] != neiColor) {
                valid = false;
                return;
            }
        }
    }
 */