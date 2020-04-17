package SlidingWindow;

/*  239. Sliding Window Maximum
    Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
    You can only see the k numbers in the window. Each time the sliding window moves right by one position.
    Return the max sliding window.

    Follow up: Could you solve it in linear time?

    Example:
    Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
    Output: [3,3,5,5,6,7]
    Explanation:

    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7

    Constraints:
    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
    1 <= k <= nums.length

    Hint:
    How about using a data structure such as deque (double-ended queue)?
    The queue size need not be te same as the window's size.
    Remove redundant elements and the queue should store only elements that need to be considered.
 */

import java.util.Deque;
import java.util.LinkedList;

/*  Deque: Time = O(n) Space = O(k)
    Deque 可以从两端以常数时间压入/弹出元素
    Deque: first -> last == max -> min
    如果当前待加入元素比 deque 的首部元素大，清空 deque，加入当前元素
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deq = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // remove from deq indexes of all elements which are smaller than current element nums[i]
            while (!deq.isEmpty() && nums[i] >= nums[deq.getLast()]) {
                deq.removeLast();
            }
            deq.addLast(i);

            // build output
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[deq.getFirst()];
            }
            // 滑动窗口并同时更新在窗口内的对应在 deque 的 index
            if (i - k + 1 >= deq.getFirst()) {
                deq.removeFirst();
            }
        }
        return res;
    }
}

/*  BST: Time = O((n-k+1)*logk) Space = O(k)

 */

/*  Brute Force (my version): Time = O((n-k+1)*k) Space = O(1)

        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
 */