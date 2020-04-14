package HashTable;

/*  525. Contiguous Array
    Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

    Example 1:
    Input: [0,1]
    Output: 2
    Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

    Example 2:
    Input: [0,1,0]
    Output: 2
    Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

    Note: The length of the given binary array will not exceed 50,000.
 */

import java.util.HashMap;
import java.util.Map;

/*  HashMap: Time = O(n) Space = O(n)
    <count, index>, encounter 0 -> -1; encounter 1 -> +1
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            // [0, 1]
            if (count == 0) {
                max = i + 1;
            } else if (map.containsKey(count)) {
                max = Math.max(max, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return max;
    }
}

/*  Using Extra Array: Time = O(n) Space = O(n)
    index(arr): count / value: index(nums)
    If we encounter the same count twice while traversing the array,
    it means that the number of zeros and ones are equal between the indices corresponding to the equal count values.
    Thus, if we keep a track of the indices corresponding to the same count values that lie farthest apart,
    we can determine the size of the largest subarray with equal number of 0s and 1s.
    Note: the count values can range between -n to n, so we make use of an array (of size 2n+1) to keep track of various count's encountered so far.
    We make an entry containing the current element's index (i) in the arr for a new count encountered everytime.
    Whenever we come across the same count, we determine the length of the subarray lying between hte indices.

        int[] arr = new int[2 * nums.length + 1];
        // 初始化基准值，方便后面判断
        Arrays.fill(arr, -1);
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (count == 0) {
                max = i + 1;
            // 如果该 index 的值不为初始值，说明已经出现了相同的 count
            } else if (arr[count + nums.length] != -1) {
                max = Math.max(max, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }
        }
        return max;
 */