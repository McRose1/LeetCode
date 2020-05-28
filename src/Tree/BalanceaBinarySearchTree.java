package Tree;

/*  1382. Balance a Binary Search Tree
    Given a binary search tree, return a balanced binary search tree with the same node values.
    A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.
    If there is more than one answer, return any of them.

    Example 1:
    Input: root = [1,null,2,null,3,null,4,null,null]
    Output: [2,1,3,null,null,null,4]
    Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.

    Constraints:
    The number of nodes in the tree is between 1 and 10^4.
    The tree nodes will have distinct values between 1 and 10^5.

    Hint:
    Convert the tree to a sorted array using an in-order traversal.
    Construct a new balanced tree from the sorted array recursively.
 */

import java.util.ArrayList;
import java.util.List;

/*  Inorder + LC108: Time = O(n) Space = O(n)
    1. BST + Inorder => vals are sorted
    2. Build a balanced BST from a sorted array (LC 108)
 */
public class BalanceaBinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return null;
        List<Integer> list = BSTtoList(root);

        return constructBST(list, 0, list.size() - 1);
    }

    private List<Integer> BSTtoList(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(list, root);
        return list;
    }

    private void helper(List<Integer> list, TreeNode root) {
        if (root == null) return;
        helper(list, root.left);
        list.add(root.val);
        helper(list, root.right);
    }

    private TreeNode constructBST(List<Integer> list, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode cur = new TreeNode(list.get(mid));
        cur.left = constructBST(list, left, mid - 1);
        cur.right = constructBST(list, mid + 1, right);
        return cur;
    }
}
