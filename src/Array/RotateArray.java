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

/*  Using Reverse: Time = O(n) Space = O(1)
    Original List                   : 1 2 3 4 5 6 7
    After reversing all numbers     : 7 6 5 4 3 2 1
    After reversing first k numbers : 5 6 7 4 3 2 1
    After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
 */

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

/*  Using Extra Array: Time = O(n) Space = O(n)

        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
 */

/*  Using Cyclic Replacements: Time = O(n) Space = O(1)
    把被替换掉的 element 存在 temp 中
    把 temp 放在它正确的位置上，做 n 遍（n 是数组的长度）
    但是，例如 [-1,-100,3,99] and k = 2: -1 -> 3 -> -1 -> 3 会进行无限次循环
    所以 when we hit the original number's index again, we start the same process with (the number following it).
                                                                // [1,2,3,4,5,6,7] -> [5,6,7,1,2,3,4]
        k = k % nums.length;                                    // k = 3 % 7 = 3
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int curIdx = start;                                // current = 0;
            int prev = nums[start];                             // prev = 1
            do {
                int nextIdx = (current + k) % nums.length; // nextIdx = (0+3)%7 = 3; (3+3)%7 = 6; (6+3)%7 = 2; (2+3)%7 = 5; (5+3)%7 = 1; (1+3)%7 = 4; (4+3)%7 = 0
                int temp = nums[nextIdx];                  // temp = 4; 7; 3; 6; 2; 5; 1
                nums[nextIdx] = prev;                      // nums[3] = 1; nums[6] = 4; nums[2] = 7; nums[5] = 3; nums[1] = 6; nums[4] = 2; nums[0] = 5
                prev = temp;                              // prev = 4; 7; 3; 6; 2; 5; 1
                curIdx = nextIdx;                         // curIdx = 3; 6; 2; 5; 1; 4; 0
                count++;                                  // count = 1; 2; 3; 4; 5; 6; 7
            } while (start != curIdx);                                              // start == curIdx
        }
 */