package Heap;

/*  253. Meeting Rooms 2
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si<ei),
    find the minimum number of conference rooms required.

    Example 1:
    Input: [[0,30],[5,10],[15,20]]
    Output: 2

    Example 2:
    Input: [[7,10],[2,4]]
    Output: 1
 */
import java.util.Arrays;
import java.util.PriorityQueue;

//  Sort + PriorityQueue
public class MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (int[] a, int[] b) -> (a[0] - b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // keep track of the earliest end time
        minHeap.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(intervals[i][1]);
        }
        return minHeap.size();
    }
}
