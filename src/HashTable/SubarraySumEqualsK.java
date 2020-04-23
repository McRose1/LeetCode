package HashTable;

import java.util.HashMap;
import java.util.Map;

/*  560. Subarray Sum Equals K
    Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

    Example 1:
    Input:nums = [1,1,1], k = 2
    Output: 2

    Note:
    The length of the array is in range [1, 20,000].
    The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
/*  HashMap: Time = O(n) Space = O(n)
    <preSum(i), Num.of occurrences of preSum(i)>
    让 i 从左往右遍历，每次找出在 <0, i> 这个区间内subarray相加等于k的数量: preSum(i) - preSum(j(0->i-1)) == k
    所以我们可以把每一种可能的preSum作为key，当我们知道preSum(i)是多少时，寻找所有preSum(j) = preSum(i) - k，这意味着i-j这一段区间的总和为k
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}

/*  my version: Time = O(n^2) Space = O(1)

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int idx = i;
            int sum = 0;
            while (idx < nums.length) {
                sum += nums[idx];
                if (sum == k) {
                    count++;
                }
                idx++;
            }
        }
        return count;
 */

/*  Using Cumulative Sum: Time = O(n^2) Space = O(n)

        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k) {
                    count++;
                }
            }
        }
        return count;
 */
