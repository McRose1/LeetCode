package Array;

/*  330. Patching Array
    Given a sorted positive integer array nums and an integer n, add/patch elements to the array
    such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array.
    Return the minimum number of patches required.

    Example 1:
    Input: nums = [1,3], n = 6
    Output: 1
    Explanation:
    Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
    Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
    Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
    So we only need 1 patch.

    Example 2:
    Input: nums = [1,5,10], n = 20
    Output: 2
    Explanation: The two patches can be [2, 4].

    Example 3:
    Input: nums = [1,2,2], n = 5
    Output: 0
 */

/*  Time = O(n) Space = O(1)
    miss: 表示[0, n] 之间最小的不能表示的值
    if num <= miss -> [0, miss + num]
    else -> add miss itself
    Example: [1, 2, 5, 13, 24], n = 50
    1: miss = 2
    2 <= miss: miss = 4
    we should add 4, then miss = 8
    5: miss = 13
    13 <= miss: miss = 26
    24 <= miss: miss = 50, we should add 50 itself
 */
public class PatchingArray {
    public int minPatches(int[] nums, int n) {
        int i = 0, res = 0;
        long miss = 1;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                res++;
            }
        }
        return res;
    }
}
