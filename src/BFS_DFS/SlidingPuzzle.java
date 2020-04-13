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
    public class Node {
        int[][] board;
        String boardstring;
        int zero_r;
        int zero_c;
        int depth;

        Node(int[][] B, int r, int c, int d) {
            board = B;
            boardstring = Arrays.deepToString(board);
            zero_r = r;
            zero_c = c;
            depth = d;
        }
    }

    public int slidingPuzzle(int[][] board) {
        int R = board.length, C = board[0].length;
        int sr = 0, sc = 0;
        // Search for the tile
        for (sr = 0; sr < R; sr++) {
            for (sc = 0; sc < C; sc++) {
                if (board[sr][sc] == 0) {
                    break;
                }
            }
        }
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<Node> queue = new ArrayDeque<>();
        Node start = new Node(board, sr, sc, 0);
        queue.offer(start);

        Set<String> seen = new HashSet<>();
        seen.add(start.boardstring);

        String target = Arrays.deepToString(new int[][] {{1, 2, 3}, {4, 5, 0}});

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.boardstring.equals(target)) {
                return node.depth;
            }

            for (int[] di : dirs) {
                int nei_r = di[0] + node.zero_r;
                int nei_c = di[1] + node.zero_c;

                if (nei_r < 0 || nei_c < 0 || nei_r >= R || nei_c >= C ||
                        (Math.abs(nei_r - node.zero_r) + Math.abs(nei_c - node.zero_c) != 1)) {
                    continue;
                }

                int[][] newboard = new int[R][C];
                int t = 0;
                for (int[] row : node.board) {
                    newboard[t++] = row.clone();
                }
                newboard[node.zero_r][node.zero_c] = newboard[nei_r][nei_c];
                newboard[nei_r][nei_c] = 0;

                Node nei = new Node(newboard, nei_r, nei_c, node.depth + 1);
                if (seen.contains(nei.boardstring)) {
                    continue;
                }
                queue.offer(nei);
                seen.add(nei.boardstring);
            }
        }
        return -1;
    }
}


/*  Optimization (A*)
    Use a string to represent a state
    pre-compute the transitions
 */