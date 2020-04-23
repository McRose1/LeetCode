package SlidingWindow;

/*  480. Sliding Window Median
    Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
    So the median is the mean of the two middle value.
    Examples:
    [2,3,4] , the median is 3
    [2,3], the median is (2 + 3) / 2 = 2.5

    Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
    You can only see the k numbers in the window.
    Each time the sliding window moves right by one position.
    Your job is to output the median array for each window in the original array.

    For example,
    Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
    Window position                Median
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       1
     1 [3  -1  -3] 5  3  6  7       -1
     1  3 [-1  -3  5] 3  6  7       -1
     1  3  -1 [-3  5  3] 6  7       3
     1  3  -1  -3 [5  3  6] 7       5
     1  3  -1  -3  5 [3  6  7]      6
     Therefore, return the median sliding window as [1,-1,-1,3,5,6].

    Note:
    You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
    Answers within 10^-5 of the actual value will be accepted as correct.
 */

import java.util.Arrays;

/*  Insertion Sort: Time = ((n-k+1)*k) Space = O(k)
    Window is sorted, we need to add one element and remove one element
    Update window via insertion sort:
    Remove():
    Binary Search O(logk) + Shift O(k)
    Insert():
    Binary Search O(logk) + Shift O(k)
 */
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        int[] window = new int[k];
        for (int i = 0; i < k; i++) {
            window[i] = nums[i];
        }
        Arrays.sort(window);                             // [-1, 1, 3]
        for (int i = k; i <= nums.length; i++) {
            res[i - k] = ((double) window[k / 2] + window[(k - 1) / 2]) / 2;    // res[0] = 1
            if (i == nums.length) break;
            // 移去滑动窗口的第一个数字
            remove(window, nums[i - k]);                // remove(0)->1->[-1, 3]
            // 将新元素插入到合适的位置
            insert(window, nums[i]);                    // insert(3)->-3->[-3,-1,3]
        }
        return res;
    }
    // Insert val into window, window[k - 1] is empty before insertion
    private void insert(int[] window, int val) {
        int i = 0;
        // 找到新元素在排好序的滑动窗口的位置
        while (i < window.length - 1 && val > window[i]) {
            i++;
        }
        int j = window.length - 1;
        // 将该位置右边的数字都往右移动一位
        while (j > i) {
            window[j] = window[--j];
        }
        window[j] = val;
    }
    // Remove val from window and shrink it
    private void remove(int[] window, int val) {
        int i = 0;
        // 在排好序的滑动窗口找到原滑动窗口的第一个数字
        while (i < window.length && val != window[i]) {
            i++;
        }
        // 整个数组向左移动一位
        while (i < window.length - 1) {
            window[i] = window[++i];
        }
    }
}

/*  BST: Time = O((n-k+1)*logk) Space = O(k)
    maintain pointer to the left-median
    Insert(A)
    Remove(B)
 */