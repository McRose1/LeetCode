package Tree;

/*  124. Binary Tree Maximum Path Sum
    Given a non-empty binary tree, find the maximum path sum.
    For this problem, a path is defined as any sequence of nodes from some starting node to any node in
    the tree along the parent-child connections.
    The path must contain at least one node and does not need to go through the root.

    Example 1:
    Input: [1,2,3]
           1
          / \
         2   3
    Output: 6

    Example 2:
    Input: [-10,9,20,null,null,15,7]
       -10
       / \
      9  20
        /  \
       15   7
    Output: 42
 */
/*  Recursion (DFS): Time = O(n) Space = O(h)
    每一步都有两种选择：
    1. 继续当前路径
    2. 以当前节点作为最高节点计算新的路径
 */
public class BinaryTreeMaximumPathSum {

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(dfs(root.left), 0);
        int rightGain = Math.max(dfs(root.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = root.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return root.val + Math.max(leftGain, rightGain);
    }
}
