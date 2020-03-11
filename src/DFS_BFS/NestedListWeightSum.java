package DFS_BFS;

/*  339. Nested List Weight Sum
    Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
    Each element is either an integer, or a list -- whose elements may also be integers or other lists.

    Example 1:
    Input: [[1,1],2,[1,1]]
    Output: 10
    Explanation: Four 1's at depth 2, one 2 at depth 1.

    Example 2:
    Input: [1,[4,[6]]]
    Output: 27
    Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */

import java.util.List;

public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) return 0;
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += depth * ni.getInteger();
            } else {
                sum += dfs(ni.getList(), depth + 1);
            }
        }
        return sum;
    }
}
