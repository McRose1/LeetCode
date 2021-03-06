package SlidingWindow;

/*  346. Moving Average from Data Stream
    Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

    Example:
    MovingAverage m = new MovingAverage(3);
    m.next(1) = 1
    m.next(10) = (1 + 10) / 2
    m.next(3) = (1 + 10 + 3) / 3
    m.next(5) = (10 + 3 + 5) / 3
 */

import java.util.LinkedList;
import java.util.Queue;

/*  Queue
    用队列模拟滑动窗口
 */
public class MovingAveragefromDataStream {
    private Queue<Integer> queue;
    private double sum = 0;
    private int size;
    /** Initialize your data structure here. */
    public MovingAveragefromDataStream(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.poll();
        }
        sum += val;
        queue.offer(val);
        return sum / queue.size();
    }
}
