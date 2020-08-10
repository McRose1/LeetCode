package OrderedMap;

/*  729. My Calendar 1
    Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
    Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end),
    the range of real numbers x such that start <= x < end.
    A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
    For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking.
    Otherwise, return false and do not add the event to the calendar.
    You class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

    Example:
    MyCalendar();
    MyCalendar.book(10, 20); // returns true
    MyCalendar.book(15, 25); // returns false
    MyCalendar.book(20, 30); // returns true
    Explanation:
    The first event can be booked.  The second can't because time 15 is already booked by another event.
    The third event can be booked, as the first event takes every time less than 20, but not including 20.

    Note:
        o The number of calls to MyCalendar.book per test case will be at most 1000.
        o In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

    Hint:
    Store the events as a sorted list of intervals. If none of the events conflict, then the new event can be added.
 */

import java.util.TreeMap;

/*  TreeMap: Time = O(nlogn) Space = O(n)
    OrderedMap<int, int> : <start, end>
    floor: largest entry whose key <= query key
    ceiling: smallest entry whose key > query key

        [10,  20]                 [15, 20]
         [12,18]                [12, 18]
    floor.end > q.start    ceiling.start < q.end
 */
public class MyCalendar1 {

    TreeMap<Integer, Integer> calendar;

    public MyCalendar1() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start);
        Integer next = calendar.ceilingKey(start);

        if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}

/*  Brute Force: Time = O(n^2) Space = O(n)
    两个日程安排 [s1，e1) 和 [s2，e2) 不冲突：e1<=s2 或 e2<=s1。
    这意味着当 s1<e2 和 s2<e1 时，日程安排发生冲突。

    List<int[]> calendar;

    public MyCalendar1() {
        calendar = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] interval : calendar) {
            if (interval[0] < end && start < interval[1]) {
                return false;
            }
        }
        calendar.add(new int[] {start, end});
        return true;
    }
 */