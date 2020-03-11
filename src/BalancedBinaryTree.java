/*
    Given a binary tree, determine if it is height-balanced.
    For this problem, a height-balanced binary tree is defined as:
        a binary tree in which the left and right subtrees of every node differ in height by no more than 1

    Example 1:
    Given the following tree [3, 9, 20, null, null, 15, 7]:
            3
           / \
          9  20
             / \
            15  7
    return true.

    Example 2:
    Given the following tree [1, 2, 2, 3, 3, null, null, 4, 4]:
            1
           / \
          2   2
         / \
        3   3
       / \
      4   4
    return false.

 */

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return height(root) != -1;
    }
    public int height(TreeNode root) {
        if (root == null) return 0;
        int lHeight = height(root.left);
        int rHeight = height(root.right);
        if (Math.abs(lHeight - rHeight) > 1 || lHeight == -1 || rHeight == -1) {
            return -1;
        }
        return Math.max(lHeight, rHeight) + 1;
    }
}
