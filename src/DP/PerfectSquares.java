package DP;

/*  279. Perfect Squares
    Given a positive integer n, find the least number of perfect square numbers
    (for example, 1, 4, 9, 16, ...) which sum to n.

    Example 1:
    Input: n = 12
    Output: 3

    Example 2:
    Input: n = 13
    Output: 2
 */

import java.util.Arrays;
/*  DP: Time = O(n * sqrt(n)) Space = O(n)
    dp[i] = min(dp[i], dp[i - j * j] + 1) (j * j <= i)
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // 初始化
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        // 1 -> n
        for (int i = 2; i <= n; i++) {
            // 1、4、9、16...
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}

/*  BFS (level by level)

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        int level = 0;
        queue.offer(n);

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                // 依次减 1，4，9... 生成下一层的节点
                for (int j = 1; j * j <= cur; j++) {
                    int next = cur - j * j;
                    if (next == 0) {
                        return level;
                    }
                    // 记录每一层可能生成的节点
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
        }
        return -1;
 */