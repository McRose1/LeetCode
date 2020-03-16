package Array;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    Note:
    nums.length will be between 1 and 50,000.
    nums[i] will be an integer between 0 and 49,999.
 */
/*  Left and Right Index: Time = O(n) Space = O(n)
    left: the index of its first occurrence
    right: the index of its last occurrence
    for each element that occurs the maximum number of times, right[x] - left[x] + 1 will be candidate answer
    take the minimum of those candidates
 */
public class DegreeofanArray {
    public int findShortestSubArray(int[] nums) {       // [1, 2, 2, 3, 1]
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];                            // x = 1; 2; 2; 3; 1
            if (left.get(x) == null) {
                // left 记录首个位置
                left.put(x, i);                         // {1, 0}; {{1, 0}, {2, 1}}; {{1, 0}, {2, 1}, {3, 3}}
            }
            // right 记录最后位置
            right.put(x, i);  // {1, 0}; {{1, 0}, {2, 1}}; {{1, 0}, {2, 2}}; {{1, 0},{2, 2}, {3, 3}}; {{1, 4}, {2, 2}, {3, 3}}
            count.put(x, count.getOrDefault(x, 0) + 1); // {1, 1}; {{1, 1}, {2, 1}}; {{1, 1}, {2, 2}}; {{1, 1}, {2, 2}, {3, 1}}; {{1, 2}, {2, 2}, {3, 1}}
        }

        int ans = nums.length;
        int degree = Collections.max(count.values());   // degree = 2
        for (int x : count.keySet()) {                  // {{1, 2}, {2, 2}, {3, 1}}
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);    // 4-0+1=5; 2-1+1=2
            }
        }
        return ans;
    }
}
