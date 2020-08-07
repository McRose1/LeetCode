package DP;

/*  1349. Maximum Students Taking Exam
    Given a m * n matrix seats that represent seats distributions in a classroom.
    If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.

    Students can see answers of those sitting next to the left, right, upper left and upper right,
    but he cannot see the answers of the student sitting directly in front or behind him.
    Return the maximum number of students that can take the exam together without any cheating being possible.
    Students must be placed in seats in good condition.

    Example 1:
    Input: seats = [["#",".","#","#",".","#"],
                    [".","#","#","#","#","."],
                    ["#",".","#","#",".","#"]]
    Output: 4
    Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam.

    Example 2:
    Input: seats = [[".","#"],
                    ["#","#"],
                    ["#","."],
                    ["#","#"],
                    [".","#"]]
    Output: 3
    Explanation: Place all students in available seats.

    Example 3:
    Input: seats = [["#",".",".",".","#"],
                    [".","#",".","#","."],
                    [".",".","#",".","."],
                    [".","#",".","#","."],
                    ["#",".",".",".","#"]]
    Output: 10
    Explanation: Place students in available seats in column 1, 3 and 5.

    Constraints:
        o seats contains only characters '.' and'#'.
        o m == seats.length
        o n == seats[i].length
        o 1 <= m <= 8
        o 1 <= n <= 8

    Hint:
    1. Students in row i only can see exams in row i+1.
    2. Use DP to compute the result given a (current row, bitmask people seated in previous row).
 */

/*  DP: Time = O(n^2) Space = O(n^2)

 */
public class MaximumStudentsTakingExam {
    public int maxStudents(char[][] seats) {
        int n = seats.length, m = seats[0].length;
        int[][] dp = new int[n + 1][1 << m];
        for (int i = n - 1; i >= 0; i--) {
            for (int preMask = 0; preMask < (1 << m); preMask++) {
                int res = 0;
                for (int curMask = 0; curMask < (1 << m); curMask++) {
                    if (isValid(curMask, preMask, seats, i)) {
                        res = Math.max(res, Integer.bitCount(curMask) + dp[i + 1][curMask]);
                    }
                }
                dp[i][preMask] = res;
            }
        }
        return dp[0][0];
    }

    private boolean isValid(int mask, int preMask, char[][] seats, int r) {
        int m = seats[0].length;
        for (int i = 0; i < m; i++) {
            if ((mask & (1 << i)) == 0) {
                continue;
            }
            if (seats[r][i] == '#') {
                return false;
            }
            // 4 个方向
            if (i > 0 && seats[r][i - 1] == '.' && (mask & (1 << (i - 1))) != 0) {
                return false;
            }
            if (i < m - 1 && seats[r][i + 1] == '.' && (mask & (1 << (i + 1))) != 0) {
                return false;
            }
            if (r > 0 && i > 0 && seats[r - 1][i - 1] == '.' && (preMask & (1 << (i - 1))) != 0) {
                return false;
            }
            if (r > 0 && i < m - 1 && seats[r - 1][i + 1] == '.' && (preMask & (1 << (i + 1))) != 0) {
                return false;
            }
        }
        return true;
    }
}

/*  Recursion with Memoization: Time = O(n^2) Space = O(n^2)

    private Integer[][] memo;
    private int n;
    private int m;

    public int maxStudents(char[][] seats) {
        n = seats.length;
        m = seats[0].length;
        memo = new Integer[n][1 << m];
        return dfs(seats, 0, 0);
    }

    private int dfs(char[][] seats, int r, int preMask) {
        if (r == n) {
            return 0;
        }
        if (memo[r][preMask] != null) {
            return memo[r][preMask];
        }
        int res = 0;
        for (int i = 0; i < (1 << m); i++) {
            if (isValid(i, preMask, seats, r)) {
                res = Math.max(res, Integer.bitCount(i) + dfs(seats, r + 1, i));
            }
        }
        memo[r][preMask] = res;
        return res;
    }

    private boolean isValid(int mask, int preMask, char[][] seats, int r) {
        int m = seats[0].length;
        for (int i = 0; i < m; i++) {
            if ((mask & (1 << i)) == 0) {
                continue;
            }
            if (seats[r][i] == '#') {
                return false;
            }
            // 4 个方向
            if (i > 0 && seats[r][i - 1] == '.' && (mask & (1 << (i - 1))) != 0) {
                return false;
            }
            if (i < m - 1 && seats[r][i + 1] == '.' && (mask & (1 << (i + 1))) != 0) {
                return false;
            }
            if (r > 0 && i > 0 && seats[r - 1][i - 1] == '.' && (preMask & (1 << (i - 1))) != 0) {
                return false;
            }
            if (r > 0 && i < m - 1 && seats[r - 1][i + 1] == '.' && (preMask & (1 << (i + 1))) != 0) {
                return false;
            }
        }
        return true;
    }
 */
