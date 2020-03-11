package PriorityQueue;

/*  218. The Skyline Problem
    A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
    Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A),
    write a program to output the skyline formed by these buildings collectively (Figure B).

    The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
    where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
    It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
    You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

    For instance, the dimensions of all buildings in Figure A are recorded as:
    [[2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8]].

    The output is a list of "key points" (red dots in Figure B) in the format of [[x1,y1], [x2,y2], [x3,y3], ...]
    that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment.
    Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline,
    and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

    For instance, the skyline in Figure B should be represented as:
    [[2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24 0]].

    Notes:
    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascending order by the left x position Li.
    The output list must be sorted by the x position.

    There must be no consecutive horizontal lines of equal height in the output skyline.
    For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable;
    the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*  Sweep Line（扫描线算法）
    所有点 —— 起始点：最高点 / 终止点：第二高点
 */
public class TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        int n = buildings.length;
        if (n == 0) return res;

        // 定义事件 & 预处理事件
        int[][] events = new int[n * 2][2];     // 2 * number of buildings
        for (int i = 0; i < n; i++) {
            int j = i * 2;
            // entertime, height
            events[j][0] = buildings[i][0];     // 起始点：2; 3; 5
            events[j][1] = buildings[i][2];     // 最高点：10; 15; 12
            j++;
            // leavetime, height
            events[j][0] = buildings[i][1];     // 终止点：9; 7; 12
            events[j][1] = -buildings[i][2];    // 做标记：-10; -15; -12 -> [[2, 10], [9, -10], [3, 15], [7, -15], [5, 12], [12, -12]]
        }

        Arrays.sort(events, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];     // [[2, 10], [3, 15], [5, 12], [7, -15], [9, -10], [12, -12]]
            } else {
                // enter event first
                return b[1] - a[1];
            }
        });

        // 以时间推动进行扫描事件
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int i = 0;
        while (i < events.length) {
            int prev = maxHeap.isEmpty() ? 0 : maxHeap.peek();  // 0; 10; 15; 15; 12; 12
            int[] event = events[i];        // [2, 10]; [3, 15]; [5, 12]; [7, -15]; [9, -10]; [12, -12]
            // this is an enter event
            if (event[1] > 0) {
                maxHeap.add(event[1]);      // 10; 15; 12
            // leaving event
            } else {
                maxHeap.remove(-event[1]);  // 15; 10; 12
            }
            int curMaxHeight = maxHeap.isEmpty() ? 0 : maxHeap.peek();      // 10; 15; 15; 12; 12; 0
            if (curMaxHeight != prev) {     // 10!=0; 15!=10; 15=15; 12!=15; 12=12; 0!=12
                res.add(new ArrayList<>(Arrays.asList(event[0], curMaxHeight)));    // [2, 10]; [3, 15]; [7, -15]; [12, 0]
            }
            i++;
        }
        return res;
    }
}
