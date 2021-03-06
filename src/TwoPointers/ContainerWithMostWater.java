package TwoPointers;

/*  11. Container With Most Water
    Given n non-negative integers a1,a2,...,an, where each represents a point at coordinate(i,ai).
    n vertical lines are drawn such that the two endpoints of line i is at (i,ai) and (i, 0).
    Find two lines, which together with x-axis forms a container, such that the container contains the most water.

    Note: You may not slant the container and n is at least 2.

    Example:
    Input: [1,8,6,2,5,4,8,3,7]
    Output: 49

    Hint 1:
    Area = length of shorter vertical line * distance between lines
    Hint 2:
    Start with the maximum width container and go to a shorter container if there is a vertical line longer than the
    current containers shorter line.
 */

/*  Two pointers: Time = O(n) single pass Space = O(1)
    one at the beginning, one at the end.
    Since it is limited by the shorter line, moving the shorter line's pointer could turn out to be beneficial
    每次向内移动较短边
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            // record the current max area
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            // move the shorter line to increase the max area, since the width will decrease and maxArea = width * minHeight
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}

/*  Brute Force: Time = O(n^2) Space = O(1)
    遍历左右边，找出所有面积，取最大

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxArea;
 */