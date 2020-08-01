package Backtracking;

/*  351. Android Unlock Patterns
    Given an Android 3x3 key lock screen and two integers m and n, where 1 <= m <= n <= 9,
    count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

    Rules for a valid pattern:
        1. Each pattern must connect at least m keys and at most n keys.
        2. All the keys must be distinct.
        3. If the line connecting two consecutive keys in the pattern passes through any other keys,
           the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
        4. The order of keys used matters.

    Explanation:
    | 1 | 2 | 3 |
    | 4 | 5 | 6 |
    | 7 | 8 | 9 |

    Invalid move: 4 - 1 - 3 - 6
    Line 1 - 3 passes through key 2 which had not been selected in the pattern.

    Invalid move: 4 - 1 - 9 - 2
    Line 1 - 9 passes through key 5 which had not been selected in the pattern.

    Valid move: 2 - 4 - 1 - 3 - 6
    Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

    Valid move: 6 - 5 - 4 - 1 - 9 - 2
    Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

    Example:
    Input: m = 1, n = 1
    Output: 9
 */

/*  Backtracking

 */
public class AndroidUnlockPatterns {
    public int numberOfPatterns(int m, int n) {
        int count = 0;
        for (int i = m; i <= n; i++) {
            count += dfs(-1, i, new boolean[9]);
        }
        return count;
    }

    private int dfs(int preKey, int num, boolean[] visited) {
        if (num == 0) {
            return 1;
        }
        int path = 0;
        for (int i = 0; i < 9; i++) {
            if (!visited[i] && valid(preKey, i, visited)) {
                visited[i] = true;
                path += dfs(i, num - 1, visited);
                visited[i] = false;
            }
        }
        return path;
    }

    private boolean valid(int preKey, int i, boolean[] visited) {
        if (preKey == -1) {
            return true;
        }
        // adjacent
        if ((preKey + i) % 2 == 1) {
            return true;
        }
        // diagonal
        if (Math.abs(preKey / 3 - i / 3) == 1 && Math.abs(preKey % 3 - i % 3) == 1) {
            return true;
        }
        // Rule 3：1 -> 3 并且 2 已经 visited
        return visited[(preKey + i) / 2];
    }
}
