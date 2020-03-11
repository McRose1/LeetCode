/*
    Given a binary array, find the maximum number of consecutive 1s in this array.

    Example 1:
    Input: [1,1,0,1,1,1]
    Output: 3

    Note:
    The input array will only contain 0 and 1.
    The length of input array is a positive integer and will not exceed 10,000
 */

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[cur] == 1) {
                cur++;
                count++;
            } else {
                cur++;
                max = Math.max(count, max);
                count = 0;
            }
        }
        max = Math.max(count, max);
        return max;
    }
}
