package Sort;

/*  252. Meeting Rooms
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si<ei),
    determine if a person could attend all meetings.

    Example 1:
    Input: [[0,30],[5,10],[15,20]]
    Output: false

    Example 2:
    Input: [[7,10],[2,4]]
    Output: true
 */

import java.util.Arrays;
//  Sort
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        int index = 0;
        for (int[] time : intervals) {
            start[index] = time[0];
            end[index] = time[1];
            index++;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        for (int i = 0; i < n - 1; i++) {
            if (start[i + 1] < end[i]) {
                return false;
            }
        }
        return true;
    }
}
