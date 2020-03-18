package Array;

/*  42. Trapping Rain Water
    Given an non-negative integers representing an elevation map where the width of each bar is 1,
    compute how much water it is able to trap after raining.

    Example:
    Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
 */

/*  Two Pointer: Time = O(n) Space = O(1)
    if there is a larger bar at one end (say right),
    we are assured that the water trapped would be dependant on height of bar in current direction (from left to right).
    As soon as we find the bar at other end (right) is smaller, we start iterating in opposite direction (from right to left).
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        int leftmax = 0, rightmax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                leftmax = Math.max(height[left], leftmax);
                res += leftmax - height[left];
                left++;
            } else {
                rightmax = Math.max(height[right], rightmax);
                res += rightmax - height[right];
                right--;
            }
        }
        return res;
    }
}

/*  Brute Force（按列求）: Time = O(n^2) Space = O(1)
    the maximum level of water it can trap is equal to the minimum of maximum height of bars on both the sides minus its own height
    求每一列的水，我们只需要关注当前列，以及左边最高的墙，右边最高的墙就够了
    装水的多少，当然根据木桶效应，我们只需要看左边最高的墙和右边最高的墙中较矮的一个就够了
    根据较矮的那个墙和当前列的墙的高度可以分为三种情况：
    1. 较矮的墙的高度大于当前列的墙的高度
    2. 较矮的墙的高度小于当前列的墙的高度 -> 当前列没有水
    3. 较矮的墙的高度等于当前列的墙的高度 -> 当前列没有水

        int ans = 0;
        // 遍历每一列
        // 最两端的列不用考虑，因为一定不会有水，下标从 1 -> length-2
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            // 找到左边最高
            for (int l = i - 1; l >= 0; l--) {
                max_left = Math.max(max_left, height[l]);
            }
            int max_right = 0;
            // 找到右边最高
            for (int r = i + 1; r < height.length; r++) {
                max_right = Math.max(max_right, height[r]);
            }
            // 从左右两边最高中选最矮的
            int min = Math.min(max_left, max_right);
            // 只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]) {
                ans += (min - height[i]);
            }
        }
        return ans;
 */

/*  DP: Time = O(n) Space = O(n)
    In brute force, we iterate over the left and right parts again and again just to find the highest bar size upto that index.
    But, this could be stored using dp.
    max_left[i] 代表第 i 列左边最高的墙的高度
    max_right[i] 代表第 i 列右边最高的墙的高度

        if (height == null || height.length == 0) {
            return 0;
        }
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum += (min - height[i]);
            }
        }
        return sum;
 */

/*  Using stacks: Time = O(n) Space = O(n)
    Using the stack, we can do the calculations in only one iteration.
    Add the index of the bar to the stack if bar is smaller than or equal to the bar at top of stack
    pop the index and add result to ans if a bar longer than that at the top
    当遍历墙的高度的时候，如果当前高度小于栈顶的墙高度，说明这里会有积水，我们将墙的高度的下标入栈。
    如果当前高度大于栈顶的墙的高度，说明之前的积水到这里停下，我们可以计算下有多少积水了。计算完，就把当前的墙继续入栈，作为新的积水的墙。
    1. 当前高度小于等于栈顶高度，入栈，指针后移。
    2. 当前高度大于栈顶高度，出栈，计算出当前墙和栈顶的墙之间水的多少，然后计算当前的高度和新栈的高度的关系，重复第 2 步。
       直到当前墙的高度不大于栈顶高度或者栈空，然后把当前墙入栈，指针后移。

        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {                               //  [0,1,0,2,1,0,1,3,2,1,2,1]
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {  // height[1]=1>0; height[2]=0<1; height[3]=2>0
                int top = stack.pop();    // 0; 2
                if (stack.empty()) {
                    break;
                }
                int distance = i - stack.peek() - 1;        // 3-1-1=1
                int bounded_height= Math.min(height[i], height[stack.peek()]) - height[top];    // min(3, height[1])-height[2]=1-0=1
                ans += distance * bounded_height;   // ans = 1
            }
            stack.push(i);      // 1; 2
        }
        return ans;
 */
