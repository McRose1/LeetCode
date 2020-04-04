package HashTable;

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

/*  Compute the Slope: Time = O(n^2) Space = O(n)
    For each point, we calculate the slopes with following points and count the points of the same slope -> HashMap
    count duplicated points
 */
public class MaxPointsonaLine {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (points.length < 3) return n;
        int res = 0;

        // 遍历每个点
        for (int i = 0; i < n; i++) {
            int duplicate = 0;
            int max = 0;
            // key 代表斜率，value 代表该斜率上点的数量
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                // 分子
                int x = points[j][0] - points[i][0];
                // 分母
                int y = points[j][1] - points[i][1];

                // 处理两点重合的情况
                if (x == 0 && y == 0) {
                    duplicate++;
                    continue;
                }

                // 对分子分母进行约分
                int gcd = gcd(x, y);
                x /= gcd;
                y /= gcd;
                String key = x + "-" + y;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            // duplicate 表示和当前点重复的点，1 代表当前点
            res = Math.max(res, max + duplicate + 1);
        }
        return res;
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}

/*  Another method with HashSet 并且用 double 解决精度问题

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (points.length < 2) return n;
        Set<String> set = new HashSet<>();
        int max = 1;

        // 利用 HashSet 剪枝
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
 */