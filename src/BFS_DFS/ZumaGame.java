package BFS_DFS;

/*  488. Zuma Game
    Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W).
    You also have several balls in your hand.
    Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost space).
    Then, if there is a group of 3 or more balls in the same color touching, remove these balls.
    Keep doing this until no more balls can be removed.
    Find the minimal balls you have to insert to remove all the balls on the table.
    If you cannot remove all the balls, output -1.

    Example 1:
    Input: board = "WRRBBW", hand = "RB"
    Output: -1
    Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

    Example 2:
    Input: board = "WWRRBBWW", hand = "WRBRW"
    Output: 2
    Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

    Example 3:
    Input: board = "G", hand = "GGGGG"
    Output: 2
    Explanation: G -> G[G] -> GG[G] -> empty

    Example 4:
    Input: board = "RBYYBBRRB", hand = "YRBGB"
    Output: 3
    Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty

    Constraints:
       o You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
       o 1 <= board.length <= 16
       o 1 <= hand.length <= 5
       o Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
 */

/*  DFS
    Pruning: only insert balls after the one(s) with the same color in order to make 3 balls in a row.
 */
public class ZumaGame {
    public int findMinStep(String board, String hand) {
        handCount = new int[26];
        for (int i = 0; i < hand.length(); i++) {
            handCount[hand.charAt(i) - 'A']++;
        }
        dfs(new StringBuilder(board), 0);
        return res == 6 ? -1 : res;
    }

    private int[] handCount;
    private int res = 6;
    private char[] colors = new char[] {'R', 'Y', 'B', 'G', 'W'};

    private void dfs(StringBuilder sb, int step) {
        if (step >= res) {
            return;
        }
        if (sb.length() == 0) {
            res = Math.min(res, step);
            return;
        }
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int j = i;
            while (j + 1 < sb.length() && sb.charAt(j + 1) == c) {
                j++;
            }
            // 只有单个球
            if (j == i && handCount[c - 'A'] >= 2) {
                StringBuilder temp = new StringBuilder(sb);
                temp.insert(i, c + "" + c);
                handCount[c - 'A'] -= 2;
                dfs(remove(temp), step + 2);
                // backtrack
                handCount[c - 'A'] += 2;
            }
            // 存在 2 个颜色相同且相邻的球
            else if (j == i + 1) {
                if (handCount[c - 'A'] >= 1) {
                    StringBuilder temp = new StringBuilder(sb);
                    temp.insert(i, c);
                    handCount[c - 'A']--;
                    dfs(remove(temp), step + 1);
                    // backtrack
                    handCount[c - 'A']++;
                }
                for (char color : colors) {
                    if (color == c) {
                        continue;
                    }
                    if (handCount[color - 'A'] >= 1) {
                        StringBuilder temp = new StringBuilder(sb);
                        // 尝试往这 2 个颜色相同且相邻的球中间插入一个颜色不同的球
                        temp.insert(i + 1, color);
                        handCount[color - 'A']--;
                        dfs(remove(temp), step + 1);
                        // backtrack
                        handCount[color - 'A']++;
                    }
                }
            }
        }
    }

    // remove consecutive balls longer than 3
    private StringBuilder remove(StringBuilder sb) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < sb.length(); i++) {
                int j = i + 1;
                while (j < sb.length() && sb.charAt(j) == sb.charAt(i)) {
                    j++;
                }
                if (j - i >= 3) {
                    sb.delete(i, j);
                    flag = true;
                }
            }
        }
        return sb;
    }
}
