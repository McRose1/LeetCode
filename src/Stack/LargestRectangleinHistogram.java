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
    在这个范围内的柱子都是大于等于当前 pop 出来的柱子高度，所以我们能用当前柱子高度作为最大高度
 */
public class LargestRectangleinHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        // 维护一个单调递增 stack
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

/*  Stack Version 2

        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        // 输出为 [1] 这种情况适用，或者输入的就是单调递增数组
        while (stack.peek() != -1) {
            max = Math.max(max, heights[stack.pop()] * (n - stack.peek() - 1));
        }
        return max;
 */

/*  Brute Force: Time = O(n^2) Space = O(n)
    在高度固定的情况下，遍历每一个柱形，来选择最大宽度

        HashSet<Integer> set = new HashSet<>();
        // 得到所有的高度，通过 set 去重
        for (int i = 0; i < heights.length; i++) {
            set.add(heights[i]);
        }
        int max = 0;
        // 遍历每一个高度
        for (int h : set) {
            int width = 0;
            int maxWidth = 1;
            // 找出连续的大于等于当前高度的柱形个数的最大值
            for (int i = 0; i < heights.length; i++) {
                if (heights[i] >= h) {
                    width++;
                }
                // 出现小于当前高度就归零，并且更新最大宽度
                else {
                    maxWidth = Math.max(width, maxWidth);
                    width = 0;
                }
            }
            maxWidth = Math.max(width, maxWidth);
            // 更新最大区域的面积
            max = Math.max(max, h * maxWidth);
        }
        return max;

 */
