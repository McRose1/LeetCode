/*
    Given two binary trees, write a function to check if they are same or not.
    Tow binary trees are considered the same if they are
    structurally identical and the nodes have the same value.

    Example 1:
    Input: 1            1
          / \          / \
         2   3        2   3
        [1, 2, 3]    [1, 2, 3]
    Output: true

    Example 2:
    Input:  1          1
           /            \
          2              2
        [1, 2]        [1, null ,2]
    Output: false

    Example 3:
    Input: 1            1
          / \          / \
         2   1        1   2
        [1, 2, 1]  [1, 1, 2]
    Output: false
 */

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both are empty: same
        if (p == null && q == null) return true;
        // One is empty: not the same
        if (p == null || q == null) return false;
        // Both are not empty, compare the root value
        if (p.val != q. val) return false;
        // Compare left-subtree and right-subtree recursively
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

/*
    recursion:

    Time complexity: O(N), where N is a number of nodes in the tree,
    since one visits each node exactly once

    Space complexity: O(logN) in the best case of completely balanced tree and
    O(N) in the worst case of completely unbalanced tree, to keep a recursion stack
 */