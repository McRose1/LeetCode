package BFS_DFS;

/*  773. Sliding Puzzle
    On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
    A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
    The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
    Given a puzzle board, return the least number of moves required so that the state of the board is solved.
    If it is impossible for the state of the board to be solved, return -1.

    Examples:
    Input: board = [[1,2,3],[4,0,5]]
    Output: 1
    Explanation: Swap the 0 and the 5 in one move.

    Input: board = [[1,2,3],[5,4,0]]
    Output: -1
    Explanation: No number of moves will make the board solved.

    Input: board = [[4,1,2],[5,0,3]]
    Output: 5
    Explanation: 5 is the smallest number of moves that solves the board.
    An example path:
    After move 0: [[4,1,2],[5,0,3]]
    After move 1: [[4,1,2],[0,5,3]]
    After move 2: [[0,1,2],[4,5,3]]
    After move 3: [[1,0,2],[4,5,3]]
    After move 4: [[1,2,0],[4,5,3]]
    After move 5: [[1,2,3],[4,5,0]]

    Input: board = [[3,2,4],[1,5,0]]
    Output: 14

    Note:
    board will be a 2 x 3 array as described above.
    board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

    Hint: Perform a BFS search, where the nodes are the puzzle boards and
    edges are if two puzzle boards can be transformed into one another with one move.
 */

import java.util.*;

/*  BFS: Time = O((n*m)!) Space = O((n*m)!)
    Expansion: swap 0 with its neighbors
    可以把这道题看成一个找出图中最短路径的问题：
    每个节点都是棋盘的一个状态，如果两个状态之间可以通过一步操作来完成转换，就用一条边将这两个节点相连。
    转换成用 BFS 来解决最短路径问题。
    需要将节点表示成可以哈希的数据结构，同时还需要找到每个节点的邻居节点。
    为了将节点表示成可以哈希的数据结构，在 Java 中，可以将棋盘转化成一个整数或者直接用 Arrays.deepToString
 */
public class SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        StringBuilder start = new StringBuilder();
        for (int[] ints : board) {
            for (int j = 0; j < board[0].length; j++) {
                start.append(ints[j]);           // "412503"
            }
        }
        Set<String> visited = new HashSet<>();
        // all the positions 0 can be swapped to
        int[][] dirs = new int[][] {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        Queue<String> queue = new LinkedList<>();
        queue.offer(start.toString());
        visited.add(start.toString());
        int res = 0;
        while (!queue.isEmpty()) {
            // 很关键的一步，需要先把 size 定死
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return res;
                }
                int zero = cur.indexOf('0');
                // swap if possible
                for (int dir : dirs[zero]) {
                    String next = swap(cur, zero, dir);
                    if (visited.contains(next)) {
                        continue;
                    }
                    visited.add(next);
                    queue.offer(next);
                }
            }
            res++;
        }
        return -1;
    }

    private String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(i, s.charAt(j));
        sb.setCharAt(j, s.charAt(i));
        return sb.toString();
    }
}


/*  Optimization (A*)
    Use a string to represent a state
    pre-compute the transitions
 */