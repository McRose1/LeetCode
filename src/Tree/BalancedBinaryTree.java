package Tree;

/*  110. Balanced Binary Tree
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
/*  Recursion: Time = O(n) Space = O(logn)
    判断整树是否为 BBT，需要满足两个条件：
    1. 左右子树为 BBT
    2. 左右子树的高度差不大于 1
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        // 如果整数有高度返回，说明已经满足 helper function 里限定的两个条件
        return height(root) != -1;
    }
    public int height(TreeNode root) {
        // 递归出口
        if (root == null) return 0;
        int lHeight = height(root.left);
        int rHeight = height(root.right);
        // 左右子树不为 BBT 或者左右子树的高度差大于 1，直接返回 -1
        if (Math.abs(lHeight - rHeight) > 1 || lHeight == -1 || rHeight == -1) {
            return -1;
        }
        // 每层更新树高
        return Math.max(lHeight, rHeight) + 1;
    }
}
