package Array;

/*  334. Increasing Triplet Subsequence
    Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array
    Formally the function should:
    Return true if there exists i, j, k
    such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.

    Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

    Example 1:
    Input: [1,2,3,4,5]
    Output: true

    Example 2:
    Input: [5,4,3,2,1]
    Output: false
 */

/*  Greedy: Time = O(n) Space = O(1)
    Keep two values when check all elements in the array:
    The minimum value min1 until now
    The second minimum value min2 from the minimum value's position until now
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur > min2) {
                return true;
            } else if (cur < min1) {
                min1 = cur;
            } else if (cur > min1 && cur < min2) {
                min2 = cur;
            }
        }
        return false;
    }
}
