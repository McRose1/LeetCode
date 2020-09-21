package TwoPointers;

/*  986. Interval List Intersections
    Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
    Return the intersection of these two interval lists.
    (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
    The intersection of two closed intervals is a set of real numbers that is either empty,
    or can be represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

    Example 1:
    Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
    Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

    Note:
        1. 0 <= A.length < 1000
        2. 0 <= B.length < 1000
        3. 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 */

import java.util.ArrayList;
import java.util.List;

/*  Two pointers: Time = O(n) Space = O(n)

 */
public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the start point of the intersection.
            // hi - the end point of the intersection.
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi) {
                ans.add(new int[] {lo, hi});
            }

            // 更新指针
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}

/*  sort: Time = O(nlogn) Space = O(1)

        List<int[]> intervals = new ArrayList<>();
        Collections.addAll(intervals, A);
        Collections.addAll(intervals, B);
        intervals.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        int start = Integer.MIN_VALUE;
        int end = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.size() - 1; i++) {
            int[] cur = intervals.get(i);
            start = Math.max(start, cur[0]);
            end = Math.max(end, cur[1]);
            if (intervals.get(i + 1)[0] >= start && intervals.get(i + 1)[0] <= end) {
                if (intervals.get(i + 1)[1] <= end) {
                    res.add(intervals.get(i + 1));
                } else {
                    res.add(new int[] {intervals.get(i + 1)[0], end});
                }
            }
        }
        int size = res.size();
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            ans[i] = res.get(i);
        }
        return ans;
 */
