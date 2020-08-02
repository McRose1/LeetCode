package BFS_DFS;

/*  364. Nested List Weight Sum 2
    Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

    Each element is either an integer, or a list -- whose elements may also be integers or other lists.

    Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up.
    i.e. the leaf level integers have weight 1, and the root level integers have the largest weight.

    Example 1:
    Input: [[1,1],2,[1,1]]
    Output: 8
    Explanation: Four 1's at depth 1, one 2 at depth 2.

    Example 2:
    Input: [1,[4,[6]]]
    Output: 17
    Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*  BFS + Queue: Time = O(n) Space = O(n)
    1*3 + 4*2 + 6*1 = 1 + (1+4) + (1+4+6)
 */
public class NestedListWeightSum2 {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) return 0;
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        int sum = 0, level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger()) {
                    level += ni.getInteger();
                } else {
                    queue.addAll(ni.getList());
                }
            }
            sum += level;
        }
        return sum;
    }
}

/*  DFS: Time = O(n) Space = O(n)

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) return 0;
        return helper(nestedList, 0);
    }

    private int helper(List<NestedInteger> nestedList, int res) {
        List<NestedInteger> nextList = new LinkedList<>();
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                res += ni.getInteger();
            } else {
                nextList.addAll(ni.getList());
            }
        }
        res += nextList.isEmpty() ? 0 : helper(nextList, res);
        return res;
    }
 */
