package Graph;

/*  149. Max Points on a Line
    Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

    Example 1:
    Input: [[1,1],[2,2],[3,3]]
    Output: 3

    Example 2:
    Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
    Output: 4
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*  Brute Force: Time = O(n^2) Space = O(n)
    For each point, we calculate the slopes with following points and count the points of the same slope -> HashMap
    count duplicated points -> HashSet
 */
public class MaxPointsonaLine {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (points.length < 2) return n;
        Set<String> set = new HashSet<>();
        int max = 1;

        for (int i = 0; i < n && !set.contains(points[i][0] + "-" + points[i][1]); i++) {
            int[] a = points[i];        // [1, 1]
            Map<Double, Integer> map = new HashMap<>();
            int same = 0;
            int localMax = 1;

            // Duplicate
            for (int j = i + 1; j < n; j++) {
                if (isSame(a, points[j])) {
                    same++;
                    continue;
                }
                // Compute the slope
                double slope = getSlope(a, points[j]);
                map.put(slope, map.getOrDefault(slope, 1) + 1);
                localMax = Math.max(localMax, map.get(slope));
            }
            set.add(a[0] + "-" + a[1]);         // [1-1]
            max = Math.max(max, localMax + same);
        }
        return max;
    }

    private boolean isSame(int[] a, int[] b) {
        return a[0] == b[0] && a[1] == b[1];
    }

    private double getSlope(int[] a, int[] b) {
        if (a[0] == b[0]) return Double.MAX_VALUE;
        if (a[1] == b[1]) return 0;
        return ((double) a[0] - b[0]) / ((double) a[1] - b[1]);
    }
}
