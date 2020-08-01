package PriorityQueue;

/*  347. Top K Frequent Elements
    Given a non-empty array of integers, return the K most frequent elements.

    Example 1:
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]

    Example 2:
    Input: nums = [1], k = 1
    Output: [1]

    Note:
        o You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
        o Your algorithm's time complexity must be better than O(nlogn), where n is the array's size.
        o It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
        o You can return the answer in any order.
 */
import java.util.*;

//  minHeap + HashMap: Time = O(nlogk) Space = O(n + k)
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) return nums;

        // build hash map : character and how often it appears
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));

        // keep k top frequent elements in the heap
        for (int n : map.keySet()) {
            minHeap.add(n);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // build output array
        int[] top_k = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            top_k[i] = minHeap.poll();
        }
        return top_k;
    }
}

/*  maxHeap: Time = O(nlogk) Space = O(n + k)

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        maxHeap.addAll(map.keySet());

        int[] res = new int[k];
        int i = 0;
        while (k > 0) {
            int cur = maxHeap.remove();
            res[i++] = cur;
            k--;
        }
        return res;
 */

/*  Bucket Sort: Time = O(n) Space = O(n)
        // nums.length + 1 因为数组 index = 0 的地方不能用（不存在出现频率为 0）
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        int[] res = new int[k];
        int idx = 0;
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null) {
                for (int i = 0; i < bucket[pos].size() && idx < k; i++) {
                    res[idx++] = bucket[pos].get(i);
                }
            }
        }
        return res;
 */

/*  Quick Select: Time = O(n) Space = O(n)

    int[] res;
    HashMap<Integer, Integer> map;

    private void swap(int a, int b) {
        int temp = res[a];
        res[a] = res[b];
        res[b] = temp;
    }

    private int partition(int left, int right, int pivot_idx) {
        int pivot_freq = map.get(res[pivot_idx]);
        // 1. move pivot to end
        swap(pivot_idx, right);
        int store_idx = left;

        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (map.get(res[i]) < pivot_freq) {
                swap(store_idx, i);
                store_idx++;
            }
        }

        // 3. move pivot to its final place
        swap(store_idx, right);

        return store_idx;
    }

    // Sort a list within left...right till kth less frequent element takes its place.
    private void quickselect(int left, int right, int k_smallest) {
        // base case: the list contains only 1 element
        if (left == right) return;

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_idx = left + random_num.nextInt(right - left);

        // find the pivot position in a sorted list
        pivot_idx = partition(left, right, pivot_idx);

        // if the pivot is in its final sorted position
        if (k_smallest == pivot_idx) {
            return;
        }  else if (k_smallest < pivot_idx) {
            // go left
            quickselect(left, pivot_idx - 1, k_smallest);
        } else {
            // go right
            quickselect(pivot_idx + 1, right, k_smallest);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        // build hash map : character and how ofer it appears
        map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // array of unique elements
        int n = map.size();
        res = new int[n];
        int i = 0;
        for (int num : map.keySet()) {
            res[i++] = num;
        }

        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till (n - k)th less frequent element takes its place (n - k) in a sorted array.
        // All element on the left are less frequent.
        // All the elements on the right are more frequent.
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(res, n - k, n);
    }
 */