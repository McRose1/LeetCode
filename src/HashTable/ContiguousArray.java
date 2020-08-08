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
    这个方法与前一个方法原理相同，但是我们不使用大小为 \text{2n+1}2n+1 的数组因为我们不可能遇到所有的值。
    因此我们只需要使用一个 HashMap map 来保存所有的 (index, count)(index,count) 对。
    对于一个 count ，我们只记录它第一次出现的位置，后面遇到相同的 count 我们都用这个第一次记录的 index 来计算子数组的长度。
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                max = Math.max(max, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return max;
    }
}

/*  Using Extra Array: Time = O(n) Space = O(n)
    我们从头开始遍历数组，在任何时候，如果 count 变成了 0 ，这表示我们从开头到当前位置遇到的 0 和 1 的数目一样多。
    如果我们在遍历数组的过程中遇到了相同的 count 2 次，这意味着这两个位置之间 0 和 1 的数目一样多。
    Thus, if we keep a track of the indices corresponding to the same count values that lie farthest apart,
    we can determine the size of the largest subarray with equal number of 0s and 1s.
    如果数组是全 0 或者全 1 的时候， count 值的可以达到 -n 和 n ，所以我们使用一个大小为 2n+1 的数组 arr ，以保存所有出现过的 count 值。
    We make an entry containing the current element's index (i) in the arr for a new count encountered everytime.
    Whenever we come across the same count, we determine the length of the subarray lying between the indices.

        // index(arr) -n ~ n: count；value: index(nums)
        int[] arr = new int[2 * nums.length + 1];
        // 初始化基准值，方便后面判断
        Arrays.fill(arr, -2);
        // count 为 0 设为 -1
        arr[nums.length] = -1;
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += (nums[i] == 1 ? 1 : -1);
            // 遇到相同 count 的不同 index，这 2 个 index 之间就是 0 1 数量相同
            if (arr[nums.length + count] >= -1) {
                max = Math.max(max, i - arr[nums.length + count]);
            } else {
                arr[nums.length + count] = i;
            }
        }
        return max;
 */