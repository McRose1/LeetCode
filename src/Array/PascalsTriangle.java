package Array;
/*  118. Pascal's Triangle
    Given a non-negative integer numRows, generate the first numRows of Pascal's triangle

    Example:
    Input: 5
    Output:
    [
            [1],
           [1,1],
          [1,2,1],
         [1,3,3,1],
        [1,4,6,4,1]
    ]
 */

import java.util.ArrayList;
import java.util.List;

//  Leetcode Discuss

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            res.add(new ArrayList<Integer>(row));   // pass by reference，即每次copy当前row
        }
        return res;
    }
}
/*  DP (memorization iterative)

    List<List<Integer>> triangle = new ArrayList<List<Integer>>();

    // First base case: if user requests zero rows, they get zero rows
    if (numRows == 0) {
        return triangle;
    }

    // Second base case: first row is always [1]
    triangle.add(new ArrayList<>());
    triangle.get(0).add(1);

    for (int rowNum = 1; rowNum < numRows; rowNum++) {
        List<Integer> row = new ArrayList<>();
        List<Integer> prevRow = triangle.get(rowNum - 1);

        // The first row element is always 1
        row.add(1);

        // Each triangle element (other than the first and last of each row)
        // is equal to the sum of the elements above-and-to-the-left and
        // above-and-to-the-right
        for (int j = 1; j < rowNum; j++) {
            row.add(prevRow.get(j - 1) + prevRow.get(j));
        }

        // The last row element is always 1
        row.add(1);

        triangle.add(row);
    }

    return triangle;

 */