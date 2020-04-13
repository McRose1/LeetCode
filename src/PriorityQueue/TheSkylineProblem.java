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

import java.util.*;

/*  Sweep Line（扫描线算法）
    所有点 —— 起始点：最高点 / 终止点：第二高点
 */
public class TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) { // [[2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8]]
        List<List<Integer>> res = new ArrayList<>();
        int n = buildings.length;
        if (n == 0) return res;

        // [2, 9, 10] -> [2, -10], [9, 10]
        List<int[]> buildLines = new ArrayList<>();
        for (int[] points : buildings) {
            buildLines.add(new int[] {points[0], -points[2]});
            buildLines.add(new int[] {points[1], points[2]});
        }

        // 将新构建的 list 里面的坐标依次排序
        Collections.sort(buildLines, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];     // [2, -10], [3, -15]
            } else {
                // 上升阶段 height 高的先被扫，下降阶段 height 低的先被扫
                return a[1] - b[1];     // [2, -10], [2, -7], [3, 13], [3, 15]
            }
        });
        // buildLines:
        // {[2, -10], [3, -15], [5, -12], [7, 15], [9, 10], [12, 12], [15, -10], [19, -8], [20, 10], [24, 8]}

        // 初始化 maxHeap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // 在此题可保证 maxHeap 永远不会 empty
        maxHeap.add(0);
        int preHighest = 0;

        for (int[] points : buildLines) {
            // 左边界，开始扫描该大楼，往 maxHeap 里 offer 当前高度
            if (points[1] < 0) {
                // 只有大于此时堆顶的数字才能改变堆顶大小
                maxHeap.add(-points[1]);
            // 右边界，结束该大楼的扫描，从 maxHeap 里 poll 出对应的高度
            } else {
                maxHeap.remove(points[1]);
            }
            // 如果当前高度改变（变大变小都满足），说明此时画笔做竖直方向移动，必然出现我们要的点
            int curHeight = maxHeap.peek();      // 10; 15; 15; 12; 12; 0
            if (curHeight != preHighest) {     // 10!=0; 15!=10; 15=15; 12!=15; 12=12; 0!=12
                res.add(Arrays.asList(points[0], curHeight));    // [2, 10]; [3, 15]; [7, 12]; [12, 0]
                // 更新当前最高点
                preHighest = curHeight;
            }
        }
        return res;
    }
}
