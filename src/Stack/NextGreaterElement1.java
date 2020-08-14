package Stack;

/*  496. Next Greater Element 1
    You are given two arrays (without duplicates) nums1 and nums2 where nums1's element are subset of nums2.
    Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
    The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
    If it does not exist, output -1 for this number.

    Example 1:
    Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
    Output: [-1,3,-1]
    Explanation:
        For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
        For number 1 in the first array, the next greater number for it in the second array is 3.
        For number 2 in the first array, there is no next greater number for it in the second array, so output -1.

    Example 2:
    Input: nums1 = [2,4], nums2 = [1,2,3,4].
    Output: [3,-1]
    Explanation:
        For number 2 in the first array, the next greater number for it in the second array is 3.
        For number 4 in the first array, there is no next greater number for it in the second array, so output -1.

    Note:
    1. All elements in nums1 and nums2 are unique.
    2. The length of both nums1 and nums2 would not exceed 1000.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/*  Monotonic Stack: Time = O(n) Space = O(n)
    对 nums2 维护单调递减栈
 */
public class NextGreaterElement1 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                res[i] = map.get(nums1[i]);
            }
        }
        return res;
    }
}

/*  HashMap: Time = O(n^2) Space = O(n)

        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        for (int i = 0; i < nums1.length; i++) {
            int j = map.get(nums1[i]) + 1;
            for (; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }
        return res;
 */

/*  Brute Force: Time = O(n^2) Space = O(1)

        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums1.length; i++) {
            int target = nums1[i];
            int start = 0;
            while (nums2[start] != target) {
                start++;
            }
            for (int j = start + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }
        return res;
 */