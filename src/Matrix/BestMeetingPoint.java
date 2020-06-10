package Matrix;

/*  296. Best Meeting Point
    A group of two or more people wants to meet and minimize the total travel distance.
    You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
    The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

    Example:
    Input:

    1 - 0 - 0 - 0 - 1
    |   |   |   |   |
    0 - 0 - 0 - 0 - 0
    |   |   |   |   |
    0 - 0 - 1 - 0 - 0

    Output: 6

    Explanation: Given three people living at (0,0), (0,4), and (2,2):
                 The point (0,2) is an ideal meeting point, as the total travel distance
                 of 2+2+2=6 is minimal. So return 6.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*  行列的最佳点分开求，组合起来就是最佳点：二维->一维
    由于曼哈顿距离((x1, y1), (x2, y2)) = |x1 - x2| + |y1 - y2|，所以只要分别最小化x, y坐标上的距离，就一定能得到总的最小曼哈顿距离
    先考虑一维的情况，如果所有人都住在一条直线上，想要找到使总行走距离最小的一个见面点，这个见面点应当为所有人居住位置的中位数
    因此我们在x轴方向上找一个中位数，再y轴方向上找一个中位数，即可得到最佳见面点
    最后计算每个人到最佳见面点的距离，求和结果就是我们想要的答案
 */
public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    x.add(i);
                    y.add(j);
                }
            }
        }
        return getMin(x) + getMin(y);
    }
    // 计算一维的最小曼哈顿距离
    private int getMin(List<Integer> list) {
        Collections.sort(list);
        int i = 0, j = list.size() - 1;
        int sum = 0;
        while (i < j) {
            sum += list.get(j--) - list.get(i++);
        }
        return sum;
    }
}
