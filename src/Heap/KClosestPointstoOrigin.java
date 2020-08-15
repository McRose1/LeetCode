package Heap;

/*  973. K Closest Points to Origin
    We have a list of points on a plane. Find the K closest points to the origin (0, 0).
    (Here, the distance between two points on a plane is the Euclidean distance.)
    You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.)

    Example 1:
    Input: points = [[1,3],[-2,2]], K = 1
    Output: [[-2,2]]
    Explanation:
    The distance between (1, 3) and the origin is sqrt(10).
    The distance between (-2, 2) and the origin is sqrt(8).
    Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
    We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

    Example 2:
    Input: points = [[3,3],[5,-1],[-2,4]], K = 2
    Output: [[3,3],[-2,4]]
    (The answer [[-2,4],[3,3]] would also be accepted.)

    Note:
        1. 1 <= K <= points.length <= 10000
        2. -10000 < points[i][0] < 10000
        3. -10000 < points[i][1] < 10000
 */

import java.util.PriorityQueue;

/*  maxHeap: Time = O(nlogk) Space = O(k)

 */
public class KClosestPointstoOrigin {
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }
        int idx = 0;
        while (!maxHeap.isEmpty()) {
            res[idx++] = maxHeap.poll();
        }
        return res;
    }
}

/*  minHeap: Time = O(nlogn) Space = O(n)

        int[][] res = new int[K][2];

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]));

        for (int[] point : points) {
            minHeap.offer(point);
        }
        for (int i = 0; i < K; i++) {
            res[i] = minHeap.poll();
        }
        return res;
 */

/*  Sort: Time = O(nlogn) Space = O(n)

    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        int[] dists = new int[n];
        for (int i = 0; i < n; i++) {
            dists[i] = dist(points[i]);
        }

        Arrays.sort(dists);
        int distK = dists[K - 1];

        int[][] ans = new int[K][2];
        int idx = 0;
        for (int[] point : points) {
            if (dist(point) <= distK) {
                ans[idx++] = point;
            }
        }
        return ans;
    }

    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
 */
