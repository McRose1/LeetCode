package Tree;

/*  235. Lowest Common Ancestor of a Binary Search Tree
    Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
    According to the definition of LCA on Wikipedia:"The lowest common ancestor is defined between two nodes
    p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself.)"

    Given a binary search tree: root = [6,2,8,0,4,7,9,null,null,3,5]
                    6
                   / \
                  2   8
                 / \ / \
                0  4 7  9
                  / \
                 3   5
    Example 1:
    Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
    Output: 6

    Example 2:
    Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
    Output: 2

    Note:
    All of the nodes' values will be unique.
    p and q are different and both values will exist in the BST.
 */

/*  Recursion: Time = O(n) Space = O(n)
    Lowest common ancestor for two nodes p and q would be the last ancestor node common to both of them.
    Here last is defined in terms of the depth of the node.
    每次只要判断 p 和 q 在当前根节点的左边还是右边：
    如果都在左子树，就把 root.left 作为新的 root 继续递归；
    如果都在右子树，就把 root.right 作为新的 root 继续递归；
    如果不在同一边，那么当前 root 就是我们要的 LCA node
 */
public class LowestCommonAncestorofaBinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        if (p.val < root.val && q.val < root.val) {
            // If both p and q are lesser than parent
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            // If both p and q are greater than parent
            return lowestCommonAncestor(root.right, p, q);
        } else {
            // We have found the split point, which is LCA node
            return root;
        }
    }
}

/*  Iteration: Time = O(n) Space = O(1)
    The only difference is instead of recursively calling the function,
    we traverse down the tree iteratively.
    This is possible without using a stack or recursion since we don't need to backtrace to find the LCA node.

        int pVal = p.val;
        int qVal = q.val;
        while (root != null) {
            int parentVal = root.val;

            if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                root = root.left;
            } else if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                root = root.right;
            } else {
                // We have found the split point, which is LCA node
                return root;
            }
        }
        return null;
 */