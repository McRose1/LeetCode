package PriorityQueue;

import java.util.PriorityQueue;

/*  215. Kth Largest Element in an Array
    Find the kth largest element in an unsorted array.
    Note that it is the kth largest element in the sorted order, not the kth distinct element.

    Example 1:
    Input: [3,2,1,5,6,4] and k = 2
    Output: 5

    Example 2:
    Input: [3,2,3,1,2,4,5,5,6] and k = 4
    Output: 4

    Note:
    You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
//  minHeap(largest): Time = O(nlogk) Time = O(k)
public class KthLargestElementinanArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // pq 可以实现 minHeap, nature order 从小到大，小的先 poll
        for (int num : nums) {
            minHeap.offer(num);
            // Control the size of pq = k
            if (minHeap.size() >  k) {
                minHeap.poll();
            }
        }
        return minHeap.poll();
    }
}

/*  Quick Select: Time = O(n) Space = O(n)

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        Random rand = new Random(0);
        while (left <= right) {
            int pivot = rand.nextInt(right - left + 1) + left;

            int finalPivot = partition(nums, left, right, pivot);

            if (finalPivot == n - k) {
                return nums[finalPivot];
            } else if (finalPivot > n - k) {
                right = finalPivot - 1;
            } else {
                left = finalPivot + 1;
            }
        }
        return -1;
    }
    private int partition(int[] nums, int left, int right, int pivot) {
        int marker = left;
        int pivotValue = nums[pivot];
        swap(nums, pivot, right);
        for (int search = left; search < right; search++) {
            if (nums[search] < pivotValue) {
                swap(nums, search, marker);
                marker++;
            }
        }
        swap(nums, right, marker);
        return marker;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
 */
