package Heap;

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
/*  PriorityQueue: Time = O(nlogk) Time = O(k)
    用 PQ 实现 minHeap，维护一个 size 为 k 的 minHeap，那么堆顶就是 kth largest element
 */
public class KthLargestElementinanArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // pq 可以实现 minHeap, nature order 从小到大，小的先 poll
        for (int num : nums) {
            minHeap.offer(num);
            // Control the size of pq = k
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.poll();
    }
}

/*  Quick Select: Time = O(n) Space = O(n)
    如果是快速排序算法，会递归地对两部分进行快速排序，Time = O(nlogn)
    而在这里我们知道要找的第 N-k 小的元素在哪部分，我们不需要对两部分都做处理，Time = O(n)
    1. 随机选择一个 pivot
    2. 使用 partition 将 pivot 放在数组中的合适位置 pos。将小于 pivot 元素移到左边，大于等于 pivot 的元素移到右边
    3. 比较 pos 和 N-k 以决定在哪边继续递归处理

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        // kth largest is (N - k)th smallest
        return quickSelect(nums, 0, n - 1, n - k);
    }

    // Returns the k-th smallest element of list within left-right
    private int quickSelect(int[] nums, int left, int right, int k_smallest) {
        // If the list contains only one element, return that element
        if (left == right) {
            return nums[left];
        }

        // select a random pivot_index
        Random rand = new Random();
        int pivot_index = left + rand.nextInt(right - left + 1);

        pivot_index = partition(nums, left, right, pivot_index);

        // the pivot is on (N - k)th smallest position
        if (k_smallest == pivot_index) {
            return nums[k_smallest];
        } else if (k_smallest < pivot_index) {
            return quickSelect(nums, left, pivot_index - 1, k_smallest);
        } else {
            return quickSelect(pivot_index + 1, right, k_smallest);
        }
    }

    private int partition(int[] nums, int left, int right, int pivot_index) {
        int pivotValue = nums[pivot_index];
        // 1. move pivot to end
        swap(nums, pivot_index, right);
        int store_index = left;

        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, store_index);
                store_index++;
            }
        }
        // 3. move pivot to its final place
        swap(nums, right, store_index);

        return store_index;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
 */
