package DP;

/*  85. Maximal Rectangle
    Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

    Example:
    Input:
    [
      ["1","0","1","0","0"],
      ["1","0","1","1","1"],
      ["1","1","1","1","1"],
      ["1","0","0","1","0"]
    ]
    Output: 6
 */

import java.util.Stack;
//  Stack + DP（DP 是 my version）: Time = O(n^2) Space = O(n^2)->O(n)
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        // 初始化 DP 的 base case
        for (int c = 0; c < n; c++) {
            if (matrix[0][c] == '1') {
                dp[0][c] = 1;
            } else {
                dp[0][c] = 0;
            }
        }
        for (int r = 1; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == '1') {
                    dp[r][c] = dp[r - 1][c] + 1;
                } else {
                    dp[r][c] = 0;
                }
            }
        }
        int max = 0;
        for (int[] heights : dp) {
            max = Math.max(max, rowMax(heights));
        }
    /*  也可以用一维的数组实现
        int max = 0;
        // 会不断更新
        int[] heights = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            max = Math.max(max, rowMax(heights));
        }
     */
        return max;
    }

    private int rowMax(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        // 注意小于等于这里的等于很重要
        for (int i = 0; i <= heights.length; i++) {     // 2; 1; 5; 6; 2; 3
            int cur = (i == heights.length) ? -1 : heights[i];
            // 如果栈顶高度大于当前高度
            while (!stack.isEmpty() && cur <= heights[stack.peek()]) {
                // 保存栈顶元素信息
                int h = heights[stack.pop()];   // 2; 6; 5; 1
                // 如果栈已经为空，宽度为 i
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;     // 1; 4-2-1=1; 4-1-1=2; 3
                max = Math.max(max, h * w);     // 2*1=2; 6; 10
            }
            stack.push(i);  // 0; 1; 1,2; 1,2,3;
        }
        return max;
    }
}
