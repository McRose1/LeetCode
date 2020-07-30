package DP;

/*  337. House Robber 3
    The thief has found himself a new place for his thievery again.
    There is only one entrance to this area, called the "root". Besides the root, each house has one and only one parent house.
    After a tour, the smart thief realized that "all houses in this place forms a binary tree".
    It weill automatically contact police if two directly-linked houses were broken into on the same night.

    Determine the maximum amount of money the thief can rob tonight without alerting the police.

    Example 1:
    Input: [3,2,3,null,3,null,1]

         3
        / \
       2   3
        \   \
         3   1

    Output: 7
    Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

    Example 2:
    Input: [3,4,5,1,3,null,1]

         3
        / \
       4   5
      / \   \
     1   3   1

    Output: 9
    Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */

import Tree.TreeNode;

/*  DP
    每个节点都有 2 个选择，rob or not rob
    如果选择不 rob，那么它的左右子节点就也有 2 个选择；
    如果选择 rob，那么它的左右子节点就不能 rob
 */
public class HouseRobber3 {
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }

    private int[] helper(TreeNode root) {
        // [0] is max value if not rub current one
        // [1] is max value if rob current one
        if (root == null) return new int[2];
        int[] res = new int[2];
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + root.val;
        return res;
    }
}
