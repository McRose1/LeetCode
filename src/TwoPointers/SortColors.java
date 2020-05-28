package TwoPointers;

/*  75. Sort Colors
    Given an array with n objects colored red, white or blue,
    sort them in-place so that objects of the same color are adjacent,
    with the colors in the order red, white and blue.
    Here, we will use the integers 0, 1, and 2 to represent the color red, white and blue respectively.

    Note: You are not suppose to use the library's sort function for this problem.

    Example:
    Input: [2,0,2,1,1,0]
    Output: [0,0,1,1,2,2]

    Follow up:
    A rather straight forward solution is a two-pass algorithm using counting sort.
    First, iterate the array counting number of 0's, 1's, and 2's,
    then overwrite array with total number of 0's, then 1's and followed by 2's.

    Could you come up with a one-pass algorithm using only constant space?
 */

/*  Two Pointers: Time = O(n) Space = O(1)
    [0, i): a
    [i, j): b
    [j, k]: unchecked elements
    (k, len - 1]: c
 */
public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = 0, j = 0, k = nums.length - 1;

        // 注意不要忘记等于
        while (j <= k) {
            // j 所在位置是红色
            if (nums[j] == 0) {
                swap(nums, i++, j++);
            }
            // j 所在位置是白色
            else if (nums[j] == 1) {
                j++;
            }
            // j 所在位置是蓝色
            else {
                // 只能保证 k 所在位置现在为蓝色，但是 j 所在位置不能确定，所以还得再判断一次
                swap(nums, j, k--);
            }
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

/*  two-pass algorithm using counting sort: Time = O(n) Space = O(1)

        int redCnt = 0;
        int whiteCnt = 0;
        int blueCnt = 0;
        for (int num : nums) {
            if (num == 0) {
                redCnt++;
            } else if (num == 1) {
                whiteCnt++;
            } else if (num == 2) {
                blueCnt++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (redCnt-- > 0) {
                nums[i] = 0;
            }
            else if (whiteCnt-- > 0) {
                nums[i] = 1;
            }
            else if (blueCnt-- > 0) {
                nums[i] = 2;
            }
        }
 */