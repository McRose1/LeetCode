/*
    Find the sum of all left leaves in a given binary tree.

    Example:
            3
           / \
          9  20
            /  \
           15   7
    There aer two left leaves in the binary tree, with values 9 and 15 respectively,
    Return 24.
 */

public class SumofLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        if (root.left != null && root.left.left == null & root.left.right == null) {
            sum = root.left.val;
        }
        return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}
