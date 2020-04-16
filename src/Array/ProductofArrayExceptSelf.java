package Array;

/*  238. Product of Array Except Self
    Given an array nums of n integers where n > 1,
    return an array output such that output[i] is equal to the product of all the elements of nums[i] except nums[i].

    Example:
    Input:  [1,2,3,4]
    Output: [24,12,8,6]

    Note: Please solve it without division and in O(n).
    Follow up:
    Could you solve it with constant space complexity?
    (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

/*  No additional array: Time = O(n) Space = O(1)
    分为左边和右边，相当于求 i 左边所有元素的乘积 * i 右边所有元素的乘积
    [1,2,3,4] -> left: [1,1,2,6] right: 1,4,12,24 乘的时候从右往左
 */
 public class ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        // Final result array to be returned
        int[] res = new int[n];

        // res[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        // left to right
        res[0] = 1;
        for (int i = 1; i < n; i++) {

            // res[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            res[i] = nums[i - 1] * res[i - 1];
        }

        // right contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the right would be 1
        // right to left
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {

            // For the index 'i', right would contain the
            // product of all elements to the right. We update right accordingly
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }
}

/*  Left and Right product lists: Time = O(n) Space = O(n)

        int n = nums.length;

        int[] left = new int[n];
        int[] right = new int[n];

        int[] res = new int[n];

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        for (int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
 */
