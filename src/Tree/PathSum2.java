package Tree;

/*  113. Path Sum 2
    Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

    Note: A leaf is a node with no children.

    Example:
    Given the below binary tree and sum = 22,
          5
         / \
        4   8
       /   / \
      11  13  4
     /  \    / \
    7    2  5   1
    Return:
    [
       [5,4,11,2],
       [5,8,4,5]
    ]
 */

import java.util.ArrayList;
import java.util.List;
//  Backtracking
public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), root, sum);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> list, TreeNode root, int sum) {
        // 出口 1
        if (root == null) return;
        // 出口 2：leaf node
        if (root.left == null && root.right == null && root.val == sum) {
            // 添加 leaf node
            list.add(root.val);
            res.add(new ArrayList<>(list));
            // backtrack
            list.remove(list.size() - 1);
            return;
        }
        // 添加非leaf node
        list.add(root.val);
        dfs(res, list, root.left, sum - root.val);
        dfs(res, list, root.right, sum - root.val);
        // backtrack
        list.remove(list.size() - 1);
    }
}
