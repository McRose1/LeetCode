package Tree;

/*  112. Path Sum
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

//  Recursion: Time = O(n) space = O(n)
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        // 说明此时的 node 已经是 leaf，可以进行结算
        if (root.left == null && root.right == null ) {
            return root.val == sum;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}