package Stack;

/*  84. Largest Rectangle Histogram
    Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
    find the area of largest rectangle in the histogram.

    Example:
    Input: [2,1,5,6,2,3]
    Output: 10
 */

import java.util.Stack;

/*  单调栈: Time = O(n) Space = O(n)
    push 之前先把大于等于自己的元素 pop 出来
    每次从栈中 pop 出一个数的时候，就找到了往左数比它小的第一个数（当前栈顶）和往右数比它小的第一个数（即将入栈的数）
 */
public class LargestRectangleinHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();   // 维护单调递增
        int max = 0;
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
