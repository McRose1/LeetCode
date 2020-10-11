package Array;

/*  FindPivotIndex
    Given an array of integers nums, write a method that returns the "pivot" index of this array.
    We define the pivot index as the index where the sum of all the numbers to the left of the index is
    equal to the sum of all the numbers to the right of the index.
    If no such index exists, we should return -1.
    If there are multiple pivot indexes, you should return the left-most pivot index.

    Example 1:
    Input: nums = [1,7,3,6,5,6]
    Output: 3
    Explanation:
    The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
    Also, 3 is the first index where this occurs.

    Example 2:
    Input: nums = [1,2,3]
    Output: -1
    Explanation:
    There is no index that satisfies the conditions in the problem statement.

    Constraints:
        o The length of nums will be in the range [0, 10000].
        o Each element nums[i] will be an integer in the range [-1000, 1000].

    Hint: We can precompute prefix sums P[i] = nums[0] + nums[1] + ... + nums[i - 1].
    Then for each index, the left sum is P[i], and the right sum is P[P.length - 1] - P[i] - nums[i].
 */

/*  Prefix Sum: Time = O(n) Space = O(n)

 */
public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < n; i++) {
            if (preSum[i] == preSum[n] - preSum[i] - nums[i]) {
                return i;
            }
        }
        return -1;
    }
}

/*  LC: Time = O(n) Space = O(1)

        int sum = 0;
        int leftSum = 0;
        for (int n : nums) {
            sum += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == sum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
 */