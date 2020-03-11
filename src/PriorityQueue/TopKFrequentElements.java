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
    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Your algorithm's time complexity must be better than O(nlogn), where n is the array's size.
 */
import java.util.*;

//  minHeap: Time = O(nlogk) Space = O(k)
public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
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

        // build output list
        List<Integer> top_k = new LinkedList<>();
        while (!minHeap.isEmpty()) {
            top_k.add(minHeap.poll());
        }
        Collections.reverse(top_k);
        return top_k;
    }
}

/*  maxHeap: Time = O(nlogk) Space = O(k)

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        maxHeap.addAll(map.keySet());
        List<Integer> res = new ArrayList<>();

        while (k > 0) {
            int cur = maxHeap.remove();
            res.add(cur);
            k--;
        }
        return res;
 */

/*
    Bucket Sort: Time = O(n) Space = O(n)

        List<Integer>[] bucket = new List[nums.length + 1];     // nums.length + 1 因为数组 index = 0 的地方不能用（不存在出现频率为 0）
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
 */
