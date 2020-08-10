package OrderedMap;

/*  731. My Calendar 2
    Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
    Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end),
    the range of real numbers x such that start <= x < end.
    A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
    For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking.
    Otherwise, return false and do not add the event to the calendar.
    You class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

    Example:
    MyCalendar();
    MyCalendar.book(10, 20); // returns true
    MyCalendar.book(50, 60); // returns true
    MyCalendar.book(10, 40); // returns true
    MyCalendar.book(5, 15); // returns false
    MyCalendar.book(5, 10); // returns true
    MyCalendar.book(25, 55); // returns true
    Explanation:
    The first two events can be booked.  The third event can be double booked.
    The fourth event (5, 15) can't be booked, because it would result in a triple booking.
    The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
    The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
    the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.

    Note:
        o The number of calls to MyCalendar.book per test case will be at most 1000.
        o In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

    Hint:
    Store two sorted lists of intervals: one list will be all times that at least single booked,
    and another list will be all times that are definitely double booked.
    If none of the double bookings conflict, then the booking will succeed, and you should update your single and double bookings accordingly.
 */

import java.util.TreeMap;

/*  Boundary Count: Time = O(n^2) Space = O(n)
    当我们预定一个新的日程安排 [start, end)，则执行 delta[start]++ 和 delta[end]--。
 */
public class MyCalendar2 {

    TreeMap<Integer, Integer> delta;

    public MyCalendar2() {
        delta = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (int d : delta.values()) {
            active += d;
            if (active >= 3) {
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0) {
                    delta.remove(start);
                }
                return false;
            }
        }
        return true;
    }
}

/*  Brute Force: Time = O(n^2) Space = O(n)
    维护一重预订列表和双重预订列表。当预订一个新的日程安排 [start, end) 时，如果它与双重预订列表冲突，则会产生三重预定。
    Store all events and al overlaps

    List<int[]> calendar;
    List<int[]> overlaps;

    public MyCalendar2() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        // 如果新的日程安排与双重预订冲突，则返回 false
        for (int[] interval : overlaps) {
            if (interval[0] < end && start < interval[1]) {
                return false;
            }
        }
        for (int[] interval : calendar) {
            if (interval[0] < end && start < interval[1]) {
                // 将与一重预定列表冲突的时间添加到双重预订列表中
                overlaps.add(new int[] {Math.max(start, interval[0]), Math.min(end, interval[1])});
            }
        }
        // 并将该预定添加到一重预定列表中
        calendar.add(new int[] {start, end});
        return true;
    }
 */