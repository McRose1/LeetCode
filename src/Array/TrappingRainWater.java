package Array;

/*  42. Trapping Rain Water
    Given an non-negative integers representing an elevation map where the width of each bar is 1,
    compute how much water it is able to trap after raining.

    Example:
    Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
 */

/*  Two Pointer

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

/*  Brute Force: Time = O(n^2) Space = O(1)
    the maximum level of water it can trap is equal to the minimum of maximum height of bars on both the sides minus its own height

        int ans = 0;
        int max_left = 0, max_right = 0;
        for (int i = 0; i < height.length; i++) {
            for (int l = i; l >= 0; l--) {
                max_left = Math.max(max_left, height[l])
            }
            for (int r = i; r < height.length; r++) {
                max_right = Math.max(max_right, height[r]);
            }
            ans += min(max_left, max_right) - height[i];
        }
        return ans;
 */

/*  DP: Time = O(n) Space = O(n)

        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            dp[i + 1] = Math.max(dp[i], height[i]);
        }
        int max = 0, ans = 0;
        for (int i = len - 1; i >=0; i--) {
            ans += Math.min(max, dp[i]) > height[i] ? Math.min(max, dp[i]) - height[i] : 0;
            max = Math.max(max, height[i]);
        }
        return ans;
 */

/*  Using stacks: Time = O(n) Space = O(n)
    Add the index of the bar to the stack if bar is smaller than or equal to the bar at top of stack
    pop the index and add result to ans if a bar longer than that at the top

        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.peek();
                stack.pop();
                if (stack.empty()) {
                    break;
                }
                int distance = i - stack.peek() - 1;
                int bounded_height= Math.min(height[i], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(i);
        }
        return ans;
 */
