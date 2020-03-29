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
//  DFS: Time = O(n) Space = O(h)
public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        // 单单传 int 原先的值并没有发生改变
        int[] max = new int[] {Integer.MIN_VALUE};
        dfs(root, max);
        return max[0];
    }
    // 以 root 为顶点的所有直上直下 path sum 最大的一条的值
    private int dfs(TreeNode root, int[] max) {
        int left = root.left != null ? Math.max(0, dfs(root.left, max)) : 0;
        int right = root.right != null ? Math.max(0, dfs(root.right, max)) : 0;

        int cur = root.val + left + right;
        max[0] = Math.max(max[0], cur);
        return root.val + Math.max(left, right);
    }
}
