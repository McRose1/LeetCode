package Tree;

/*  530. Minimum Absolute Difference in BST
    Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

    Example:
    Input:

       1
        \
         3
        /
       2

    Output:
    1

    Explanation:
    The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).

    Note:
        o There are at least two nodes in this BST.
        o This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */

/*  Inorder: Time = O(logn) Space = O(logn)
    BST property: Vals are sorted if traverse the tree in-order
    Compare the adjacent elements to get the min_diff
 */
public class MinimumAbsoluteDifferenceinBST {

    int min = Integer.MAX_VALUE;
    int prev = -1;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != -1) {
            min = Math.min(min, Math.abs(prev - root.val));
        }
        prev = root.val;
        inorder(root.right);
    }
}
