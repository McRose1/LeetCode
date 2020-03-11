package Array;

/*  189. Rotate Array
    Given an array, rotate the array yo the right by k steps, where k is non-negative.

    Example 1:
    Input: [1,2,3,4,5,6,7] and k = 3
    Output: [5,6,7,1,2,3,4]

    Example 2:
    Input: [-1,-100,3,99] and k = 2
    Output: [3,99,-1,-100]

    Note:
        Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
        Could you do it in-place with O(1) extra space?
 */
/*
    Using Reverse
    Original List                   : 1 2 3 4 5 6 7
    After reversing all numbers     : 7 6 5 4 3 2 1
    After reversing first k numbers : 5 6 7 4 3 2 1
    After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result

    Time complexity: O(n)
    Space complexity: O(1)
 */
import java.util.Queue;
/*
public class RotateArray {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k -  1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
*/
/*
    Using Extra Array

        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }

    Time = O(n). One pass is used to put the numbers in the new array. And another pass to copy the new array to the original one.
    Space = O(n).
 */

/*
    Using Cyclic Replacements
    */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
}
/*
    Algorithm:
    把被替换掉的 element 存在 temp 中
    把 temp 放在它正确的位置上，做 n 遍（n 是数组的长度）
    但是，例如 [-1,-100,3,99] and k = 2: -1 -> 3 -> -1 -> 3 会进行无限次循环
    所以 when we hit the original number's index again, we start the same process with (the number following it).
 */
