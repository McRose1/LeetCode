package Tree;

/*  285. Inorder Successor in BST
    Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
    The successor of a node p is the node with the smallest key greater then p.val.

    Example 1:
    Input: root = [2,1,3], p = 1
    Output: 2
    Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

    Example 2:
    Input: root = [5,3,6,2,4,null,null,1], p = 6
    Output: null
    Explanation: There is no in-order successor of the current node, so the answer is null.

    Note:
    If the given node has no in-order successor in the tree, return null.
    It's guaranteed that the values of the tree are unique.
 */

/*  Recursion
    首先要确定中序遍历的后继:
    如果该节点有右子节点, 那么后继是其右子节点的子树中最左端的节点
    如果该节点没有右子节点, 那么后继是离它最近的祖先, 该节点在这个祖先的左子树内.
 */

public class InorderSuccessorinBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        // 如果根节点小于或等于要查找的节点, 直接进入右子树递归
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        // 如果根节点大于要查找的节点, 则暂存左子树递归查找的结果, 如果是 null, 则直接返回当前根节点; 反之返回左子树递归查找的结果.
        else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }
}

/*  Iteration

        TreeNode successor = null;
        while (root != null && root.val != p.val) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        if (root == null) {
            return null;
        }

        if (root.right == null) {
            return successor;
        }

        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
 */