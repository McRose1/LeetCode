package Array;

/*  31. Next Permutation
    Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
    If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
    The replacement must be in-place and use only constant extra memory.
    Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1
 */

/*  Single Pass Approach: Time = O(n) Space = O(1)
    First, we observe that for any given sequence that is in descending order, no next larger permutation is possible.
    For example, no next permutation is possible for: [9,5,4,3,1]
    We need to find the first pair of two successive numbers a[i] and a[i-1] from the right, which satisfy a[i]>a[i-1].
    Now, no rearrangements to the right of a[i-1] can create a larger permutation since that subarray consists of numbers in descending order.
    We need to replace the number a[i-1] with the number which is just larger than itself among the numbers lying to its right section, say a[j].
    We swap the numbers a[i-1] and a[j]. Swapping a[i-1] and a[j] didn't change the descending order.
    Lexicographically:
    3 5 8 7 6 4
    1. 从原排列中，从右至左，找到第一个左邻小于右邻的字符，记左邻位置为 a -> a = 1, list[a] = 5.
    2. 重新从右至左，找到第一个比 list[a] 大的字符，记为位置 b -> b = 4, list[b] = 6.
    3. 交换 a 和 b 两个位置的值 -> 3 6 8 7 5 4
    4. 将 a 后面的数，由小到大排列 -> 3 6 4 5 7 8

    3 5 8 7 6 4
      ^
    3 5 8 7 6 4
            ^
    3 6 8 7 5 4
      ^     ^
    3 6 4 5 7 8
        ^ ^ ^ ^
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {       // 123; 321
        int i = nums.length - 2;                    // i = 1; 1
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;                                              // i = -1
        }
        if (i >= 0) {
            int j = nums.length - 1;                // j = 2
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);                       // swap(2, 3)
        }
        reverse(nums, i + 1);   // swap(321, 0)
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*  my version:

    public void nextPermutation(int[] nums) {
        int target = -1;
        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i - 1] < nums[i]) {
                target = i - 1;
                break;
            }
        }
        // 如果从右往左能找到左边比右边大的数，进入循环；否则直接 reverse 整个数组
        if (target != -1) {
            for (int i = nums.length - 1; i > target; i--) {
                if (nums[i] > nums[target]) {
                    int temp = nums[target];
                    nums[target] = nums[i];
                    nums[i] = temp;
                    break;
                }
            }
        }
        reverse(nums, target + 1);
        return;
    }

    private void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

 */