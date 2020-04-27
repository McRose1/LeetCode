package Sort;

/*  493. Reverse Pairs
    Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
    You need to return the number of important reverse pairs in the given array.

    Example 1:
    Input: [1,3,2,3,1]
    Output: 2

    Example 2:
    Input: [2,4,3,5,1]
    Output: 3

    Note:
    The length of the given array will not exceed 50,000.
    All the numbers in the input array are in the range of 32-bit integer.
 */

/*  Merged Sort: Time = O(nlogn) Space = O(n)
    在归并排序的同时求出 reverse pair 的数量
 */
public class ReversePairs {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        return mergeSort(nums, 0, n - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int res = 0;
        int mid = left + (right - left) / 2;
        res += mergeSort(nums, left, mid);
        res += mergeSort(nums, mid + 1, right);
        res += merge(nums, left, mid, right);
        return res;
    }

    private int merge(int[] nums, int left, int mid, int right) {
        int count = 0;
        int[] temp = new int[right - left + 1];
        int leftIdx = left;
        int rightIdx = mid + 1;

        // 这题的专属步骤
        while (leftIdx <= mid && rightIdx <= right) {
            if ((long) nums[leftIdx] > 2 * (long) nums[rightIdx]) {
                // 因为左右数组均为升序，当 leftIdx 满足条件，说明左数组 left 右边的所有元素也满足
                count += mid - leftIdx + 1;
                rightIdx++;
            } else {
                leftIdx++;
            }
        }

        // 常规的 merge 步骤
        // 重置
        leftIdx = left;
        rightIdx = mid + 1;
        int idx = 0;
        while (leftIdx <= mid && rightIdx <= right) {
            if (nums[leftIdx] <= nums[rightIdx]) {
                temp[idx++] = nums[leftIdx++];
            } else {
                temp[idx++] = nums[rightIdx++];
            }
        }
        while (rightIdx <= right) {
            temp[idx++] = nums[rightIdx++];
        }
        while (leftIdx <= mid) {
            temp[idx++] = nums[leftIdx++];
        }
        for (int i = 0; i < idx; i++) {
            nums[left++] = temp[i];
        }
        return count;
    }
}
