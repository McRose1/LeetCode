package Array;
/*  119. Pascal's Triangle 2
    Given a non-negative index k where k <= 33, return the kth index row of the Pascal's triangle.
    Note that the row index starts from 0.

    Example:
    Input: 3
    Output: [1, 3, 3, 1]
 */

import java.util.ArrayList;
import java.util.List;

// 和 118 一模一样

public class PascalsTriangle2 {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; i++) {
            row.add(0, 1);  // 在每一 row 头部加一个 1，再把中间元素合二为一
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
        }
        return row;
    }

}
