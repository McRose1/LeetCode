package HashTable;

/*  697. Degree of an Array
    Given a non-empty array of non-negative integers nums,
    the degree of this array is defined as the maximum frequency of any one of its elements.
    Your task is to find the smallest possible length of a (contiguous) subarray of nums,
    that has the same degree as nums.

    Example 1:
    Input: [1, 2, 2, 3, 1]
    Output: 2
    Explanation:
    The input array has a degree of 2 because both elements 1 and 2 appear twice.
    Of the subarrays that have the same degree:
    [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
    The shortest length is 2. So return 2.

    Example 2:
    Input: [1,2,2,3,1,4,2]
    Output: 6

    Constraints:
        o nums.length will be between 1 and 50,000.
        o nums[i] will be an integer between 0 and 49,999.

    Hint:
    Say 5 is the only element that occurs the most number of times - for example, nums = [1, 5, 2, 3, 5, 4, 5, 6].
    What is the answer?
 */
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*  HashMap: Time = O(n) Space = O(n)
    left: the index of its first occurrence
    right: the index of its last occurrence
    for each element that occurs the maximum number of times, right[x] - left[x] + 1 will be candidate answer
    take the minimum of those candidates
 */
public class DegreeofanArray {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            // left 记录首个位置
            left.putIfAbsent(x, i);
            // right 记录最后位置
            right.put(x, i);
            // 记录每个数字的出现次数
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        int min = nums.length;
        // 找出最大的出现次数
        int degree = Collections.max(count.values());
        for (int x : count.keySet()) {
            // 所有等于最大次数的都是 candidates，在其中我们要选出最短的 subarray
            if (count.get(x) == degree) {
                min = Math.min(min, right.get(x) - left.get(x) + 1);
            }
        }
        return min;
    }
}
