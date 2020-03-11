/*
    Given a binary tree and a sum, determine if the tree has a root-to-leaf path
    such that adding up all values along the path equals the given sum
    Note: A leaf is a node with no children

    Example:
    Given the below binary tree and sum = 22
            5
           / \
          4   8
         /   / \
        11  13  4
       /  \      \
      7    2      1
    return true, as there exits a root-to-leaf path 5->4->11->2 which sum is 22
 */

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null & root.right == null ) {
            return root.val == sum;
        }
        int new_sum = sum - root.val;
        return hasPathSum(root.left, new_sum) || hasPathSum(root.right, new_sum);
    }
}
