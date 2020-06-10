package PriorityQueue;

/*  295. Find Median from Data Stream
    Median is the middle value in an ordered integer list.
    If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

    For example,
    [2,3,4], the median is 3
    [2,3], the median is (2 + 3) / 2 = 2.5

    Design a data structure that supports the following two operations:
    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.

    Example:
    addNum(1)
    addNum(2)
    findMedian() -> 1.5
    addNum(3)
    findMedian() -> 2

    Follow up:
    1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */

import java.util.PriorityQueue;

/*  2 Heaps: Time = O(logn) Space = O(n)
    维护一个 maxHeap 和一个 minHeap，使得 maxHeap size 和 minHeap 相等或者比它大 1
    最后计算 median 的时候只需要取出堆顶即可，因为 maxHeap 的最大值和 minHeap 的最小值一定是所有数中的中间数
 */
public class FindMedianfromDataStream {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    /** initialize your data structure here. */
    public FindMedianfromDataStream() {
        maxHeap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // 左半边小的数字放在 maxHeap
        if (maxHeap.isEmpty() || num < maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        // minHeap size 最多比 maxHeap size 大 1
        if (maxHeap.size() == minHeap.size() + 2) {
            minHeap.offer(maxHeap.poll());
        }
        // maxHeap size 不能超过 minHeap size
        if (minHeap.size()== maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        // size 相同，说明是偶数个，取两个堆顶的平均值
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}
