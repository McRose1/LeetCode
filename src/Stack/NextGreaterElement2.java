package Stack;

/*  503. Next Greater Element 2
    Given a circular array (the next element of the last element is the first element of the array),
    print the Next Greater Number for every element.
    The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
    which means you could search circularly to find its next greater number.
    If it doesn't exist, output -1 for this number.

    Example:
    Input: [1,2,1]
    Output: [2,-1,2]
    Explanation: The first 1's next greater number is 2;
    The number 2 can't find next greater number;
    The second 1's next greater number needs to search circularly, which is also 2.

    Note: The length of given array won't exceed 10000.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/*  Monotonic Stack: Time = O(n) Space = O(n)
    [1, 2, 1] -> [1, 2, 1, 1, 2, 1]
 */
public class NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }
}

/*  Laioffer: Time = O(n) Space = O(1)

        int n = nums.length;
        int[] res = new int[n];
        for (int i = 2 * n - 2; i >= 0; i--) {
            res[i % n] = -1;
            for (int j = i + 1; j < 2 * n - 1; ) {
                if (nums[j % n] > nums[i % n]) {
                    res[i % n] = j % n;
                    break;
                } else if (res[j % n] == -1) {
                    break;
                } else {
                    j = res[j % n];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (res[i] < -1) {
                res[i] = nums[res[i]];
            }
        }
        return res;
 */

/*  Stack（LC）

        int n = nums.length;
        int[] res = new int[n];
        // 存的是 index
        Stack<Integer> stack = new Stack<>();

        // 从右到左
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i % n] >= nums[stack.peek()]) {
                stack.pop();
            }
            res[i % n] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i % n);
        }
        return res;
 */

/*  Brute Force

        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (nums[(i + j) % n] > nums[i]) {
                    res[i] = nums[(i + j) % n];
                    break;
                }
            }
        }
        return res;
 */
